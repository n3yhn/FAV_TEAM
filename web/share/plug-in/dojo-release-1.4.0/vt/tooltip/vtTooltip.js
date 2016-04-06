dojo.provide("vt.tooltip.vtTooltip");
dojo.provide("vt.vtTooltip");
dojo.declare("dijit.vtMasterTooltip",[dijit._MasterTooltip],{
    hide:function() {
        if (this._onDeck) {
            this._onDeck=null;
        } else {
            if (this.aroundNode) {
                this.fadeIn.stop();
                this.isShowingNow=false;
                this.aroundNode=null;
                this.fadeOut.play();
            } else {
        }
        }
    }
});

dijit.vtShowTooltip = function (/*String*/ innerHTML, /*DomNode*/ aroundNode, /*String[]?*/ position) {
    if (!dijit.vtMasterTT) {
        dijit.vtMasterTT = new dijit.vtMasterTooltip();
    }
    return dijit.vtMasterTT.show(innerHTML,aroundNode,position);
};
dijit.vtHideTooltip = function() {
    if (!dijit.vtMasterTT) {
        dijit.vtMasterTT = new dijit.vtMasterTooltip();
    }
    return dijit.vtMasterTT.hide();
};