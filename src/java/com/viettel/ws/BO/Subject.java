package com.viettel.ws.BO;

import java.lang.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Subject", propOrder = {
    "type",
    "function",
    "reference",
    "documentYear",
    "preReference",
    "preDocumentYear",
    "sendDate",
    "documentType"
})
public class Subject {

    public Subject() {
    }

    public Subject(String type, String function, String reference, String preReference, String documentYear, String sendDate) {
        this.type = type;
        this.function = function;
        this.reference = reference;
        this.preReference = preReference;
        this.documentYear = documentYear;
        this.sendDate = sendDate;
    }
    protected String type;
    protected String function;
    @XmlElement(required = true)
    protected String reference;
    protected String documentYear;
    @XmlElement(required = true)
    protected String preReference;
    protected String preDocumentYear;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected String sendDate;
    @XmlElement(required = true)
    protected String documentType;

    public String getType() {
        return type;
    }

    public void setType(String value) {
        this.type = value;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String value) {
        this.function = value;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String value) {
        this.reference = value;
    }

    public String getDocumentYear() {
        return documentYear;
    }

    public void setDocumentYear(String value) {
        this.documentYear = value;
    }

    public String getPreReference() {
        return preReference;
    }

    public void setPreReference(String value) {
        this.preReference = value;
    }

    public String getPreDocumentYear() {
        return preDocumentYear;
    }

    public void setPreDocumentYear(String value) {
        this.preDocumentYear = value;
    }

    public String getSendDate() {
        return sendDate;
    }

    public void setSendDate(String value) {
        this.sendDate = value;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String value) {
        this.documentType = value;
    }

}
