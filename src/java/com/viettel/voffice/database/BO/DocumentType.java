/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.voffice.database.BO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 *
 * @author Viettel
 */
@Entity
@Table(name = "DOCUMENT_TYPE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DocumentType.findAll", query = "SELECT d FROM DocumentType d"),
    @NamedQuery(name = "DocumentType.findByTypeId", query = "SELECT d FROM DocumentType d WHERE d.typeId = :typeId"),
    @NamedQuery(name = "DocumentType.findByName", query = "SELECT d FROM DocumentType d WHERE d.name = :name"),
    @NamedQuery(name = "DocumentType.findByDescription", query = "SELECT d FROM DocumentType d WHERE d.description = :description"),
    @NamedQuery(name = "DocumentType.findByAbbreviate", query = "SELECT d FROM DocumentType d WHERE d.abbreviate = :abbreviate"),
    @NamedQuery(name = "DocumentType.findByIsActive", query = "SELECT d FROM DocumentType d WHERE d.isActive = :isActive"),
    @NamedQuery(name = "DocumentType.findByUserCreate", query = "SELECT d FROM DocumentType d WHERE d.userCreate = :userCreate"),
    @NamedQuery(name = "DocumentType.findByUserCreateName", query = "SELECT d FROM DocumentType d WHERE d.userCreateName = :userCreateName"),
    @NamedQuery(name = "DocumentType.findByCreateDate", query = "SELECT d FROM DocumentType d WHERE d.createDate = :createDate"),
    @NamedQuery(name = "DocumentType.findByUserUpdate", query = "SELECT d FROM DocumentType d WHERE d.userUpdate = :userUpdate"),
    @NamedQuery(name = "DocumentType.findByUserUpdateName", query = "SELECT d FROM DocumentType d WHERE d.userUpdateName = :userUpdateName"),
    @NamedQuery(name = "DocumentType.findByUpdateDate", query = "SELECT d FROM DocumentType d WHERE d.updateDate = :updateDate"),
    @NamedQuery(name = "DocumentType.findByIsOldData", query = "SELECT d FROM DocumentType d WHERE d.isOldData = :isOldData"),
    @NamedQuery(name = "DocumentType.findByType", query = "SELECT d FROM DocumentType d WHERE d.type = :type")})
public class DocumentType implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
    parameters = {
        @Parameter(name = "sequence", value = "DOCUMENT_TYPE_SEQ")
    })
    @Basic(optional = false)
    @Column(name = "TYPE_ID")
    private Long typeId;
    @Basic(optional = false)
    @Column(name = "NAME")
    private String name;
    @Column(name = "DESCRIPTION")
    private String description;
    @Basic(optional = false)
    @Column(name = "ABBREVIATE")
    private String abbreviate;
    @Column(name = "IS_ACTIVE")
    private Long isActive;
    @Column(name = "USER_CREATE")
    private Long userCreate;
    @Column(name = "USER_CREATE_NAME")
    private String userCreateName;
    @Column(name = "CREATE_DATE")
    @Temporal(TemporalType.DATE)
    private Date createDate;
    @Column(name = "USER_UPDATE")
    private Long userUpdate;
    @Column(name = "USER_UPDATE_NAME")
    private String userUpdateName;
    @Column(name = "UPDATE_DATE")
    @Temporal(TemporalType.DATE)
    private Date updateDate;
    @Column(name = "IS_OLD_DATA")
    private Long isOldData;
    @Column(name = "TYPE")
    private Long type;

    public DocumentType() {
    }

    public DocumentType(Long typeId) {
        this.typeId = typeId;
    }

    public DocumentType(Long typeId, String name, String abbreviate) {
        this.typeId = typeId;
        this.name = name;
        this.abbreviate = abbreviate;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAbbreviate() {
        return abbreviate;
    }

    public void setAbbreviate(String abbreviate) {
        this.abbreviate = abbreviate;
    }

    public Long getIsActive() {
        return isActive;
    }

    public void setIsActive(Long isActive) {
        this.isActive = isActive;
    }

    public Long getUserCreate() {
        return userCreate;
    }

    public void setUserCreate(Long userCreate) {
        this.userCreate = userCreate;
    }

    public String getUserCreateName() {
        return userCreateName;
    }

    public void setUserCreateName(String userCreateName) {
        this.userCreateName = userCreateName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getUserUpdate() {
        return userUpdate;
    }

    public void setUserUpdate(Long userUpdate) {
        this.userUpdate = userUpdate;
    }

    public String getUserUpdateName() {
        return userUpdateName;
    }

    public void setUserUpdateName(String userUpdateName) {
        this.userUpdateName = userUpdateName;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Long getIsOldData() {
        return isOldData;
    }

    public void setIsOldData(Long isOldData) {
        this.isOldData = isOldData;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (typeId != null ? typeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DocumentType)) {
            return false;
        }
        DocumentType other = (DocumentType) object;
        if ((this.typeId == null && other.typeId != null) || (this.typeId != null && !this.typeId.equals(other.typeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.viettel.voffice.database.BO.DocumentType[ typeId=" + typeId + " ]";
    }
    
}
