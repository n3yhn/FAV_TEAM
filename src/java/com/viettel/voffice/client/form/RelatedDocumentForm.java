/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.voffice.client.form;

/**
 *
 * @author HanPT1
 */
public class RelatedDocumentForm {
    
    private Long objectId;
    private Long objectType;
    private String attacthIds;

    public String getAttacthIds() {
        return attacthIds;
    }

    public void setAttacthIds(String attacthIds) {
        this.attacthIds = attacthIds;
    }

    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    public Long getObjectType() {
        return objectType;
    }

    public void setObjectType(Long objectType) {
        this.objectType = objectType;
    }
    
}
