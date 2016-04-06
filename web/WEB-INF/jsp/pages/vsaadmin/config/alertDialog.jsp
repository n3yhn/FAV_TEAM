<%-- 
    Document   : alertDialog
    Created on : Jan 18, 2010, 10:39:10 AM
    Author     : ThienKQ
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<script type="text/javascript">
    dojo.require("dijit.form.Button");
    dojo.require("dijit.Dialog");
    var connections = [];

    page.alert = function(txtTitle,txtContent,type){
        alert(txtContent);
    }

    page.dialogAlert = function(txtContent){
        alert(txtContent);
    }
    // function()
    page.confirmAsJS = function(txtContent,callback){
        if(confirm(txtContent)){
            if(typeof(callback)!="undefined"){
                try{
                    callback.call(this);
                }catch(err){
                }
            }
        }

    }
            
    page.onEnter = function(id,callback){
        try{
            dojo.connect(dojo.byId(id), 'onkeypress', function (evt) {
                key = evt.keyCode;
                if(key == dojo.keys.ENTER) {
                    try{
                        callback.call(this);
                    }catch(e){
                        alert(e.message);
                    }
                }
            });
        }catch(err){
            alert(err.message);
        }
    }
    page.onCustomEnter = function(id,callback){
        try{
            for(var i in connections){
                dojo.disconnect(connections[i]);
            }
            var el = dojo.connect(dojo.byId(id), 'onkeypress', function (evt) {
                key = evt.keyCode;
                if(key == dojo.keys.ENTER) {
                    try{
                        callback.call(this);
                    }catch(e){
                        alert(e.message);
                    }
                }
            });
            connections.push(el);
        }catch(err){
            alert(err.message);
        }
    }
</script>
