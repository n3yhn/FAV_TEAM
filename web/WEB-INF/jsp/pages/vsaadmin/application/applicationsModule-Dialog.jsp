<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
    <table width="100%">
        <tr>
            <td width="30%">
                <sd:TitlePane id="appModuleTreePane" key="applicationsForm.moduleTree">
                    <div style="height:100%; width:100%; overflow:auto; background-color:#F7F7F7;">
                        <sd:AjaxTree id="ajxTreeId"
                                     getChildrenUrl="object!getChildrenDataByNode.do"
                                     getTopLevelUrl="object!getData.do?appId=${fn:escapeXml(par)}"
                                     rootLabel="applicationsForm.moduleTree"
                                     onClick="page.onNodeClick" />
                    </div>
                </sd:TitlePane>
            </td>
            <td width="70%">
                <jsp:include page="objectsCatalog-Window.jsp"/>
            </td>
        </tr>
    </table>