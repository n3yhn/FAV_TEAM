/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.BO;

import com.viettel.common.util.StringUtils;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author GPCP_BINHNT53
 */
@Entity
@Table(name = "MESSAGE_GROUP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MessageGroup.findAll", query = "SELECT m FROM MessageGroup m"),
    @NamedQuery(name = "MessageGroup.findByMesssageGroupId", query = "SELECT m FROM MessageGroup m WHERE m.messsageGroupId = :messsageGroupId"),
    @NamedQuery(name = "MessageGroup.findByMesssageGroupName", query = "SELECT m FROM MessageGroup m WHERE m.messsageGroupName = :messsageGroupName"),
    @NamedQuery(name = "MessageGroup.findByIsActive", query = "SELECT m FROM MessageGroup m WHERE m.isActive = :isActive"),
    @NamedQuery(name = "MessageGroup.findByMesssageGroupCode", query = "SELECT m FROM MessageGroup m WHERE m.messsageGroupCode = :messsageGroupCode"),
    @NamedQuery(name = "MessageGroup.findByDescription", query = "SELECT m FROM MessageGroup m WHERE m.description = :description")})
public class MessageGroup implements Serializable {

    private static final long serialVersionUID = 1L;
    @SequenceGenerator(name = "MESSAGE_GROUP_SEQ", sequenceName = "MESSAGE_GROUP_SEQ")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MESSAGE_GROUP_SEQ")
    @Basic(optional = false)
    @Column(name = "MESSSAGE_GROUP_ID")
    private Long messsageGroupId;
    @Column(name = "MESSSAGE_GROUP_NAME")
    private String messsageGroupName;
    @Column(name = "IS_ACTIVE")
    private Long isActive;
    @Column(name = "MESSSAGE_GROUP_CODE")
    private String messsageGroupCode;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "DEPT_ID")
    private Long deptId;

    public MessageGroup() {
    }

    public MessageGroup(Long messsageGroupId) {
        this.messsageGroupId = messsageGroupId;
    }

    public Long getMesssageGroupId() {
        return messsageGroupId;
    }

    public void setMesssageGroupId(Long messsageGroupId) {
        this.messsageGroupId = messsageGroupId;
    }

    public String getMesssageGroupName() {
        return messsageGroupName;
    }

    public void setMesssageGroupName(String messsageGroupName) {
        this.messsageGroupName = StringUtils.removeEventHandlerJS(messsageGroupName);
    }

    public Long getIsActive() {
        return isActive;
    }

    public void setIsActive(Long isActive) {
        this.isActive = isActive;
    }

    public String getMesssageGroupCode() {
        return messsageGroupCode;
    }

    public void setMesssageGroupCode(String messsageGroupCode) {
        this.messsageGroupCode = StringUtils.removeEventHandlerJS(messsageGroupCode);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = StringUtils.removeEventHandlerJS(description);
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (messsageGroupId != null ? messsageGroupId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MessageGroup)) {
            return false;
        }
        MessageGroup other = (MessageGroup) object;
        if ((this.messsageGroupId == null && other.messsageGroupId != null) || (this.messsageGroupId != null && !this.messsageGroupId.equals(other.messsageGroupId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.viettel.hqmc.BO.MessageGroup[ messsageGroupId=" + messsageGroupId + " ]";
    }

}
