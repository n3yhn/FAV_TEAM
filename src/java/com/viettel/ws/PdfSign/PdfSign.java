/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.ws.PdfSign;


import java.util.Date;

/**
 *
 * @author vtit_havm2
 */

public class PdfSign {

    private String pdfName;
    private byte[] pdfFile;
    private Date serverDate;
    private byte[] cerFile;
    private byte[] fontFile;
    private byte[] imageStamp;
    private byte[] imageSign;
    private byte[] dllLoginBcy;
    private Integer indexFile;

    public PdfSign(String pdfName, byte[] pdfFile, Date serverDate, byte[] imageStamp, byte[] imageSign, byte[] dllLoginBcy, Integer indexFile) {
        this.pdfName = pdfName;
        this.pdfFile = pdfFile;
        this.serverDate = serverDate;
        this.imageStamp = imageStamp;
        this.imageSign = imageSign;
        this.dllLoginBcy = dllLoginBcy;
        this.indexFile = indexFile;
    }

    public PdfSign() {
    }

    public String getPdfName() {
        return pdfName;
    }

    public void setPdfName(String pdfName) {
        this.pdfName = pdfName;
    }

    public byte[] getPdfFile() {
        return pdfFile;
    }

    public void setPdfFile(byte[] pdfFile) {
        this.pdfFile = pdfFile;
    }

    public Date getServerDate() {
        return serverDate;
    }

    public void setServerDate(Date serverDate) {
        this.serverDate = serverDate;
    }

    public byte[] getCerFile() {
        return cerFile;
    }

    public void setCerFile(byte[] cerFile) {
        this.cerFile = cerFile;
    }

    public byte[] getFontFile() {
        return fontFile;
    }

    public void setFontFile(byte[] fontFile) {
        this.fontFile = fontFile;
    }

    public byte[] getImageStamp() {
        return imageStamp;
    }

    public void setImageStamp(byte[] imageStamp) {
        this.imageStamp = imageStamp;
    }

    public byte[] getImageSign() {
        return imageSign;
    }

    public void setImageSign(byte[] imageSign) {
        this.imageSign = imageSign;
    }

    public byte[] getDllLoginBcy() {
        return dllLoginBcy;
    }

    public void setDllLoginBcy(byte[] dllLoginBcy) {
        this.dllLoginBcy = dllLoginBcy;
    }

    public Integer getIndexFile() {
        return indexFile;
    }

    public void setIndexFile(Integer indexFile) {
        this.indexFile = indexFile;
    }
    
}
