/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.voffice.database.BO;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 *
 * @author sytv
 */
@Entity
@Table(name = "VO_ATTACHS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VoAttachs.findAll", query = "SELECT v FROM VoAttachs v"),
    @NamedQuery(name = "VoAttachs.findByAttachId", query = "SELECT v FROM VoAttachs v WHERE v.attachId = :attachId"),
    @NamedQuery(name = "VoAttachs.findByObjectId", query = "SELECT v FROM VoAttachs v WHERE v.objectId = :objectId"),
    @NamedQuery(name = "VoAttachs.findByObjectType", query = "SELECT v FROM VoAttachs v WHERE v.objectType = :objectType"),
    @NamedQuery(name = "VoAttachs.findByAttachName", query = "SELECT v FROM VoAttachs v WHERE v.attachName = :attachName"),
    @NamedQuery(name = "VoAttachs.findByAttachPath", query = "SELECT v FROM VoAttachs v WHERE v.attachPath = :attachPath"),
    @NamedQuery(name = "VoAttachs.findByIsActive", query = "SELECT v FROM VoAttachs v WHERE v.isActive = :isActive"),
    @NamedQuery(name = "VoAttachs.findByAttachDes", query = "SELECT v FROM VoAttachs v WHERE v.attachDes = :attachDes"),
    @NamedQuery(name = "VoAttachs.findByUserCreateId", query = "SELECT v FROM VoAttachs v WHERE v.userCreateId = :userCreateId"),
    @NamedQuery(name = "VoAttachs.findByCreateDate", query = "SELECT v FROM VoAttachs v WHERE v.createDate = :createDate")
})
public class VoAttachs implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "VO_ATTACHS_SEQ")
            })
    @Basic(optional = false)
    @Column(name = "ATTACH_ID")
    private Long attachId;
    @Column(name = "OBJECT_ID")
    private Long objectId;
    @Column(name = "OBJECT_TYPE")
    private Long objectType;
    @Column(name = "ATTACH_NAME")
    private String attachName;
    @Column(name = "ATTACH_PATH")
    private String attachPath;
    @Column(name = "IS_ACTIVE")
    private Long isActive;
    @Column(name = "ATTACH_DES")
    private String attachDes;
    @Column(name = "USER_CREATE_ID")
    private Long userCreateId;
    @Column(name = "DEPT_ID")
    private Long deptId;
    @Column(name = "CATEGORY_ID")
    private Long categoryId;
    @Column(name = "CATEGORY_NAME")
    private String categoryName;
    @Column(name = "CREATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Column(name = "IS_TEMP")
    private Long isTemp;
    @Column(name = "ORIGINAL_ID")
    private Long originalId;

    public VoAttachs() {
    }

    public String getAttachName() {
        return attachName;
    }

    public void setAttachName(String attachName) {
        this.attachName = attachName;
    }

    public String getAttachPath() {
        return attachPath;
    }

    public void setAttachPath(String attachPath) {
        this.attachPath = attachPath;
    }

    public Long getIsActive() {
        return isActive;
    }

    public void setIsActive(Long isActive) {
        this.isActive = isActive;
    }

    public String getAttachDes() {
        return attachDes;
    }

    public void setAttachDes(String attachDes) {
        this.attachDes = attachDes;
    }

    public Long getAttachId() {
        return attachId;
    }

    public void setAttachId(Long attachId) {
        this.attachId = attachId;
    }

    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    public Long getUserCreateId() {
        return userCreateId;
    }

    public void setUserCreateId(Long userCreateId) {
        this.userCreateId = userCreateId;
    }

    public Long getObjectType() {
        return objectType;
    }

    public void setObjectType(Long objectType) {
        this.objectType = objectType;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Long getIsTemp() {
        return isTemp;
    }

    public void setIsTemp(Long isTemp) {
        this.isTemp = isTemp;
    }

    public Long getOriginalId() {
        return originalId;
    }

    public void setOriginalId(Long originalId) {
        this.originalId = originalId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (attachId != null ? attachId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VoAttachs)) {
            return false;
        }
        VoAttachs other = (VoAttachs) object;
        if ((this.attachId == null && other.attachId != null) || (this.attachId != null && !this.attachId.equals(other.attachId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.viettel.voffice.database.BO.VoAttachs[ attachId=" + attachId + " ]";
    }

    public VoAttachs cloneEntity(VoAttachs original) {
        VoAttachs entity = new VoAttachs();
        entity.setAttachDes(original.getAttachDes());
        entity.setAttachName(original.getAttachName());
        entity.setAttachPath(original.getAttachPath());
        entity.setCategoryId(original.getCategoryId());
        entity.setCategoryName(original.getCategoryName());
        entity.setCreateDate(original.getCreateDate());
        entity.setDeptId(original.getDeptId());
        entity.setIsActive(original.getIsActive());
        entity.setIsTemp(0L);
        entity.setObjectId(original.getObjectId());
        entity.setObjectType(original.getObjectType());
        entity.setOriginalId(original.getAttachId());
        entity.setUserCreateId(original.getUserCreateId());
        return entity;
    }
    
    public VoAttachs cloneEntity() {
        VoAttachs entity = new VoAttachs();
        entity.setAttachDes(this.getAttachDes());
        entity.setAttachName(this.getAttachName());
        entity.setAttachPath(this.getAttachPath());
        entity.setCategoryId(this.getCategoryId());
        entity.setCategoryName(this.getCategoryName());
        entity.setCreateDate(this.getCreateDate());
        entity.setDeptId(this.getDeptId());
        entity.setIsActive(this.getIsActive());
        entity.setIsTemp(0L);
        entity.setObjectId(null);
        entity.setObjectType(this.getObjectType());
        entity.setOriginalId(this.getAttachId());
        entity.setUserCreateId(this.getUserCreateId());
        return entity;
    }

    public VoAttachs toEntity(VoAttachs original) {
        VoAttachs entity = new VoAttachs();
        entity.setAttachDes(original.getAttachDes());
        entity.setAttachName(original.getAttachName());
        entity.setAttachPath(original.getAttachPath());
        entity.setCategoryId(original.getCategoryId());
        entity.setCategoryName(original.getCategoryName());
        entity.setCreateDate(original.getCreateDate());
        entity.setDeptId(original.getDeptId());
        entity.setIsActive(original.getIsActive());
//        entity.setIsTemp(0L);
        entity.setObjectId(original.getObjectId());
        entity.setObjectType(original.getObjectType());
//        entity.setOriginalId(original.getAttachId());
        entity.setUserCreateId(original.getUserCreateId());
        return entity;
    }
}
