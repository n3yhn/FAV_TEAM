/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

//[ LongH @18May11
dojo.require("dojo.dnd.move");
dojo.require("dojo.dnd.TimedMoveable");
dojo.require("dojo.fx");
//] LongH @18May11

dojo.require("dijit.Dialog");

dojo.provide("vt.dialog.vtDialog");
dojo.declare("vt.dialog.vtDialog", dijit.Dialog,{
    templateString: null,
    templatePath: dojo.moduleUrl("vt", "templates/Dialog.html"),

    vt_showToggleSizeNode : true,
    vt_showCloseNode : true,

    vt_fullscreen:false,
    vt_beforeFullscreenWidth : undefined,
    vt_beforeFullscreenHeight : undefined,
    vt_beforeFullscreenLeft : undefined,
    vt_beforeFullscreenTop : undefined,

    vtOnPreClose : undefined,
    vtOnPostClose : undefined,

    vtDomNode : undefined, //LongH says: fix null-node bug in 2nd reload widget
    vtContainerNode : undefined, //LongH says: fix null-node bug in 2nd reload widget
    vtFakeContainerNode : undefined, //LongH says: fix null-node bug in 2nd reload widget

    vt_disableSwitchSizeFeature : function() {
        dojo.removeClass(this.toggleSizeButtonNode,"dijitDialogMaximizeIcon-hover");
        dojo.removeClass(this.toggleSizeButtonNode,"dijitDialogMaximizeIcon");
        dojo.removeClass(this.toggleSizeButtonNode,"dijitDialogMinimizeIcon");

        this.vt_showToggleSizeNode = false;
    },

    vt_gotoFullViewableScreenSizeMode : function() {
        var node = this.domNode;

        // [ Backup previous display state
        this.vt_beforeFullscreenWidth = node.style.width;
        this.vt_beforeFullscreenHeight = node.style.height;

        this.vt_beforeFullscreenLeft = node.style.left;
        this.vt_beforeFullscreenTop = node.style.top;
        // ]

        // [ Fullscreen the dialog
        var pageWH = sd.util.getPageWH();
        var adjustWSize = 17;
        var adjustHSize = 17;
        var w = pageWH[0] - adjustWSize;
        var h = pageWH[1] - adjustHSize;

        node.style.width =  w;
        node.style.height = h;

        node.style.left = 0;
        node.style.top = 0;

        this.containerNode.style.width = "100%";
        this.containerNode.style.height = "100%";

        window.scrollTo(0, 0);
        this.vt_disableSwitchSizeFeature();
    },

    _vt_onToggleSize:function() {
        try {
            if( this.vt_showToggleSizeNode ) {
                // If being in fullscreen mode
                if( this.vt_fullscreen ) {
                    this._vt_gotoNormalSizeMode();
                }
                // If being in normal mode
                else {
                    this._vt_gotoFullScreenSizeMode();
                }
            }

        } catch( e ) {
            alert( "_vt_onToggleSize: " + e.message );
        }
    },

    _vt_gotoNormalSizeMode:function() {
        var node = this.domNode;
        
        // [ Restore previous display state
        node.style.width = this.vt_beforeFullscreenWidth;
        node.style.height = this.vt_beforeFullscreenHeight;

        node.style.left = this.vt_beforeFullscreenLeft;
        node.style.top = this.vt_beforeFullscreenTop;

        this.containerNode.style.height = "100%";
        // [ Restore previous display state
        //[duongtb
        dojo.query(".dojoxGrid", node.id).forEach(function(grid_node, index){
            dojo.style(grid_node, {
                width:'97%'
            });
            dijit.byId(grid_node.id).resize();
        });
        //]end duongtb

        dojo.removeClass(this.toggleSizeButtonNode,"dijitDialogMinimizeIcon-hover");
        dojo.removeClass(this.toggleSizeButtonNode,"dijitDialogMinimizeIcon");
        dojo.addClass(this.toggleSizeButtonNode,"dijitDialogMaximizeIcon");

        this.vt_fullscreen = false;
    },

    _vt_gotoFullScreenSizeMode : function() {
        var node = this.domNode;
        
        // [ Backup previous display state
        this.vt_beforeFullscreenWidth = node.style.width;
        this.vt_beforeFullscreenHeight = node.style.height;

        this.vt_beforeFullscreenLeft = node.style.left;
        this.vt_beforeFullscreenTop = node.style.top;
        // ]

        // [ Fullscreen the dialog
        var pageWH = sd.util.getPageWH();
        var adjustWSize = 17;
        var adjustHSize = 17;
        var w = pageWH[2] - adjustWSize;
        var h = pageWH[3] - adjustHSize;

        node.style.width =  w;
        node.style.height = h;

        node.style.left = 0;
        node.style.top = 0;

        this.containerNode.style.width = "100%";
        this.containerNode.style.height = "100%";

        // ] Fullscreen the dialog
        //[duongtb
        dojo.query(".dojoxGrid", node.id).forEach(function(grid_node, index){
            dojo.style(grid_node, {
                width:'98%'
            });
            dijit.byId(grid_node.id).resize();
        });
        //]end duongtb
        dojo.removeClass(this.toggleSizeButtonNode,"dijitDialogMaximizeIcon-hover");
        dojo.removeClass(this.toggleSizeButtonNode,"dijitDialogMaximizeIcon");
        dojo.addClass(this.toggleSizeButtonNode,"dijitDialogMinimizeIcon");

        this.vt_fullscreen = true;
    },

    _vt_onToggleEnter:function(){
        if( !this.vt_showToggleSizeNode ) {
            if( this.vt_fullscreen ) {
                dojo.addClass(this.toggleSizeButtonNode,"dijitDialogMinimizeIcon-hover");
            } else {
                dojo.addClass(this.toggleSizeButtonNode,"dijitDialogMaximizeIcon-hover");
            }
        }
    },

    _vt_onToggleLeave:function(){
        if( !this.vt_showToggleSizeNode ) {
            if( this.vt_fullscreen ) {
                dojo.removeClass(this.toggleSizeButtonNode,"dijitDialogMinimizeIcon-hover");
            } else {
                dojo.removeClass(this.toggleSizeButtonNode,"dijitDialogMaximizeIcon-hover");
            }
        }
    },

    _onCloseEnter:function(){
        if( !this.vt_showCloseNode ) {
            dojo.addClass(this.closeButtonNode,"dijitDialogCloseIcon-hover");
        }
    },

    _onCloseLeave:function(){
        if( !this.vt_showCloseNode ) {
            dojo.removeClass(this.closeButtonNode,"dijitDialogCloseIcon-hover");
        }
    },

    postCreate : function() {
        this.inherited(arguments);
        
        if( !this.vt_showToggleSizeNode ) {
            //this.toggleSizeButtonNode.style.visibility = "hidden";
            dojo.addClass(this.toggleSizeButtonNode,"dijitDialogMaximizeIcon-disabled");
        }
        if( !this.vt_showCloseNode ) {
            //this.closeButtonNode.style.visibility = "hidden";
            dojo.addClass(this.closeButtonNode,"dijitDialogCloseIcon-disabled");
        }
    },

    _position: function(){
        // summary:
        //		Position modal dialog in the viewport. If no relative offset
        //		in the viewport has been determined (by dragging, for instance),
        //		center the node. Otherwise, use the Dialog's stored relative offset,
        //		and position the node to top: left: values based on the viewport.
        // tags:
        //		private
        if(!dojo.hasClass(dojo.body(),"dojoMove")){
            var node = this.domNode,
            viewport = dijit.getViewport(),
            p = this._relativePosition,
            bb = p ? null : dojo._getBorderBox(node),
            l = Math.floor(viewport.l + (p ? p.x : (viewport.w - bb.w) / 2)),
            t = Math.floor(viewport.t + (p ? p.y : (viewport.h - bb.h) / 2))
            ;

            //[ LongH @ 08Jan10
            var vt_t, vt_l, vt_scrXY;
            vt_scrXY = sd.util.getScrollXY();

            vt_l = vt_scrXY[0] + l;
            vt_t = vt_scrXY[1] + 150;

            l = vt_l;
            t = vt_t;
            //] LongH @ 08Jan10

            dojo.style(node,{
                left: l + "px",
                top: t + "px"
            });
        }
    },

    /**
     * Author: LongH
     * Description: Thay đổi cơ chế starDrag
     * Date: 07/06/2011
     * FWVersion: 3.3
     **/
    _vtStartDrag : function(e) {
        var domNode = this.vtDomNode;
        var realNode = this.vtContainerNode;
        var fakeNode = this.vtFakeContainerNode;

        fakeNode.style.width = parseInt(realNode.offsetWidth) + "px";
        fakeNode.style.height = parseInt(realNode.offsetHeight) + "px";

        fakeNode.style.display = "";
        realNode.style.display = "none";
        //realNode.style.visibility = "hidden";

        sd.util.setOpacity(domNode, 65);
    },

    /**
     * Author: LongH
     * Description: Thay đổi cơ chế endDrag
     * Date: 07/06/2011
     * FWVersion: 3.3
     **/
    _vtEndDrag : function(e) {
        if(e && e.node && e.node === this.domNode){
            this._relativePosition = dojo.position(e.node);
        }

        var domNode = this.vtDomNode;
        var realNode = this.vtContainerNode;
        var fakeNode = this.vtFakeContainerNode;

        fakeNode.style.display = "none";
        realNode.style.display = "";
        //realNode.style.visibility = "visible";

        sd.util.setOpacity(domNode, 100);
    },

    //Mod by LongH to make DnD faster @18May11
    _setup: function(){
        // summary:
        //		Stuff we need to do before showing the Dialog for the first
        //		time (but we defer it until right beforehand, for
        //		performance reasons).
        // tags:
        //		private

        var node = this.domNode;
        
        //[ LongH
        this.vtDomNode = this.domNode;
        this.vtContainerNode = this.containerNode;
        this.vtFakeContainerNode = this.fakeContainerNode;
        
        this.vtFakeContainerNode.style.display = "none";
        //] LongH

        if(this.titleBar && this.draggable){
            this._moveable = (dojo.isIE == 6) ?
            new dojo.dnd.TimedMoveable(node, {
                handle: this.titleBar
            }) :	// prevent overload, see #5285
            new dojo.dnd.Moveable(node, {
                handle: this.titleBar,
                timeout: 0
            });

            dojo.subscribe("/dnd/move/start",this,"_vtStartDrag"); //by LongH
            dojo.subscribe("/dnd/move/stop",this,"_vtEndDrag"); //by LongH
            
        }else{
            dojo.addClass(node,"dijitDialogFixed");
        }

        this.underlayAttrs = {
            dialogId: this.id,
            "class": dojo.map(this["class"].split(/\s/), function(s){
                return s+"_underlay";
            }).join(" ")
        };

        this._fadeIn = dojo.fadeIn({
            node: node,
            duration: this.duration,
            beforeBegin: dojo.hitch(this, function(){
                var underlay = dijit._underlay;
                if(!underlay){
                    underlay = dijit._underlay = new dijit.DialogUnderlay(this.underlayAttrs);
                }else{
                    underlay.attr(this.underlayAttrs);
                }

                var zIndex = 948 + dijit._dialogStack.length*2;
                dojo.style(dijit._underlay.domNode, 'zIndex', zIndex);
                dojo.style(this.domNode, 'zIndex', zIndex + 1);
                underlay.show();
            }),
            onEnd: dojo.hitch(this, function(){
                if(this.autofocus){
                    // find focusable Items each time dialog is shown since if dialog contains a widget the
                    // first focusable items can change
                    this._getFocusItems(this.domNode);
                    dijit.focus(this._firstFocusItem);
                }
            })
        });

        this._fadeOut = dojo.fadeOut({
            node: node,
            duration: this.duration,
            onEnd: dojo.hitch(this, function(){
                node.style.display = "none";

                // Restore the previous dialog in the stack, or if this is the only dialog
                // then restore to original page
                var ds = dijit._dialogStack;
                if(ds.length == 0){
                    dijit._underlay.hide();
                }else{
                    dojo.style(dijit._underlay.domNode, 'zIndex', 948 + ds.length*2);
                    dijit._underlay.attr(ds[ds.length-1].underlayAttrs);
                }

                // Restore focus to wherever it was before this dialog was displayed
                if(this.refocus){
                    var focus = this._savedFocus;

                    // If we are returning control to a previous dialog but for some reason
                    // that dialog didn't have a focused field, set focus to first focusable item.
                    // This situation could happen if two dialogs appeared at nearly the same time,
                    // since a dialog doesn't set it's focus until the fade-in is finished.
                    if(ds.length > 0){
                        var pd = ds[ds.length-1];
                        if(!dojo.isDescendant(focus.node, pd.domNode)){
                            pd._getFocusItems(pd.domNode);
                            focus = pd._firstFocusItem;
                        }
                    }

                    dijit.focus(focus);
                }
            })
        });
    },

    show: function(){
        // summary:
        //		Display the dialog
        if(this.open){ 
            return;
        }

        // first time we show the dialog, there's some initialization stuff to do
        if(!this._alreadyInitialized){
            this._setup();
            this._alreadyInitialized=true;
        }

        if(this._fadeOut.status() == "playing"){
            this._fadeOut.stop();
        }

        // [ LongH @ 11Jan10
        this._modalconnects.push(dojo.connect(window, "onscroll", this, "vtLayoutScroll"));
        // ] LongH @ 11Jan10
        this._modalconnects.push(dojo.connect(window, "onresize", this, function(){
            // IE gives spurious resize events and can actually get stuck
            // in an infinite loop if we don't ignore them
            var viewport = dijit.getViewport();
            if(!this._oldViewport ||
                viewport.h != this._oldViewport.h ||
                viewport.w != this._oldViewport.w){
                this.layout();
                this._oldViewport = viewport;
            }
        }));
        this._modalconnects.push(dojo.connect(dojo.doc.documentElement, "onkeypress", this, "_onKey"));

        dojo.style(this.domNode, {
            opacity:0,
            display:""
        });

        this.open = true;
        this._onShow(); // lazy load trigger

        this._size();
        this._position();
        dijit._dialogStack.push(this);
        this._fadeIn.play();

        this._savedFocus = dijit.getFocus(this);
        //[ Datbt 2010/05/17
        var gridAry = this.findGridInside(this.domNode);
        for (var i = 0; i <gridAry.length; i++){
            gridAry[i]._refresh();
            //            [duongtb
            var gridNode = gridAry[i].domNode;
            dojo.style(gridNode, {
                width:'98%'
            });
            dijit.byId(gridNode.id).resize();
        //            ]
        }
    //] Datbt 2010/05/17
    },

    hide:function(){
        if( this.vt_showCloseNode ) {
            if(this.vtOnPreClose) {
                this.vtOnPreClose();
            }

            if( this.vt_fullscreen ) {
                this._vt_gotoNormalSizeMode();
                this.vt_fullscreen = false;
            }
            this.inherited(arguments);

            if(this.vtOnPostClose) {
                this.vtOnPostClose();
            }
        }
    },
    
    //[ Datbt 2010/05/17
    findGridInside : function(/*DomNode*/ root){
        // summary:
        //		Search subtree under root returning widgets found.
        //		Doesn't search for nested widgets (ie, widgets inside other widgets).
        var outAry = [];
        function getChildrenHelper(root){
            for(var node = root.firstChild; node; node = node.nextSibling){
                if(node.nodeType == 1){
                    var widgetId = node.getAttribute("widgetId");
                    if(widgetId ){
                        var widget = {};
                        if (widgetId){
                            widget = dijit.byId(widgetId);
                            if (widget){
                                if (widget.declaredClass == "dojox.grid.DataGrid"){
                                    outAry.push(widget);
                                }
                            }
                        } 
                    }else{
                        getChildrenHelper(node);
                    }
                }
            }
        }

        getChildrenHelper(root);
        return outAry;
    },
    //] Datbt 2010/05/17
        
    vtLayoutScroll : function() {
        if( dijit._underlay ){
            dijit._underlay.layout();
        }
    },

    layout:function(){
        if(this.domNode.style.visibility!="hidden" || this.domNode.style.display!="none"){

            // [ LongH
            if( dijit._underlay ){
                dijit._underlay.layout();
            }
            if( !this.vt_fullscreen ) {
            //this._position();
            }
            else {
                // [ Resize the dialog fullscreen-sizes to fit the viewport
                var node = this.domNode;

                var pageWH = sd.util.getPageWH();
                var adjustSize = 3;
                var w = pageWH[2] - adjustSize;
                var h = pageWH[3] - adjustSize;

                node.style.width =  w;
                node.style.height = h;

                node.style.left = 0;
                node.style.top = 0;
                node.style.display = "block";
            // ]
            }
        // ] LongH
        }

    },

    vtSetTitle: function(title){
        this.titleNode.innerHTML = title;
    },
    
    _size: function(){
        // summary:
        // 		If necessary, shrink dialog contents so dialog fits in viewport
        // tags:
        //		private
        this._checkIfSingleChild();

        // If we resized the dialog contents earlier, reset them back to original size, so
        // that if the user later increases the viewport size, the dialog can display w/out a scrollbar.
        // Need to do this before the dojo.marginBox(this.domNode) call below.
        if(this._singleChild){
            if(this._singleChildOriginalStyle){
                this._singleChild.domNode.style.cssText = this._singleChildOriginalStyle;
            }
            delete this._singleChildOriginalStyle;
        }else{
            dojo.style(this.containerNode, {
                width:"auto",
                height:"auto"
            });
        }

        var mb = dojo.marginBox(this.domNode);
        var viewport = dijit.getViewport();
        if(mb.w >= viewport.w || mb.h >= viewport.h){
            // Reduce size of dialog contents so that dialog fits in viewport
            //[Datbt
            //            var w = Math.min(mb.w, Math.floor(viewport.w * 0.75)),
            //            h = Math.min(mb.h, Math.floor(viewport.h * 0.75));
            var w = mb.w ,
            h = mb.h;
            //]Datbt

            if(this._singleChild && this._singleChild.resize){
                this._singleChildOriginalStyle = this._singleChild.domNode.style.cssText;
                this._singleChild.resize({
                    w: w,
                    h: h
                });
            }else{
                dojo.style(this.containerNode, {
                    width: w + "px",
                    height: h + "px",
                    overflow: "auto",
                    position: "relative"	// workaround IE bug moving scrollbar or dragging dialog
                });
            }
        }else{
            if(this._singleChild && this._singleChild.resize){
                this._singleChild.resize();
            }
        }
    },

    destroy: function(){
        //[ Datbt
        dojo.forEach(this._modalconnects, dojo.disconnect);
        if(this.refocus && this.open){
            setTimeout(dojo.hitch(dijit,"focus",this._savedFocus), 25);
        }
        //        if (this.open){
        //            this.hide();
        //        }
        this.inherited(arguments);
    //] Datbt
    },
    //    [duongtb:for setting hidden/showwing options of grid
    _vtSaveOnClose:function(){
        var arrHidden = [];
        var arrShow = [];
        var gridId = null;
        dojo.query(".settings", this.id).forEach(function(node, index){
            if(node.checked==true){
                arrShow.push(index);
            }
            else{
                arrHidden.push(index);
            }
            if(gridId == null){//stupid
                gridId = node.id;
            }
        });
        try{
            gridId = gridId.split("_column_")[0];
            var grid = dijit.byId(gridId);
            for(var i =0; i < arrShow.length;i++){
                grid.layout.setColumnVisibility(arrShow[i], true);
            }
            for(var j = 0; j< arrHidden.length; j++){
                grid.layout.setColumnVisibility(arrHidden[j], false);
            }
        }catch(e){
            console.log(e.message)
        }
        this.hide();
    }
//    ]

})

