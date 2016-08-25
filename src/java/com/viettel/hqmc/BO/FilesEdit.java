/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.BO;

import com.viettel.common.util.StringUtils;
import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Os_Hiepvv
 */
@Entity
@Table(name = "FILES_EDIT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FilesEdit.findAll", query = "SELECT f FROM FilesEdit f"),
    @NamedQuery(name = "FilesEdit.findByFilesEditId", query = "SELECT f FROM FilesEdit f WHERE f.filesEditId = :filesEditId"),
    @NamedQuery(name = "FilesEdit.findByFileId", query = "SELECT f FROM FilesEdit f WHERE f.fileId = :fileId"),
    @NamedQuery(name = "FilesEdit.findByVersion", query = "SELECT f FROM FilesEdit f WHERE f.version = :version"),
    @NamedQuery(name = "FilesEdit.findByStatus", query = "SELECT f FROM FilesEdit f WHERE f.status = :status"),
    @NamedQuery(name = "FilesEdit.findByContent", query = "SELECT f FROM FilesEdit f WHERE f.content = :content"),
    @NamedQuery(name = "FilesEdit.findByNote", query = "SELECT f FROM FilesEdit f WHERE f.note = :note"),
    @NamedQuery(name = "FilesEdit.findByCreateDate", query = "SELECT f FROM FilesEdit f WHERE f.createDate = :createDate")})
public class FilesEdit implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @SequenceGenerator(name = "FILES_EDIT_SEQ", sequenceName = "FILES_EDIT_SEQ")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FILES_EDIT_SEQ")
    @Basic(optional = false)
    @Column(name = "FILES_EDIT_ID")
    private Long filesEditId;
    @Column(name = "FILE_ID")
    private Long fileId;
    @Column(name = "VERSION")
    private Long version;
    @Column(name = "STATUS")
    private Long status;
    @Column(name = "CONTENT")
    private String content;
    @Column(name = "NOTE")
    private String note;
    @Column(name = "CREATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    public FilesEdit() {
    }

    public FilesEdit(Long filesEditId) {
        this.filesEditId = filesEditId;
    }

    public Long getFilesEditId() {
        return filesEditId;
    }

    public void setFilesEditId(Long filesEditId) {
        this.filesEditId = filesEditId;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = StringUtils.removeEventHandlerJS(content);
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = StringUtils.removeEventHandlerJS(note);
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (filesEditId != null ? filesEditId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FilesEdit)) {
            return false;
        }
        FilesEdit other = (FilesEdit) object;
        if ((this.filesEditId == null && other.filesEditId != null) || (this.filesEditId != null && !this.filesEditId.equals(other.filesEditId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.viettel.hqmc.BO.FilesEdit[ filesEditId=" + filesEditId + " ]";
    }
    
}
