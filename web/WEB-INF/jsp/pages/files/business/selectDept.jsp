<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="../../common/commonJavascript.jsp" />
<jsp:include page="approve_CA_js.jsp" />
<script type="text/javascript">
    page.getNoDept = function(index) {
        return dijit.byId("deptGrid").currentRow + index + 1;
    }

    page.getIndexDept = function(index) {
        return index + 1;
    }

    page.formatSelect = function(inData) {
        var item = dijit.byId("deptGrid").getItem(inData - 1);
        var row = inData - 1;
        var url = "";
        if (item != null) {
            var url = "<a href='#'><img src='${contextPath}/share/images/record_view.png' width='17px' height='17px'\
                                        title='Chọn'\
                                        onClick='page.onSelectDept(" + row + ");' /></a>";
        }
        return url;

    }
</script>
<table width="100%" style="table-layout:fixed;">
    <tr>
        <td width="100%" valign="top" style="overflow: auto;">
            <div id="treeDiv" style="overflow: auto;">
                <sd:DataGrid id="deptGrid"
                             getDataUrl=""
                             rowSelector="0%"
                             style="width:auto;height:auto"
                             rowsPerPage="20"
                             serverPaging="true"
                             clientSort="false">
                    <sd:ColumnDataGrid key="category.No" get="page.getNoDept" width="5%"  styles="text-align:center;" />
                    <sd:ColumnDataGrid key="Chọn" formatter="page.formatSelect" get="page.getIndexDept"
                                       width="10%"  headerStyles="text-align:center;" cellStyles="text-align:center;"/>
                    <sd:ColumnDataGrid  key="Đơn vị" field="deptName"
                                        width="40%"  headerStyles="text-align:center;" />
                </sd:DataGrid>
            </div>
        </td>
    </tr>
</table>
<td width="70%">

    <input type="radio" id="Sign" name="SignCa" value="1" style="display:none"/>
    <%-- <sd:Label key="Ký CA"/> --%>
    <input type="radio" id="Unsign" name="SignCa" value="0" style="display:none"/>
    <%-- <sd:Label key="Không ký CA"/> --%> 
</td>
<sd:Dialog  id="dlgReturn1" height="auto" width="500px"
            key="" showFullscreenButton="true" showCloseButton="true"
            >
    <jsp:include page="dlgReturnOrApprove.jsp"></jsp:include>
</sd:Dialog>
<div id="Selectdeptdivhidden" style="visibility:hidden;"> 
    <form id="signCAForm" name="signCAForm" cssStyle="display:none" >   
        <table width="100%" cellspacing="5" cellpadding="0" border="0" class="viewTable" style="display: none">
            <tr style="display: none">
                <td style="padding-right: 2em;text-align: center"> 
                    <sd:TextBox key="" id="signCAForm.fileId" name="signCAForm.fileId" cssStyle="display:none" />
                    <sd:TextBox key="" id="signCAForm.contentSigned" name="signCAForm.contentSigned" cssStyle="display:none"/>
                    <sd:TextBox id="signCAForm.contentXml" key="" name="signCAForm.contentXml" cssStyle="display:none;"/>
                    <sd:TextBox name="validCAStatus1" key="" cssStyle="display:none;" id="validCAStatus1" value="${fn:escapeXml(validCAStatus1)}"/>
                </td>
            </tr>
        </table>
    </form>
</div>           
<div id="myapplet" style="visibility:hidden; height: 0px;"> 
</div>
<script type="text/javascript">
    var check;
    var item;
    page.afterSend = function(data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        resultMessage_show("resultCreateMessage", result[0], result[1], 5000);
        var result0 = result[0];
        if (result0 == "3") {
        } else {
            dijit.byId("selectDeptDlg").hide();
            page.search();
        }
    };


    page.showApproveDlgCA = function() {

        dijit.byId("btnOK1").domNode.style.display = "";
        dijit.byId("actionFormDlg1").setValue("approveDocumentCA");
        document.getElementById("isApproveCA1").value = "true";
        dijit.byId("dlgReturn1").vtSetTitle("Ký duyệt CA");
        dijit.byId("dlgReturn1").show();
    };
    page.afterShow = function(data) {

        var obj = dojo.fromJson(data);
        var result = obj.items;
        check = result[0];
//        document.getElementById("Sign").checked = false;
//        document.getElementById("Unsign").checked = false;
        
        page.showApproveDlgCA();
    };
    page.onSelectDept = function(row) {
        item = dijit.byId("deptGrid").getItem(row);
         document.getElementById("Unsign").checked = true;
        if (document.getElementById("Unsign").checked == false && document.getElementById("Sign").checked == false)
        {
            msg.alert("Chọn ký CA hoặc không ký");
        }
        if (document.getElementById("Unsign").checked == true) {

            msg.confirm('<sd:Property>Bạn có chắc chắn muốn gửi hồ sơ đến đơn vị "</sd:Property>' + item.deptName + '<sd:Property>" không?</sd:Property>', '<sd:Property>confirm.title1</sd:Property>', function() {
                            sd.connector.post("filesAction!onSelectFlow.do?" + token.getTokenParamString() + "&createForm.deptId=" + item.deptId + "&createForm.fileId=" + workingFileId, null, null, null, page.afterSend);
                        });
                    }
                    if (document.getElementById("Sign").checked == true)
                    {
                        sd.connector.post("filesAction!loadFile.do?createForm.fileId=" + workingFileId, null, null, null, page.afterShow);
                    }

                };

                page.onSelectDeptNew = function(row) {
               
                    document.getElementById("Sign").checked = true;
                    var item = dijit.byId("filesGrid").getItem(row);
                    sd.connector.post("filesAction!loadFile.do?createForm.fileId=" + item.fileId, null, null, null, page.afterShow);
                    
                };

</script>