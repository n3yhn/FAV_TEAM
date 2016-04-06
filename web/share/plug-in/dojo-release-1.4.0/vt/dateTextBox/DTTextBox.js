dojo.provide("vt.dateTextBox.DTTextBox");

dojo.require("dijit._TimePicker");
dojo.require("vt.dateTextBox._DateTimeTextBox");

dojo.require("vt.dateTextBox.MyDateTimeWidget");

/*=====
dojo.declare(
	"dijit.form.TimeTextBox.__Constraints",
	[dijit.form._DateTimeTextBox.__Constraints, dijit._TimePicker.__Constraints]
);
=====*/

dojo.declare(
    "vt.dateTextBox.DTTextBox",
    dijit.form._DateTimeTextBox,
    {
        // summary:
        //		A validating, serializable, range-bound time text box with a drop down time picker

        baseClass: "dijitTextBox dijitDateTextBox",
        popupClass: "vt.dateTextBox.MyDateTimeWidget",
		

        /*=====
		// constraints: dijit.form.TimeTextBox.__Constraints
		constraints:{},
=====*/

        // value: Date
        //		The value of this widget as a JavaScript Date object, with only hours/minutes/seconds specified.
        //
        //		Example:
        // |	new dijit.form.TimeTextBox({value: dojo.date.local.fromISOString("T:12:59:59")})
        //
        //		When passed to the parser in markup, must be specified according to locale-independent
        //		`dojo.date.stamp.fromISOString` format.
        //
        //		Example:
        // |	<input dojotype='dijit.form.TimeTextBox' value='T12:34:00'>
        value: new Date(""),
        
        convertToString : function(pattern){
            if (!this.value.valueOf())
                return '';

            var d = this.value;

            return pattern.replace(/(yyyy|MM|dd|HH|mm|ss)/gi,
                function($1)
                {
                    switch ($1)
                    {
                        case 'yyyy': return d.getFullYear();
                        case 'MM':
                            var m = d.getMonth() + 1 ;
                            return (m >=10)? m:'0' + m;
                        case 'dd':
                            var day = d.getDate();
                            return (day >= 10)?day:'0'+ day;
                        case 'HH':
                            var h =(d.getHours() % 12) ? d.getHours() % 12 : 12;
                            return (h>=10)?h:'0'+ h;
                        case 'mm':
                            var n = d.getMinutes();
                            return (n>=10)?n:'0' + n;
                        case 'ss':
                            var s = d.getSeconds();
                            return (s>=10)?s:'0' + s;
                    }
                }
                );
        }
        
    }
    );
