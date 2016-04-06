/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.BO;

import com.viettel.hqmc.DAOHE.DetailProductDAOHE;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.persistence.SequenceGenerator;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 *
 * @author vtit_havm2
 */
@Entity
@Table(name = "DETAIL_PRODUCT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetailProduct.findAll", query = "SELECT d FROM DetailProduct d"),
    @NamedQuery(name = "DetailProduct.findByDetailProductId", query = "SELECT d FROM DetailProduct d WHERE d.detailProductId = :detailProductId"),
    @NamedQuery(name = "DetailProduct.findByProductTypeName", query = "SELECT d FROM DetailProduct d WHERE d.productTypeName = :productTypeName"),
    @NamedQuery(name = "DetailProduct.findByProductSmell", query = "SELECT d FROM DetailProduct d WHERE d.productSmell = :productSmell"),
    @NamedQuery(name = "DetailProduct.findByProductStatus", query = "SELECT d FROM DetailProduct d WHERE d.productStatus = :productStatus"),
    @NamedQuery(name = "DetailProduct.findByProductColor", query = "SELECT d FROM DetailProduct d WHERE d.productColor = :productColor"),
    @NamedQuery(name = "DetailProduct.findByProductOtherStatus", query = "SELECT d FROM DetailProduct d WHERE d.productOtherStatus = :productOtherStatus"),
    @NamedQuery(name = "DetailProduct.findByComponents", query = "SELECT d FROM DetailProduct d WHERE d.components = :components"),
    @NamedQuery(name = "DetailProduct.findByUseage", query = "SELECT d FROM DetailProduct d WHERE d.useage = :useage"),
    @NamedQuery(name = "DetailProduct.findByTimeInUse", query = "SELECT d FROM DetailProduct d WHERE d.timeInUse = :timeInUse"),
    @NamedQuery(name = "DetailProduct.findByObjectUse", query = "SELECT d FROM DetailProduct d WHERE d.objectUse = :objectUse"),
    @NamedQuery(name = "DetailProduct.findByGuideline", query = "SELECT d FROM DetailProduct d WHERE d.guideline = :guideline"),
    @NamedQuery(name = "DetailProduct.findByPreservation", query = "SELECT d FROM DetailProduct d WHERE d.preservation = :preservation"),
    @NamedQuery(name = "DetailProduct.findByPackateMaterial", query = "SELECT d FROM DetailProduct d WHERE d.packateMaterial = :packateMaterial"),
    @NamedQuery(name = "DetailProduct.findByPackageRecipe", query = "SELECT d FROM DetailProduct d WHERE d.packageRecipe = :packageRecipe"),
    @NamedQuery(name = "DetailProduct.findByProductionProcess", query = "SELECT d FROM DetailProduct d WHERE d.productionProcess = :productionProcess"),
    @NamedQuery(name = "DetailProduct.findByCounterfeitDistinctive", query = "SELECT d FROM DetailProduct d WHERE d.counterfeitDistinctive = :counterfeitDistinctive"),
    @NamedQuery(name = "DetailProduct.findBySignDate", query = "SELECT d FROM DetailProduct d WHERE d.signDate = :signDate"),
    @NamedQuery(name = "DetailProduct.findBySigner", query = "SELECT d FROM DetailProduct d WHERE d.signer = :signer")})
public class DetailProduct implements Serializable {

    private static final long serialVersionUID = 1L;
    @SequenceGenerator(name = "DETAIL_PRODUCT_SEQ", sequenceName = "DETAIL_PRODUCT_SEQ")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DETAIL_PRODUCT_SEQ")
    @Basic(optional = false)
    @Column(name = "DETAIL_PRODUCT_ID")
    private Long detailProductId;
    @Column(name = "PRODUCT_TYPE")
    private Long productType;
    @Column(name = "PRODUCT_TYPE_NAME")
    private String productTypeName;
    @Column(name = "PRODUCT_SMELL")
    private String productSmell;
    @Column(name = "PRODUCT_STATUS")
    private String productStatus;
    @Column(name = "PRODUCT_COLOR")
    private String productColor;
    @Column(name = "PRODUCT_OTHER_STATUS")
    private String productOtherStatus;
    @Column(name = "COMPONENTS")
    private String components;
    @Column(name = "USEAGE")
    private String useage;
    @Column(name = "TIME_IN_USE")
    private String timeInUse;
    @Column(name = "OBJECT_USE")
    private String objectUse;
    @Column(name = "GUIDELINE")
    private String guideline;
    @Column(name = "PRESERVATION")
    private String preservation;
    @Column(name = "PACKATE_MATERIAL")
    private String packateMaterial;
    @Column(name = "PACKAGE_RECIPE")
    private String packageRecipe;
    @Column(name = "PRODUCTION_PROCESS")
    private String productionProcess;
    @Column(name = "COUNTERFEIT_DISTINCTIVE")
    private String counterfeitDistinctive;
    @Column(name = "SIGN_DATE")
    @Temporal(TemporalType.DATE)
    private Date signDate;
    @Column(name = "SIGNER")
    private String signer;
    @Column(name = "ORIGIN")
    private String origin;
    @Column(name = "PRODUCT_NO")
    private String productNo;
    @Column(name = "OTHER_TARGET")
    private String otherTarget;
    @Column(name = "IS_TEMP")
    private Long isTemp;
    @Column(name = "ORIGINAL_ID")
    private Long originalId;
    @Column(name = "CHEMICAL_TARGET_UNWANTED")
    private String chemicalTargetUnwanted;
    @Column(name = "VERSION")
    private Long version;
    @Column(name = "LAST_IS_TEMP")
    private Long lastIsTemp;
//U150123 BINHNT53
    @Column(name = "DATE_OF_MANUFACTURE")
    @Temporal(TemporalType.DATE)
    private Date dateOfManufacture;
    @Column(name = "NET_WEIGHT")
    private String netWeight;
    @Column(name = "RECOMMEND_AND_WARNING")
    private String recommendAndWarning;
//!U150123

