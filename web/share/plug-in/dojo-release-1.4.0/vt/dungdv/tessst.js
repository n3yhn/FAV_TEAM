dojo.provide("vt.dungdv.tessst");

dojo.declare("vt.dungdv.abc", null, {
    count: 100,
    constructor: function(args){
        dojo.safeMixin(this, args);
    }
});

dojo.declare("vt.dungdv.tessst", [vt.dungdv.abc], {
    divisor: 5,
    constructor: function(args){
        console.log('OtherTessst constructor called');
        this.total = this.count / this.divisor;
    }
});