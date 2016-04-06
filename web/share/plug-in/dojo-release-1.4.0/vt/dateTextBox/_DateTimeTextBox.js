dojo.provide("vt.dateTextBox._DateTimeTextBox");
dojo.require("dijit.form._DateTimeTextBox");

/*=====
dojo.declare(
	"dijit.form._DateTimeTextBox.__Constraints",
	[dijit.form.RangeBoundTextBox.__Constraints, dojo.date.locale.__FormatOptions]
);
=====*/

dojo.declare(
    "vt.dateTextBox._DateTimeTextBox",
    dijit.form._DateTimeTextBox,
    {
        //[ Datbt
        // tao bien rieng de da ngon ngu
        monthNames:[],
        dayNames:["S","M","T","W","T","F","S"],
        //] Datbt

        _open: function(){
            // summary:
            //		opens the TimePicker, and sets the onValueSelected value

            if(this.disabled || this.readOnly || !this.popupClass){ //Linhntn: set lai thuoc tinh readOnly thi khong open popup
                       return;
            }

            var textBox = this;

            if(!this._picker){
                var PopupProto=dojo.getObject(this.popupClass, false);
                this._picker = new PopupProto({
                    //[Datbt
                    dayNames:this.dayNames,
                    monthNames: this.monthNames,
                    //]Datbt
                    onValueSelected: function(value){
                        if(textBox._tabbingAway){
                            delete textBox._tabbingAway;
                        }else{
                            textBox.focus(); // focus the textbox before the popup closes to avoid reopening the popup
                        }
                        //[Datbt
                        setTimeout(dojo.hitch(textBox, "_close"), 1); // allow focus time to take
                        //[Datbt
                        // this will cause InlineEditBox and other handlers to do stuff so make sure it's last
                        dijit.form._DateTimeTextBox.superclass._setValueAttr.call(textBox, value, true);
                    },
                    lang: textBox.lang,
                    constraints: textBox.constraints,
                    isDisabledDate: function(/*Date*/ date){
                        // summary:
                        // 	disables dates outside of the min/max of the _DateTimeTextBox
                        var compare = dojo.date.compare;
                        var constraints = textBox.constraints;
                        return constraints && (constraints.min && (compare(constraints.min, date, "date") > 0) ||
                            (constraints.max && compare(constraints.max, date, "date") < 0));
                    }
                });
                
                this._picker.attr('value', this.attr('value') || new Date());
            }
            if(!this._opened){
                dijit.popup.open({
                    parent: this,
                    popup: this._picker,
                    around: this.domNode,
                    onCancel: dojo.hitch(this, this._close),
                    onClose: function(){
                        textBox._opened=false;
                    }
                });
                this._opened=true;
            }
            // [Datbt
            if (!this._picker.keyHandle){
                this._picker.keyHandle = dojo.connect(dojo.doc.documentElement, "onkeypress", this._picker, "_onDayKeyPress");
            }
            // ]Datbt
			
            dojo.marginBox(this._picker.domNode,{
                w:this.domNode.offsetWidth
            });
        },

        _close: function(){
            // [ Datbt, mod by LongH @06June2011
            if (this._picker && this._picker.keyHandle){
                dojo.disconnect(this._picker.keyHandle);
                this._picker.keyHandle = null;
            }
            // ] Datbt
            if(this._opened){
                dijit.popup.close(this._picker);
                this._opened=false;
            }
        }
    }
    );
