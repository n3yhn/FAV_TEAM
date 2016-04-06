/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.voffice.client.form;

import com.viettel.flow.BO.Node;
import java.io.Serializable;


/**
 *
 * @author vtit_havm2
 */
public class NodeForm implements Serializable {
    private Long id;
    private Long nodeId;
    private String nodeName;
    private Long x;
    private Long y;
    private Long isActive;
    private Long flowId;
    private Long status;

    public NodeForm() {
    }

    public NodeForm(Long nodeId) {
        this.nodeId = nodeId;
    }

    public NodeForm(Long nodeId, String nodeName, Long flowId) {
        this.nodeId = nodeId;
        this.nodeName = nodeName;
        this.flowId = flowId;
    }
    
    public NodeForm(Node entity){
        if(entity == null){
            return;
        }
        this.nodeId = entity.getNodeId();
        this.flowId = entity.getFlowId();
        this.nodeName = entity.getNodeName();
        this.status = entity.getStatus();
        this.x = entity.getXpoint();
        this.y = entity.getYpoint();
    }
    
    public Node convertToEntity(){
        Node entity = new Node();
        entity.setNodeId(nodeId);
        entity.setNodeName(nodeName);
        entity.setStatus(status);
        entity.setFlowId(flowId);
        entity.setXpoint(x);
        entity.setYpoint(y);
        entity.setIsActive(1L);
        return entity;
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

    public Long getX() {
        return x;
    }

    public void setX(Long x) {
        this.x = x;
    }

    public Long getY() {
        return y;
    }

    public void setY(Long y) {
        this.y = y;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIsActive() {
        return isActive;
    }

    public void setIsActive(Long isActive) {
        this.isActive = isActive;
    }

    public Long getFlowId() {
        return flowId;
    }

    public void setFlowId(Long flowId) {
        this.flowId = flowId;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }
}
