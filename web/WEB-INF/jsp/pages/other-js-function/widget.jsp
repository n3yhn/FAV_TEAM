<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>


<sd:TitlePane id="tp0" key="widget.tp0.title">
    <table width="100%" style="height: 22px;">
        <tr>
            <td align="center">
                <sd:TextBox id="form1.democontrol" mask="digit" required="false" name="form1.democontrol" key="widget.control" value="TextBox"/>
            </td>
        </tr>
        <tr>
            <td align="center">
                <input type="button" value="Required" onclick="sd.widget.setRequired('form1.democontrol', true);"/>
                <input type="button" value="Not Required" onclick="sd.widget.setRequired('form1.democontrol', false);"/>
            </td>
        </tr>
    </table>
</sd:TitlePane>

<sd:TitlePane id="tp1" key="widget.tp1.title">
    <input type="button" onclick='page.setDisplay();' value='Toggle Display' />
</sd:TitlePane>
<sd:TitlePane id="tp2" key="widget.tp2.title">
    <input type="button" onclick='page.setDisabled();' value='Toggle Disable' />
</sd:TitlePane>
<sd:TitlePane id="tp3" key="widget.tp3.title">
    <input type="button" onclick='page.setReadOnly();' value='Toggle Set ReadOnly' />
</sd:TitlePane>
<sd:TitlePane id="tp4" key="widget.tp4.title">
    <input type="button" onclick='page.hide();' value='hide' />
    <input type="button" onclick='page.show();' value='show' />

</sd:TitlePane>
<sd:TitlePane id="tp5" key="widget.tp5.title">
    <input type="button" onclick='page.disable();' value='disable' />
    <input type="button" onclick='page.enable();' value='enable' />

</sd:TitlePane>
<sd:TitlePane id="tp6" key="widget.tp6.title">
    <input type="button" onclick='page.lock();' value='lock' />
    <input type="button" onclick='page.unlock();' value='unlock' />    
</sd:TitlePane>

<script>
    var id = "form1.democontrol";

    page.setDisplay = function(){
        var nextState = ( dijit.byId(id).domNode.style.display == "none" ) ?true:false;
        sd.widget.setDisplay(id, nextState);
    }
    page.setDisabled = function(){
        var nextState = dijit.byId(id).attr("disabled") ?false:true;
        sd.widget.setDisabled(id, nextState);
    }
    page.setReadOnly = function(){
        var nextState = dijit.byId(id).attr("readOnly") ?false:true;
        sd.widget.setReadOnly(id, nextState);
    }
    page.hide = function(){
        sd.widget.hide(id);
    }
    page.show = function(){
        sd.widget.show(id);
    }
    
    page.disable = function(){
        sd.widget.disable(id);
    }
    page.enable = function(){
        sd.widget.enable(id);
    }

    page.lock = function(){
        sd.widget.lock(id);
    }
    page.unlock = function(){
        sd.widget.unlock(id);
    }
</script>