/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

dojo.provide("vt.dataGrid.vtExtension");
dojo.declare("vt.dataGrid.vtExtension",null,{
    extend: function(grid){
        grid.scroller.scroll = this.scroller_scroll;
        grid._vtRowsPerPageChanged = this._vtRowsPerPageChanged;
        grid._abortResize = this._abortResize;

        // datbt debug
        // [modified down_arrow, up_arrow on grid
        grid.onKeyDown = this.onKeyDown;
        grid.selection.clickSelect = this.selection_clickSelect;
        // ]modified down_arrow, up_arrow on grid

        // [functions for post grid data
        grid._vtGetDataForPost = this._vtGetDataForPost;
        grid.vtGetTotalDataForPost = this.vtGetTotalDataForPost;
        grid.vtGetModifiedDataForPost = this.vtGetModifiedDataForPost;
        grid.vtGetSelectedDataForPost = this.vtGetSelectedDataForPost;
        grid.vtGetCheckedDataForPost = this.vtGetCheckedDataForPost;
        // ]functions for post grid data

        // [add function callback for click select on grid row
        grid.vtClickSelectCallBack = this.vtClickSelectCallBack;
        // ]add event callback for click select on grid row

        // [function get grid data
        grid.vtGetCheckedItems = this.vtGetCheckedItems;
        // ]function get grid data

        // [add function call for reload grid data
        grid.vtReload = this.vtReload;
        grid.forceLoadCallback = this.forceLoadCallback;
        // ]add function call for reload grid data

        // [modified for update total row column - test
        grid.updateRowCount = this.updateRowCount;
        grid._onFetchComplete = this._onFetchComplete;
        grid._vtSelectorChanged = this._vtSelectorChanged;
        // ]modified for update total row column - test
        grid.vtCreatPageSelector = this.vtCreatPageSelector;
        grid.vtCreateRowsPerPageSelector = this.vtCreateRowsPerPageSelector;

    },
    updateRowCount: function(inRowCount){
        //summary:
        //	Change the number of rows.
        // inRowCount: int
        //	Number of rows in the grid.
        console.debug("updateRowCount");
        if(this.updating){
            this.invalidated.rowCount = inRowCount;
        }else{
            console.debug(this.rowCount + "" + inRowCount );
            this.rowCount = inRowCount;
            // [Datbt
            if (dojo.byId(this.id + "_rowCount")){
                dojo.byId(this.id + "_rowCount").innerHTML = inRowCount;
                if (inRowCount != 0){
                    this.vtCreatPageSelector();
                }
            }
            // ]Datbt
            this._setAutoHeightAttr(this.autoHeight, true);
            if(this.layout.cells.length){
                this.scroller.updateRowCount(inRowCount);
            }
            this._resize();
            if(this.layout.cells.length){
                this.setScrollTop(this.scrollTop);
            }
        }

    },
    vtCreateRowsPerPageSelector: function(){
        try{
            // check de hien thi rowsPerPage selector
            if(this.rowsPerPage < this.rowCount){
                dojo.byId(this.id + "_vtRowsPerPageTotalContainer").style.display = "inline";
                if (this.vtRowsPerPageSelector == null){
                    dojo.require("dijit.form.NumberSpinner");
                    this.vtRowsPerPageSelector = new dijit.form.NumberSpinner({
                        smallDelta: 1,
                        largeDelta: 10,
                        value: this.rowsPerPage,
                        constraints:{
                            min:5,
                            max:this.rowCount,
                            places:0
                        },
                        style:"font-size:120%;font-family:Courier;width:100px;"
                    },dojo.byId(this.id + "_vtRowsPerPageContainer"));
                    this.connect(this.vtRowsPerPageSelector, "onChange", "_vtRowsPerPageChanged");
                    this.connect(dojo.byId(this.id + "_vtRowsPerPageContainer"), "onkeypress", "_vtRowsPerPageChanged");
                }else{
                    this.vtRowsPerPageSelector.constraints = {
                        min:5,
                        max:this.rowCount,
                        places:0
                    };
                }
                var grid = this;
                this.vtRowsPerPageSelector._arrowPressed = function(/*Node*/ nodePressed, /*Number*/ direction, /*Number*/ increment){
                    // summary:
                    //		Handler for arrow button or arrow key being pressed
                    if(this.disabled || this.readOnly){
                        return;
                    }
                    this._arrowState(nodePressed, true);
                    this._setValueAttr(this.adjust(this.attr('value'), direction*increment), false);
                    dijit.selectInputText(this.textbox, this.textbox.value.length);
                    var resizeFunction = "_vtRowsPerPageChanged" ;
                    grid._abortResize();
                    this.resizeTimer = setTimeout(dojo.hitch(grid, resizeFunction),1000);
                }
            }else{
                dojo.byId(this.id + "_vtRowsPerPageTotalContainer").style.display = "none";
            }
        }catch(e){
            console.debug("create rowsPerPage selector: " + e.message);
        }
    },
    vtCreatPageSelector:function(){
        var pc = Math.ceil(this.rowCount/this.rowsPerPage);
        var data = {
            identifier: "page",
            label: "desc",
            items: []
        };
        var i;
        for(i = 0; i < pc; i++){
            data.items.push({
                page: i,
                desc: "Page " + (i + 1) + " of " + pc
            });
        }
        var ifsStore = new dojo.data.ItemFileReadStore({
            data: data
        });
        if (this.vtSelector == null){
            dojo.require("dijit.form.FilteringSelect");
            this.vtSelector = new dijit.form.FilteringSelect({
                pageSize: 10,
                store: ifsStore,
                value: this.scroller.page,
                searchAttr: "desc"
            //                    id: this.id + "_vtSelector"
            },dojo.byId(this.id + "_vtSelectorContainer"));
            this.connect(this.vtSelector, "onChange", "_vtSelectorChanged");
        }
        else{
            this.vtSelector.store = ifsStore;
            this.vtSelector.attr("value", this.scroller.page);
        }
    },
    _onFetchComplete: function(items, req){
        if(!this.scroller){
            return;
        }
        if(items && items.length > 0){
            //console.log(items);
            dojo.forEach(items, function(item, idx){
                this._addItem(item, req.start+idx, true);
            }, this);
            if(this._autoHeight){
                this._skipRowRenormalize = true;
            }
            this.updateRows(req.start, items.length);
            if(this._autoHeight){
                this._skipRowRenormalize = false;
            }
            if(req.isRender){
                this.setScrollTop(0);
                this.postrender();
            }else if(this._lastScrollTop){
                this.setScrollTop(this._lastScrollTop);
            }
            // datbt
            try{
                if (req.start == 0){
                    if (dojo.byId(this.id + "_rowCount")){
                        dojo.byId(this.id + "_rowCount").innerHTML = this.rowCount;
                    };
                    this.vtCreatPageSelector();
                    this.vtCreateRowsPerPageSelector();

                // datbt
                // number of page

                // datbt
                }
            }catch(e){
                console.debug("create rows total, page selector, rowsPerPage selector: " + e.message);
            }
        //datbt
        }
        delete this._lastScrollTop;
        if(!this._isLoaded){
            this._isLoading = false;
            this._isLoaded = true;
        }
        this._pending_requests[req.start] = false;

    },
    _abortResize: function(){
        // stop in-progress query
        if(this.vtRowsPerPageSelector.resizeTimer){
            clearTimeout(this.vtRowsPerPageSelector.resizeTimer);
            this.vtRowsPerPageSelector.resizeTimer = null;
        }
    },
    selection_clickSelect: function(inIndex, inCtrlKey, inShiftKey){
        if(this.mode == 'none'){
            return;
        }
        this._beginUpdate();
        if(this.mode != 'extended'){
            this.select(inIndex);
        }else{
            var lastSelected = this.selectedIndex;
            if(!inCtrlKey){
                this.deselectAll(inIndex);
            }
            if(inShiftKey){
                this.selectRange(lastSelected, inIndex);
            }else if(inCtrlKey){
                this.toggleSelect(inIndex);
            }else{
                this.addToSelection(inIndex)
            }
        }
        this._endUpdate();

        this.grid.vtClickSelectCallBack(this.grid.getItem(inIndex));
    },

    vtClickSelectCallBack : function(item){

    },
    onKeyDown: function(e){
        // summary:
        // 		Grid key event handler. By default enter begins editing and applies edits, escape cancels an edit,
        // 		tab, shift-tab, and arrow keys move grid cell focus.
        if(e.altKey || e.metaKey){
            return;
        }
        var dk = dojo.keys;
        var colIdx;
        switch(e.keyCode){
            case dk.ESCAPE:
                this.edit.cancel();
                break;
            case dk.ENTER:
                if(!this.edit.isEditing()){
                    colIdx = this.focus.getHeaderIndex();
                    if(colIdx >= 0) {
                        this.setSortIndex(colIdx);
                        break;
                    }else {
                        this.selection.clickSelect(this.focus.rowIndex, dojo.isCopyKey(e), e.shiftKey);
                    }
                    dojo.stopEvent(e);
                }
                if(!e.shiftKey){
                    var isEditing = this.edit.isEditing();
                    this.edit.apply();
                    if(!isEditing){
                        this.edit.setEditCell(this.focus.cell, this.focus.rowIndex);
                    }
                }
                if (!this.edit.isEditing()){
                    var curView = this.focus.focusView || this.views.views[0];  //if no focusView than only one view
                    curView.content.decorateEvent(e);
                    this.onRowClick(e);
                }
                break;
            case dk.SPACE:
                if(!this.edit.isEditing()){
                    colIdx = this.focus.getHeaderIndex();
                    if(colIdx >= 0) {
                        this.setSortIndex(colIdx);
                        break;
                    }else {
                        this.selection.clickSelect(this.focus.rowIndex, dojo.isCopyKey(e), e.shiftKey);
                    }
                    dojo.stopEvent(e);
                }
                break;
            case dk.TAB:
                this.focus[e.shiftKey ? 'previousKey' : 'nextKey'](e);
                break;
            case dk.LEFT_ARROW:
            case dk.RIGHT_ARROW:
                if(!this.edit.isEditing()){
                    var keyCode = e.keyCode;  // IE seems to lose after stopEvent when modifier keys
                    dojo.stopEvent(e);
                    colIdx = this.focus.getHeaderIndex();
                    if (colIdx >= 0 && (e.shiftKey && e.ctrlKey)){
                        this.focus.colSizeAdjust(e, colIdx, (keyCode == dk.LEFT_ARROW ? -1 : 1)*5);
                    }
                    else{
                        var offset = (keyCode == dk.LEFT_ARROW) ? 1 : -1;
                        if(dojo._isBodyLtr()){
                            offset *= -1;
                        }
                        this.focus.move(0, offset);
                    }
                }
                break;
            case dk.UP_ARROW:
                if(!this.edit.isEditing() && this.focus.rowIndex !== 0){
                    dojo.stopEvent(e);
                    this.focus.move(-1, 0);
                }
                //[Datbt
                this.selection.clickSelect(this.focus.rowIndex, dojo.isCopyKey(e), e.shiftKey);
                //]Datbt
                break;
            case dk.DOWN_ARROW:
                if(!this.edit.isEditing() && this.focus.rowIndex+1 != this.rowCount){
                    dojo.stopEvent(e);
                    this.focus.move(1, 0);
                }
                //[Datbt
                this.selection.clickSelect(this.focus.rowIndex, dojo.isCopyKey(e), e.shiftKey);
                //]Datbt
                break;
            case dk.PAGE_UP:
                if(!this.edit.isEditing() && this.focus.rowIndex !== 0){
                    dojo.stopEvent(e);
                    if(this.focus.rowIndex != this.scroller.firstVisibleRow+1){
                        this.focus.move(this.scroller.firstVisibleRow-this.focus.rowIndex, 0);
                    }else{
                        this.setScrollTop(this.scroller.findScrollTop(this.focus.rowIndex-1));
                        this.focus.move(this.scroller.firstVisibleRow-this.scroller.lastVisibleRow+1, 0);
                    }
                }
                break;
            case dk.PAGE_DOWN:
                if(!this.edit.isEditing() && this.focus.rowIndex+1 != this.rowCount){
                    dojo.stopEvent(e);
                    if(this.focus.rowIndex != this.scroller.lastVisibleRow-1){
                        this.focus.move(this.scroller.lastVisibleRow-this.focus.rowIndex-1, 0);
                    }else{
                        this.setScrollTop(this.scroller.findScrollTop(this.focus.rowIndex+1));
                        this.focus.move(this.scroller.lastVisibleRow-this.scroller.firstVisibleRow-1, 0);
                    }
                }
                break;
            default:
                break;
        }
    },
    vtReload : function(url,formId,param,callback){
        this.store._jsonFileUrl = url;
        this._clearData();
        this._pages = [];
        this._store_connects = [];
        this._by_idty = {};
        this._by_idx = [];
        this._cache = [];
        this._pending_requests = {};
        this.store._arrayOfAllItems = [];
        this.store._arrayOfTopLevelItems = [];
        this.store._loadFinished = false;
        this.store._itemsByIdentity = null;
        this.store._loadInProgress = false;
        this.store._queuedFetches = [];
        this.store._pending = {
            _newItems:{},
            _modifiedItems:{},
            _deletedItems:{}
        };
        var _this = this;
        this.store._forceLoadByPost(_this, formId, param, callback);
    },
    forceLoadCallback:function(data,bSuccess){

    },
    _vtGetDataForPost: function(outContent,form,item,arrProps){
        if (arrProps){
            for (var i = 0;i < arrProps.length; i++ ){
                var prop = arrProps[i];
                if (item[prop]){
                    var propForPost = form +  "." + prop;
                    if (!dojo.isObject(item[prop][0])||item[prop][0] == null){
                        if (!outContent[ propForPost]){
                            outContent[ propForPost] = [];
                        }
                        outContent[ propForPost].push(item[prop][0]);
                    }
                    else{
                        var compositItem = item[prop][0];
                        this._vtGetDataForPost(outContent,propForPost,compositItem,arrProps);
                    }
                }
            }
        }
        else{
            for (var prop in item){
                if ((prop != "_0") && (prop != "_R") && (prop!="_RS")
                    && (prop!="_RI") && (prop!="_S") && (prop!="_RRM")){
                    var propForPost = form +  "." + prop;
                    if (!dojo.isObject(item[prop][0])||item[prop][0] == null){
                        if (!outContent[ propForPost]){
                            outContent[ propForPost] = [];
                        }
                        outContent[ propForPost].push(item[prop][0]);
                    }
                    else{
                        var compositItem = item[prop][0];
                        this._vtGetDataForPost(outContent,propForPost,compositItem,arrProps);
                    }
                }
            }
        }

    },


    vtGetTotalDataForPost: function(form,arrProps){
        var outContent = {};
        for (var idx in this.store._arrayOfTopLevelItems){
            var item = this.store._arrayOfTopLevelItems[idx];
            this._vtGetDataForPost(outContent,form,item,arrProps);
        }
        return outContent;
    },

    vtGetSelectedDataForPost: function(form,arrProps){

        var outContent = {};
        for (var i = 0; i < this.selection.getSelected().length ; i ++){
            var item = this.selection.getSelected()[i];
            this._vtGetDataForPost(outContent,form,item,arrProps);
        }
        return outContent;
    },

    vtGetModifiedDataForPost: function(form,arrProps){
        var outContent = {};
        for( var id in this.store._pending._modifiedItems ) {
            var item = this.store._getItemByIdentity( id );
            this._vtGetDataForPost(outContent,form,item,arrProps);
        }
        return outContent;
    },

    vtGetCheckedDataForPost: function(form,checkField,arrProps){
        var outContent = {};
        for (var idx in this.store._arrayOfTopLevelItems){
            var item = this.store._arrayOfTopLevelItems[idx];
            if (item[checkField][0]=="true" || item[checkField][0]){
                this._vtGetDataForPost(outContent,form,item,arrProps);
            }
        }
        return outContent;
    },
    vtGetCheckedItems: function (checkField){
        var outContent = [];
        for (var idx in this.store._arrayOfTopLevelItems){
            var item = this.store._arrayOfTopLevelItems[idx];
            if (item[checkField][0]=="true" || item[checkField][0]){
                outContent.push(item);
            }
        }
        return outContent;

    }

    //datbt debug
    ,
    scroller_scroll: function(inTop){
        try{
            this.grid.scrollTop = inTop;
            if(this.colCount){
                this.startPacify();
                this.findPage(inTop);
                //[ datbt
                if (this.grid.vtSelector){
                    if(this.getScrollBottom(inTop) == this.height){
                        this.grid.vtSelector.attr("value", this.page + 1);
                    }else{
                        this.grid.vtSelector.attr("value", this.page);
                    }
                }
                //] datbt
                var h = this.height;
                var b = this.getScrollBottom(inTop);

                for(var p=this.page, y=this.pageTop; (p<this.pageCount)&&((b<0)||(y<b)); p++){
                    y += this.needPage(p, y);
                }
                this.firstVisibleRow = this.getFirstVisibleRow(this.page, this.pageTop, inTop);
                this.lastVisibleRow = this.getLastVisibleRow(p - 1, y, b);
                // indicates some page size has been updated
                if(h != this.height){
                    this.repositionPages(p-1);
                }
                this.endPacify();
            }
        }catch(e){
            console.debug("scroller_scroll: " + e.message);
        }
    },
    _vtRowsPerPageChanged: function(e){
        try{
            var rpp = parseInt(this.vtRowsPerPageSelector.attr("value"));
            if (rpp != this.rowsPerPage){
                var dk = dojo.keys;
                if ((typeof e !="number")){
                    if (e.keyCode == dk.ENTER){
                        this.rowsPerPage = rpp;
                        this._refresh(true);
                        this.autoHeight = rpp;
                        this.resize()  ;
                    }
                }
                else{
                    this.rowsPerPage = rpp;
                    this._refresh(true);
                    this.autoHeight = rpp;
                    this.resize()  ;
                }
            }
        }catch(e){
            console.debug("_vtRowsPerPageChanged: " + e.message);
        }
    },
    _vtSelectorChanged: function(){
        try{
            var pg = parseInt(this.vtSelector.attr("value"));
            if (pg != this.scroller.page){
                this.scrollToRow(pg * this.rowsPerPage);
            }
            else{
                if(this.scroller.getScrollBottom(this.scrollTop) == this.scroller.height){
                    this.scrollToRow(pg * this.rowsPerPage);
                }
            }
        }catch(e){
            console.debug("_vtRowsPerPageChanged: " + e.message);
        }
    }
// datbt debug

});
