/* 
 * Copyright (C) 2010 Viettel Telecom. All rights reserved. 
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.vsaadmin.database.BO;

import com.viettel.database.BO.BasicBO;

/** 
 * @author 
 * @since Thu Jan 27 08:29:54 ICT 2011
 * @version 1.0 
 */
public class UnitTreeNodeBO extends BasicBO {

    private Long nodeId;
    private String nodeName;
    private Long nodeType;
    private Long itemId;
    private Long parentId;
    private Long id;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNodeId() {
        return nodeId;
    }

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public Long getNodeType() {
        return nodeType;
    }

    public void setNodeType(Long nodeType) {
        this.nodeType = nodeType;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public UnitTreeNodeBO() {
    }

    public UnitTreeNodeBO(Long nodeId) {
        this.nodeId = nodeId;
    }
}
