dojo.provide("vt.dataGrid.cells._base");

dojo.require("dojox.grid.cells._base");
dojo.require("dojox.grid.cells.dijit");
/*
 * Linhntn
 * Sua cac ham setValue, getWidgetProps cho loi hien thi 1/1/1970 tren datepicker grid
 * 08/07/2011
 * version 3.3
 */


(function(){
	
    var dgc = dojox.grid.cells;

    dojo.declare("dojox.grid.cells.vtDateTextBox",dgc._Widget,{
        widgetClass:dijit.form.DateTextBox,
        setValue:function(inRowIndex,inDatum){
            if(this.widget){
                var tempDate;
                //    [duongtb
                try{
                    if((/[0-9]T[0-9]/).test(inDatum)){
                        //_inDatum = inDatum.replace(/T/i,' ');
                        var temp = inDatum.split("T");
                        var inDatum_1 = temp[0];
                        var inDatum_2 = temp[1];//[hh:mm:ss]
                        if(inDatum_1.indexOf('-')!=-1){
                            var tmp = inDatum_1.split("-");
                            // MM/dd/yyyy for constructing
                            var date_string = tmp[1]+"/"+tmp[2]+"/"+tmp[0] + " " + inDatum_2;
                            this.widget.attr("value",new Date(date_string));
                        }
                    }
                    else{

                        if (inDatum != null) {
                            if (inDatum != " ") tempDate = new Date(inDatum);
                            else tempDate = "";
                        }
                        else {
                            tempDate="";
                        }
                        this.widget.attr("value",tempDate);
                    }
                }catch(err){
                    console.log(err.message);
                }
            //    ]end
            }else{
        //this.inherited(arguments);
        }
        },
        getWidgetProps:function(inDatum){
            var tempDate;
            if((/[0-9]T[0-9]/).test(inDatum)){
                var temp = inDatum.split("T");
                var inDatum_1 = temp[0];
                var inDatum_2 = temp[1];//[hh:mm:ss]
                if(inDatum_1.indexOf('-')!=-1){
                    var tmp = inDatum_1.split("-");
                    // MM/dd/yyyy for constructing
                    inDatum = tmp[1]+"/"+tmp[2]+"/"+tmp[0] + " " + inDatum_2;
                    if (inDatum != null) {
                        if (inDatum != " ") tempDate = new Date(inDatum);
                        else tempDate = "";
                    }
                    else {
                        tempDate="";
                    }
                }
            }
            else{
                if (inDatum != null) {
                    if (inDatum != " ") tempDate = new Date(inDatum);
                    else tempDate = "";
                }
                else {
                    tempDate="";
                }
            }
            return dojo.mixin(this.inherited(arguments),{
                value:tempDate
            });
        }
    });
    dgc.vtDateTextBox.markupFactory=function(node,cell){
    //        dgc._Widget.markupFactory(node,cell);
    };
    /**
     * Author: DungDV
     * Description: Comment lai ham needFormatNode de sua loi alwaysEditing, fix post modify data on IE
     * Date: 25/06/2011
     * FWVersion: 3.3
     **/
    dojo.declare("dojox.grid.cells.vtSelect", dgc.Select, {
        formatEditing: function(inDatum, inRowIndex){
            //this.needFormatNode(inDatum, inRowIndex);
            var funcName = "select_" + this.grid.id + "_inRowIndex_" + inRowIndex;//onchange=\"page." + funcName + "();\"
            var h=["<select onfocus=\"javascript:page.enableGridMouseEvent=false;\" onblur=\"javascript:page.enableGridMouseEvent=true;\" " + (dojo.isIE ? "onchange=\"page." + funcName + "(this);\"" : "") + " >"];
            for (var i=0, o, v; ((o=this.options[i]) !== undefined)&&((v=this.values[i]) !== undefined); i++){
                h.push("<option", (inDatum==v ? ' selected' : ''), ' value="' + v + '"', ">", o, "</option>");
            }
            h.push('</select>');

            if (dojo.isIE) {
                var self = this;
                page[funcName] = function(select) {
                    var e = {};
                    if (self.grid != null && self.grid.focus != null) {
                        e.rowIndex = self.grid.focus.info.rowIndex;
                        e.cell = {
                            field : self.grid.focus.info.cell.field
                        };
                        self.dochange2(self.grid, select, e.rowIndex, e.cell.field);
                    }
                }
            }

            return h.join('');
        },

        dochange2:function(grid, select, rowIndex, field){
            var val = select.options[select.selectedIndex].value;
            //                  var item = this.grid.getItem(e.rowIndex);
            //                  var msg = e.cell.get ? e.cell.get(e.rowIndex, item) : (e.cell.value || e.cell.defaultValue);
            try{
                grid.doApplyCellEdit(val,rowIndex,field);
            }catch(ex){
                console.log(ex.message);
                grid.edit.applyCellEdit(val,select,rowIndex);
            }

        }
        
    });
    dgc.vtSelect.markupFactory = function(node, cell){
        dgc.Cell.markupFactory(node, cell);
        var d=dojo;
        var options = d.trim(d.attr(node, "options")||"");
        if(options){
            var o = options.split(',');
            if(o[0] != options){
                cell.options = o;
            }
        }
        var values = d.trim(d.attr(node, "values")||"");
        if(values){
            var v = values.split(',');
            if(v[0] != values){
                cell.values = v;
            }
        }
    };
})();
