//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.05.29 at 03:13:40 PM ICT 
//


package com.viettel.ws.FORM;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PRODUCT_PHYSIC_CHEMICALType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PRODUCT_PHYSIC_CHEMICALType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TARGET_PHYSIC_CHEMICAL" type="{}TARGET_PHYSIC_CHEMICALType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PRODUCT_PHYSIC_CHEMICALType", propOrder = {
    "targetphysicchemical"
})
public class PRODUCTPHYSICCHEMICALType {

    @XmlElement(name = "TARGET_PHYSIC_CHEMICAL", required = true)
    protected List<TARGETPHYSICCHEMICALType> targetphysicchemical;

    /**
     * Gets the value of the targetphysicchemical property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the targetphysicchemical property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTARGETPHYSICCHEMICAL().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TARGETPHYSICCHEMICALType }
     * 
     * 
     */
    public List<TARGETPHYSICCHEMICALType> getTARGETPHYSICCHEMICAL() {
        if (targetphysicchemical == null) {
            targetphysicchemical = new ArrayList<TARGETPHYSICCHEMICALType>();
        }
        return this.targetphysicchemical;
    }

}
