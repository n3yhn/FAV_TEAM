/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.viettel.ws.BO;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author HaVM2
 */
@Entity
@Table(name = "XML_MESSAGE")
@XmlRootElement

public class XmlMessage implements Serializable {
    private static final long serialVersionUID = 1L;
    @SequenceGenerator(name = "XML_MESSAGE_SEQ", sequenceName = "XML_MESSAGE_SEQ")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "XML_MESSAGE_SEQ")
    @Column(name = "ID")
    private Long id;

    @Column(name = "MESSAGE")
    private String message;
   
    @Column(name = "CREATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;    


    public XmlMessage() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

   
    @Override
    public String toString() {
        return "com.viettel.voffice.BO.ActionLog[ actionLogId=" + id + " ]";
    }
    
}
