<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib  prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>

<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<s:i18n name="com/viettel/config/Language">
    <tiles:insertAttribute name="body"/>
</s:i18n>
<script type="text/javascript">
    setOnFocus();
</script>