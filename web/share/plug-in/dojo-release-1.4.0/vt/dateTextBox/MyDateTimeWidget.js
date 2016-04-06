dojo.provide("vt.dateTextBox.MyDateTimeWidget");

dojo.require("dojo.fx");
dojo.require("dojo.cldr.supplemental");
dojo.require("dojo.date");
dojo.require("dojo.date.locale");
dojo.require("dijit._Widget");
dojo.require("dijit._Calendar");
dojo.require("dijit.form.TimeTextBox");
dojo.require("dijit._Templated");
dojo.require("dijit.Calendar");

dojo.declare("vt.dateTextBox.MyDateTimeWidget", [ dijit._Widget, dijit._Templated ],

{

        widgetsInTemplate : true,

        templateString : dojo.cache("vt", "templates/DateTimeWidget.html"),
        target : "",
        
        time : new Date(),
        
        constructor : function() {

        },
        postMixInProperties : function() {

        },
        postCreate : function() {

            this.inherited(arguments);

            // set default value for timetextbox
            this.myTime.attr('value', new Date());
           
            var cal = this.myCalendar;
            dojo.connect(cal, "onValueSelected", this, "showDate");

            var calRe = this.calendarReport;
            var str = dojo.date.locale.format(new Date(), {
                datePattern : 'dd/MM/yyyy',
                selector : 'date'
            })

            calRe.innerHTML = str;

        },

        onParBlur : function(){
          
        },

        showDate : function(date){
            var cal = this.calendarReport;

            var string = dojo.date.locale.format(date, {
                datePattern : 'dd/MM/yyyy',
                selector : 'date'
            })

            cal.innerHTML = string;
        },

        getDateTime : function() {
            var dateVal=this.myCalendar.value;
            var timeVal=this.myTime.value;

            if(timeVal == null){
                timeVal = new Date();
            }

            var datetime=new Date(dateVal.getTime());

            datetime.setHours(timeVal.getHours());
            datetime.setMinutes(timeVal.getMinutes());
            datetime.setSeconds(timeVal.getSeconds());

            return datetime;
        },
        setDateTime : function(e) {
        // this.myTextBox.value = e;
        },

        onValueSelected: function(/*Date*/ date){
        // summary:
        //		Notification that a time was selected.  It may be the same as the previous value.
        // description:
        //      Used by `dijit.form._DateTimeTextBox` (and thus `dijit.form.TimeTextBox`)
        //      to get notification when the user has clicked a time.
        // tags:
        //      protected
        },

        isDisabledDate: function(/*Date*/ dateObject, /*String?*/ locale){
            // summary:
            //		May be overridden to disable certain dates in the TimePicker e.g. `isDisabledDate=dojo.date.locale.isWeekend`
            // type:
            //		extension
            return false; // Boolean
        },

        onSelect : function(e) {
             
            var dateVal=this.myCalendar.value;
            var timeVal=this.myTime.value;


            if(timeVal == null){
                timeVal = new Date();
            }
            
            var datetime = new Date(dateVal.getTime());

            datetime.setHours(timeVal.getHours());
            datetime.setMinutes(timeVal.getMinutes());
            datetime.setSeconds(timeVal.getSeconds());

            this.onValueSelected(datetime);
        }
    });
