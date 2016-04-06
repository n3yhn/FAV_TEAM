<%-- 
    Document   : ButtonAdd
    Created on : Dec 7, 2011, 3:32:13 PM
    Author     : gpdn_huannn
--%>

<%@tag description="Auto DatePicker" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="id"%>
<%@attribute name="name"%>
<%@attribute name="format"%>
<%@attribute name="required"%>
<%@attribute name="cssStyle"%>
<%@attribute name="cssClass"%>
<%@attribute name="key"%>
<%@attribute name="readonly"%>
<%@attribute name="maxlength"%>
<%@attribute name="value"%>
<%@attribute name="disabled"%>
<%@attribute name="onclick"%>
<%@attribute name="onchange"%>

<sd:DatePicker id="${id}"
               key="${key}"
               name="${name}"
               cssStyle="${cssStyle}"
               required="${required}"
               format="${format}"
               disabled="${disabled}"
               value="${value}"
               maxlength="${maxlength}"
               cssClass="${cssClass}"
               onchange="${onchange}"
               onclick="${onclick}"
               onblur="autoCompleteDate('${id}');"
               readonly="${readonly}"
               >
</sd:DatePicker>