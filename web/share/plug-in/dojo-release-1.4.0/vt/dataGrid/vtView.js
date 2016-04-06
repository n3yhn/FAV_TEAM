dojo.provide("vt.dataGrid.vtView");
dojo.require("dojox.grid._View");
dojo.declare("vt.dataGrid.vtView",[dojox.grid._View],{
    doscroll: function(inEvent){
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
    }
});