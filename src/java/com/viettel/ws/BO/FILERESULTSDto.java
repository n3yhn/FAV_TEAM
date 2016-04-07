//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.07.15 at 04:37:11 PM ICT 
//


package com.viettel.ws.BO;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import jxl.write.DateTime;


/**
 * <p>Java class for FILERESULTSDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FILERESULTSDto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AUTHENTICATE" type="{}AUTHENTICATE"/>
 *         &lt;element name="NSW_FILE_CODE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="STATUS_CODE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DEPT_CODE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SIGNER_NAME" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SIGN_DATE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="COMMENTS" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IMPORT_RESULT" type="{}IMPORT_RESULT"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FILERESULTSDto", propOrder = {
    "authenticate",
    "nswfilecode",
    "statuscode",
    "deptcode",
    "signername",
    "signdate",
    "comments",
    "importresult"
})
public class FILERESULTSDto {

    @XmlElement(name = "AUTHENTICATE", required = true)
    protected AUTHENTICATE authenticate;
    @XmlElement(name = "NSW_FILE_CODE", required = true)
    protected String nswfilecode;
    @XmlElement(name = "PROCESSED_NAME", required = true)
    protected String processedname;
    @XmlElement(name = "PROCESSED_DATE", required = true)
    protected DateTime processeddate;
    @XmlElement(name = "STATUS_CODE", required = true)
    protected String statuscode;
    @XmlElement(name = "DEPT_CODE", required = true)
    protected String deptcode;
    @XmlElement(name = "RECEIVED_PLACE", required = true)
    protected String receivedplace;
    @XmlElement(name = "PROVINCE_CODE", required = true)
    protected String provicecode;
    @XmlElement(name = "IMPORT_RESULT", required = true)
    protected IMPORTRESULT importresult;

    /**
     * Gets the value of the authenticate property.
     * 
     * @return
     *     possible object is
     *     {@link AUTHENTICATE }
     *     
     */
    public AUTHENTICATE getAUTHENTICATE() {
        return authenticate;
    }

    /**
     * Sets the value of the authenticate property.
     * 
     * @param value
     *     allowed object is
     *     {@link AUTHENTICATE }
     *     
     */
    public void setAUTHENTICATE(AUTHENTICATE value) {
        this.authenticate = value;
    }

    /**
     * Gets the value of the nswfilecode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNSWFILECODE() {
        return nswfilecode;
    }

    /**
     * Sets the value of the nswfilecode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNSWFILECODE(String value) {
        this.nswfilecode = value;
    }

    /**
     * Gets the value of the statuscode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSTATUSCODE() {
        return statuscode;
    }

    /**
     * Sets the value of the statuscode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSTATUSCODE(String value) {
        this.statuscode = value;
    }

    /**
     * Gets the value of the deptcode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDEPTCODE() {
        return deptcode;
    }

    /**
     * Sets the value of the deptcode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDEPTCODE(String value) {
        this.deptcode = value;
    }

    /**
     * Gets the value of the signername property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPROCESSEDNAME() {
        return processedname;
    }

    /**
     * Sets the value of the signername property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPROCESSEDNAME(String value) {
        this.processedname = value;
    }

    /**
     * Gets the value of the signdate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public DateTime getPROCESSEDDATE() {
        return processeddate;
    }

    /**
     * Sets the value of the signdate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setROCESSEDDATE(DateTime value) {
        this.processeddate = value;
    }

    /**
     * Gets the value of the comments property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRECEIVEDPLACE() {
        return receivedplace;
    }

    /**
     * Sets the value of the comments property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }PROVINCE_CODE
     *     
     */
    public void setRECEIVEDPLACE(String value) {
        this.receivedplace = value;
    }

      public String getPROVINCECODE() {
        return provicecode;
    }
    
      public void setPROVINCECODE(String value) {
        this.provicecode = value;
    }
    /**
     * Gets the value of the importresult property.
     * 
     * @return
     *     possible object is
     *     {@link IMPORTRESULT }
     *     
     */
    public IMPORTRESULT getIMPORTRESULT() {
        return importresult;
    }

    /**
     * Sets the value of the importresult property.
     * 
     * @param value
     *     allowed object is
     *     {@link IMPORTRESULT }
     *     
     */
    public void setIMPORTRESULT(IMPORTRESULT value) {
        this.importresult = value;
    }

}