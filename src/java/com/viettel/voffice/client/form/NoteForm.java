/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.voffice.client.form;

/**
 *
 * @author dungnt78
 */
public class NoteForm {

    private long noteId;
    private Long height;
    private Long width;
    private Long posX;
    private Long posY;
    private String text;
    private String security;

    public long getNoteId() {
        return noteId;
    }

    public void setNoteId(long noteId) {
        this.noteId = noteId;
    }

    public Long getHeight() {
        return height;
    }

    public void setHeight(Long height) {
        this.height = height;
    }

    public Long getWidth() {
        return width;
    }

    public void setWidth(Long width) {
        this.width = width;
    }

    public Long getPosX() {
        return posX;
    }

    public void setPosX(Long posX) {
        this.posX = posX;
    }

    public Long getPosY() {
        return posY;
    }

    public void setPosY(Long posY) {
        this.posY = posY;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getSecurity() {
        return security;
    }

    public void setSecurity(String security) {
        this.security = security;
    }
    
    private Long userId;
}
