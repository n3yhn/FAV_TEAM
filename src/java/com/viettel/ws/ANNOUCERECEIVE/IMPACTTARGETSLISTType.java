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
 * <p>Java class for IMPACT_TARGETS_LISTType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="IMPACT_TARGETS_LISTType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IMPACTTARGETDto" type="{}IMPACTTARGETDtoType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IMPACT_TARGETS_LISTType", propOrder = {
    "impacttargetDto"
})
public class IMPACTTARGETSLISTType {

    @XmlElement(name = "IMPACTTARGETDto", required = true)
    protected List<IMPACTTARGETDtoType> impacttargetDto;

    /**
     * Gets the value of the impacttargetDto property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the impacttargetDto property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIMPACTTARGETDto().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link IMPACTTARGETDtoType }
     * 
     * 
     */
    public List<IMPACTTARGETDtoType> getIMPACTTARGETDto() {
        if (impacttargetDto == null) {
            impacttargetDto = new ArrayList<IMPACTTARGETDtoType>();
        }
        return this.impacttargetDto;
    }

}