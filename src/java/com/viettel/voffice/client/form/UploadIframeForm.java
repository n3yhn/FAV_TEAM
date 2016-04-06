/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.voffice.client.form;

import java.io.File;

/**
 *
 * @author cn_longh
 */
public class UploadIframeForm {

    private String fileName;
    private File file;

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = com.viettel.common.util.StringUtils.MyConvertString(fileName);
    }
}
