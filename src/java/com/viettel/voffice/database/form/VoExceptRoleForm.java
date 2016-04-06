/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.voffice.database.form;

/**
 *
 * @author HanPT1
 */
public class VoExceptRoleForm {
    
    private Long exceptRoleId;
    private Long roleId;
    private String role;
    private Long groupId;
    private String groupName;
    private Long canView;
    private Long canViewChild;
    private Long flowType;

    public Long getCanView() {
        return canView;
    }

    public void setCanView(Long canView) {
        this.canView = canView;
    }

    public Long getCanViewChild() {
        return canViewChild;
    }

    public void setCanViewChild(Long canViewChild) {
        this.canViewChild = canViewChild;
    }

    public Long getFlowType() {
        return flowType;
    }

    public void setFlowType(Long flowType) {
        this.flowType = flowType;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getExceptRoleId() {
        return exceptRoleId;
    }

    public void setExceptRoleId(Long exceptRoleId) {
        this.exceptRoleId = exceptRoleId;
    }   
    
}
