<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>

<script type="text/javascript">
    <s:if test="#session.vsaUserToken != null">
        <s:set name="menuTokenAll" value="#session.vsaUserToken.menuTokenUnion"/>
    </s:if>
    <s:if test="#session.vsaUserToken != null">
        myMenu = [null
        <s:iterator id="permission" value="#session.vsaUserToken.parentMenu">
                ,[null, '<s:property value="getText(#permission.objectName)"/>', null, '_self', '<s:property value="getText(#permission.objectName)"/>'
            <s:iterator id="childFunction" value="#permission.childObjects">
                <s:if test="#childFunction.childObjects.size()==0">
                    <s:set name="url" value="#childFunction.objectUrl" scope="request"/>

                            ,( "${url}" == "_" ) ? parent._cmSplit : [null, '&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="getText(#childFunction.objectName)"/>', 'javascript:gotoMenu( \"${url}\" );', '_self', '<s:property value="getText(#childFunction.objectName)"/>']

                </s:if>
                <s:else>
                            ,[null, '<s:property value="getText(#childFunction.objectName)"/>', null, '_self', '<s:property value="getText(#childFunction.objectName)"/>'
                    <s:iterator id="child2Function" value="#childFunction.childObjects">
                        <s:set name="url2" value="#child2Function.objectUrl" scope="request"/>
                                        ,( "${url}" == "_" ) ? parent._cmSplit : [null, '&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="getText(#child2Function.objectName)"/>', 'javascript:gotoMenu( \"${url2}\" );', '_self', '<s:property value="getText(#child2Function.objectName)"/>']
                    </s:iterator>
                                ]
                </s:else>
            </s:iterator>
                    ]
        </s:iterator>
            ];
    </s:if>
        parent.makeMenu( myMenu );
</script>
