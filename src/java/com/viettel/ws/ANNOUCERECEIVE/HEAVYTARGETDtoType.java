//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.07.04 at 10:52:42 AM ICT 
//


package com.viettel.ws.ANNOUCERECEIVE;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for HEAVYTARGETDtoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="HEAVYTARGETDtoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="HEAVY_ID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="HEAVY_CODE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TARGET_NAME" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="UNIT_CODE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="MAX_LEVEL" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="IS_DELETED" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ANNOUNCE_SEND_ID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HEAVYTARGETDtoType", propOrder = {
    "heavyid",
    "heavycode",
    "targetname",
    "unitcode",
    "maxlevel",
    "isdeleted",
    "announcesendid"
})
public class HEAVYTARGETDtoType {

    @XmlElement(name = "HEAVY_ID")
    public int heavyid;
    @XmlElement(name = "HEAVY_CODE", required = true)
    public String heavycode;
    @XmlElement(name = "TARGET_NAME3")
    public String targetname;
    @XmlElement(name = "UNIT_CODE3", required = true)
    public String unitcode;
    @XmlElement(name = "MAX_LEVEL2")
    public String maxlevel;
    @XmlElement(name = "IS_DELETE5")
    public int isdeleted;
    @XmlElement(name = "ANNOUNCE_SEND_ID")
    public int announcesendid;

    /**
     * Gets the value of the heavyid property.
     * 
     */
    public int getHEAVYID() {
        return heavyid;
    }

    /**
     * Sets the value of the heavyid property.
     * 
     */
    public void setHEAVYID(int value) {
        this.heavyid = value;
    }

    /**
     * Gets the value of the heavycode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHEAVYCODE() {
        return heavycode;
    }

    /**
     * Sets the value of the heavycode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHEAVYCODE(String value) {
        this.heavycode = value;
    }

    /**
     * Gets the value of the targetname property.
     * 
     */
    public String getTARGETNAME() {
        return targetname;
    }

    /**
     * Sets the value of the targetname property.
     * 
     */
    public void setTARGETNAME(String value) {
        this.targetname = value;
    }

    /**
     * Gets the value of the unitcode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUNITCODE() {
        return unitcode;
    }

    /**
     * Sets the value of the unitcode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUNITCODE(String value) {
        this.unitcode = value;
    }

    /**
     * Gets the value of the maxlevel property.
     * 
     */
    public String getMAXLEVEL() {
        return maxlevel;
    }

    /**
     * Sets the value of the maxlevel property.
     * 
     */
    public void setMAXLEVEL(String value) {
        this.maxlevel = value;
    }

    /**
     * Gets the value of the isdeleted property.
     * 
     */
    public int getISDELETED() {
        return isdeleted;
    }

    /**
     * Sets the value of the isdeleted property.
     * 
     */
    public void setISDELETED(int value) {
        this.isdeleted = value;
    }

    /**
     * Gets the value of the announcesendid property.
     * 
     */
    public int getANNOUNCESENDID() {
        return announcesendid;
    }

    /**
     * Sets the value of the announcesendid property.
     * 
     */
    public void setANNOUNCESENDID(int value) {
        this.announcesendid = value;
    }

}
