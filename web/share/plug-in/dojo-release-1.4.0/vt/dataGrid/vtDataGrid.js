/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author: DungDV
 * Description: Sua cac ham onCellMouseOver, extendGrid cho loi tooltip
 * Date: 08/07/2011
 * FWVersion: 3.3
 **/
/**
 * Author: LinhNTN
 * Description: Comment lai ham setFocusCell de sua loi alwaysEditing
 * Date: 06/09/2011
 * FWVersion: 3.3
 **/

/**
 * Author: LinhNTN
 * Description:
 * 1. Sua co che phan trang tung phan
 *  - Them ham vtFetch, needPage, _fetch.
 *    Trong ham _fetch them tham so startVal la so thu tu dau trang can request. Bien start luon bang 0
 *  - Sua ham vtPageSelectorChange de goi request lay noi dung 1 trang
 *  - Date: 06/09/2011
 * 2.Do cao cua row lam sai lech so ban ghi ngoai man hinh
 *  - Bo ham updateRows trong _onFetchComplete
 *  - Date: 06/06/2011
 * 3.Grid GetDataURL
 * - Thay doi phan dieu kien hien thi rowCount, rowsPerpage trong ham vtCreateRowsPerPageSelector
 * - Date: 13/06/2011
 *
 * FWVersion: 3.3
 **/
