/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.FORM;

import com.viettel.hqmc.BO.FilesEdit;
import com.viettel.voffice.database.BO.VoAttachs;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Os_Hiepvv
 */
@XmlRootElement
public class FilesEditForm implements Serializable{
    private Long filesEditID;
    private Long filesID;
    private Date createDate;
    private String contents;
    private String notes;
    
    public FilesEditForm(){};

    public FilesEditForm(FilesEdit entity) {
        if (entity == null) {
            return;
        }
        filesEditID = entity.getFilesEditId();
        filesID     = entity.getFileId();
        createDate  = entity.getCreateDate();
        contents    = entity.getContent();
        notes       = entity.getNote();
        
    }
    
    public FilesEdit convertToEntity() {
        FilesEdit entity = new FilesEdit();
        if (filesEditID!=null) {
            entity.setFilesEditId(filesEditID);
        }
        if(filesID!=null)
            entity.setFileId(filesID);
        if(createDate!=null)
            entity.setCreateDate(createDate);
        entity.setContent(contents);
        entity.setNote(notes);
        return entity;
    }
    
    public Long getFilesEditID() {
        return filesEditID;
    }

    public void setFilesEditID(Long filesEditID) {
        this.filesEditID = filesEditID;
    }

    public Long getFilesID() {
        return filesID;
    }

    public void setFilesID(Long filesID) {
        this.filesID = filesID;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    
    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
    
}
