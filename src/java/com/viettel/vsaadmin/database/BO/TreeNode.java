/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.vsaadmin.database.BO;

import com.viettel.dojoTag.DojoAjaxTreeNode;

public class TreeNode extends DojoAjaxTreeNode {

    private String level;
    private String appId;
    private String treeName;
    private String description;
    private String rootDeptId;
    private String treeId;

    public String getLevel() {
        return this.level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getAppId() {
        return this.appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTreeName() {
        return this.treeName;
    }

    public void setTreeName(String treeName) {
        this.treeName = treeName;
    }

    public String getRootDeptId() {
        return this.rootDeptId;
    }

    public void setRootDeptId(String rootDeptId) {
        this.rootDeptId = rootDeptId;
    }

    public String getTreeId() {
        return this.treeId;
    }

    public void setTreeId(String treeId) {
        this.treeId = treeId;
    }
}
