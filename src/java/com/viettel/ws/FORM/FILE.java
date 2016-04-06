/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.ws.FORM;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrator
 */
@XmlRootElement
public class FILE {

    private String FILE_CODE;
    private List<FILE_ITEMS> FILE_LIST;

    public FILE() {
    }

    public FILE(String FILE_CODE, List<FILE_ITEMS> FILE_LIST) {
        this.FILE_CODE = FILE_CODE;
        this.FILE_LIST = FILE_LIST;
    }

    public String getFILE_CODE() {
        return FILE_CODE;
    }

    public void setFILE_CODE(String FILE_CODE) {
        this.FILE_CODE = FILE_CODE;
    }

    public List<FILE_ITEMS> getFILE_LIST() {
        return FILE_LIST;
    }

    public void setFILE_LIST(List<FILE_ITEMS> FILE_LIST) {
        this.FILE_LIST = FILE_LIST;
    }

}
