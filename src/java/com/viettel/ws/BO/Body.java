package com.viettel.ws.BO;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Body", propOrder = {
    "content"
})
public class Body {

    public Body() {
    }

    public Body(Content content) {
        this.content = content;
    }

    @XmlElement(name = "Content")
    protected Content content;

    public Content getContent() {
        return content;
    }

    public void setContent(Content value) {
        this.content = value;
    }

}
