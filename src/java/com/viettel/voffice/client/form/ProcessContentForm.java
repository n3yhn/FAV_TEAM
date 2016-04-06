/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.voffice.client.form;

import java.util.Date;

/**
 *
 * @author sytv
 */
public class ProcessContentForm {
    
    private Date deadline;
    private String processContent;

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getProcessContent() {
        return processContent;
    }

    public void setProcessContent(String processContent) {
        this.processContent = processContent;
    }
    
}
