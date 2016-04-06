//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.05.29 at 03:13:40 PM ICT 
//


package com.viettel.ws.FORM;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for QUALITYType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="QUALITYType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PRODUCT_PROCESS_DETAIL" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="CONTROL_TARGET" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TECHNICAL_REGULATION" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PATTERN_FREQUENCE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TEST_DEVICE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TEST_METHOD" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="NOTE_FORM" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="NOTE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "QUALITYType", propOrder = {
    "productprocessdetail",
    "controltarget",
    "technicalregulation",
    "patternfrequence",
    "testdevice",
    "testmethod",
    "noteform",
    "note"
})
public class QUALITYType {

    @XmlElement(name = "PRODUCT_PROCESS_DETAIL")
    protected String productprocessdetail;
    @XmlElement(name = "CONTROL_TARGET", required = true)
    protected String controltarget;
    @XmlElement(name = "TECHNICAL_REGULATION", required = true)
    protected String technicalregulation;
    @XmlElement(name = "PATTERN_FREQUENCE", required = true)
    protected String patternfrequence;
    @XmlElement(name = "TEST_DEVICE", required = true)
    protected String testdevice;
    @XmlElement(name = "TEST_METHOD", required = true)
    protected String testmethod;
    @XmlElement(name = "NOTE_FORM", required = true)
    protected String noteform;
    @XmlElement(name = "NOTE", required = true)
    protected String note;

    /**
     * Gets the value of the productprocessdetail property.
     * 
     */
    public String getPRODUCTPROCESSDETAIL() {
        return productprocessdetail;
    }

    /**
     * Sets the value of the productprocessdetail property.
     * 
     */
    public void setPRODUCTPROCESSDETAIL(String value) {
        this.productprocessdetail = value;
    }

    /**
     * Gets the value of the controltarget property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCONTROLTARGET() {
        return controltarget;
    }

    /**
     * Sets the value of the controltarget property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCONTROLTARGET(String value) {
        this.controltarget = value;
    }

    /**
     * Gets the value of the technicalregulation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTECHNICALREGULATION() {
        return technicalregulation;
    }

    /**
     * Sets the value of the technicalregulation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTECHNICALREGULATION(String value) {
        this.technicalregulation = value;
    }

    /**
     * Gets the value of the patternfrequence property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPATTERNFREQUENCE() {
        return patternfrequence;
    }

    /**
     * Sets the value of the patternfrequence property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPATTERNFREQUENCE(String value) {
        this.patternfrequence = value;
    }

    /**
     * Gets the value of the testdevice property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTESTDEVICE() {
        return testdevice;
    }

    /**
     * Sets the value of the testdevice property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTESTDEVICE(String value) {
        this.testdevice = value;
    }

    /**
     * Gets the value of the testmethod property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTESTMETHOD() {
        return testmethod;
    }

    /**
     * Sets the value of the testmethod property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTESTMETHOD(String value) {
        this.testmethod = value;
    }

    /**
     * Gets the value of the noteform property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNOTEFORM() {
        return noteform;
    }

    /**
     * Sets the value of the noteform property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNOTEFORM(String value) {
        this.noteform = value;
    }

    /**
     * Gets the value of the note property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNOTE() {
        return note;
    }

    /**
     * Sets the value of the note property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNOTE(String value) {
        this.note = value;
    }

}
