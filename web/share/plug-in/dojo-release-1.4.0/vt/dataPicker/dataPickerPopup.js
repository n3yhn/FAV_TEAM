/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


dojo.provide("vt.dataPicker.dataPickerPopup");
dojo.require("dijit.form.ValidationTextBox");
dojo.require("vt.dataGrid.vtExtension");
dojo.require("dojox.grid.DataGrid");
dojo.require("vt.dataGrid.dataStore");
dojo.require("dijit.form.TextBox");
dojo.require("dijit.form.Button");

dojo.declare("vt.dataPicker.dataPickerPopup",[dijit._Widget, dijit._Templated],{
    templatePath: dojo.moduleUrl("vt", "templates/dataPickerPopup.html"),
    dataGrid: null,
    searchTxts:[],
    query: {},
    idPrifix: "",
    dataPicker:{},

    postCreate: function(){
        try{
            this.inherited(arguments);
            this.idPrifix = this.dataPicker.id + "_searchParam_";
            var dataStore = new vt.dataGrid.dataStore({
                url: this.dataPicker.gridUrl,
                preventCache:true
            });
            // set the layout structure:
            // neu co cot no scroll
            var structure = [];
            if (this.dataPicker.gridStructure[0].cells.length !=0){
                structure = this.dataPicker.gridStructure;
            }else{
                structure = this.dataPicker.gridStructure[1];
            }
            this.dataGrid = new dojox.grid.DataGrid({
                store: dataStore,
                id: this.id + 'dataGrid',
                clientSort: true,
                structure: structure,
                style:"height:100%;width:100%;"
            },
            document.createElement('div'));
            // add dataPicker
            this.dataGrid.dataPicker = this.dataPicker;

            //
            // extend grid
            var gridExtension = new vt.dataGrid.vtExtension();
            this.dataGrid.onKeyDown = this._onGridKeyDown;
            this.dataGrid.selection.clickSelect = gridExtension.selection_clickSelect;
            this.dataGrid.vtReload = gridExtension.vtReload;
            this.dataGrid.vtClickSelectCallBack = gridExtension.vtClickSelectCallBack;
            this.dataGrid.onRowDblClick = function(){
                this.dataPicker._close();
            }
            // extend grid
    
            var tdGrid = dojo.query(".dataPickerGrid", this.domNode)[0];
            tdGrid.appendChild(this.dataGrid.domNode);
    
            // [ Datbt tam comment de debug
            var tableSearch = dojo.query(".searchParams", this.domNode)[0];
            var tbody = document.createElement('tbody');
            var i = 0;
            var numRow = Math.floor(this.dataPicker.searchParams.length/this.dataPicker.searchNumCol);
            for ( i=0; i < numRow;i++){
                var tr = document.createElement('tr');
                for (var k =0; k<this.dataPicker.searchNumCol; k ++){
                    var lbltd = document.createElement('td');
                    lbltd.innerHTML = "<label>" + this.dataPicker.searchParams[i*this.dataPicker.searchNumCol + k][0] + "</label>";
                    tr.appendChild(lbltd);
                    var txttd = document.createElement('td');
                    tr.appendChild(txttd);
                    var txt = new dijit.form.TextBox({
                        id: this.idPrifix + this.dataPicker.searchParams[i*this.dataPicker.searchNumCol + k][1]
                    });
                    //this.connect(txt.domNode, 'onkeyup', this._onKeyPressSearch);
                    // test
                    this.connect(txt.domNode, 'onkeypress', this._onKeyPress);
                    // test
                    txttd.appendChild(txt.domNode);
                }
                tbody.appendChild(tr);
            }
            tr = document.createElement('tr');
            var bCreateTd = false;
            for (i= this.dataPicker.searchNumCol * numRow ;i<this.dataPicker.searchParams.length;i++){
                lbltd = document.createElement('td');
                bCreateTd = true;
                lbltd.innerHTML = "<label>" + this.dataPicker.searchParams[i][0] + "</label>";
                tr.appendChild(lbltd);
                txttd = document.createElement('td');
                tr.appendChild(txttd);
                txt = new dijit.form.TextBox({
                    id: this.idPrifix + this.dataPicker.searchParams[i][1]
                });
                //this.connect(txt.domNode, 'onkeyup', this._onKeyPressSearch);
                // test
                this.connect(txt.domNode, 'onkeypress', this._onKeyPress);
                // test
                txttd.appendChild(txt.domNode);
            }
            if (bCreateTd){
                tbody.appendChild(tr);
            }
            if (this.dataPicker.bServerSearch){
                tr = document.createElement('tr');
                var searchTd = document.createElement('td');
                tr.appendChild(searchTd);
                var btn = new dijit.form.Button({
                    id: this.idPrifix + "BtnSearch",
                    label: this.dataPicker.btnSearchLabel
                });
                this.connect(btn.domNode, 'onclick', this._onBtnSearch);
                searchTd.appendChild(btn.domNode);
                tbody.appendChild(tr);
            }
            tableSearch.appendChild(tbody);
        }catch(e){
            console.debug("create dataPickerPopup: " + e.message);
        }
    },

    _onBtnSearch: function (/*Event*/ e){
        var url = this.dataPicker.searchUrl;
        if (url.indexOf("?") != -1){
            url = url + "&";
        }
        else{
            url = url + "?";
        }
        for (var i = 0; i < this.dataPicker.searchParams.length; i ++ ){
            var value = dijit.byId(this.idPrifix + this.dataPicker.searchParams[i][1]).getValue();
            var param = this.dataPicker.searchParams[i][1];
            url = url + param + "=" + value + "&";
        }
        this.dataGrid.vtReload(url);
    },

    _startSearchFromInput: function(){
        var txt = this.curSearchNode.childNodes[1];
        var s = txt.value;
        if (s == null || s == undefined) { // DungDV fix for search on IE
            txt = this.curSearchNode.childNodes[0];
            s = txt.value;
        }
        
        var field = txt.id.replace(this.idPrifix, "");
        this.query[field] = "*" + s + "*";
        this.dataGrid.setQuery(this.query,{
            ignoreCase:true
        });
        this.dataGrid._refresh(true);
        this.curSearchNode.focus();
    },
    _abortQuery: function(){
        // stop in-progress query
        if(this.searchTimer){
            clearTimeout(this.searchTimer);
            this.searchTimer = null;
        }
    },
    _onKeyPress: function(/*Event*/ evt){
        
        // summary:
        //		Handles keyboard events
        var key = evt.charOrCode;
        
        
        // except for cutting/pasting case - ctrl + x/v
        if(evt.altKey || ((evt.ctrlKey || evt.metaKey) && (key != 'x' && key != 'v'))){
            return; // throw out weird key combinations and spurious events
        }
        
        //Linhntn: For tab to next node[[
        if (key == dojo.keys.TAB){
            var currentId = evt.currentTarget.childNodes[1].id;
            var index1 = currentId.indexOf("_") + 1;
            var index2 = currentId.indexOf("_",index1)+1;
            var currentParam = currentId.substring(index2);
            var ii = 0;
            for (var i = 0; i<this.dataPicker.searchParams.length;i++)
               if (this.dataPicker.searchParams[i][1] === currentParam)
                {
                    ii = i;
                }
            if (evt.shiftKey){
                if (ii !== 0){
                    ii--;
                    var preTabNode = this.idPrifix + this.dataPicker.searchParams[ii][1];
                    document.getElementById(preTabNode).focus();
                }
            }    
            else{
                if (ii !== (this.dataPicker.searchParams.length - 1)){
                    ii++;
                    var nextTabNode = this.idPrifix + this.dataPicker.searchParams[ii][1];
                    document.getElementById(nextTabNode).focus();
                }
                else{
                    this.dataGrid.domNode.focus();
                }
            }
        }
        //]]
        if (key == dojo.keys.ESCAPE){
                // close popup
                this.dataPicker.focusNode.focus();
                this.dataPicker._close();
        }        
        this.curSearchNode = evt.currentTarget;
        var searchFunction = "_startSearchFromInput" ;
        this._abortQuery();
        
        // need to wait a tad before start search so that the event
        this.searchTimer = setTimeout(dojo.hitch(this, searchFunction),this.dataPicker.searchDelay);
        return true;
    },
    reset:function(){
        this.dataGrid = null;
        this.query = {};
        this.searchTxts = [];
    },

    // overide grid on key down
    // modify enter event
    _onGridKeyDown: function(e){
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
                // close popup
                this.dataPicker.focusNode.focus();
                this.dataPicker._close();
                break;
            case dk.ENTER:
                // comment orginal code
                //                if(!this.edit.isEditing()){
                //                    colIdx = this.focus.getHeaderIndex();
                //                    if(colIdx >= 0) {
                //                        this.setSortIndex(colIdx);
                //                        break;
                //                    }else {
                //                        this.selection.clickSelect(this.focus.rowIndex, dojo.isCopyKey(e), e.shiftKey);
                //                    }
                //                    dojo.stopEvent(e);
                //                }
                //                if(!e.shiftKey){
                //                    var isEditing = this.edit.isEditing();
                //                    this.edit.apply();
                //                    if(!isEditing){
                //                        this.edit.setEditCell(this.focus.cell, this.focus.rowIndex);
                //                    }
                //                }
                //                if (!this.edit.isEditing()){
                //                    var curView = this.focus.focusView || this.views.views[0];  //if no focusView than only one view
                //                    curView.content.decorateEvent(e);
                //                    this.onRowClick(e);
                //                }
                // add dataPicker event
                this.dataPicker.focusNode.focus();
                this.dataPicker._close();
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
    }
});
