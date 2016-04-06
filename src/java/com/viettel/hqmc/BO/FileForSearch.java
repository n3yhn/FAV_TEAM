/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.BO;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Vu Manh Ha
 */
@Entity
@Table(name = "FILE_FOR_SEARCH")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FileForSearch.findAll", query = "SELECT f FROM FileForSearch f"),
    @NamedQuery(name = "FileForSearch.findByFileId", query = "SELECT f FROM FileForSearch f WHERE f.fileId = :fileId")})
public class FileForSearch implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "FILE_ID")
    private Long fileId;
    @Lob
    @Column(name = "CONTENT")
    private String content;

    public FileForSearch() {
    }

    public FileForSearch(Long fileId) {
        this.fileId = fileId;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fileId != null ? fileId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FileForSearch)) {
            return false;
        }
        FileForSearch other = (FileForSearch) object;
        if ((this.fileId == null && other.fileId != null) || (this.fileId != null && !this.fileId.equals(other.fileId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.viettel.hqmc.BO.FileForSearch[ fileId=" + fileId + " ]";
    }
    
}
