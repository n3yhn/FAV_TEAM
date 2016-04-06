/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.viettel.hqmc.BO;

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
 * @author GPCP_BINHNT53
 */
@Entity
@Table(name = "MESSAGE_EMAIL_FLAG")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MessageEmailFlag.findAll", query = "SELECT m FROM MessageEmailFlag m"),
    @NamedQuery(name = "MessageEmailFlag.findByFlag", query = "SELECT m FROM MessageEmailFlag m WHERE m.flag = :flag")})
public class MessageEmailFlag implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "FLAG")
    private Short flag;

    public MessageEmailFlag() {
    }

    public MessageEmailFlag(Short flag) {
        this.flag = flag;
    }

    public Short getFlag() {
        return flag;
    }

    public void setFlag(Short flag) {
        this.flag = flag;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (flag != null ? flag.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MessageEmailFlag)) {
            return false;
        }
        MessageEmailFlag other = (MessageEmailFlag) object;
        if ((this.flag == null && other.flag != null) || (this.flag != null && !this.flag.equals(other.flag))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.viettel.hqmc.BO.MessageEmailFlag[ flag=" + flag + " ]";
    }
    
}
