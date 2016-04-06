<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>

<form id="unitTreeFormOnDialogDept" name="unitTreeForm">
    <s:hidden name="unitTreeForm.id" id="unitTreeFormOnDialogDept.treeId" value="0"/>
    <s:hidden name="unitTreeForm.deptId" id="unitTreeFormOnDialogDept.deptId" value="0"/>
    <s:hidden name="unitTreeForm.parentId" id="unitTreeFormOnDialogDept.parentId" value="0"/>
    <table width ="100%">
        <tr>
            <td align="center">
                <sd:Button id="objectsFormOnDialog.btnInsert" key="" onclick="page.onAddDeptToTree()" >
                    <img src="share/images/icons/a7.png" height="20" width="20">
                    <span style="font-size:12px"><sd:Property>global.checkboxCol</sd:Property></span>
                </sd:Button>
            </td>
        </tr>
        <tr>
            <td>
                <sd:AjaxTree id="treeIdDept" rootLabel="usersForm.department"
                             getChildrenUrl="UserAction!getChildrenDataByNode.do"
                             getTopLevelUrl="UserAction!getData.do"
                             onClick="page.onDeptTreeClick" />
            </td>
        </tr>
    </table>
</form>
<script>
    page.onAddDeptToTree = function(){

        var afterCallback = function(){
           var dialog = dijit.byId("dialogIdDept");
           dialog.hide();
        }
        if (sd.$("unitTreeFormOnDialogDept.deptId").value == "0"){
            alert("Bạn chưa chọn phòng/ban cần thêm vào cây.");
        }
        else{
            sd.connector.post("UnitTreeAction!onAddDeptToTree.do", null, "unitTreeFormOnDialogDept", null, afterCallback);
        }
    }
    
    page.onDeptTreeClick = function(item, node){
        if (item.id == undefined){
            sd.$("unitTreeFormOnDialogDept.deptId").value = "0";
        }
        else{
            sd.$("unitTreeFormOnDialogDept.deptId").value = item.id;
        }
        dijit.byId("treeId").reloadSelectedNode();
    }
</script>