<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List" %>
<%@page import="java.util.ArrayList" %>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<script type="text/javascript">
    //[ LongH says: menu-model testing purpose
    var myMenu;
    useMenuTest=false;
    if(useMenuTest) {
        myMenu = [
            [null, 'Control', null, 'target', '0',   // a folder item
                [null, 'Grid Display', 'javascript:doGoToMenu("grid.do", "0.0.0");', '_self', '0.0'] 
            ]
        ];
    }
    //] LongH says: menu-model testing purpose

    
    <s:if test="#session.vsaUserToken != null">
        <s:set name="menuTokenAll" value="#session.vsaUserToken.menuTokenUnion"/>
    </s:if>
    <s:if test="#session.vsaUserToken != null">
        <%
            int parentCounter = 0, childCounter = 0, grandChildCounter = 0;
            String sep = ".";
            request.setAttribute("menuItemKey", "");
        %>
            sd.operator.menuModel = [null
        <s:iterator id="permission" value="#session.vsaUserToken.parentMenu">
            <%
                request.setAttribute("menuItemKey", parentCounter + "");
                childCounter = 0;
            %>
                    ,[null, '<s:property value="getText(#permission.objectName)"/>', null, '_self', '${menuItemKey}'
            <s:iterator id="childFunction" value="#permission.childObjects">
                <%
                    request.setAttribute("menuItemKey", parentCounter + sep + childCounter);
                    grandChildCounter = 0;
                %>
                <s:if test=" #childFunction.childObjects == null ||#childFunction.childObjects.size()==0">
                    <s:set name="url" value="#childFunction.objectUrl" scope="request"/>
                                ,( "${url}" == "_" ) ? parent._cmSplit : [null, '<s:property value="getText(#childFunction.objectName)"/>', 'javascript:doGoToMenu( "${url}", "${menuItemKey}" );', '_self', '${menuItemKey}']
                </s:if>
                <s:else>
                            ,[null, '&nbsp;<s:property value="getText(#childFunction.objectName)"/>', null, '_self', '${menuItemKey}'
                    <s:iterator id="child2Function" value="#childFunction.childObjects">
                        <%
                            request.setAttribute("menuItemKey", parentCounter + sep + childCounter + sep + grandChildCounter);
                        %>
                        <s:set name="url2" value="#child2Function.objectUrl" scope="request"/>
                                        ,( "${url}" == "_" ) ? parent._cmSplit : [null, '<s:property value="getText(#child2Function.objectName)"/>', 'javascript:doGoToMenu( \"${url2}\", \"${menuItemKey}\" );', '_self', '${menuItemKey}']
                        <%
                            grandChildCounter++;
                        %>
                    </s:iterator>
                                ]
                </s:else>
                <%
                    childCounter++;
                %>
            </s:iterator>
                    ]
            <%
                parentCounter++;
            %>
        </s:iterator>
            ];
    </s:if>
        try {
            if(useMenuTest) {
                makeMenu(myMenu); // Testing purpose
            } else {
                makeMenu(sd.operator.menuModel);
            }
        } catch(e) {
            sd.log.error("In menubar.jsp::makeMenu: \n" + e.message);
        }
</script>