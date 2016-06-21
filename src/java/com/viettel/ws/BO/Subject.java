//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.11.24 at 11:29:34 AM ICT 
//


package com.viettel.ws.BO;

import java.lang.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for Subject complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Subject">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="type" type="{http://www.w3.org/2001/XMLSchema}String"/>
 *         &lt;element name="function" type="{http://www.w3.org/2001/XMLSchema}String"/>
 *         &lt;element name="reference" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="documentYear" type="{http://www.w3.org/2001/XMLSchema}String"/>
 *         &lt;element name="preReference" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="preDocumentYear" type="{http://www.w3.org/2001/XMLSchema}String"/>
 *         &lt;element name="sendDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="documentType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
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
        this.type=type;
        this.function=function;
        this.reference=reference;
        this.preReference=preReference;
        this.documentYear=documentYear;
        this.sendDate=sendDate;
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

    /**
     * Gets the value of the type property.
     * 
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * Gets the value of the function property.
     * 
     */
    public String getFunction() {
        return function;
    }

    /**
     * Sets the value of the function property.
     * 
     */
    public void setFunction(String value) {
        this.function = value;
    }

    /**
     * Gets the value of the reference property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReference() {
        return reference;
    }

    /**
     * Sets the value of the reference property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReference(String value) {
        this.reference = value;
    }

    /**
     * Gets the value of the documentYear property.
     * 
     */
    public String getDocumentYear() {
        return documentYear;
    }

    /**
     * Sets the value of the documentYear property.
     * 
     */
    public void setDocumentYear(String value) {
        this.documentYear = value;
    }

    /**
     * Gets the value of the preReference property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPreReference() {
        return preReference;
    }

    /**
     * Sets the value of the preReference property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPreReference(String value) {
        this.preReference = value;
    }

    /**
     * Gets the value of the preDocumentYear property.
     * 
     */
    public String getPreDocumentYear() {
        return preDocumentYear;
    }

    /**
     * Sets the value of the preDocumentYear property.
     * 
     */
    public void setPreDocumentYear(String value) {
        this.preDocumentYear = value;
    }

    /**
     * Gets the value of the sendDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public String getSendDate() {
        return sendDate;
    }

    /**
     * Sets the value of the sendDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setSendDate(String value) {
        this.sendDate = value;
    }

    /**
     * Gets the value of the documentType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocumentType() {
        return documentType;
    }

    /**
     * Sets the value of the documentType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocumentType(String value) {
        this.documentType = value;
    }

}
