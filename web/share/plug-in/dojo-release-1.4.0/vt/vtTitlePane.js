if(!dojo._hasResource["vt.vtTitlePane"]){
    dojo._hasResource["vt.vtTitlePane"] = true;
    dojo.provide("vt.vtTitlePane");
    dojo.require("dijit.TitlePane");
    dojo.declare("vt.vtTitlePane",[dijit.TitlePane],{
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
                                    if (widget.declaredClass == "vt.dataGrid.vtDataGrid"){
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
        // DungDV for fix bug: Grid don't display when toggle TitlePane
        toggle:function(){
            dojo.forEach([this._wipeIn,this._wipeOut],function(_6){
                if(_6&&_6.status()=="playing"){
                    _6.stop();
                }
            });
            var _7=this[this.open?"_wipeOut":"_wipeIn"];
            if(_7){
                _7.play();
            }else{
                this.hideNode.style.display=this.open?"":"none";
            }
            this.open=!this.open;
            dijit.setWaiState(this.containerNode,"hidden",this.open?"false":"true");
            dijit.setWaiState(this.focusNode,"pressed",this.open?"true":"false");
            if(this.open){
                var gridAry = this.findGridInside(this.domNode);
                for (var i = 0; i <gridAry.length; i++){
                    gridAry[i].grid.renderNoReload();
                }
                this._onShow();
            }else{
                this.onHide();
            }
            this._setCss();
        }
    });
}