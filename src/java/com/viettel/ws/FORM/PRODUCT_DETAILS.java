/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.ws.FORM;

import com.viettel.hqmc.BO.DetailProduct;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrator
 */
@XmlRootElement
public class PRODUCT_DETAILS {

    private String PRODUCT_SMELL;
    private String PRODUCT_STATUS;
    private String PRODUCT_COLOR;
    private String PRODUCT_OTHER_STATUS;
    private String COMPONENTS;
    private String TIME_IN_USE;
    private String USEAGE;
    private String GUIDELINE;
    private String PRESERVATION;
    private String PACKATE_MATERIAL;
    private String PACKAGE_RECIPE;
    private String PRODUCTION_PROCESS;
    private String PRODUCT_COMPARE;

    public PRODUCT_DETAILS() {
    }

    public PRODUCT_DETAILS(String PRODUCT_SMELL, String PRODUCT_STATUS, String PRODUCT_COLOR, String PRODUCT_OTHER_STATUS, String COMPONENTS, String TIME_IN_USE, String USEAGE, String GUIDELINE, String PRESERVATION, String PACKATE_MATERIAL, String PACKAGE_RECIPE, String PRODUCTION_PROCESS, String PRODUCT_COMPARE) {
        this.PRODUCT_SMELL = PRODUCT_SMELL;
        this.PRODUCT_STATUS = PRODUCT_STATUS;
        this.PRODUCT_COLOR = PRODUCT_COLOR;
        this.PRODUCT_OTHER_STATUS = PRODUCT_OTHER_STATUS;
        this.COMPONENTS = COMPONENTS;
        this.TIME_IN_USE = TIME_IN_USE;
        this.USEAGE = USEAGE;
        this.GUIDELINE = GUIDELINE;
        this.PRESERVATION = PRESERVATION;
        this.PACKATE_MATERIAL = PACKATE_MATERIAL;
        this.PACKAGE_RECIPE = PACKAGE_RECIPE;
        this.PRODUCTION_PROCESS = PRODUCTION_PROCESS;
        this.PRODUCT_COMPARE = PRODUCT_COMPARE;
    }

    public String getCOMPONENTS() {
        return COMPONENTS;
    }

    public void setCOMPONENTS(String COMPONENTS) {
        this.COMPONENTS = COMPONENTS;
    }

    public String getTIME_IN_USE() {
        return TIME_IN_USE;
    }

    public void setTIME_IN_USE(String TIME_IN_USE) {
        this.TIME_IN_USE = TIME_IN_USE;
    }

    public String getUSEAGE() {
        return USEAGE;
    }

    public void setUSEAGE(String USEAGE) {
        this.USEAGE = USEAGE;
    }

    public String getGUIDELINE() {
        return GUIDELINE;
    }

    public void setGUIDELINE(String GUIDELINE) {
        this.GUIDELINE = GUIDELINE;
    }

    public String getPRESERVATION() {
        return PRESERVATION;
    }

    public void setPRESERVATION(String PRESERVATION) {
        this.PRESERVATION = PRESERVATION;
    }

    public String getPACKATE_MATERIAL() {
        return PACKATE_MATERIAL;
    }

    public void setPACKATE_MATERIAL(String PACKATE_MATERIAL) {
        this.PACKATE_MATERIAL = PACKATE_MATERIAL;
    }

    public String getPACKAGE_RECIPE() {
        return PACKAGE_RECIPE;
    }

    public void setPACKAGE_RECIPE(String PACKAGE_RECIPE) {
        this.PACKAGE_RECIPE = PACKAGE_RECIPE;
    }

    public String getPRODUCTION_PROCESS() {
        return PRODUCTION_PROCESS;
    }

    public void setPRODUCTION_PROCESS(String PRODUCTION_PROCESS) {
        this.PRODUCTION_PROCESS = PRODUCTION_PROCESS;
    }

    public String getPRODUCT_COMPARE() {
        return PRODUCT_COMPARE;
    }

    public void setPRODUCT_COMPARE(String PRODUCT_COMPARE) {
        this.PRODUCT_COMPARE = PRODUCT_COMPARE;
    }

    public String getPRODUCT_SMELL() {
        return PRODUCT_SMELL;
    }

    public void setPRODUCT_SMELL(String PRODUCT_SMELL) {
        this.PRODUCT_SMELL = PRODUCT_SMELL;
    }

    public String getPRODUCT_STATUS() {
        return PRODUCT_STATUS;
    }

    public void setPRODUCT_STATUS(String PRODUCT_STATUS) {
        this.PRODUCT_STATUS = PRODUCT_STATUS;
    }

    public String getPRODUCT_COLOR() {
        return PRODUCT_COLOR;
    }

    public void setPRODUCT_COLOR(String PRODUCT_COLOR) {
        this.PRODUCT_COLOR = PRODUCT_COLOR;
    }

    public String getPRODUCT_OTHER_STATUS() {
        return PRODUCT_OTHER_STATUS;
    }

    public void setPRODUCT_OTHER_STATUS(String PRODUCT_OTHER_STATUS) {
        this.PRODUCT_OTHER_STATUS = PRODUCT_OTHER_STATUS;
    }

    public DetailProduct wsToBO() {
        DetailProduct bo = new DetailProduct();

        bo.setProductSmell(PRODUCT_SMELL);
        bo.setProductStatus(PRODUCT_STATUS);
        bo.setProductColor(PRODUCT_COLOR);
        bo.setProductOtherStatus(PRODUCT_OTHER_STATUS);

        bo.setDetailProductId(Long.MIN_VALUE);
        bo.setProductType(Long.MIN_VALUE);
        bo.setProductTypeName(PRODUCT_SMELL);

        bo.setComponents(PRODUCT_SMELL);
        bo.setUseage(PRODUCT_SMELL);
        bo.setTimeInUse(PRODUCT_SMELL);
        bo.setObjectUse(PRODUCT_SMELL);
        bo.setGuideline(PRODUCT_SMELL);
        bo.setPreservation(PRODUCT_SMELL);
        bo.setPackateMaterial(PRODUCT_SMELL);
        //bo.setPackageRecipe(PRODUCT_SMELL);
        bo.setProductionProcess(PRODUCT_SMELL);
        bo.setCounterfeitDistinctive(PRODUCT_SMELL);
        bo.setSignDate(null);
        bo.setSigner(PRODUCT_SMELL);
        bo.setOrigin(PRODUCT_SMELL);
        bo.setProductNo(PRODUCT_SMELL);
        bo.setOtherTarget(PRODUCT_SMELL);
        bo.setIsTemp(Long.MIN_VALUE);
        bo.setOriginalId(Long.MIN_VALUE);
        return bo;
    }
}
