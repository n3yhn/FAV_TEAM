//[duongtb
dojo.provide("vt.dateTextBox.MonthTextBox");

dojo.require("dijit._Widget");
dojo.require("dijit._Templated");
dojo.require("dijit.form.NumberSpinner");
dojo.require("dijit.form._DateTimeTextBox");
dojo.require("dojo.cldr.supplemental");
dojo.require("dojo.date");
dojo.require("dojo.date.locale");

dojo.declare("vt.dateTextBox.MonthTextBox",dijit.form._DateTimeTextBox,{
    // summary:
    //		A validating, serializable, range-bound date text box with a popup calendar that contains only years

    // popupClass: String
    //  The popup widget to use. In this case, a calendar with just a Year view.
    popupClass: "vt.dateTextBox.MonthlyCalendar",

    selector: "date",
    
    monthNames:null,

    postMixInProperties: function(){
        this.inherited(arguments);
        if(this.constraints.datePattern == undefined){
            this.constraints.datePattern = 'MM/yyyy';
        }
        this.constraints.selector = this.selector;
                
    },
  
    _open: function(){

        // summary:
        //		opens the TimePicker, and sets the onValueSelected value
//        [begin
        //if(this.disabled || this.readOnly || !this.popupClass){
        if(this.disabled || !this.popupClass){
            return;
        }

        var textBox = this;

        if(!this._picker){
            var PopupProto = dojo.getObject(this.popupClass, false);
            this._picker = new PopupProto({
//                [duongtb
                monthNames: this.monthNames,
//                ]
                onValueSelected: function(value){
                    if(textBox._tabbingAway){
                        delete textBox._tabbingAway;
                    }else{
                        textBox.focus(); // focus the textbox before the popup closes to avoid reopening the popup
                    }
                    setTimeout(dojo.hitch(textBox, "_close"), 1); // allow focus time to take

                    // this will cause InlineEditBox and other handlers to do stuff so make sure it's last
                    dijit.form._DateTimeTextBox.superclass._setValueAttr.call(textBox, value, true);
                },
                id: this.id + "_popup",
                lang: textBox.lang,
                constraints: textBox.constraints,

                datePackage: textBox.datePackage,

                isDisabledDate: function(/*Date*/ date){
                    // summary:
                    // 	disables dates outside of the min/max of the _DateTimeTextBox
                    var compare = dojo.date.compare;
                    var constraints = textBox.constraints;
                    return constraints && (constraints.min && (compare(constraints.min, date, textBox._selector) > 0) ||
                        (constraints.max && compare(constraints.max, date, textBox._selector) < 0));
                }
            });
            this._picker.attr('value', this.attr('value') || new this.dateClassObj());
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
        dojo.marginBox(this._picker.domNode,{
            w:this.domNode.offsetWidth
        });
//        ]end core
        this._picker.onValueSelected = dojo.hitch(this, function(value){
            this.focus(); // focus the textbox before the popup closes to avoid reopening the popup
            setTimeout(dojo.hitch(this, "_close"), 1); // allow focus time to take
            dijit.form.TextBox.prototype._setValueAttr.call(this,value, true, value);
        });

    },
    _onKeyPress: function(/*Event*/ e){
        
        // summary:
        //		Handler for keypress events
        if(this._opened){
            this._close();
        }
        var p = this._picker, dk = dojo.keys;
        // Handle the key in the picker, if it has a handler.  If the handler
        // returns false, then don't handle any other keys.
        if(p && this._opened && p.handleKey){
            if(p.handleKey(e) === false){
                return;
            }
        }

        if(this._opened && e.charOrCode == dk.ESCAPE && !(e.shiftKey || e.ctrlKey || e.altKey || e.metaKey)){
            this._close();
            dojo.stopEvent(e);
        }else if(!this._opened && e.charOrCode == dk.DOWN_ARROW){
            this._open();
            dojo.stopEvent(e);
        }else if(e.charOrCode === dk.TAB){
            this._tabbingAway = true;
        }else if(this._opened && (e.keyChar || e.charOrCode === dk.BACKSPACE || e.charOrCode == dk.DELETE)){
            // Replace the element - but do it after a delay to allow for
            // filtering to occur
            setTimeout(dojo.hitch(this, function(){
                dijit.placeOnScreenAroundElement(p.domNode.parentNode, this.domNode, {
                    'BL':'TL',
                    'TL':'BL'
                }, p.orient ? dojo.hitch(p, "orient") : null);
            }), 1);
        }
    }
}
);
dojo.declare("vt.dateTextBox.MonthlyCalendar",[dijit._Widget, dijit._Templated],
{
    
    templateString:dojo.cache("vt","templates/CalendarMonth.html"),

    keyHandle: null,
    
    monthNames:null,
    defaultMonthNames:['1','2','3','4','5','6','7','8','9','10','11','12'],
    // value: Date
    //		The currently selected Date
    value: new Date(),

    // datePackage: String
    //		JavaScript namespace to find Calendar routines.  Uses Gregorian Calendar routines
    //		at dojo.date by default.
    datePackage: "dojo.date",

    // dayWidth: String
    //		How to represent the days of the week in the calendar header. See dojo.date.locale
    dayWidth: "narrow",

    // tabIndex: Integer
    //		Order fields are traversed when user hits the tab key
    tabIndex: "0",

    attributeMap: dojo.delegate(dijit._Widget.prototype.attributeMap, {
        tabIndex: "domNode"
    }),
 
    setValue: function(/*Date*/ value){
        // summary:
        //      Deprecated.   Used attr('value', ...) instead.
        // tags:
        //      deprecated
        dojo.deprecated("dijit.Calendar:setValue() is deprecated.  Use attr('value', ...) instead.", "", "2.0");
        this.attr('value', value);
    },

    _getValueAttr: function(){
        // summary:
        //		Support getter attr('value')
        var value = new this.dateClassObj(this.value);
        value.setHours(0, 0, 0, 0); // return midnight, local time for back-compat

        // If daylight savings pushes midnight to the previous date, fix the Date
        // object to point at 1am so it will represent the correct day. See #9366
        if(value.getDate() < this.value.getDate()){
            value = this.dateFuncObj.add(value, "hour", 1);
        }
        return value;
    },
    //called in dijit.form._DateTimeTextBox to display
    _setValueAttr: function(/*Date*/ value){
        // summary:
        //		Support setter attr("value", ...)
        // description:
        // 		Set the current date and update the UI.  If the date is disabled, the value will
        //		not change, but the display will change to the corresponding month.
        // tags:
        //      protected
        if(!this.value || this.dateFuncObj.compare(value, this.value)){
            value = new this.dateClassObj(value);
            value.setHours(1); // to avoid issues when DST shift occurs at midnight, see #8521, #9366
            this.displayMonth = new this.dateClassObj(value);//value:date
            if(!this.isDisabledDate(value, this.lang)){
                this.value = value;
                this.onChange(this.attr('value'));
            }
            dojo.attr(this.domNode, "aria-label",
                this.dateLocaleModule.format(value,
                {
                    selector:"date",
                    formatLength:"full"
                }));
            this._populateGrid();
        }
    },

    _setText: function(node, text){
        // summary:
        //		This just sets the content of node to the specified text.
        //		Can't do "node.innerHTML=text" because of an IE bug w/tables, see #3434.
        // tags:
        //      private
        while(node.firstChild){
            node.removeChild(node.firstChild);
        }
        node.appendChild(dojo.doc.createTextNode(text));
    },

    _populateGrid: function(){
        // tags:
        //      private
        var month = this.displayMonth;//value:Date
        month.setDate(1);
        var today = new this.dateClassObj();
        // Iterate through dates in the calendar and fill in 
        var _this = this;
        dojo.query(".dijitCalendarDateTemplate", this.domNode).forEach(function(template, i){
            var date = new this.dateClassObj(month),
            clazz = "dijitCalendarCurrent";//normal style
             
            date = new Date(month.getFullYear(), i, 1);
            if(today.getMonth() == date.getMonth()){
                clazz = "dijitCalendarCurrentDate " + clazz;
                if(this._isSelectedDate(today, this.lang)){
                    clazz = "dijitCalendarSelectedDate " + clazz;
                }
            }
            if(this._isSelectedDate(date, this.lang)){
                clazz = "dijitCalendarSelectedDate " + clazz;
            }
            if(this.monthNames == null){
                this.monthNames = this.defaultMonthNames;
                
            } else if (this.monthNames !== null && dojo.isArray(this.monthNames) && parseInt(this.monthNames[0]) !== 1) {
                template.style.fontWeight = 'normal'; 
            }
            template.className = clazz + "Month dijitCalendarDateTemplate";
            template.dijitDateValue = date.valueOf();
            var label = dojo.query(".dijitCalendarDateLabel", template)[0],
            //text = date.getDateLocalized ? date.getDateLocalized(this.lang) : date.getDate();
            text = this.monthNames[i];
            this._setText(label, text);
        }, this);
        // Fill in localized prev/current/next years
        this._setText(this.currentYearLabelNode,month.getFullYear());
//        var y = month.getFullYear() - 1;
//        var d = new this.dateClassObj();
//        dojo.forEach(["previous", "current", "next"], function(name){
//            d.setFullYear(y++);
//            this._setText(this[name+"YearLabelNode"],
//                this.dateLocaleModule.format(d, {
//                    selector:'year',
//                    locale:this.lang
//                    }));
//        }, this);

        // Set up repeating mouse behavior
        var typematic = function(nodeProp, dateProp, adj){
            //FIXME: leaks (collects) listeners if populateGrid is called multiple times.  Do this once?
            _this._connects.push(
                dijit.typematic.addMouseListener(_this[nodeProp], _this, function(count){
                    if(count >= 0){
                        _this._adjustDisplay(dateProp, adj);
                    }
                }, 0.8, 500)
                );
        };
        typematic("nextYearLabelNode", "year", 1);
        typematic("previousYearLabelNode", "year", -1);
    },

    constructor: function(/*Object*/args){
        var dateClass = (args.datePackage && (args.datePackage != "dojo.date"))? args.datePackage + ".Date" : "Date";
        this.dateClassObj = dojo.getObject(dateClass, false);
        this.datePackage = args.datePackage || this.datePackage;
        this.dateFuncObj = dojo.getObject(this.datePackage, false);
        this.dateLocaleModule = dojo.getObject(this.datePackage + ".locale", false);
    },

    postMixInProperties: function(){
        // parser.instantiate sometimes passes in NaN for IE.  Use default value in prototype instead.
        if(isNaN(this.value)){
            delete this.value;
        }
        this.inherited(arguments);
    },

    postCreate: function(){
        this.inherited(arguments);
        dojo.setSelectable(this.domNode, false);

        var cloneClass = dojo.hitch(this, function(clazz, n){
            var template = dojo.query(clazz, this.domNode)[0];
            for(var i=0; i<n; i++){
                template.parentNode.appendChild(template.cloneNode(true));
            }
        });

        cloneClass(".dijitCalendarDateTemplate", 2);

        // now make 4 week rows
        cloneClass(".dijitCalendarWeekTemplate", 3);

        var value = this.value;
        this.value = null;
        this.attr('value', new this.dateClassObj(value));
    },

    _adjustDisplay: function(/*String*/ part, /*int*/ amount){
        // summary:
        //      Moves calendar forwards or backwards by months or years
        // part:
        //      "month" or "year"
        // amount:
        //      Number of months or years
        // tags:
        //      private
        this.displayMonth = this.dateFuncObj.add(this.displayMonth, part, amount);
        this._populateGrid();
    },

    _onMonthSelect: function(/*Event*/ evt){
        // summary:
        //      Handler for when user selects a month from a list
        // tags:
        //      protected
        this._onMonthToggle(evt);
        this.displayMonth.setMonth(dojo.attr(evt.target, "month"));
        this._populateGrid();
    },

    _onDayClick: function(/*Event*/ evt){
        // summary:
        //      Handler for day clicks, selects the date if appropriate
        // tags:
        //      protected
        dojo.stopEvent(evt);
        for(var node = evt.target; node && !node.dijitDateValue; node = node.parentNode);
        if(node && !dojo.hasClass(node, "dijitCalendarDisabledDate")){
            this.attr('value', node.dijitDateValue);
            this.onValueSelected(this.attr('value'));
        }
    },

    _onDayMouseOver: function(/*Event*/ evt){
        // summary:
        //      Handler for mouse over events on days, sets up hovered style
        // tags:
        //      protected
        var node = evt.target;
        if(node && (node.dijitDateValue || node == this.previousYearLabelNode || node == this.nextYearLabelNode) ){
            dojo.addClass(node, "dijitCalendarHoveredDate");
            this._currentNode = node;
        }
    },

    _onDayMouseOut: function(/*Event*/ evt){
        // summary:
        //      Handler for mouse out events on days, clears hovered style
        // tags:
        //      protected
        if(!this._currentNode){
            return;
        }
        for(var node = evt.relatedTarget; node;){
            if(node == this._currentNode){
                return;
            }
            try{
                node = node.parentNode;
            }catch(x){
                node = null;
            }
        }
        dojo.removeClass(this._currentNode, "dijitCalendarHoveredDate");
        this._currentNode = null;
    },

    //TODO: use typematic
    //TODO: skip disabled dates without ending up in a loop
    //TODO: could optimize by avoiding populate grid when month does not change
    
    onValueSelected: function(/*Date*/ date){
    // summary:
    //		Notification that a date cell was selected.  It may be the same as the previous value.
    // description:
    //      Used by `dijit.form._DateTimeTextBox` (and thus `dijit.form.DateTextBox`)
    //      to get notification when the user has clicked a date.
    // tags:
    //      protected
    },

    onChange: function(/*Date*/ date){
    // summary:
    //		Called only when the selected date has changed
    },

    _isSelectedDate: function(/*Date*/ dateObject, /*String?*/ locale){
        // summary:
        //		Extension point so developers can subclass Calendar to
        //		support multiple (concurrently) selected dates
        // tags:
        //		protected extension
        return !this.dateFuncObj.compare(dateObject, this.value, "date")
    },

    isDisabledDate: function(/*Date*/ dateObject, /*String?*/ locale){
    // summary:
    //		May be overridden to disable certain dates in the calendar e.g. `isDisabledDate=dojo.date.locale.isWeekend`
    // tags:
    //      extension
    /*=====
			return false; // Boolean
=====*/
    }
}
);

//]


