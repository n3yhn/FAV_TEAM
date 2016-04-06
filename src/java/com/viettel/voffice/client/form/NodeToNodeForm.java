/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.voffice.client.form;

import com.viettel.flow.BO.NodeToNode;
import java.io.Serializable;

/**
 *
 * @author vtit_havm2
 */
public class NodeToNodeForm implements Serializable {
    private Long previousId;
    private Long nextId;
    private String action;

    public NodeToNodeForm() {
    }

    public NodeToNodeForm(Long previousId, Long nextId) {
        this.previousId = previousId;
        this.nextId = nextId;
    }

    public NodeToNodeForm(NodeToNode entities) {
        this.previousId = entities.getNodeToNodePK().getPreviousId();
        this.nextId = entities.getNodeToNodePK().getNextId();
        this.action = entities.getAction();
    }

    public NodeToNode convertToEntity() {
        NodeToNode entity = new NodeToNode(previousId, nextId);
        entity.setAction(action);
        entity.setIsActive(1l);
        return entity;
    }

    public Long getPreviousId() {
        return previousId;
    }

    public void setPreviousId(Long previousId) {
        this.previousId = previousId;
    }

    public Long getNextId() {
        return nextId;
    }

    public void setNextId(Long nextId) {
        this.nextId = nextId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
    
}
