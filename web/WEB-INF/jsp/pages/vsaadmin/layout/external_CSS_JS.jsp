<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>


<% request.setAttribute("contextPath", request.getContextPath());%>

<link rel="stylesheet" href="${contextPath}/share/css/inner-layout.css" charset="UTF-8" type="text/css" />

<link rel="stylesheet" href="${contextPath}/share/plug-in/dojo-release-1.4.0/dojo/resources/dojo.css" type="text/css" />
<link rel="stylesheet" href="${contextPath}/share/plug-in/dojo-release-1.4.0/dojox/grid/resources/Grid.css" type="text/css" />


<%--<link rel="stylesheet" href="${contextPath}/share/plug-in/dojo-release-1.4.0/dojox/grid/resources/tundraGrid.css" type="text/css" />--%>
<%--<link rel="stylesheet" href="${contextPath}/share/plug-in/dojo-release-1.4.0/dijit/themes/tundra/tundra.css" type="text/css" />--%>

<link rel="stylesheet" href="${contextPath}/share/plug-in/dojo-release-1.4.0/dojox/grid/resources/tadaloGrid.css" type="text/css" />
<link rel="stylesheet" href="${contextPath}/share/plug-in/dojo-release-1.4.0/dijit/themes/tadalo/tadalo.css" type="text/css" />

<link rel="stylesheet" href="${contextPath}/share/plug-in/dojo-release-1.4.0/vt/css/dataPicker.css" type="text/css" />
<link rel="stylesheet" href="${contextPath}/share/plug-in/dojo-release-1.4.0/vt/css/treePicker.css" type="text/css" />

<script type="text/javascript" src="${contextPath}/share/plug-in/dojo-release-1.4.0/dojo/dojo.js" djConfig="parseOnLoad:true" ></script>
<script type="text/javascript" src="${contextPath}/share/js/vt.js"></script>
<script type="text/javascript" src="${contextPath}/share/js/vt-widget.js"></script>
<script type="text/javascript" src="${contextPath}/share/js/validator.js"></script>
<script type="text/javascript" src="debug.js"></script>

<script type="text/javascript" src="${contextPath}/share/plug-in/dojo-release-1.4.0/dijit/form/_FormWidget.js"></script>

<script type="text/javascript">
    var g_contextPath = "${contextPath}";
</script>
<script>
    dojo.require( "dojo.parser" );
    dojo.require( "dijit._base.manager" );
    dojo.require("dijit.dijit"); // optimize: load dijit layer
    dojo.require("dijit.TitlePane");
    dojo.require("dojo.data.ItemFileWriteStore");
    sd.operator.timeout = null;
    sd.validator = new Validator();

    var vt = sd; // previous coding compatible
    var page = sd.page;
</script>
<style type="text/css">
    .headerGridClass{
        overflow:auto;
        clip:auto;
        width:95px;
        text-align:center;
        WORD-BREAK:BREAK-ALL;
    }

</style>