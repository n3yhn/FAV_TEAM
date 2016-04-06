<%-- 
    Document   : tree
    Created on : Apr 6, 2011, 8:27:36 AM
    Author     : gpdn_trungnq7
--%>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<table class="treeContainer">
    <tr>
        <td class="padding-10">
                <sd:AjaxTree id="deptTreeId" getChildrenUrl="departmentAction!getChildrenDataByNode.do" getTopLevelUrl="departmentAction!getMyDeptRootTree.do" rootLabel="" onClick="page.onNodeClick"/>
        </td>
    </tr>
</table>