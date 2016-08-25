package com.viettel.ws.BO;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Envelope")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Envelope", propOrder = {
    "header",
    "body"
})
public class Envelope {

    public Envelope() {
    }

    public Envelope(Header header, Body body) {
        this.header = header;
        this.body = body;
    }

    @XmlElement(name = "Header", required = true)
    protected Header header;
    @XmlElement(name = "Body", required = true)
    protected Body body;

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header value) {
        this.header = value;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body value) {
        this.body = value;
    }

}