dojo.provide("vt.dataGrid.vtDataGrid");
dojo.require("vt.dataGrid.vtView");
dojo.provide("vt.dialog.vtDialog");
dojo.require("dojox.grid._Grid");
dojo.require("dojox.grid.DataGrid");
dojo.require("dojo.data.ItemFileWriteStore");
dojo.require("vt.dataGrid.dataStore");
dojo.require("vt.dataGrid.iQueryWriteStore");
dojo.require("vt.tooltip.vtTooltip"); // dungdv add 18/06/2011
dojo.declare("vt.dataGrid.vtDataGrid",[dijit._Widget, dijit._Templated],{
    templatePath: dojo.moduleUrl("vt", "templates/dataGrid.html"),
    grid:null,
    getDataUrl:"",
    structure:null,
    gridAttr: null,
    container:"",
    vtRowsPerPageSelector:null,
    vtPageSelector:null,
    sortableArray:null, // DungDV: for option sortable column
    postCreate: function(){
        try{
            this.gridAttr.serverPaging = true; //Linhntn: for serverPaging
            this.inherited(arguments);
            var store = null;
            if (this.getDataUrl == ""){
                store = new vt.dataGrid.dataStore({
                    data:{
                        identifier: '',
                        label: '',
                        items: []
                    }
                });
            }else{
                if(this.gridAttr.serverPaging !== undefined && this.gridAttr.serverPaging == true){
                    var params = {
                        query: this.gridAttr.query !== undefined ? this.gridAttr.query:{
                            name: "*"
                        },
                        queryOptions: {
                            ignoreCase: true
                        },
                        url:this.getDataUrl
                    //,doClientPaging:true
                    };
                    store = new vt.dataGrid.iQueryWriteStore(params);

                    
                } else{
                    store = new vt.dataGrid.dataStore({
                        url:this.getDataUrl,
                        urlPreventCache:true
                    });
                }

            }
            this.gridAttr.store = store;
            // neu co cot no scroll
            if (this.structure[0].cells.length !=0){
                this.gridAttr.structure = this.structure;
            }else{
                this.gridAttr.structure = this.structure[1];
            }
            this.grid = new dojox.grid.DataGrid(
                this.gridAttr,document.createElement('div')
                );
            //            [duongtb : marks the radio/checkbox status
            this.grid._markup = [];
            this.grid._initialized = [];
            this.grid._checked = null;
            this.grid.unchecked = false;
            this.grid.noFetchData = false;
            this.grid.dataTypeArray = []; // DungDV: check data type: number, text, date
            if(this.grid.serverPaging == true)
                this.grid.store.grid = this.grid;// do something in iQueryWriteStore
            //            ]
            this.extendGrid();
            this.grid.parentWidget = this;
           
        }catch(e){
            logger.error("vt.dataGrid.vtDataGrid.postCreate: " + e.message);
        }
    },
    startup:function(){
        try{
            //            var gridDiv = dojo.query(".gridDiv", this.domNode)[0];
            if (this.container == ""){
                this.gridDiv.appendChild(this.grid.domNode);
            }else{
                dojo.byId(this.container).appendChild(this.grid.domNode);
            }
            this.grid.startup();
            //[ Them custom sort
            this.grid.store.comparatorMap = {};
            var dataUtil = new vt.dataGrid.dataUtil();
            
            // neu co cot no scroll
            if (this.structure[0].cells.length !=0){
                var structure_noScroll = this.gridAttr.structure[0];
                var structure_scroll = this.gridAttr.structure[1];
                for (var i in structure_noScroll.cells){
                    var sortType = structure_noScroll[i]["sortType"];
                    var field = structure_noScroll[i]["field"];
                    switch(sortType){
                        case "vi" :
                            this.grid.store.comparatorMap[field] = dataUtil.viComparator;
                            break;
                        case "std" :
                            this.grid.store.comparatorMap[field] = dataUtil.stdComparator;
                            break;
                        case "number" :
                            this.grid.store.comparatorMap[field] = dataUtil.numberComparator;
                            break;
                        case "date" :
                            this.grid.store.comparatorMap[field] = dataUtil.dateComparator;
                            break;
                        default:
                            break;
                    }

                }
                for (var i in structure_scroll){
                    var sortType = structure_scroll[i]["sortType"];
                    var field = structure_scroll[i]["field"];
                    switch(sortType){
                        case "vi" :
                            this.grid.store.comparatorMap[field] = dataUtil.viComparator;
                            break;
                        case "std" :
                            this.grid.store.comparatorMap[field] = dataUtil.stdComparator;
                            break;
                        case "number" :
                            this.grid.store.comparatorMap[field] = dataUtil.numberComparator;
                            break;
                        case "date" :
                            this.grid.store.comparatorMap[field] = dataUtil.dateComparator;
                            break;
                        default:
                            break;
                    }

                }
            }else{
                for (var i in this.gridAttr.structure){
                    var sortType = this.gridAttr.structure[i]["sortType"];
                    var field = this.gridAttr.structure[i]["field"];
                    switch(sortType){
                        case "vi" :
                            this.grid.store.comparatorMap[field] = dataUtil.viComparator;
                            break;
                        case "std" :
                            this.grid.store.comparatorMap[field] = dataUtil.stdComparator;
                            break;
                        case "number" :
                            this.grid.store.comparatorMap[field] = dataUtil.numberComparator;
                            break;
                        case "date" :
                            this.grid.store.comparatorMap[field] = dataUtil.dateComparator;
                            break;
                        default:
                            break;
                    }

                }
            }

        //] Them custome sort
        }catch(e){
            logger.error("startup: " + e.message);
        }
        
    },
    destroyRecursive:function(){
        try{
            if (this.container == ""){
                if(this.grid){
                    this.grid.destroyRecursive();
                }
            }
            this.inherited(arguments);
        }catch(e){
            logger.error("destroyRecursive: " + e.message);
        }
    },
    _vtRowsPerPageChanged: function(e){
        try{
            if (this.vtRowsPerPageSelector.isValid()){
                var rpp = parseInt(this.vtRowsPerPageSelector.attr("value"));
                if (!isNaN(rpp) &&rpp<=100 ){
                    if (rpp != this.grid.rowsPerPage){
                        this.grid.rowsPerPage = rpp;
                        //this.grid.store.setStoreEmpty();//Linhntn for server paging
                        // this.grid._refresh(true);
                        this.grid.autoHeight = rpp;
                        this.grid.edit.info = new Object();
                        this.grid._fetch(0,true,0);
                    // this.grid.autoHeight = rpp;
                    //  this.grid.resize()  ;
                    }
                }
                if (rpp>100){
                    alert("Không được chọn quá 100 bản ghi trên 1 trang");
                    logger.error("_vtRowsPerPageChanged: Không được chọn quá 100 bản ghi trên 1 trang");
                }

            }
        }catch(e){
            logger.error("_vtRowsPerPageChanged: " + e.message);
        }
    },
    _vtPageSelectorChanged: function(){
        //  alert("Vao selector change");
        
        try{
            // co 2 user case goi den ham nay
            // khi user chon gia tri tren selecc
            // khi user scroll grid
            if (this.vtPageSelector._isvalid){ //Linhntn for check valid value in selector
                var pg = parseInt(this.vtPageSelector.attr("value"));
                
                //console.log(this.vtPageSelector.attr("value"));
                // truong hop khong tron trang
                /*if (pg != this.grid.scroller.page){
                    this.grid.scrollToRow(pg * this.grid.rowsPerPage);
                }
                else{
                    // when the scroller's in the bottom
                    if(this.grid.scroller.getScrollBottom(this.grid.scrollTop) >= this.grid.scroller.height){
                        this.grid.scrollToRow(pg * this.grid.rowsPerPage);
                    }
                }*/
                //this.grid.scroller.pageNodes[0][0]=null;
                this.grid.selectorChange = true;
                this.grid.currentRow = pg * this.grid.rowsPerPage;
                //this.grid.store.setStoreEmpty(); //Linhntn bo di de tranh grid bi co lai
                this.grid.edit.info = new Object();
                //  alert(sd.util.dump(this.grid.store,1));
                this.grid._fetch(0,true,pg * this.grid.rowsPerPage);
            }
        //this.grid.resize();
         
        //         if (this.grid.parentWidget.vtPageSelector.attr("value") !=  this.grid.currentPage )
        //       this.grid.parentWidget.vtPageSelector.attr("value",  this.grid.currentPage );
                        
        //] datbt
            
        }catch(e){
            logger.error("_vtPageSelectorChanged: " + e.message);
        }
    },
    vtGotoPage: function(pageNumber){
        try{
            if ((pageNumber !== undefined)&&(!isNaN(pageNumber))){ //Linhntn for check valid value in selector
                pageNumber--;
                this.parentWidget.vtPageSelector.attr("value",pageNumber)
            }
        }catch(e){
            logger.error("vtGotoPage: " + e.message);
        }
    },
    vtRefresh: function(){
        try{
            this.parentWidget.vtPageSelector.attr("value",pageNumber)
        }catch(e){
            logger.error("vtGotoPage: " + e.message);
        }
    },
    vtCreateRowsPerPageSelector: function(){
        
        try{
            // check de hien thi rowsPerPage selector
            // if((this.grid.rowsPerPage < this.grid.rowCount) && (this.grid.rowCount > 5)){ //Linhntn for gridGetDataUrl
            if (dojo.byId(this.id + "_rppContainer")){
                dojo.style(dojo.byId(this.id + "_rppContainer"),"display","inline");
            }
            if (this.vtRowsPerPageSelector == null){
                dojo.require("dijit.form.NumberSpinner");
                this.vtRowsPerPageSelector = new dijit.form.NumberSpinner({
                    smallDelta: 1,
                    largeDelta: 10,
                    value: this.grid.rowsPerPage,
                    constraints:{
                        min:1,
                        //  max:this.grid.totalRows, //Linhntn for rowsPerPage is not have max
                        places:0
                    },
                    style:"font-size:120%;font-family:Courier;width:100px;"
                },this.rppSelectorContainer);
                
                this.connect(this.vtRowsPerPageSelector, "onChange", "_vtRowsPerPageChanged");
                             
            }else{
                this.vtRowsPerPageSelector.constraints = {
                    min:1,
                    //  max:this.grid.totalRows, //Linhntn for rowsPerPage is not have max
                    places:0
                };
            }
            var grid = this;
            this.vtRowsPerPageSelector._enterFunction = function(){
                var resizeFunction = "_vtRowsPerPageChanged" ;
                grid._abortResize();
                this.resizeTimer = setTimeout(dojo.hitch(grid, resizeFunction),1000);
                
            }
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
        /*   }else{
                dojo.style(dojo.byId(this.id + "_rppContainer"),"display","none");
            }*/
        }catch(e){
            logger.error("create rowsPerPage selector: " + e.message);
        }
    },
    _abortResize: function(){
        // stop in-progress query
        if(this.vtRowsPerPageSelector.resizeTimer){
            clearTimeout(this.vtRowsPerPageSelector.resizeTimer);
            this.vtRowsPerPageSelector.resizeTimer = null;
        }
    },
    vtCreatPageSelector:function(){
        //var pc = Math.ceil(this.grid.rowCount/this.grid.rowsPerPage);
        // console.log(this.grid.currentPage);
        //Linhntn
        var pc = Math.ceil(this.grid.totalRows/this.grid.rowsPerPage);
        var data = {
            identifier: "page",
            label: "desc",
            items: []
        };
        var i;
        for(i = 0; i < pc; i++){
            data.items.push({
                page: i,
                desc: (i + 1) + " / " + pc
            });
        }
        //Linhnt for rowCount = 0 [[
        if(this.grid.totalRows == 0){
            data.items.push({
                page: 0,
                desc: 1 + " / " + 1
            });
        }
        //]]
        var ifsStore = new dojo.data.ItemFileReadStore({
            data: data
        });
        if (this.vtPageSelector == null){
            dojo.require("dijit.form.FilteringSelect");
            this.vtPageSelector = new dijit.form.FilteringSelect({
                pageSize: 10,
                store: ifsStore,
                value: this.grid.currentPage,
                searchAttr: "desc",
                style:"width:100px"
                
            //                    id: this.id + "_vtPageSelector"
            },this.pageSelectorContainer);
            this.connect(this.vtPageSelector, "onChange", "_vtPageSelectorChanged");
        }
        else{
            //            this.vtPageSelector.setStore(ifsStore,this.grid.scroller.page);
            this.vtPageSelector.store = ifsStore;
            this.vtPageSelector.attr("value", this.grid.currentPage);
        }
    },
    extendGrid:function(){
        var grid = this.grid;
        grid.scroller.scroll = this.scroller_scroll;
        grid.scroller.renderPage = this.renderPage;
        grid.scroller.needPage = this.needPage;
        grid.scroller.resize = this.scroller_resize; // DungDV fix bug: zoom Grid when rowCount < rowPerPage

        // datbt debug
        // [modified down_arrow, up_arrow on grid
        grid.onKeyDown = this.onKeyDown;
        grid.selection.clickSelect = this.selection_clickSelect;
        grid.selection.getSelectedCount = this.getSelectedCount;
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
        grid.vtGotoPage = this.vtGotoPage;
        grid.vtFetch = this.vtFetch;
        grid._vtFetchItems = this._vtFetchItems;
        grid.forceLoadCallback = this.forceLoadCallback;
       
        
        // ]add function call for reload grid data

        // [modified for update total row column - test
        grid.updateRowCount = this.updateRowCount;
        grid._onFetchComplete = this._onFetchComplete;
        
        //        [duongtb
        grid.vtCheckAll = this.vtCheckAll;
        grid.vtUnCheckAll = this.vtUnCheckAll;
        grid.vtGetCheckedData = this.vtGetCheckedData;
        grid.vtIsChecked = this.vtIsChecked;
        grid.vtGetRadioChecked = this.vtGetRadioChecked;
        grid.vtIsCheckedRadio = this.vtIsCheckedRadio;
        grid.onCellMouseOver = this.onCellMouseOver;
        grid.onCellMouseOut = this.onCellMouseOut;
        grid.doApplyCellEdit = this.doApplyCellEdit;
        grid.vtUncheckRadio = this.vtUncheckRadio;
        grid._onDelete = this._onDelete;
        grid.buildRendering = this.buildRendering;
        grid._fetch = this._fetch;
        grid.store.fetch = this.vtFetch;
        grid.store._fetchItems = this._vtFetchItems;
        grid.onCellClick = this._onCellClick;
        grid.vtOnCellMouseOver = this.vtOnCellMouseOver;
        // [DungDV 2011/11/08: loi sort column, zoom grid, grid hidden
        grid.onHeaderCellClick = this._onHeaderCellClick;
        grid.doheaderclick = this._doHeaderClick;
        grid.doclick = this._doClick;
        grid.textSizeChanged = this._textSizeChanged;
        grid.renderNoReload = this.renderNoReload;
        grid._render = this._render;
        grid.sort = this.vtSort;//Linhntn for sort
        for(var i=0,vs;(vs=grid.views.views[i]);i++){
            vs.doscroll = this._doScroll;
            vs.doContentEvent = this._doContentEvent;
        }
    // ]DungDV 2011/11/08
    //        ]

    },
    vtSort:function(){
        this._lastScrollTop=this.scrollTop;
        this._fetch(0,true,0); //Linhntn sort de grid khong bi co lai
    },
    // DungDV fix bug: zoom Grid when rowCount < rowPerPage
    scroller_resize:function(){
        if(this.scrollboxNode){
            this.windowHeight = this.scrollboxNode.clientHeight;
        }
        for(var i=0; i<this.colCount; i++){
            //We want to have 1px in height min to keep scroller.  Otherwise can't scroll
            //and see header in empty grid.
            dojox.grid.util.setStyleHeightPx(this.contentNodes[i], Math.max(1,this.height));
        }

        // Calculate the average row height and update the defaults (row and page).
        var needPage = (!this._invalidating);
        if(!needPage){
            var ah = this.grid.attr("autoHeight");
            if(typeof ah == "number"){
                needPage = true;
            }
        }
        if(needPage){
            this.needPage(this.page, this.pageTop);
        }
        var rowsOnPage = (this.page < this.pageCount - 1) ? this.rowsPerPage : ((this.rowCount % this.rowsPerPage) || this.rowsPerPage);
        var pageHeight = this.getPageHeight(this.page);
        this.averageRowHeight = (pageHeight > 0 && rowsOnPage > 0) ? (pageHeight / rowsOnPage) : 0;
    },
    // DungDV fix bug khong doi mau item duoc chon tren Select: Khi focus Select thi disable cac mouse event
    _doContentEvent:function(e){
        try{
            if (page.enableGridMouseEvent == true || e.type.indexOf('mouse') == -1) {
                if(this.content.decorateEvent(e)){
                    this.grid.onContentEvent(e);
                }
            }
        }
        catch(ex){
            console.log("Linhntn 1232 " + ex.message);
        }
    },
    getSelectedCount:function(){
        var c=0;
        for(var i=0;i<this.selected.length;i++){
            if(this.selected[i]){
                c++;
            }
        }
        var rowCount = this.grid._getRowCountAttr ();
        return (c > rowCount ? rowCount : c);
    },
    renderNoReload:function(){
        this.noFetchData = true;
        this._render();
    },
    _render:function(){
        if(this.domNode.parentNode){
            this.scroller.init(this.attr("rowCount"),this.keepRows,this.rowsPerPage);
            this.prerender();
            if (this.noFetchData == true) {
                this.noFetchData = false;
            } else {
                this._fetch(0,true);
            }
        }
    },
    _textSizeChanged:function(){
        this.noFetchData = true;
        this.render();
    },
    _doHeaderClick:function(e) { // DungDV: fix bug bao loi khi boi den header tren IE
        if(this.store.url != undefined &&this.store.url != "" && e.cell !== null && e.cell !== undefined && (e.cell.type === null || e.cell.type === undefined
            || (e.cell.type !== dojox.grid.cells.vtBool && e.cell.type !== dojox.grid.cells.Bool))){ // header checkbox
            if(e.cellNode){
                this.onHeaderCellClick(e);
            }else{
                this.onHeaderClick(e);
            }
        }
    },
    _onHeaderCellClick:function(e){
        if (this.parentWidget.sortableArray[e.cell.index])
            this.setSortIndex(e.cell.index);
        this.onHeaderClick(e);
    },
    _doClick:function(e){
        if(e.cellNode){
            this.onCellClick(e);
        }else{
            this.onRowClick(e);
        }
    },
    _onCellClick:function(e){
        this._click[0]=this._click[1];
        this._click[1]=e;
        
        //[ LinhNTN3@9Sep11 kill focus
        this.focus._focusifyCellNode(false);
        this.focus.cell=null;
        this.focus.rowIndex=null;
        this.focus._focusifyCellNode(true);
        //] LinhNTN@9Sep11 kill focus
        
        if (e.cell && e.cell.declaredClass.indexOf("Select") !== -1) {
            this.focus.info={
                cell:e.cell,
                rowIndex:e.rowIndex
            };
        } else if(!this.edit.isEditCell(e.rowIndex,e.cellIndex)){
            this.focus.setFocusCell(e.cell,e.rowIndex);
        }
        
        //[ LinhNTN@9Sep11 kiem tra xem event.target co phai la the select hay khong
        var isTargetSelect = false;
        if (e.target.nodeName == "SELECT") {
            isTargetSelect = true;
            //alert(this.onRowClick);
            return;
        }
        //] LinhNTN@9Sep11 kiem tra xem event.target co phai la the select hay khong
        this.onRowClick(e);
    },
    _doScroll: function(inEvent){
        //var s = dojo.marginBox(this.headerContentNode.firstChild);
        var isLtr = dojo._isBodyLtr();
        if(this.firstScroll < 2){
            if((!isLtr && this.firstScroll == 1) || (isLtr && this.firstScroll === 0)){
                var s = dojo.marginBox(this.headerNodeContainer);
                if(dojo.isIE){
                    this.headerNodeContainer.style.width = s.w + this.getScrollbarWidth() + 'px';
                }else if(dojo.isMoz){
                    //TODO currently only for FF, not sure for safari and opera
                    this.headerNodeContainer.style.width = s.w - this.getScrollbarWidth() + 'px';
                    //this.headerNodeContainer.style.width = s.w + 'px';
                    //set scroll to right in FF
                    this.scrollboxNode.scrollLeft = isLtr ?
                    this.scrollboxNode.clientWidth - this.scrollboxNode.scrollWidth :
                    this.scrollboxNode.scrollWidth - this.scrollboxNode.clientWidth;
                }
            }
            this.firstScroll++;
        }
        this.headerNode.scrollLeft = this.scrollboxNode.scrollLeft;
    },
    //    [duongtb:
    _vtSetting:function() {
        try{
            var dialog = new vt.dialog.vtDialog();
            var html = "<span style=\"color:#0B0B3B;\"><b>Cột</b></span>";
            html += "<table cellspacing=\"0\" cellpadding=\"0\"><tbody><tr><td valign=\"top\">" ;
            var cells  = this.grid.layout.cells;
            for(var i=0, cell; (cell=cells[i]); i++){
                if(cell.hidden == false){
                    html += "<input id=\"" + this.grid.id + "_column_"+ i +
                    "\" type=\"checkbox\" class=\"settings\" value=\"" +
                    i +"\" checked />&nbsp;" + cell.name + "<br />";
                }
                else{
                    html+= "<input id=\"" + this.grid.id + "_column_"+ i +
                    "\" type=\"checkbox\" class=\"settings\" value=\"" +
                    i +"\" />&nbsp;" + cell.name + "<br />";
                }
                if((i+1)%5==0){
                    html += "</td><td valign=\"top\">";
                }
            }
            html += "</td></tr></tbody><table>";
            try{
                dialog.vt_showToggleSizeNode = false;
                dojo.removeClass(dialog.toggleSizeButtonNode,"dijitDialogMaximizeIcon");
                dojo.removeClass(dialog.iconNode,"dijitDialogIcon");
                dialog.domNode.style.minWidth = "250px";
            }catch(ex){
                console.debug("[removeClass:<" + ex.message + ">]")
            }
            dialog.titleNode.innerHTML = "Thiết lập hiển thị cột";
            dialog.saveOnClose.innerHTML = "<button class=\"setting-save\">" +
            "<span class=\"setting-save-img\"></span>" +
            "<span>Lưu và đóng</span></button>" ;
            dialog.saveOnClose.style.display = "inline";
            dialog.settingsContent.innerHTML = html;
            dialog.settingsContent.style.backgroundColor = "#EEF6FB";
            dialog.settingsContent.style.marginTop = "5px";
            dialog.show();
        }catch(e){
            console.debug("[duongtb:<" + e.message + ">]");
        }
    },
    
    doApplyCellEdit: function(inValue, inRowIndex, inAttrName){
        //summary: .duongtb: !important
        //          need to get pageIndex coresponding to rowIndex to determine start,count for loading dynamically.
        //          construct request with {start,count} to pass into the fetchItemByIdentity of the store
        //alert("doApplyCellEdit vtDataGrid" + inValue + " "+ inRowIndex + " " + inAttrName);
        
        try{
            var pageIndex = this._rowToPage(inRowIndex);
            var start = pageIndex * this.rowsPerPage;
            var count = this.rowsPerPage;
            var self  = this;
            var indexInPage = inRowIndex - start; // for fetching the item which has not been loaded
            this.store.fetchItemByIdentity({
                identity: this._by_idx[inRowIndex].idty,
                start : start,
                startval: this.currentRow,
                count : count,
                query: self.query,
                sort: self.getSortProps(),
                indexInPage:indexInPage,
                queryOptions: this.queryOptions,
                onItem: dojo.hitch(this, function(item){
                    if (!this.dataTypeArray[inAttrName]) {
                        var oldValue = this.store.getValue(item, inAttrName);
                        if(typeof oldValue == 'number'){
                            this.dataTypeArray[inAttrName] = 'number';
                        }else if(typeof oldValue == 'boolean'){
                            this.dataTypeArray[inAttrName] = 'boolean';
                        }else if ((oldValue==null)||(oldValue instanceof Date)){
                            this.dataTypeArray[inAttrName] = 'date';
                        }
                    }
                    if(this.dataTypeArray[inAttrName] == 'number'){
                        inValue = isNaN(inValue) ? '' : parseFloat(inValue);
                        inValue = isNaN(inValue) ? '' : inValue; // DungDV: for fix number NaN
                    }else if(this.dataTypeArray[inAttrName] == 'boolean'){
                        inValue = inValue == 'true' ? true : inValue == 'false' ? false : inValue;
                    }else if (this.dataTypeArray[inAttrName] == 'date'){ //Linhntn: for fix 1/1/1970
                        if(inValue != null) {
                            var asDate = new Date(inValue);
                            inValue = isNaN(asDate.getTime()) ? inValue : asDate;
                        }
                    }
                    if ((inValue !== null)&&(inValue === undefined)){ //Linhntn for fix invalid date
                        inValue = this.store.getValue(item, inAttrName);
                    }
                    
                    if((inValue != null)&&(inValue instanceof Date)){ //Linhntn: for fix 1/1/1970
                        inValue = new Date(inValue).toJavaDate('yyyy-mm-ddThh:nn:ss');
                    }
                    // [DungDV: for fix number NaN
                    this.store.setValue(item, inAttrName, (this.dataTypeArray[inAttrName] == 'date' || this.dataTypeArray[inAttrName] == null) ? inValue : inValue + '');
                    this.onApplyCellEdit((this.dataTypeArray[inAttrName] == 'number' ? inValue + "" : inValue), inRowIndex, inAttrName);
                // ]DungDV: for fix number NaN
                })
            });
        }catch(e){
            console.debug("[linhntn:<" + e.message + ">]");
        }
    },
    onCellMouseOver:function(e){
        // summary:
        //		Event fired when mouse is over a cell.
        // e: Event
        //		Decorated event object contains reference to grid, cell, and rowIndex

        if(e.cellNode){
            dojo.addClass(e.cellNode, this.cellOverClass);
        //        console.debug(e.cellNode.innerHTML);
        }
        if(e.cell!=undefined){
            if(e.cell.wrapping !== true && e.cell.formatter==undefined){
                var item = this.getItem(e.rowIndex);
                var msg = e.cell.get ? e.cell.get(e.rowIndex, item) : (e.cell.value || e.cell.defaultValue);
                if(msg != null && msg != e.cellNode.innerHTML){
                    if(!this.edit.isEditing() && e.cell.alwaysEditing!=true){
                        //var msg = e.cell.defaultValue;
                        if (this.parentWidget) {
                            var cellEventMsg = this.vtOnCellMouseOver(msg, e.cellIndex, e.rowIndex);
                            if (cellEventMsg.constructor == Boolean) {
                                if (cellEventMsg) {
                                    msg=String(msg).replace(/&/gm,"&amp;").replace(/</gm,"&lt;").replace(/>/gm,"&gt;").replace(/"/gm,"&quot;");
                                    dijit.vtShowTooltip(msg, e.cellNode);
                                }
                            } else {
                                dijit.vtShowTooltip(cellEventMsg, e.cellNode);
                            }
                        } else {
                            console.log('vtDataGrid: Some error occured!');
                        }
                    }

                }
            }
        }
    },
    onCellMouseOut:function(e){
        // summary:
        //		Overwritten, see dojox.grid._Events.onCellMouseOut()

        if(e.cellNode){
            dojo.removeClass(e.cellNode, this.cellOverClass);
        }
        if(e.cell!=undefined){
            if(e.cell.wrapping !== true && e.cell.formatter==undefined){
                try{
                    
                    //dijit.hideTooltip(e.cellNode);
                    dijit.vtHideTooltip();
                //dijit._masterTT._onDeck=null;
                }catch(e){
                    console.log(e.message);
                }
            }
        }
    },
    vtOnCellMouseOver: function (msg, columnIndex, rowIndex) {
        return true;
    },
    //[ for radio
    vtIsCheckedRadio: function(){
        var checked = false;
        if(this._checked != null){
            checked = true;
        }

        //        var nodeList = dojo.query(".dojoxGridInput_radio", this.id);
        //        if(nodeList!=null){
        //            for(var i in nodeList){
        ////                console.log((nodeList[i].id));
        //                if(nodeList[i].checked==true){
        //                    check = true;
        //                    break;
        //                }
        //            }
        //        }
        return checked;
    },
    vtGetRadioChecked: function(){
        var item = null;
        //
        if(this._checked !=null){
            //item = this.getItem(this._checked);
            item = this._checked;
        }
        //        var grid_id = this.id;
        //        var grid = dijit.byId(grid_id);
        //        var nodeList = dojo.query(".dojoxGridInput_radio", this.id);
        //        if(nodeList!=null){
        //            for(var i in nodeList){
        //                if(nodeList[i].checked==true){
        ////                    console.log(nodeList[i].id);
        //                    var index = nodeList[i].id;
        //                    var rowIndex = index.split('_')[1];
        //                    item = grid.getItem(rowIndex);
        //                    break;
        //                }
        //            }
        //        }
        return item;
    },
    vtUncheckRadio:function(){

        if (this._checked != null) {
            this.unchecked = true; // DungDV: for bug full screen Dialog, unchecked radio not work (it happen when implement option setChecked of sd:ColumnDataGrid)
            var radio = dojo.byId("radio_" + this._checked._0 + "_" + this.id);
            this._checked = null;
            this.checked = false;
            if(radio!=null){
                radio.checked = false;
            }
        }

    },
    vtIsChecked: function(){
        var checked = false;
        //        var nodeList = dojo.query(".dojoxGridInput_checkbox", this.id);
        //        if(nodeList!=null){
        //            for(var i in nodeList){
        //                if(nodeList[i].checked==true){
        //                    check = true;
        //                    break;
        //                }
        //            }
        //        }
        if(this.serverPaging !== undefined && this.serverPaging === true){
            if(this._markup.length > 0){
                checked = true;
            }
        } else {
            for(var idx = 0; idx < this.store._arrayOfTopLevelItems.length; idx++){
                var item = this.store._arrayOfTopLevelItems[idx];
                if(item !== undefined && item !== null){
                    if(item.checked == true){ 
                        return true;
                    }
                }

            }
        }
        return checked;
    },
    //]end radio
    
    //Cuongnx đã sửa be bét
    // Checkbox khi có disable
    vtCheckAll: function(){

        this._markup = [];
        //var checkArr=dojo.query(".dojoxGridInput_checkbox", this.id);
        
        for(var idx = 0; idx < this.store._arrayOfTopLevelItems.length; idx++){
            //var node=checkArr[idx];
            var node=dojo.byId("checkbox_"+idx+"_"+ this.id);
            if (node!=null && node.disabled!=true){
                node.checked=true;
                var item = this.store._arrayOfTopLevelItems[idx];
                if(item !== undefined && item !== null){
                    if(this.serverPaging !== undefined && this.serverPaging == true){
                        this._markup.push(item);
                    }
                    item.checked = true;
                }
                this.selection.addToSelection(idx);
                
            }
        }
        dojo.query(".dojoxGridInput_checkbox", this.id).forEach(function(node, index){
            if (node!=null && node.disabled!=true){
                node.checked=true;
            }
        });
        try{
            this._checkAll = true;
            dojo.byId("cbx_" + this.id).checked = true;
        }catch(ex){

        }

    },
    vtUnCheckAll: function(){
        this._markup = [];
        //var checkArr=dojo.query(".dojoxGridInput_checkbox", this.id);
        for(var idx = 0; idx < this.store._arrayOfTopLevelItems.length; idx++){
            //var node=checkArr[idx];
            var node=dojo.byId("checkbox_"+idx+"_"+ this.id);
            if (node!=null && node.disabled!=true){
                node.checked=false;
                var item = this.store._arrayOfTopLevelItems[idx];
                if(item !== undefined && item !== null){
                    item.checked = false;
                }
            }
        }
        var _this = this;
        dojo.query(".dojoxGridInput_checkbox", this.id).forEach(function(node, index){
            node.checked=false;
        });
        if(_this.selection.getSelectedCount()!=1){
            try{
                _this.selection.deselectAll();

            }catch(ex){

            }
        }
        try{
            this._checkAll = false;
            if(this.serverPaging !== undefined && this.serverPaging == true){
                this.store._arrayOfUnCheck = []; // do something when sorting while rendering is incomplete
            }
            dojo.byId("cbx_" + this.id).checked = false;
        }catch(e){

        }
    },
    // cuongnx end
    
    // DungDV: for bug get checked data when serverPaging
    vtGetCheckedData: function(object,arrProps){
        var prop;
        var item;
        var i;
        var outContent = {};
        if(this.serverPaging !== undefined && this.serverPaging == true){
            i = 0;
            //            for(var idx = 0; idx < this._markup.length; idx++){
            //                item = this._markup[idx];
            //                prop = object +"[" + i + "]";
            //                i ++;
            //                this._vtGetDataForPost(outContent,prop,item,arrProps);
            //            }
            for (var idx in this.store._arrayOfAllItems) {
                item = this.store._arrayOfAllItems[idx];
                if (item !== undefined && item !== null && item.checked && item.checked == true) {
                    prop = object +"[" + i + "]";
                    i ++;
                    this._vtGetDataForPost(outContent,prop,item,arrProps);
                }
            }
        } else {
            i = 0;
            for(var j = 0; j < this.store._arrayOfTopLevelItems.length;j++){
                item = this.store._arrayOfTopLevelItems[j];
                if(item !== undefined && item.checked === true){
                    prop = object +"[" + i + "]";
                    i ++;
                    this._vtGetDataForPost(outContent,prop,item,arrProps);
                }
            }
        }
        return outContent;
    },
    // DungDV: for bug get checked items when serverPaging
    /* vtGetCheckedItems:function(){
        var arr = [];
        var i = 0;
        if(this.serverPaging == true){
            for (var idx in this.store._itemsByIdentity) {
                item = this.store._itemsByIdentity[idx];
                if (item.checked && item.checked == true) {
                    arr[i++] = item;
                }
            }
        } else {
            for(i = 0; i < this.store._arrayOfTopLevelItems.length;i++){
                var item = this.store._arrayOfTopLevelItems[i];
                if(item !== undefined && item.checked === true){
                    arr.push(item);
                }
            }
        }
        return arr;
    },*/
    vtGetCheckedItems:function(){
        var arr = [];
        var i = 0;
        if(this.serverPaging == true){
            for (var idx in this.store._arrayOfAllItems) {
                item = this.store._arrayOfAllItems[idx];
                if (item !== undefined && item !== null && item.checked && item.checked == true) {
                    arr[i++] = item;
                }
            }
        } else {
            for(i = 0; i < this.store._arrayOfTopLevelItems.length;i++){
                var item = this.store._arrayOfTopLevelItems[i];
                if(item !== undefined && item.checked === true){
                    arr.push(item);
                }
            }
        }
        return arr;
    },
    _onDelete: function(item){
        this._checkUpdateStatus();
        var idx = this._getItemIndex(item, true);

        if(idx >= 0){
            // When a row is deleted, all rest rows are shifted down,
            // and migrate from page to page. If some page is not
            // loaded yet empty rows can migrate to initialized pages
            // without refreshing. It causes empty rows in some pages, see:
            // http://bugs.dojotoolkit.org/ticket/6818
            // this code fix this problem by reseting loaded page info
            this._pages = [];
            this._bop = -1;
            this._eop = -1;

            var o = this._by_idx[idx];
            this._by_idx.splice(idx, 1);
            delete this._by_idty[o.idty];
            this.updateRowCount(this.attr('rowCount')-1);
            if(this.attr('rowCount') === 0){
                if (this.selectorChange) {
                    this.showMessage(this.loadingMessage);
                } else {
                    this.showMessage(this.noDataMessage);
                }
            }
        }
        //[ remove item in this._markup
        if(this.serverPaging == true){
            for(var i =0; i< this._markup.length; i++){
                if(item == this._markup[i]){
                    this._markup.splice(i,1);
                    break;
                }
            }
        }
    //]
    },
    //    end duongtb]
    updateRowCount: function(inRowCount){
        //summary:
        //	Change the number of rows.
        // inRowCount: int
        //	Number of rows in the grid.
        if(this.updating){
            this.invalidated.rowCount = inRowCount;
        }else{
            this.rowCount = inRowCount;
            // [Datbt
            
            this.parentWidget.rowCount.innerHTML = this.totalRows;
            if (inRowCount == 0){
                dojo.style(this.parentWidget.extensionDiv,"display","");//Linhntn for show selectors after delete item all item
            }else{
                dojo.style(this.parentWidget.extensionDiv,"display","");
                this.parentWidget.vtCreatPageSelector();
                this.parentWidget.vtCreateRowsPerPageSelector();
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
    
    _onFetchComplete: function(items, req){
        
        // Datbt
        if (items.length == 0){
            this.parentWidget.rowCount.innerHTML = this.totalRows;
            this.parentWidget.vtCreatPageSelector();
            this.parentWidget.vtCreateRowsPerPageSelector();
        }else{
            dojo.style(this.parentWidget.extensionDiv,"display","");
        }
        // Datbt
        if(!this.scroller){
            return;
        }
        if(items && items.length > 0){
            
            dojo.forEach(items, function(item, idx){
                this._addItem(item, req.start+idx, true);
            }, this);
            if(this._autoHeight){
                this._skipRowRenormalize = true;
            }
            // this.updateRows(req.start, items.length); //Linhntn remove for server Paging
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
                //if (req.start == 0){
                //linhntn
                if ((req.startval == 0)&&(!this.selectorChange)){ //linhntn
                    //alert("Vao day");
                    this.parentWidget.rowCount.innerHTML = this.totalRows;
                    this.parentWidget.vtCreatPageSelector();
                    this.parentWidget.vtCreateRowsPerPageSelector();
                }
            //  else {
                 
            //  if (this.parentWidget.vtPageSelector.attr("value") != this.currentPage )
            //      this.parentWidget.vtPageSelector.attr("value",  this.currentPage );
                  
            //  }
            }catch(e){
                logger.error("create rows total, page selector, rowsPerPage selector: " + e.message);
            }
        //datbt
        }
        delete this._lastScrollTop;
        if(!this._isLoaded){
            this._isLoading = false;
            this._isLoaded = true;
        }
        //linhntn
        this._pending_requests[req.startval] = false;
        
        // [duongtb: focus checked status of radio
        try{
            if(this._checked !=null){
                var radio = dojo.byId("radio_" + this._checked._0+"_" + this.id); // DungDV: last id will be null
                if(radio!=null){
                    radio.checked = true;
                }
            }
        }catch(ex){
            console.log("[duongtb:_onFetchComplete<" + ex.message + ">]");
        }
    //        ]
      
    //        ]

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
                //Linhntn for Up error in radio box
                if(!this.edit.isEditing() && this.focus.rowIndex === 0){
                    dojo.stopEvent(e);
                }
                //[Datbt
                this.selection.clickSelect(this.focus.rowIndex, dojo.isCopyKey(e), e.shiftKey);
                //]Datbt
                break;
            case dk.DOWN_ARROW:
              
                if(!this.edit.isEditing() && this.focus.rowIndex+1 != this.rowsPerPage){
                    dojo.stopEvent(e);
                    this.focus.move(1, 0);
                }
                //Linhntn for Down error in radio box
                if(!this.edit.isEditing() && this.focus.rowIndex === this.rowsPerPage - 1){
                    dojo.stopEvent(e);
                }
                /* if (this.focus.rowIndex === (this.rowsPerPage-1)){
                   // alert("Vao dong cuoi");
                    dojo.stopEvent(e);
                    return;
                }*/
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
    vtReload : function(url,formId,param,callback,pageNumber){
        //        this.store._jsonFileUrl = url;
        //        this._clearData();
        //        this._pages = [];
        //        this._store_connects = [];
        //        this._by_idty = {};
        //        this._by_idx = [];
        //        this._cache = [];
        //        this._pending_requests = {};
        //        this.store._arrayOfAllItems = [];
        //        this.store._arrayOfTopLevelItems = [];
        //        this.store._loadFinished = false;
        //        this.store._itemsByIdentity = null;
        //        this.store._loadInProgress = false;
        //        this.store._queuedFetches = [];
        //        this.store._pending = {
        //            _newItems:{},
        //            _modifiedItems:{},
        //            _deletedItems:{}
        //        };
        //        var _this = this;
        //        this.store._forceLoadByPost(_this, formId, param, callback);
        var store = {};
        
        if (url!=""){
            if(this.serverPaging !== undefined && this.serverPaging == true){
                var params = {
                    url:url
                    
                };
                store = new vt.dataGrid.iQueryWriteStore(params);
                store.formId = formId;
                store.param = param;
                store.grid = this;
                store.vtReloading = true;
                store.vtCallback = callback;
                store.fetch = this.vtFetch;
                store._fetchItems = this._vtFetchItems;
                this.showMessage(this.loadingMessage);
                this._setStore(store);
                
                // this.setStore(store);
                // this.store.fetch = this.fetch;
               
                //alert("bbb " +  store.fetch);
                
                /*this.fetch({
                            start:0,
                            startval:0,
                            count:this.rowsPerPage,
                            query:this.query,
                            sort:this.getSortProps(),
                            queryOptions:this.queryOptions,
                            isRender:true,
                            onBegin:dojo.hitch(this,"_onFetchBegin"),
                            onComplete:dojo.hitch(this,"_onFetchComplete"),
                            onError:dojo.hitch(this,"_onFetchError")
                        });*/
                // alert(this._fetch);
              
                //  this._fetch(0,true,0);
                //   this.vtFetch(0,true,0);
                //alert(store.fetch);
                this._refresh(true,pageNumber);
                this.resize(); // fix losting scrollbar when reloading
            }else {
                store = new vt.dataGrid.dataStore({
                    url:url,
                    urlPreventCache:true
                });
                store.formId = formId;
                store.param = param;
                store.grid = this;
                store.vtReloading = true;
                store.vtCallback = callback;
                sd.operator.displayWaitScreen(true);
                store.fetch();
            }
            

        //this.store.fetch(request);
        }else{
            store = vt.dataGrid.dataStore({
                data:{
                    identifier: null,
                    label: null,
                    items: []
                }
            });
            this.setStore(store);
            this.showMessage(this.noDataMessage);
        }
        try{
            this._checkAll = false;// header checkbox
            dojo.byId("cbx_" + this.id).checked = false;
            this.selection.deselectAll();
            this._markup = [];
            this._initialized = [];
            this._checked = null;
        }catch(e){

        }

    },
    forceLoadCallback:function(data,bSuccess){

    },
    _vtGetDataForPost: function(outContent,form,item,arrProps){
        var propForPost = "";
        var prop = "";
        var compositItem = null;
        if (arrProps){
            for (var i = 0;i < arrProps.length; i++ ){
                prop = arrProps[i];
                if (item[prop]){
                    propForPost = form +  "." + prop;
                    //[ Datbt 2010/06/07
                    //                    if (!dojo.isObject(item[prop][0])||item[prop][0] == null){
                    //] Datbt 2010/06/07
                    if (!dojo.isObject(item[prop][0])){
                        if (!outContent[ propForPost]){
                            outContent[ propForPost] = [];
                        }
                        outContent[ propForPost].push(item[prop][0]);
                    }
                    else{
                        compositItem = item[prop][0];
                        this._vtGetDataForPost(outContent,propForPost,compositItem,arrProps);
                    }
                }
            }
        }
        else{
            for (prop in item){
                if ((prop != "_0") && (prop != "_R") && (prop!="_RS")
                    && (prop!="_RI") && (prop!="_S") && (prop!="_RRM") && (prop!="_R_D_") && (prop!="checked")){
                    propForPost = form +  "." + prop;
                    //[ Datbt 2010/06/07
                    //                    if (!dojo.isObject(item[prop][0])||item[prop][0] == null){
                    //] Datbt 2010/06/07
                    if (!dojo.isObject(item[prop][0])){
                        if (!outContent[ propForPost]){
                            outContent[ propForPost] = [];
                        }
                        outContent[ propForPost].push(item[prop][0]);
                    }
                    else{
                        compositItem = item[prop][0];
                        this._vtGetDataForPost(outContent,propForPost,compositItem,arrProps);
                    }
                }
            }
        }

    },
    /* vtGetTotalDataForPost: function(object,arrProps){
        var i = 0;
        var outContent = {};
        for (var idx in this.store._items){
            var item = this.store._items[idx];
            if(item !== undefined && item !== null){
                var prop = object +"[" + i + "]";
                i ++;
                this._vtGetDataForPost(outContent,prop,item,arrProps);
            }
            
        }
        return outContent;
    },*/
    vtGetTotalDataForPost: function(object,arrProps){ //Linhntn: for post data in serverPaging
        var i = 0;
        var outContent = {};
        for (var idx in this.store._arrayOfAllItems){
            var item = this.store._arrayOfAllItems[idx];
            if(item !== undefined && item !== null && typeof item === 'object'){
                var prop = object +"[" + i + "]";
                i ++;
                this._vtGetDataForPost(outContent,prop,item,arrProps);
            }

        }
        return outContent;
    },
    vtGetSelectedDataForPost: function(object,arrProps){
        var outContent = {};
        for (var i = 0; i < this.selection.getSelected().length ; i ++){
            var item = this.selection.getSelected()[i];
            var prop = object +"[" + i + "]";
            this._vtGetDataForPost(outContent,prop,item,arrProps);
        }
        return outContent;
    },
    vtGetModifiedDataForPost: function(object,arrProps){
        var outContent = {};
        var i = 0;
        for( var id in this.store._pending._modifiedItems ) {
            var item = this.store._getItemByIdentity( id );
            var prop = object +"[" + i + "]";
            i ++;
            this._vtGetDataForPost(outContent,prop,item,arrProps);
        }
        return outContent;
    },
    // DungDV 10/8/2011: for bug checked data when serverPaging
    vtGetCheckedDataForPost: function(object,arrProps){
        var prop;
        var item;
        var i;
        var outContent = {};
        if(this.serverPaging !== undefined && this.serverPaging == true){
            i = 0;
            //for(var idx = 0; idx < this.store._itemsByIdentity.length; idx++){
            for (var idx in this.store._arrayOfAllItems) {
                item = this.store._arrayOfAllItems[idx];
                if (item !== undefined && item !== null && item.checked && item.checked == true) {
                    prop = object +"[" + i + "]";
                    i ++;
                    this._vtGetDataForPost(outContent,prop,item,arrProps);
                }
            }
        } else {
            i = 0;
            for(var j = 0; j < this.store._arrayOfAllItems.length;j++){
                item = this.store._arrayOfAllItems[j];
                if(item !== undefined && item.checked === true){
                    prop = object +"[" + i + "]";
                    i ++;
                    this._vtGetDataForPost(outContent,prop,item,arrProps);
                }
            }
        }
        return outContent;
    },
    scroller_scroll: function(inTop){
        //alert("Vao scroller");
        try{
            this.grid.scrollTop = inTop;
            var lastPage =  Math.ceil(this.grid.totalRows/this.grid.rowsPerPage) -1 ;
            //if (this.grid.currentPage === lastPage)
            //  var h = this.grid._getHeaderHeight() + this.grid.store._arrayOfAllItems.length * this.grid.scroller.averageRowHeight ;
            // this.grid.domNode.style.height = h+"px";
            if(this.colCount){
                this.startPacify();
                this.findPage(inTop);
                //[ datbt
                if (this.grid.parentWidget.vtPageSelector){
                    /* if(this.getScrollBottom(inTop) >= this.height){
                        var max_page = 0;
                        //max_page =  Math.ceil(this.grid.rowCount/this.grid.rowsPerPage) -1 ;
                        max_page =  Math.ceil(this.grid.totalRows/this.grid.rowsPerPage) -1 ;
                        this.grid.parentWidget.vtPageSelector.attr("value",max_page );
                        
                    }else{*/
                    if (this.grid.parentWidget.vtPageSelector.attr("value") != this.grid.currentPage){
                        this.grid.parentWidget.vtPageSelector.attr("value", this.grid.currentPage);
                    }
                // }
                }
                //] datbt
                 
                var h = this.height;
                var b = this.getScrollBottom(inTop);

                for(var p=this.page, y=this.pageTop; (p<this.pageCount); p++){
                    y += this.needPage(p, y);
                }
                this.firstVisibleRow = this.getFirstVisibleRow(this.grid.currentPage, this.pageTop, inTop);
                this.lastVisibleRow = this.getLastVisibleRow(p - 1, y, b);
                // indicates some page size has been updated
                if(h != this.height){
                    this.repositionPages(p-1);
                }
                this.grid.selectorChange = false;//Linhntn: set status for changing page
                this.endPacify();
            }
 
        }catch(e){
            console.debug("scroller_scroll: " + e.message);
        }
    },
    renderPage:function(_27){
        var _28=[];
        var i,j;
        for(i=0;i<this.colCount;i++){
            _28[i]=this.pageNodes[i][_27];
        }
        for(i=0,j=_27*this.rowsPerPage;(i<this.rowsPerPage)&&(j<this.rowCount);i++,j++){ //Linhntn:For server paging
            this.renderRow(j,_28);
        }
    },
    _fetch:function(_4a4,_4a5, startVal){
        _4a4=_4a4||0;
        //[Linhntn
        if (startVal == undefined)startVal= 0;
        this.currentPage = Math.round(startVal/this.rowsPerPage);
        //Linhntn
        if(this.store&&!this._pending_requests[startVal]){
            if(!this._isLoaded&&!this._isLoading){
                this._isLoading=true;
                this.showMessage(this.loadingMessage);
            }
            this._pending_requests[startVal]=true;
            try{
                if(this.items){
                    var _4a6=this.items;
                    var _4a7=this.store;
                    this.rowsPerPage=_4a6.length;
                    var req={
                        start:_4a4,
                        startVal: startVal,
                        count:this.rowsPerPage,
                        isRender:_4a5
                    };
                        
                    this._onFetchBegin(_4a6.length,req);
                    var _4a8=0;
                    dojo.forEach(_4a6,function(i){
                        if(!_4a7.isItemLoaded(i)){
                            _4a8++;
                        }
                    });
                    if(_4a8===0){
                        this._onFetchComplete(_4a6,req);
                    }else{
                        var _4a9=function(item){
                            _4a8--;
                            if(_4a8===0){
                                this._onFetchComplete(_4a6,req);
                            }
                        };
                            
                        dojo.forEach(_4a6,function(i){
                            if(!_4a7.isItemLoaded(i)){
                                _4a7.loadItem({
                                    item:i,
                                    onItem:_4a9,
                                    scope:this
                                });
                            }
                        },this);
                    }
                }else{
                    this.store.fetch({
                        start:_4a4,
                        startval:startVal,
                        count:this.rowsPerPage,
                        query:this.query,
                        sort:this.getSortProps(),
                        queryOptions:this.queryOptions,
                        isRender:_4a5,
                        onBegin:dojo.hitch(this,"_onFetchBegin"),
                        onComplete:dojo.hitch(this,"_onFetchComplete"),
                        onError:dojo.hitch(this,"_onFetchError")
                    });
                }
            }catch(e){
                this._onFetchError(e,{
                    start:_4a4,
                    count:this.rowsPerPage
                });
            }
        }
    },
    vtFetch:function(/* Object? */ request){
       
        //	summary:
        //		See dojo.data.util.simpleFetch.fetch() this is just a copy and I adjusted
        //		only the paging, since it happens on the server if doClientPaging is
        //		false, thx to http://trac.dojotoolkit.org/ticket/4761 reporting this.
        //		Would be nice to be able to use simpleFetch() to reduce copied code,
        //		but i dont know how yet. Ideas please!
        //              serverQuery:{start:,count:,sort:} load dynamically from server
        if (request.query == undefined){
            request.query = {
                name:'*'
            };
        }
        request.start = request.start || 0;
        request.serverQuery = {
            q:request.query.name,
            start:request.start,
            count:request.count,
            startval:request.startval //Linhntn for server paging
        };
        if(this.formId == null && request.query.formId !== undefined){ 
            this.formId = request.query.formId;
        }
        request = request || {};
        if(!request.store){
            request.store = this;
        }
        var self = this;

        var _errorHandler = function(errorData, requestObject){
            if(requestObject.onError){
                var scope = requestObject.scope || dojo.global;
                requestObject.onError.call(scope, errorData, requestObject);
            }
        };

        var _fetchHandler = function(items, requestObject, numRows){
            var oldAbortFunction = requestObject.abort || null;
            var aborted = false;

            var startIndex = requestObject.start?requestObject.start:0;
            if(self.doClientPaging == false){
                // For client paging we dont need no slicing of the result.
                startIndex = 0;
            }
            var endIndex = requestObject.count?(startIndex + requestObject.count):items.length;

            requestObject.abort = function(){
                aborted = true;
                if(oldAbortFunction){
                    oldAbortFunction.call(requestObject);
                }
            };

            var scope = requestObject.scope || dojo.global;
            if(!requestObject.store){
                requestObject.store = self;
            }
            if(requestObject.onBegin){
                requestObject.onBegin.call(scope, numRows, requestObject); // numRows: paging
            }
            if(requestObject.sort && self.doClientSorting){
                
                items.sort(dojo.data.util.sorter.createSortFunction(requestObject.sort, self));
            }
            if(requestObject.onItem){
                for(var i = startIndex; (i < items.length) && (i < endIndex); ++i){
                    var item = items[i];
                    if(!aborted){
                        requestObject.onItem.call(scope, item, requestObject);
                    }
                }
            }
            if(requestObject.onComplete && !aborted){
                var subset = null;
                if(!requestObject.onItem){
                    subset = items.slice(startIndex, endIndex);
                }
                requestObject.onComplete.call(scope, subset, requestObject);
            }
        };
         
        //this.grid.showMessage(this.grid.loadingMessage);
        sd.operator.displayWaitScreen(true); 
        this._fetchItems(request, _fetchHandler, _errorHandler);
        return request;	// Object
    },
    _vtFetchItems: function(/* Object */ request, /* Function */ fetchHandler, /* Function */ errorHandler){
        //	summary:
        //		See dojo.data.util.simpleFetch.fetch()
        //	summary:
        // 		The request contains the data as defined in the Read-API.
        // 		Additionally there is following keyword "serverQuery".
        //
        //	The *serverQuery* parameter, optional.
        //		This parameter contains the data that will be sent to the server.
        //		If this parameter is not given the parameter "query"'s
        //		data are sent to the server. This is done for some reasons:
        //		- to specify explicitly which data are sent to the server, they
        //		  might also be a mix of what is contained in "query", "queryOptions"
        //		  and the paging parameters "start" and "count" or may be even
        //		  completely different things.
        //		- don't modify the request.query data, so the interface using this
        //		  store can rely on unmodified data, as the combobox dijit currently
        //		  does it, it compares if the query has changed
        //		- request.query is required by the Read-API
        //
        // 		I.e. the following examples might be sent via GET:
        //		  fetch({query:{name:"abc"}, queryOptions:{ignoreCase:true}})
        //		  the URL will become:   /url.php?name=abc
        //
        //		  fetch({serverQuery:{q:"abc", c:true}, query:{name:"abc"}, queryOptions:{ignoreCase:true}})
        //		  the URL will become:   /url.php?q=abc&c=true
        //		  // The serverQuery-parameter has overruled the query-parameter
        //		  // but the query parameter stays untouched, but is not sent to the server!
        //		  // The serverQuery contains more data than the query, so they might differ!
        //
        // alert("Vao fetchItems");
        var serverQuery = request.serverQuery || request.query || {};
        //Need to add start and count
        if(!this.doClientPaging){
            serverQuery.start = request.start || 0;
            // Count might not be sent if not given.
            if(request.count){
                serverQuery.count = request.count;
            }
        }
        if(!this.doClientSorting){
            if(request.sort){
                
                var sort = request.sort[0];
                if(sort && sort.attribute){
                    var sortStr = sort.attribute;
                    if(sort.descending){
                        sortStr = "-" + sortStr;
                    }
                    serverQuery.sort = sortStr;
                }
            }
        }



        var self = this;
        //sumary:
        //      coresponding to _xhrFetchHandler in QueryReadStore.js,
        //      local client-sorting : sorting depends on data of each page
        //var filter = function(requestArgs, arrayOfItems){
        var filter = function(requestArgs, data){
            self._items = [];
            // alert(data.totalRows);
            var numRows = data.numRows || -1;

            // [!important

            if(numRows == -1){
                numRows = data.items.length;
            }
            // numRows += self.numOfNewRows; //Linhntn comment for severpaging. Because not need keep rows
            
            //]
            self._count = requestArgs.count; // fix bug when rowsPerPage has been changed
            if(request.sort){
                self._sortInfo = request.sort;
            }
            //]
            var start = requestArgs.start||0;

            var endIndex = start + data.items.length;
            endIndex = endIndex > data.numRows ? data.numRows : endIndex;
            var i, key;
            if(self.doClientSorting){//client-sorting
                
                if(requestArgs.query){
                    var value;
                    var ignoreCase = requestArgs.queryOptions ? requestArgs.queryOptions.ignoreCase : false;

                    //See if there are any string values that can be regexp parsed first to avoid multiple regexp gens on the
                    //same value for each item examined.  Much more efficient.
                    var regexpList = {};
                    for(key in requestArgs.query){
                        value = requestArgs.query[key];
                        if(typeof value === "string"){
                            regexpList[key] = dojo.data.util.filter.patternToRegExp(value, ignoreCase);
                        }else if(value instanceof RegExp){
                            regexpList[key] = value;
                        }
                    }
                    //for(i = start; i < arrayOfItems.length; ++i){
                    for(i = start; i < endIndex; ++i){
                        var match = true;
                        //var candidateItem = arrayOfItems[i];
                        var candidateItem = self._arrayOfTopLevelItems[i];
                        if(candidateItem === null){
                            match = false;
                        }else{
                            for(key in requestArgs.query){
                                value = requestArgs.query[key];
                                if(!self._containsValue(candidateItem, key, value, regexpList[key])){
                                    match = false;
                                }
                            }
                        }
                        if(match){
                            self._items.push(candidateItem);
                        }
                    }
                    fetchHandler(self._items, requestArgs,numRows);
                }
            } else{
                
                // We want a copy to pass back in case the parent wishes to sort the array.
                // We shouldn't allow resort of the internal list, so that multiple callers
                // can get lists and sort without affecting each other.  We also need to
                // filter out any null values that have been left as a result of deleteItem()
                // calls in ItemFileWriteStore.
                //for(i = 0; i < arrayOfItems.length; ++i){
                for(i = start; i < endIndex; i++){
                    //var item = arrayOfItems[i];
                    var item = self._arrayOfTopLevelItems[i];
                    if(item !== null){
                        self._items.push(item);
                    }
                }
                
                numRows = self._numRows = (numRows === -1) ? self._items.length : numRows;
                fetchHandler(self._items, requestArgs,self._numRows);
                self._numRows = numRows;
            }
        };
       
        //if(this._loadFinished){
        //filter(request, this._getItemsArray(request.queryOptions));
        //}
        // Compare the last query and the current query by simply json-encoding them,
        // so we dont have to do any deep object compare ... is there some dojo.areObjectsEqual()???
        if(this.doClientPaging && this._lastServerQuery !== null &&
            dojo.toJson(serverQuery) == dojo.toJson(this._lastServerQuery)
            ){
            this._numRows = (this._numRows === -1) ? this._items.length : this._numRows;
            fetchHandler(this._items, request, this._numRows);
        }
        else{
            //Do a check on the JsonFileUrl and crosscheck it.
            //If it doesn't match the cross-check, it needs to be updated
            //This allows for either url or _jsonFileUrl to he changed to
            //reset the store load location.  Done this way for backwards
            //compatibility.  People use _jsonFileUrl (even though officially
            //private.
            //[ set url: !important
            if(this._jsonFileUrl !== this._ccUrl){
                dojo.deprecated("dojo.data.ItemFileReadStore: ",
                    "To change the url, set the url property of the store," +
                    " not _jsonFileUrl.  _jsonFileUrl support will be removed in 2.0");
                this._ccUrl = this._jsonFileUrl;
                this.url = this._jsonFileUrl;
            }else if(this.url !== this._ccUrl){
                this._jsonFileUrl = this.url;
                this._ccUrl = this.url;
            }
            //]
            //See if there was any forced reset of data.
            //  keywordParameters: {url: String}
            //	keywordParameters: {data: jsonObject}
            //	keywordParameters: {typeMap: object)
            if(this.data != null && this._jsonData == null){ // if data had been setted to store when initting
                this._jsonData = this.data;
                this.data = null;
                
            }

            if(this._jsonFileUrl){
               
                //If fetches come in before the loading has finished, but while
                //a load is in progress, we have to defer the fetching to be
                //invoked in the callback.
                //                if(this._loadInProgress){
                //                    this._queuedFetches.push({
                //                        args: request,
                //                        filter: filter
                //                    });
                //}else{ //begin else 1
                this._loadInProgress = true;
                // do fetching from server
                var xhrFunc = this.requestMethod.toLowerCase() == "post" ? dojo.xhrPost : dojo.xhrGet;
                //  alert("xhrHandler");
                var xhrHandler = xhrFunc({
                    url:this.url,
                    handleAs:"json-comment-optional",
                    content:dojo.mixin(serverQuery, this.param), // mixin param into serverQuery
                    form:this.formId,
                    //preventCache: this.urlPreventCache,
                    failOk: this.failOk,
                    
                    //LongH@6Aug11
                    load : dojo.hitch(this, function(data){
                        //this._xhrFetchHandler(data, request, fetchHandler, errorHandler);
                        try{
                            //[stupid code : purpose for fix bug when not to implement paging on server, means to
                            //the length of data is greater than request.count
                            // alert("Vao call back");
                            //  if(data.numRows == 0){
                            data.numRows = data.items.length;
                            // }
                            //Linhntn
                            this.grid.store.setStoreEmpty(); //Linhntn them vao de khong co lai gri
                            this.grid.totalRows = data.totalRows;

                            //request.start = 0;
                            //alert("grid totalRows " + this.grid.totalRows);
                            if(data.items.length > request.count){
                                var count;
                                if(request.start + request.count > data.items.length){
                                    count = data.items.length - request.start;

                                }
                                else{
                                    count = request.count;
                                }
                                data.items = data.items.splice(request.start,count);
                            }

                            //]
                            // ]end stupid
                            //self._getItemsFromLoadedData(data);
                            //summary:
                            //      each loading time, data need to merge into _arrayOfAllItems,_arrayOfTopLevelItems
                            self._getItemsFromLoadedData(data, request); // review???

                            if ((self.counter + 1)* request.count >= data.numRows) {
                                self._loadFinished = true;
                                self._loadInProgress = false;
                            }
                            self.counter++;
                            //filter(request, self._getItemsArray(request.queryOptions));
                            //summary:
                            //        delegate to _fetchHandler
                            filter(request, data);// get data coresponding to each page (see _xhrFetchHandler)
                            //self._xhrFetchHandler(data, request, fetchHandler, errorHandler);
                            self._handleQueuedFetches();
                            sd.operator.displayWaitScreen(false);
                            try{
                                this.grid.updateRowCount(data.numRows);

                            }catch(e){
                                console.log("[updateRowCount in iQueryWriteStore:" + e.message + "]");
                            }
                            // set if successful
                            if (self.vtReloading){
                                self.vtReloading = false;

                                sd.operator.displayWaitScreen(false);
                                if (dojo.isFunction(self.vtCallback)){
                                    self.vtCallback(data);
                                }
                            }
                        }catch(e){
                            // have some errors occur
                            self._loadFinished = true;
                            self._loadInProgress = false;
                            errorHandler(e, request);
                            // if failure
                            if (self.vtReloading){
                                if (data.indexOf("This is logout page") != -1){
                                    var arr = data.split("//@@");
                                    parent.document.location.href = arr[1];
                                }
                                else{
                                    var messageObject = sd.operator.getMessageObject(data);
                                    if (messageObject){
                                        if (messageObject.type =="ERROR"){
                                            console.log("Error in reloading: " + messageObject.content);
                                        }
                                    }
                                }
                                self.vtReloading = false;
                                sd.operator.displayWaitScreen( false );
                            }
                        }
                    }),
                    
                    //LongH@6Aug11
                    error : function(error){
                        // have some errors occur
                        self._loadInProgress = false;
                        errorHandler(error, request);
                        // if failure
                        if (self.vtReloading){
                            self.vtReloading = false;
                            sd.operator.displayWaitScreen( false );

                        }
                    }
                });
                
                //xhrHandler.addCallback();
                //xhrHandler.addErrback();
              
                //this.lastRequestHash = new Date().getTime()+"-"+String(Math.random()).substring(2);
                //this._lastServerQuery = dojo.mixin({}, serverQuery);// smart convert serverQuery into {}
                //Wire up the cancel to abort of the request
                //This call cancel on the deferred if it hasn't been called
                //yet and then will chain to the simple abort of the
                //simpleFetch keywordArgs
                var oldAbort = null;
                if(request.abort){
                    oldAbort = request.abort;
                }
                request.abort = function(){
                    var df = xhrHandler;
                    if(df && df.fired === -1){
                        df.cancel();
                        df = null;
                    }
                    if(oldAbort){
                        oldAbort.call(request);
                    }
                };
                
            //} //end else 1
            }else if(this._jsonData){ // data had been loaded
                try{
                    if ((this.counter + 1)* request.count >= this._jsonData.numRows) {
                        this._loadFinished = true;
                        this._loadInProgress = false;
                    }
                    this.counter++;
                    //copy of stupid code
                    if(this._jsonData.numRows === undefined  ||this._jsonData.numRows == 0){
                        this._jsonData.numRows = this._jsonData.items.length;
                    }
                    if(this._jsonData.items.length > request.count){
                        var count;
                        if(request.start + request.count > this._jsonData.items.length){
                            count = this._jsonData.items.length - request.start;

                        }
                        else{
                            count = request.count;
                        }
                        this._jsonData.items = this._jsonData.items.splice(request.start,count)
                    }
                    //]
                    this._getItemsFromLoadedData(this._jsonData,request);

                    //this._jsonData = null;
                    //filter(request, this._getItemsArray(request.queryOptions));
                    filter(request, this._jsonData);
                }catch(e){
                    errorCallback(e, request);
                }
            }else{
                errorCallback(new Error("dojo.data.ItemFileReadStore: No JSON source data was provided as either URL or a nested Javascript object."), keywordArgs);
            }
        }
    },
    needPage:function(_3d,_3e){
        var h=this.getPageHeight(_3d),oh=h;
        if((!this.pageExists(_3d))||(this.grid.selectorChange)){ //Linhntn for server paging
            this.buildPage(_3d,this.keepPages&&(this.stack.length>=this.keepPages),_3e);
            h=this.updatePageHeight(_3d,true);
        }else{
            this.positionPage(_3d,_3e);
        }
        return h;
    }

});
// for formatting date
var gsMonthNames = new Array(
    'January',
    'February',
    'March',
    'April',
    'May',
    'June',
    'July',
    'August',
    'September',
    'October',
    'November',
    'December'
    );
var gsDayNames = new Array(
    'Sunday',
    'Monday',
    'Tuesday',
    'Wednesday',
    'Thursday',
    'Friday',
    'Saturday'
    );
Date.prototype.toJavaDate = function(f)
{
    if (!this.valueOf())
        return ' ';

    var d = this;
    return f.replace(/(yyyy|mmmm|mmm|mm|dddd|ddd|dd|hh|nn|ss|a\/p)/gi,
        function($1)
        {
            switch ($1.toLowerCase())
            {
                case 'yyyy':
                    return d.getFullYear();
                case 'mmmm':
                    return gsMonthNames[d.getMonth()];
                case 'mmm':
                    return gsMonthNames[d.getMonth()].substr(0, 3);
                case 'mm':
                    var m = d.getMonth() + 1 ;
                    return (m >=10)?m:'0' + m;
                case 'dddd':
                    return gsDayNames[d.getDay()];
                case 'ddd':
                    return gsDayNames[d.getDay()].substr(0, 3);
                case 'dd':
                    var day = d.getDate();
                    return (day >= 10)?day:'0'+ day;
                case 'hh':
                    var h =(d.getHours() % 12) ? d.getHours() % 12 : 12;
                    return (h>=10)?h:'0'+ h;
                case 'nn':
                    var n = d.getMinutes();
                    return (n>=10)?n:'0' + n;
                case 'ss':
                    var s = d.getSeconds();
                    return (s>=10)?s:'0' + s;
                case 'a/p':
                    return d.getHours() < 12 ? 'AM' : 'PM';
            }
        }
        );
}


