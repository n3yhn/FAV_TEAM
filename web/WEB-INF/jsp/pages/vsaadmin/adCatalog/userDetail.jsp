<%-- 
    Document   : userDetail
    Created on : Feb 9, 2010, 10:54:12 AM
    Author     : duongtb
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<style type="text/css">
     .style1
    {
        font-weight:normal;
        background-color: white;
        border-left:0px;
    }

    .tbl_header_captions
    {
        border:1px solid;
        border-style:dotted;
        /* border-color:#c8c7c7;*/
        border-color:#c8c7c7;
        border-right:0px;
        border-top:0px;
    }

    .tbl_fiedl
    {
        vertical-align:middle;
    }

    .sub_captions
    {
        font-size:12px;
        font-weight:normal;
        padding:7px 0px 7px 5px;
    }

</style>

<div style="border:1px solid;border-color:#c8c7c7;">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#c8c7c7">
        <tr>
            <td width="20%" class="style1 sub_captions tbl_header_captions" style="border-left:0px;"><sd:Property>usersForm.staffCode</sd:Property>:</td>
            <td class="style1 sub_captions tbl_header_captions">&nbsp;<s:property value="#request.staffCode.staffCode" />&nbsp;</td>
        </tr>
        <tr>
            <td width="20%" class="style1 sub_captions tbl_header_captions" style="border-left:0px;">Tỉnh thành:</td>
            <td class="style1 sub_captions tbl_header_captions">&nbsp;<s:property value="#request.location.locationName" />&nbsp;</td>
        </tr>
        <tr>
            <td width="20%" class="style1 sub_captions tbl_header_captions" style="border-left:0px;">
                <sd:Property>manager</sd:Property>:
            </td>
            <td class="style1 sub_captions tbl_header_captions">&nbsp;<s:property value="#request.manager.userName" />&nbsp;</td>
        </tr>
    </table>
            &nbsp;Phòng ban của <span style="color:red;"> <s:property value="#request.staffCode.userName" /> </span>:<br />
<table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#c8c7c7" width="100%">
	<tr>
            <td width="50%" align="center" class="style1 sub_captions tbl_header_captions" style="font-weight:bold;border-top:1px solid;border-color:#c8c7c7;border-bottom:0px;border-left:0px;border-style:dotted;">
                <sd:Property>departmentForm.code</sd:Property>
            </td>
            <td align="center" class="style1 sub_captions tbl_header_captions" style="font-weight:bold;border-top:1px solid;border-color:#c8c7c7;border-bottom:0px;border-style:dotted;">
                <sd:Property>departmentForm.deptName</sd:Property>
            </td>
	</tr>
        <s:if test="%{#request.lstDept.size>0 && #request.lstDept!=null}">
            <s:iterator value="#request.lstDept">
                <tr>
                    <td align="center" class="style1 sub_captions tbl_header_captions" style="border-top:1px solid;border-color:#c8c7c7;border-bottom:0px;border-style:dotted;">
                        <s:property value="code" />
                    </td>
                    <td align="center" class="style1 sub_captions tbl_header_captions" style="border-top:1px solid;border-color:#c8c7c7;border-bottom:0px;border-style:dotted;">
                        <s:property value="deptName" />
                    </td>
                </tr>
                </s:iterator>
        </s:if>
</table>
</div>

