<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>

<sd:TitlePane id="tp1" key="enterevent.tp1.title">
    <table width="100%">
        <tr>
            <td width="50%">
                <sd:TextBox id="tb" name="tb" key="enterevent.textbox"/>
            </td>
            <td width="50%">
                <sd:CheckBox id="cb" name="cb" key="enterevent.checkbox"/>
                &nbsp;
                &nbsp;
                &nbsp;
                &nbsp;
                <sd:RadioButton id="rb" name="rb" key="enterevent.radiobutton"/>
            </td>
        </tr>
        <tr>
            <td>
                <sd:SelectBox id="sb" name="sb" key="enterevent.selectbox">
            <option value="0">0</option>
            <option value="1">1</option>
        </sd:SelectBox>

    </td>
<%--    <td>
        <sd:Textarea id="ta" name="ta" key="enterevent.textarea"/>

    </td>--%>

</tr>
<tr>
    <td colspan="2" align="center">
        <sd:Button key="form.button.simple-button" onclick="alert('onclick event!');" />
    </td>
</tr>
</table>
</sd:TitlePane>

<script>
    sd.util.makeEnterDoAsTab();
</script>