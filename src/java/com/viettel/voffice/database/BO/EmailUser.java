/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.voffice.database.BO;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author dungnt78
 */
@Entity
@Table(name = "EMAIL_USER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EmailUser.findAll", query = "SELECT e FROM EmailUser e"),
    @NamedQuery(name = "EmailUser.findByEmailAddress", query = "SELECT e FROM EmailUser e WHERE e.emailAddress = :emailAddress"),
    @NamedQuery(name = "EmailUser.findByEmailPassword", query = "SELECT e FROM EmailUser e WHERE e.emailPassword = :emailPassword"),
    @NamedQuery(name = "EmailUser.findByUserId", query = "SELECT e FROM EmailUser e WHERE e.userId = :userId")})
public class EmailUser implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "EMAIL_ADDRESS")
    private String emailAddress;
    @Basic(optional = false)
    @Column(name = "EMAIL_PASSWORD")
    private String emailPassword;
    @Id
    @Basic(optional = false)
    @Column(name = "USER_ID")
    private Long userId;

    public EmailUser() {
    }

    public EmailUser(Long userId) {
        this.userId = userId;
    }

    public EmailUser(Long userId, String emailAddress, String emailPassword) {
        this.userId = userId;
        this.emailAddress = emailAddress;
        this.emailPassword = emailPassword;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getEmailPassword() {
        return emailPassword;
    }

    public void setEmailPassword(String emailPassword) {
        this.emailPassword = emailPassword;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmailUser)) {
            return false;
        }
        EmailUser other = (EmailUser) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.viettel.voffice.database.BO.EmailUser[ userId=" + userId + " ]";
    }
    
}
