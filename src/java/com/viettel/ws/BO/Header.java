//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.11.24 at 11:29:34 AM ICT 
//
package com.viettel.ws.BO;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for Header complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="Header">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Reference" type="{}Reference"/>
 *         &lt;element name="From" type="{}From"/>
 *         &lt;element name="To" type="{}To"/>
 *         &lt;element name="Subject" type="{}Subject"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Header", propOrder = {
    "reference",
    "from",
    "to",
    "subject"
})
public class Header {

    public Header() {
    }

    public Header(Reference reference, From from, To to, Subject subject) {
        this.reference = reference;
        this.from = from;
        this.to = to;
        this.subject = subject;
    }

    @XmlElement(name = "Reference", required = true)
    protected Reference reference;
    @XmlElement(name = "From", required = true)
    protected From from;
    @XmlElement(name = "To", required = true)
    protected To to;
    @XmlElement(name = "Subject", required = true)
    protected Subject subject;

    /**
     * Gets the value of the reference property.
     *
     * @return possible object is {@link Reference }
     *
     */
    public Reference getReference() {
        return reference;
    }

    /**
     * Sets the value of the reference property.
     *
     * @param value allowed object is {@link Reference }
     *
     */
    public void setReference(Reference value) {
        this.reference = value;
    }

    /**
     * Gets the value of the from property.
     *
     * @return possible object is {@link From }
     *
     */
    public From getFrom() {
        return from;
    }

    /**
     * Sets the value of the from property.
     *
     * @param value allowed object is {@link From }
     *
     */
    public void setFrom(From value) {
        this.from = value;
    }

    /**
     * Gets the value of the to property.
     *
     * @return possible object is {@link To }
     *
     */
    public To getTo() {
        return to;
    }

    /**
     * Sets the value of the to property.
     *
     * @param value allowed object is {@link To }
     *
     */
    public void setTo(To value) {
        this.to = value;
    }

    /**
     * Gets the value of the subject property.
     *
     * @return possible object is {@link Subject }
     *
     */
    public Subject getSubject() {
        return subject;
    }

    /**
     * Sets the value of the subject property.
     *
     * @param value allowed object is {@link Subject }
     *
     */
    public void setSubject(Subject value) {
        this.subject = value;
    }

}
