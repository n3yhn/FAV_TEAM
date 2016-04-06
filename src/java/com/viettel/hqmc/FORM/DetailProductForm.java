/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.FORM;

import com.viettel.common.util.DateTimeUtils;
import com.viettel.hqmc.BO.DetailProduct;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author vtit_havm2
 */
public class DetailProductForm implements Serializable {

    private Long detailProductId;
    private Long productType;
    private String productTypeName;
    private String productSmell;
    private String productStatus;
    private String productColor;
    private String productOtherStatus;
    private String components;
    private String useage;
    private String timeInUse;
    private String objectUse;
    private String guideline;
    private String preservation;
    private String packateMaterial;
    //private String packageRecipe;
    private String productionProcess;
    private String counterfeitDistinctive;
    private Date signDate;
    private String signer;
    private String origin;
    private String productNo;
    private String otherTarget;
    private String chemicalTargetUnwanted;
    //U150123 BINHNT53
    private Date dateOfManufacture;
    private String netWeight;
    private String recommendAndWarning;
    //!U150123 BINHNT53
    private String dateOfManufactureStr;

    public DetailProductForm() {
    }

    public DetailProductForm(DetailProduct entity) {
        if (entity == null) {
            return;
        }
        detailProductId = entity.getDetailProductId();
        productType = entity.getProductType();
        productTypeName = entity.getProductTypeName();
        productSmell = entity.getProductSmell();
        productStatus = entity.getProductStatus();
        productColor = entity.getProductColor();
        productOtherStatus = entity.getProductOtherStatus();
        components = entity.getComponents();
        useage = entity.getUseage();
        timeInUse = entity.getTimeInUse();
        objectUse = entity.getObjectUse();
        guideline = entity.getGuideline();
        preservation = entity.getPreservation();
        packateMaterial = entity.getPackateMaterial();
        //packageRecipe = entity.getPackageRecipe();
        productionProcess = entity.getProductionProcess();
        counterfeitDistinctive = entity.getCounterfeitDistinctive();
        signDate = entity.getSignDate();
        signer = entity.getSigner();
        origin = entity.getOrigin();
        productNo = entity.getProductNo();
        otherTarget = entity.getOtherTarget();
        chemicalTargetUnwanted = entity.getChemicalTargetUnwanted();
        //U150123 BINHNT53
//        dateOfManufacture = entity.getDateOfManufacture();
//        netWeight = entity.getNetWeight();
//        recommendAndWarning = entity.getRecommendAndWarning();
        //U150123 BINHNT53
    }

    public DetailProduct convertToEntity() {
        DetailProduct entity = new DetailProduct();
        entity.setDetailProductId(detailProductId);
        entity.setProductType(productType);
        entity.setProductTypeName(productTypeName);
        entity.setProductSmell(productSmell);
        entity.setProductStatus(productStatus);
        entity.setProductColor(productColor);
        entity.setProductOtherStatus(productOtherStatus);
        entity.setComponents(components);
        entity.setUseage(useage);
        entity.setTimeInUse(timeInUse);
        entity.setObjectUse(objectUse);
        entity.setGuideline(guideline);
        entity.setPreservation(preservation);
        entity.setPackateMaterial(packateMaterial);
        //entity.setPackageRecipe(packageRecipe);
        entity.setProductionProcess(productionProcess);
        entity.setCounterfeitDistinctive(counterfeitDistinctive);
        entity.setSignDate(signDate);
        entity.setSigner(signer);
        entity.setOrigin(origin);
        entity.setProductNo(productNo);
        entity.setOtherTarget(otherTarget);
        entity.setChemicalTargetUnwanted(chemicalTargetUnwanted);
        //U150123 BINHNT53
//        entity.setRecommendAndWarning(recommendAndWarning);
//        entity.setDateOfManufacture(dateOfManufacture);
//        entity.setNetWeight(netWeight);
        //U150123 BINHNT53
        return entity;
    }

    public DetailProductForm(Long detailProductId) {
        this.detailProductId = detailProductId;
    }

    public Long getDetailProductId() {
        return detailProductId;
    }

    public void setDetailProductId(Long detailProductId) {
        this.detailProductId = detailProductId;
    }