    public DetailProduct() {
    }

    public DetailProduct(Long detailProductId) {
        this.detailProductId = detailProductId;
    }

    public DetailProduct cloneEntity(DetailProduct original) {
        DetailProduct entity = new DetailProduct();

        //entity.setDetailProductId(original.getDetailProductId());
        entity.setProductType(original.getProductType());
        entity.setProductTypeName(original.getProductTypeName());
        entity.setProductSmell(original.getProductSmell());
        entity.setProductStatus(original.getProductStatus());
        entity.setProductColor(original.getProductColor());
        entity.setProductOtherStatus(original.getProductOtherStatus());
        entity.setComponents(original.getComponents());
        entity.setUseage(original.getUseage());
        entity.setTimeInUse(original.getTimeInUse());
        entity.setObjectUse(original.getObjectUse());
        entity.setGuideline(original.getGuideline());
        entity.setPreservation(original.getPreservation());
        entity.setPackateMaterial(original.getPackateMaterial());
        entity.setPackageRecipe(original.getPackageRecipe());
        entity.setProductionProcess(original.getProductionProcess());
        entity.setCounterfeitDistinctive(original.getCounterfeitDistinctive());
        entity.setSignDate(original.getSignDate());
        entity.setSigner(original.getSigner());
        entity.setOrigin(original.getOrigin());
        entity.setProductNo(original.getProductNo());
        entity.setOtherTarget(original.getOtherTarget());
        entity.setIsTemp(1L);
        entity.setOriginalId(original.getDetailProductId());
        //U150123 BINHNT53
//        entity.setDateOfManufacture(original.getDateOfManufacture());
//        entity.setRecommendAndWarning(original.getRecommendAndWarning());
//        entity.setNetWeight(original.getNetWeight());
        //!U150123
        entity.setLastIsTemp(1L);
        DetailProductDAOHE anndaohe = new DetailProductDAOHE();
        entity.setVersion(anndaohe.getCountVersion(original.getDetailProductId()));
        return entity;
    }

    public DetailProduct cloneEntity() {
        return cloneEntity(this);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detailProductId != null ? detailProductId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetailProduct)) {
            return false;
        }
        DetailProduct other = (DetailProduct) object;
        if ((this.detailProductId == null && other.detailProductId != null) || (this.detailProductId != null && !this.detailProductId.equals(other.detailProductId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.viettel.hqmc.BO.DetailProduct[ detailProductId=" + detailProductId + " ]";
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

    public String getPackageRecipe() {
        return packageRecipe;
    }

    public void setPackageRecipe(String packageRecipe) {
        this.packageRecipe = packageRecipe;
    }

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

    public Long getIsTemp() {
        return isTemp;
    }

    public void setIsTemp(Long isTemp) {
        this.isTemp = isTemp;
    }

    public Long getOriginalId() {
        return originalId;
    }

    public void setOriginalId(Long originalId) {
        this.originalId = originalId;
    }

    // ham luong hoa chat khong mong muon bo sung ngay 30.06.2014 - hieptq
    public String getChemicalTargetUnwanted() {
        return chemicalTargetUnwanted;
    }

    public void setChemicalTargetUnwanted(String chemicalTargetUnwanted) {
        this.chemicalTargetUnwanted = chemicalTargetUnwanted;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Long getLastIsTemp() {
        return lastIsTemp;
    }

    public void setLastIsTemp(Long lastIsTemp) {
        this.lastIsTemp = lastIsTemp;
    }
//U150123 BINHNT53

    public Date getDateOfManufacture() {
        return dateOfManufacture;
    }

    public void setDateOfManufacture(Date dateOfManufacture) {
        this.dateOfManufacture = dateOfManufacture;
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
//!U150123
}
