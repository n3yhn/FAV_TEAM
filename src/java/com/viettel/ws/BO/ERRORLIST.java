//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.07.15 at 02:16:21 PM ICT 
//
package com.viettel.ws.BO;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for ERROR_LIST complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="ERROR_LIST">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ERRORDto" type="{}ERRORDto" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ERROR_LIST", propOrder = {
    "errorDto"
})
public class ERRORLIST {

    @XmlElement(name = "ERRORDto", required = true)
    protected List<ERRORDto> errorDto;

    /**
     * Gets the value of the errorDto property.
     *
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the errorDto property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getERRORDto().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ERRORDto }
     *
     *
     */
    public List<ERRORDto> getERRORDto() {
        if (errorDto == null) {
            errorDto = new ArrayList<ERRORDto>();
        }
        return this.errorDto;
    }

    public List<ERRORDto> getErrorDto() {
        return errorDto;
    }

    public void setErrorDto(List<ERRORDto> errorDto) {
        this.errorDto = errorDto;
    }

    public ERRORLIST(List<ERRORDto> errorDto) {
        this.errorDto = errorDto;
    }

    public ERRORLIST() {
    }

}
