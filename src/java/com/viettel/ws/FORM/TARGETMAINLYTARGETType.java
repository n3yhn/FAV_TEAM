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
 * <p>Java class for TARGET_MAINLY_TARGETType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TARGET_MAINLY_TARGETType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TARGET_NAME" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="UNIT_ID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="PUBLISH_LEVEL" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="MEET_LEVEL" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TARGET_MAINLY_TARGETType", propOrder = {
    "targetname",
    "unitid",
    "publishlevel",
    "meetlevel"
})
public class TARGETMAINLYTARGETType {

    @XmlElement(name = "TARGET_NAME", required = true)
    protected String targetname;
    @XmlElement(name = "UNIT_ID")
    protected String unitid;
    @XmlElement(name = "PUBLISH_LEVEL")
    protected String publishlevel;
    @XmlElement(name = "MEET_LEVEL")
    protected String meetlevel;

    /**
     * Gets the value of the targetname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTARGETNAME() {
        return targetname;
    }

    /**
     * Sets the value of the targetname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTARGETNAME(String value) {
        this.targetname = value;
    }

    /**
     * Gets the value of the unitid property.
     * 
     */
    public String getUNITID() {
        return unitid;
    }

    /**
     * Sets the value of the unitid property.
     * 
     */
    public void setUNITID(String value) {
        this.unitid = value;
    }

    /**
     * Gets the value of the publishlevel property.
     * 
     */
    public String getPUBLISHLEVEL() {
        return publishlevel;
    }

    /**
     * Sets the value of the publishlevel property.
     * 
     */
    public void setPUBLISHLEVEL(String value) {
        this.publishlevel = value;
    }

    /**
     * Gets the value of the meetlevel property.
     * 
     */
    public String getMEETLEVEL() {
        return meetlevel;
    }

    /**
     * Sets the value of the meetlevel property.
     * 
     */
    public void setMEETLEVEL(String value) {
        this.meetlevel = value;
    }

}
