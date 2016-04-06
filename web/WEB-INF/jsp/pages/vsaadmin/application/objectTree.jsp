
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<strong>Mã ứng dụng:</strong> [<span style="color:red;">${fn:escapeXml(appCode)}</span>]
<sd:AjaxTree id="ajxTreeId" getChildrenUrl="object!getChildrenDataByNode.do" getTopLevelUrl="object!getData.do?appId=${fn:escapeXml(par)}" rootLabel="tree.root" onClick="page.onNodeClick" />
