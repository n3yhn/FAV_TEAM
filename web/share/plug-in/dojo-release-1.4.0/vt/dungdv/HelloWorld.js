dojo.provide("vt.dungdv.HelloWorld");

dojo.require("dijit._Widget");
dojo.require("dijit._Templated");

dojo.declare(
    "vt.dungdv.HelloWorld",
    [dijit._Widget, dijit._Templated],
    {
        greeting : "",
        templatePath: dojo.moduleUrl(
            "vt.dungdv",
            "templates/HelloWorld.html"
            ),
        postMixInProperties: function( ) {
            //this.greeting = "Hello World";
            console.log(this.greeting);
        }
    }
    );