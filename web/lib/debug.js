
function debugScroller (_this,inTop){
    var i = 0;
}
function debugSetValueAttr(/*anything*/ newValue, /*Boolean, optional*/ priorityChange, thisObject){
    // summary:
    //		set the value of the widget.
    //		If a string is passed, then we set our value from looking it up.
    if(thisObject._loadingStore){
        // Our store is loading - so save our value, and we'll set it when
        // we're done
        thisObject._pendingValue = newValue;
        return;
    }
    var opts = thisObject.getOptions() || [];
    if(!dojo.isArray(newValue)){
        newValue = [newValue];
    }
    dojo.forEach(newValue, function(i, idx){
        if(!dojo.isObject(i)){
            i = i + "";
        }
        if(typeof i === "string"){
            newValue[idx] = dojo.filter(opts, function(node){
                return node.value === i;
            })[0]   ||  {value: "",label: ""};
        }
    }, thisObject);

    // Make sure some sane default is set
    newValue = dojo.filter(newValue, function(i){
        return i && i.value;
    });
    if(!thisObject.multiple && (!newValue[0] || !newValue[0].value) && opts.length){
        newValue[0] = opts[0];
    }
    dojo.forEach(opts, function(i){
        i.selected = dojo.some(newValue, function(v){
            return v.value === i.value;
        });
    });
    var val = dojo.map(newValue, function(i){
        return i.value;
    }),
    disp = dojo.map(newValue, function(i){
        return i.label;
    });

    thisObject.value = thisObject.multiple ? val : val[0];
    thisObject._setDisplay(thisObject.multiple ? disp : disp[0]);
    thisObject._updateSelection();
    thisObject._handleOnChange(thisObject.value, priorityChange);
}
