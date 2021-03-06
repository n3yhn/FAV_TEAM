package com.viettel.ws.BO;

import java.lang.*;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Java class for ProfilePublishedCosmetics complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="ProfilePublishedCosmetics">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="NSWFileCode" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="StatusCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="CreateDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="ModifyDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="TemplateNotiCosmeticProduct" type="{}TemplateNotiCosmeticProduct"/>
 *         &lt;element name="Attachment" type="{}Attachment"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProfilePublishedCosmetics", propOrder = {
    "nswFileCode",
    "taxCode",
    "statusCode",
    "createDate",
    "modifyDate"
})
public class ProfilePublishedCosmetics {

    @XmlElement(name = "NSWFileCode", required = true)
    protected Long nswFileCode;
    @XmlElement(name = "TaxCode")
    protected String taxCode;
    @XmlElement(name = "StatusCode")
    protected int statusCode;
    @XmlElement(name = "CreateDate", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createDate;
    @XmlElement(name = "ModifyDate", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar modifyDate;
//    @XmlElement(name = "Attachment", required = true)
//    protected List<Attachment> attachment;

    /**
     * Gets the value of the nswFileCode property.
     *
     * @return possible object is {@link Long }
     *
     */
    public Long getNSWFileCode() {
        return nswFileCode;
    }

    /**
     * Sets the value of the nswFileCode property.
     *
     * @param value allowed object is {@link Long }
     *
     */
    public void setNSWFileCode(Long value) {
        this.nswFileCode = value;
    }

    /**
     * Gets the value of the statusCode property.
     *
     */
    public int getStatusCode() {
        return statusCode;
    }

    /**
     * Sets the value of the statusCode property.
     *
     */
    public void setStatusCode(int value) {
        this.statusCode = value;
    }

    /**
     * Gets the value of the createDate property.
     *
     * @return possible object is {@link XMLGregorianCalendar }
     *
     */
    public XMLGregorianCalendar getCreateDate() {
        return createDate;
    }

    /**
     * Sets the value of the createDate property.
     *
     * @param value allowed object is {@link XMLGregorianCalendar }
     *
     */
    public void setCreateDate(XMLGregorianCalendar value) {
        this.createDate = value;
    }

    /**
     * Gets the value of the modifyDate property.
     *
     * @return possible object is {@link XMLGregorianCalendar }
     *
     */
    public XMLGregorianCalendar getModifyDate() {
        return modifyDate;
    }

    /**
     * Sets the value of the modifyDate property.
     *
     * @param value allowed object is {@link XMLGregorianCalendar }
     *
     */
    public void setModifyDate(XMLGregorianCalendar value) {
        this.modifyDate = value;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public ProfilePublishedCosmetics() {
    }

    public ProfilePublishedCosmetics(Long nswFileCode, String taxCode, int statusCode, XMLGregorianCalendar createDate, XMLGregorianCalendar modifyDate) {
        this.nswFileCode = nswFileCode;
        this.taxCode = taxCode;
        this.statusCode = statusCode;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
    }

}
