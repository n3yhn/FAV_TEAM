dojo.provide("vt.dateTextBox.DateTextBox");

dojo.require("vt.dateTextBox._Calendar");
dojo.require("vt.dateTextBox._DateTimeTextBox");

dojo.declare(
	"vt.dateTextBox.DateTextBox",
	vt.dateTextBox._DateTimeTextBox,
	{
		// summary:
		//		A validating, serializable, range-bound date text box with a drop down calendar

		baseClass: "dijitTextBox dijitDateTextBox",
		popupClass: "vt.dateTextBox._Calendar",
		_selector: "date",
                
		//	value: Date
		//		The value of this widget as a JavaScript Date object, with only year/month/day specified.
		//
		//		Example:
		// |	new dijit.form.DateTextBox({value: new Date(2009,1,20)})
		//
		//		When passed to the parser in markup, must be specified according to locale-independent
		//		`dojo.date.stamp.fromISOString` format.
		//
		//		Example:
		// |	<input dojotype='dijit.form.DateTextBox' value='2009-01-20'>
		value: new Date("")	// value.toString()="NaN"

	}
);
