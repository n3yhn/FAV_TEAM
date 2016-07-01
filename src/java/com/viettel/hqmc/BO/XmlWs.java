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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "XML_WS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "XmlWs.findAll", query = "SELECT x FROM XmlWs x"),
    @NamedQuery(name = "XmlWs.findByXmlWsId", query = "SELECT x FROM XmlWs x WHERE x.xmlWsId = :xmlWsId"),
    @NamedQuery(name = "XmlWs.findByUserCreateId", query = "SELECT x FROM XmlWs x WHERE x.userCreateId = :userCreateId"),
    @NamedQuery(name = "XmlWs.findByUserCreateName", query = "SELECT x FROM XmlWs x WHERE x.userCreateName = :userCreateName"),
    @NamedQuery(name = "XmlWs.findByType", query = "SELECT x FROM XmlWs x WHERE x.type = :type"),
    @NamedQuery(name = "XmlWs.findByTypeCode", query = "SELECT x FROM XmlWs x WHERE x.typeCode = :typeCode"),
    @NamedQuery(name = "XmlWs.findByTypeName", query = "SELECT x FROM XmlWs x WHERE x.typeName = :typeName")})
public class XmlWs implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @SequenceGenerator(name = "XML_WS_SEQ", sequenceName = "XML_WS_SEQ")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "XML_WS_SEQ")
    @Basic(optional = false)
    @Column(name = "XML_WS_ID")
    private Long xmlWsId;
    @Lob
    @Column(name = "CONTENT")
    private String content;
    @Column(name = "USER_CREATE_ID")
    private Long userCreateId;
    @Column(name = "USER_CREATE_NAME")
    private String userCreateName;
    @Column(name = "TYPE")
    private Long type;
    @Column(name = "TYPE_CODE")
    private String typeCode;
    @Column(name = "TYPE_NAME")
    private String typeName;

    public XmlWs() {
    }

    public XmlWs(Long xmlWsId) {
        this.xmlWsId = xmlWsId;
    }

    public Long getXmlWsId() {
        return xmlWsId;
    }

    public void setXmlWsId(Long xmlWsId) {
        this.xmlWsId = xmlWsId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getUserCreateId() {
        return userCreateId;
    }

    public void setUserCreateId(Long userCreateId) {
        this.userCreateId = userCreateId;
    }

    public String getUserCreateName() {
        return userCreateName;
    }

    public void setUserCreateName(String userCreateName) {
        this.userCreateName = userCreateName;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (xmlWsId != null ? xmlWsId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof XmlWs)) {
            return false;
        }
        XmlWs other = (XmlWs) object;
        if ((this.xmlWsId == null && other.xmlWsId != null) || (this.xmlWsId != null && !this.xmlWsId.equals(other.xmlWsId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.viettel.hqmc.BO.XmlWs[ xmlWsId=" + xmlWsId + " ]";
    }
    
}
