//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.07.04 at 10:52:42 AM ICT 
//


package com.viettel.ws.ANNOUCERECEIVE;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for QUALITY_CONTROL_PLANType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="QUALITY_CONTROL_PLANType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CONTROLPLANDto" type="{}CONTROLPLANDtoType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "QUALITY_CONTROL_PLANType", propOrder = {
    "controlplanDto"
})
public class QUALITYCONTROLPLANType {

    @XmlElement(name = "CONTROLPLANDto", required = true)
    protected List<CONTROLPLANDtoType> controlplanDto;

    /**
     * Gets the value of the controlplanDto property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the controlplanDto property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCONTROLPLANDto().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CONTROLPLANDtoType }
     * 
     * 
     */
    public List<CONTROLPLANDtoType> getCONTROLPLANDto() {
        if (controlplanDto == null) {
            controlplanDto = new ArrayList<CONTROLPLANDtoType>();
        }
        return this.controlplanDto;
    }

}