/**
 * Author: LongH
 * Description: Thay đổi toàn bộ nội dung để sửa lỗi close
 * Date: 03/06/2011
 * FWVersion: 3.3
 **/
dojo.declare("vt.dialog.innerDialog", dijit.Dialog,{
    templateString: null,
    templatePath: dojo.moduleUrl("vt","templates/messageBox.html"),
    style:{
        width:"auto",
        height:'auto',
        overflow:"auto"
    },
    title:"",
    content:"",
    _yesCallback:undefined,
    _noCallback:undefined,
    _isConfirm:false,
    _param:null,

    info:function(){
        this._isConfirm = false;
        this._setAttr(arguments);

        dojo.attr(this.imgNode,"class","");
        dojo.addClass(this.imgNode,"iconMB info-icon");
        dojo.style(this.spanNoNode, {
            display:"none"
        });
        this.vtShow(this.title, this.content);
    },
    alert:function(){
        this._isConfirm = false;
        this._setAttr(arguments);
        dojo.attr(this.imgNode,"class","");
        dojo.addClass(this.imgNode,"iconMB alert-icon");
        dojo.style(this.spanNoNode, {
            display:"none"
        });
        this.vtShow(this.title,this.content);
    },
    error:function(){
        this._isConfirm = false;
        this._setAttr(arguments);
        dojo.attr(this.imgNode,"class","");
        dojo.addClass(this.imgNode,"iconMB error-icon");
        dojo.style(this.spanNoNode, {
            display:"none"
        });
        this.vtShow(this.title,this.content);
    },
    confirm:function(/* String message, Function callback, Object param */){

        try{
            this._isConfirm = true;
            this._setAttr(arguments);
            dojo.attr(this.imgNode,"class","");
            dojo.addClass(this.imgNode,"iconMB confirm-icon");
            dojo.style(this.spanNoNode, {
                display:"inline"
            });

            this.vtShow(this.title,this.content);
        }catch(e){
            alert(e.message);
        }

    //this._callback = callback;


    },
    _setAttr:function(args){
        this._yesCallback = undefined;
        this._noCallback = undefined;
        this.title = "Message";

        switch( args.length ) {
            case 0:
                this.content = "Message Here!";
                break;

            case 1:
                this.content = args[0];
                break;

            case 2:
                this.content = args[0];
                this.title = args[1];
                break;

            case 3:
                this.content = args[0];
                this.title = args[1];
                this._yesCallback = args[2];
                break;

            default:
                this.content = args[0];
                this.title = args[1];
                this._yesCallback = args[2];

                var arr = [];
                for(var i = 3; i < args.length; i++)
                {
                    arr.push(args[i]);
                }

                this._param = arr;
                break;
        }
    },
    vtSetTitle: function(title){

        if(title == null){
            title = "Message";
        }
        this.titleNode.innerHTML = title;
    },
    vtSetMessage:function(content){
        var  desiredLength = 150 ;
        var delimiter = "<br/>";
      
        var cellLength = content.length;
        if( desiredLength < cellLength ) {
            var counter=0;
            var output="";
            while( counter < cellLength ) {
                output += content.substr(counter,desiredLength) + delimiter;
                counter+= desiredLength;
            }
            content = output;
        }

        this.contentNode.innerHTML = content;
    },
    vtShow:function(title,content){
        this.vtSetTitle(title);
        this.vtSetMessage(content);

        try{
            //dojo.style(this.domNode,this.style);
            dojo.style(this.containerNode,this.style);
        }catch(e){
            alert(e.message);
        }
        dojo.body().appendChild(this.domNode);
        this.startup();
        this.show();
    },
    _onYes:function(){
        this.hide();
        if(this._yesCallback!=undefined){
            try{
                if(/\(*(,?)*\)/.test(this._yesCallback)){
                    //var func = this._yesCallback;
                    //func.apply(this, this._param);
                    this._yesCallback.call(this, this._param);
                }
            }catch(e){
                alert(e);
            }

        }
        

    },
    _onNo:function(){
        if(this._noCallback!=undefined){
            alert(this._noCallback);
        }
        this.hide();
    }
});