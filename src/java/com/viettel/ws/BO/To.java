package com.viettel.ws.BO;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;



@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "To", propOrder = {
    "name",
    "identity",
    "countryCode",
    "ministryCode",
    "organizationCode",
    "unitCode"
})
public class To {

    public To() {
    }

    public To(String name, String identity) {
        this.name = name;
        this.identity = identity;
//        this.countryCode = countryCode;
//        this.ministryCode = ministryCode;
//        this.organizationCode = organizationCode;
//        this.unitCode = unitCode;
    }

    @XmlElement(required = true)
    protected String name;
    protected String identity;
    @XmlElement(required = true)
    protected String countryCode;
    @XmlElement(required = true)
    protected String ministryCode;
    @XmlElement(required = true)
    protected String organizationCode;
    @XmlElement(required = true)
    protected String unitCode;

    public String getName() {
        return name;
    }
   
    public void setName(String value) {
        this.name = value;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String value) {
        this.identity = value;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String value) {
        this.countryCode = value;
    }

    public String getMinistryCode() {
        return ministryCode;
    }

    public void setMinistryCode(String value) {
        this.ministryCode = value;
    }

    public String getOrganizationCode() {
        return organizationCode;
    }

    public void setOrganizationCode(String value) {
        this.organizationCode = value;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String value) {
        this.unitCode = value;
    }

}
