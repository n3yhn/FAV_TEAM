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
@Table(name = "MESSAGE_GROUP_PERSON")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MessageGroupPerson.findAll", query = "SELECT m FROM MessageGroupPerson m"),
    @NamedQuery(name = "MessageGroupPerson.findByMessageGroupPersonId", query = "SELECT m FROM MessageGroupPerson m WHERE m.messageGroupPersonId = :messageGroupPersonId"),
    @NamedQuery(name = "MessageGroupPerson.findByPersonName", query = "SELECT m FROM MessageGroupPerson m WHERE m.personName = :personName"),
    @NamedQuery(name = "MessageGroupPerson.findByPersonEmail", query = "SELECT m FROM MessageGroupPerson m WHERE m.personEmail = :personEmail"),
    @NamedQuery(name = "MessageGroupPerson.findByIsActive", query = "SELECT m FROM MessageGroupPerson m WHERE m.isActive = :isActive"),
    @NamedQuery(name = "MessageGroupPerson.findByMessageGroupId", query = "SELECT m FROM MessageGroupPerson m WHERE m.messageGroupId = :messageGroupId"),
    @NamedQuery(name = "MessageGroupPerson.findByPersonPhoneNumber", query = "SELECT m FROM MessageGroupPerson m WHERE m.personPhoneNumber = :personPhoneNumber")})
public class MessageGroupPerson implements Serializable {

    private static final long serialVersionUID = 1L;
    @SequenceGenerator(name = "MESSAGE_GROUP_PERSON_SEQ", sequenceName = "MESSAGE_GROUP_PERSON_SEQ")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MESSAGE_GROUP_PERSON_SEQ")
    @Basic(optional = false)
    @Column(name = "MESSAGE_GROUP_PERSON_ID")
    private Long messageGroupPersonId;
    @Column(name = "PERSON_NAME")
    private String personName;
    @Column(name = "PERSON_EMAIL")
    private String personEmail;
    @Column(name = "IS_ACTIVE")
    private Long isActive;
    @Column(name = "MESSAGE_GROUP_ID")
    private Long messageGroupId;
    @Column(name = "PERSON_PHONE_NUMBER")
    private String personPhoneNumber;

    public MessageGroupPerson() {
    }

    public MessageGroupPerson(Long messageGroupPersonId) {
        this.messageGroupPersonId = messageGroupPersonId;
    }

    public Long getMessageGroupPersonId() {
        return messageGroupPersonId;
    }

    public void setMessageGroupPersonId(Long messageGroupPersonId) {
        this.messageGroupPersonId = messageGroupPersonId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = StringUtils.removeEventHandlerJS(personName);
    }

    public String getPersonEmail() {
        return personEmail;
    }

    public void setPersonEmail(String personEmail) {
        this.personEmail = StringUtils.removeEventHandlerJS(personEmail);
    }

    public Long getIsActive() {
        return isActive;
    }

    public void setIsActive(Long isActive) {
        this.isActive = isActive;
    }

    public Long getMessageGroupId() {
        return messageGroupId;
    }

    public void setMessageGroupId(Long messageGroupId) {
        this.messageGroupId = messageGroupId;
    }

    public String getPersonPhoneNumber() {
        return personPhoneNumber;
    }

    public void setPersonPhoneNumber(String personPhoneNumber) {
        this.personPhoneNumber = StringUtils.removeEventHandlerJS(personPhoneNumber);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (messageGroupPersonId != null ? messageGroupPersonId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MessageGroupPerson)) {
            return false;
        }
        MessageGroupPerson other = (MessageGroupPerson) object;
        if ((this.messageGroupPersonId == null && other.messageGroupPersonId != null) || (this.messageGroupPersonId != null && !this.messageGroupPersonId.equals(other.messageGroupPersonId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.viettel.hqmc.BO.MessageGroupPerson[ messageGroupPersonId=" + messageGroupPersonId + " ]";
    }

}
