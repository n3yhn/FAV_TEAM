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
 * <p>Java class for FILE_LISTType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FILE_LISTType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="FILE_ITEMS" type="{}FILE_ITEMSType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FILE_LISTType", propOrder = {
    "fileitems"
})
public class FILELISTType {

    @XmlElement(name = "FILE_ITEMS", required = true)
    protected FILEITEMSType fileitems;

    /**
     * Gets the value of the fileitems property.
     * 
     * @return
     *     possible object is
     *     {@link FILEITEMSType }
     *     
     */
    public FILEITEMSType getFILEITEMS() {
        return fileitems;
    }

    /**
     * Sets the value of the fileitems property.
     * 
     * @param value
     *     allowed object is
     *     {@link FILEITEMSType }
     *     
     */
    public void setFILEITEMS(FILEITEMSType value) {
        this.fileitems = value;
    }

}