    public Long getProductType() {
        return productType;
    }

    public void setProductType(Long productType) {
        this.productType = productType;
    }

    public String getProductTypeName() {
        return productTypeName;
    }

    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }

    public String getProductSmell() {
        return productSmell;
    }

    public void setProductSmell(String productSmell) {
        this.productSmell = productSmell;
    }

    public String getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(String productStatus) {
        this.productStatus = productStatus;
    }

    public String getProductColor() {
        return productColor;
    }

    public void setProductColor(String productColor) {
        this.productColor = productColor;
    }

    public String getProductOtherStatus() {
        return productOtherStatus;
    }

    public void setProductOtherStatus(String productOtherStatus) {
        this.productOtherStatus = productOtherStatus;
    }

    public String getComponents() {
        return components;
    }

    public void setComponents(String components) {
        this.components = components;
    }

    public String getUseage() {
        return useage;
    }

    public void setUseage(String useage) {
        this.useage = useage;
    }

    public String getTimeInUse() {
        return timeInUse;
    }

    public void setTimeInUse(String timeInUse) {
        this.timeInUse = timeInUse;
    }

    public String getObjectUse() {
        return objectUse;
    }

    public void setObjectUse(String objectUse) {
        this.objectUse = objectUse;
    }

    public String getGuideline() {
        return guideline;
    }

    public void setGuideline(String guideline) {
        this.guideline = guideline;
    }

    public String getPreservation() {
        return preservation;
    }

    public void setPreservation(String preservation) {
        this.preservation = preservation;
    }

    public String getPackateMaterial() {
        return packateMaterial;
    }

    public void setPackateMaterial(String packateMaterial) {
        this.packateMaterial = packateMaterial;
    }

//    public String getPackageRecipe() {
//        return packageRecipe;
//    }
//
//    public void setPackageRecipe(String packageRecipe) {
//        this.packageRecipe = packageRecipe;
//    }
    public String getProductionProcess() {
        return productionProcess;
    }

    public void setProductionProcess(String productionProcess) {
        this.productionProcess = productionProcess;
    }

    public String getCounterfeitDistinctive() {
        return counterfeitDistinctive;
    }

    public void setCounterfeitDistinctive(String counterfeitDistinctive) {
        this.counterfeitDistinctive = counterfeitDistinctive;
    }

    public Date getSignDate() {
        return signDate;
    }

    public void setSignDate(Date signDate) {
        this.signDate = signDate;
    }

    public String getSignDateStr() {
        if (signDate == null) {
            return "";
        }
        return DateTimeUtils.convertDateToString(signDate, "dd/MM/yyyy");
    }

    public String getSigner() {
        return signer;
    }

    public void setSigner(String signer) {
        this.signer = signer;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public String getOtherTarget() {
        return otherTarget;
    }

    public void setOtherTarget(String otherTarget) {
        this.otherTarget = otherTarget;
    }

    public String getChemicalTargetUnwanted() {
        return chemicalTargetUnwanted;
    }

    public void setChemicalTargetUnwanted(String chemicalTargetUnwanted) {
        this.chemicalTargetUnwanted = chemicalTargetUnwanted;
    }

    //U150123 BINHNT53
    //U150123 BINHNT53
    public Date getDateOfManufacture() {
        return dateOfManufacture;
    }

    public void setDateOfManufacture(Date dateOfManufacture) {
        this.dateOfManufacture = dateOfManufacture;
    }

    public String getDateOfManufactureStr() {
        if (dateOfManufacture == null) {
            return "";
        }
        return DateTimeUtils.convertDateToString(dateOfManufacture, "dd/MM/yyyy");
    }

    public void setDateOfManufactureStr(String dateOfManufactureStr) {
        this.dateOfManufactureStr = dateOfManufactureStr;
    }

    public String getNetWeight() {
        return netWeight;
    }

    public void setNetWeight(String netWeight) {
        this.netWeight = netWeight;
    }

    public String getRecommendAndWarning() {
        return recommendAndWarning;
    }

    public void setRecommendAndWarning(String recommendAndWarning) {
        this.recommendAndWarning = recommendAndWarning;
    }
}
