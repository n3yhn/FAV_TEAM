/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.flow.BO;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author vtit_havm2
 */
@Embeddable
public class NodeToNodePK implements Serializable {
    @Basic(optional = false)
    @Column(name = "PREVIOUS_ID")
    private Long previousId;
    @Basic(optional = false)
    @Column(name = "NEXT_ID")
    private Long nextId;

    public NodeToNodePK() {
    }

    public NodeToNodePK(Long previousId, Long nextId) {
        this.previousId = previousId;
        this.nextId = nextId;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (long) previousId;
        hash += (long) nextId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NodeToNodePK)) {
            return false;
        }
        NodeToNodePK other = (NodeToNodePK) object;
        if (this.previousId != other.previousId) {
            return false;
        }
        if (this.nextId != other.nextId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.viettel.voffice.database.BO.NodeToNodePK[ previousId=" + previousId + ", nextId=" + nextId + " ]";
    }
    
}
