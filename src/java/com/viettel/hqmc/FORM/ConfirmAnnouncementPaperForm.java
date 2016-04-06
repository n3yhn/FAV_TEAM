/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.FORM;

import com.viettel.hqmc.BO.ConfirmAnnouncementPaper;
import java.util.Date;

/**
 *
 * @author vtit_binhnt53
 */
public class ConfirmAnnouncementPaperForm {
    private Long confirmAnnouncementPaperId;
    private String confirmationNo;
    private Date dateOfIssue;
    private String issueAgencyName;
    private Long issueAgencyId;
    private Long isActive;

    public ConfirmAnnouncementPaperForm() {
    }

    public ConfirmAnnouncementPaperForm(Long confirmAnnouncementPaperId, String confirmationNo, Date dateOfIssue, String issueAgencyName, Long issueAgencyId, Long isActive) {
        this.confirmAnnouncementPaperId = confirmAnnouncementPaperId;
        this.confirmationNo = confirmationNo;
        this.dateOfIssue = dateOfIssue;
        this.issueAgencyName = issueAgencyName;
        this.issueAgencyId = issueAgencyId;
        this.isActive = isActive;
    }
public ConfirmAnnouncementPaper convertToEntity() {
        ConfirmAnnouncementPaper item = new ConfirmAnnouncementPaper();
        item.setConfirmAnnouncementPaperId(confirmAnnouncementPaperId);
        item.setConfirmationNo(confirmationNo);
        item.setDateOfIssue(dateOfIssue);
        item.setIsActive(isActive);
        item.setIssueAgencyId(issueAgencyId);
        item.setIssueAgencyName(issueAgencyName);

        return item;
    }
    public Long getConfirmAnnouncementPaperId() {
        return confirmAnnouncementPaperId;
    }

    public void setConfirmAnnouncementPaperId(Long confirmAnnouncementPaperId) {
        this.confirmAnnouncementPaperId = confirmAnnouncementPaperId;
    }

    public String getConfirmationNo() {
        return confirmationNo;
    }

    public void setConfirmationNo(String confirmationNo) {
        this.confirmationNo = confirmationNo;
    }

    public Date getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(Date dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public String getIssueAgencyName() {
        return issueAgencyName;
    }

    public void setIssueAgencyName(String issueAgencyName) {
        this.issueAgencyName = issueAgencyName;
    }

    public Long getIssueAgencyId() {
        return issueAgencyId;
    }

    public void setIssueAgencyId(Long issueAgencyId) {
        this.issueAgencyId = issueAgencyId;
    }

    public Long getIsActive() {
        return isActive;
    }

    public void setIsActive(Long isActive) {
        this.isActive = isActive;
    }
    
}
