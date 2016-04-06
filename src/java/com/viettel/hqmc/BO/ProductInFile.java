/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.BO;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author havm2
 */
@Entity
@Table(name = "PRODUCT_IN_FILE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductInFile.findAll", query = "SELECT p FROM ProductInFile p"),
    @NamedQuery(name = "ProductInFile.findByProductInFileId", query = "SELECT p FROM ProductInFile p WHERE p.productInFileId = :productInFileId"),
    @NamedQuery(name = "ProductInFile.findByProductName", query = "SELECT p FROM ProductInFile p WHERE p.productName = :productName"),
    @NamedQuery(name = "ProductInFile.findByComponent", query = "SELECT p FROM ProductInFile p WHERE p.component = :component"),
    @NamedQuery(name = "ProductInFile.findByMainlyTarget", query = "SELECT p FROM ProductInFile p WHERE p.mainlyTarget = :mainlyTarget"),
    @NamedQuery(name = "ProductInFile.findByManufacture", query = "SELECT p FROM ProductInFile p WHERE p.manufacture = :manufacture"),
    @NamedQuery(name = "ProductInFile.findByDeadline", query = "SELECT p FROM ProductInFile p WHERE p.deadline = :deadline"),
    @NamedQuery(name = "ProductInFile.findByPackageRecipe", query = "SELECT p FROM ProductInFile p WHERE p.packageRecipe = :packageRecipe"),
    @NamedQuery(name = "ProductInFile.findByForProduct", query = "SELECT p FROM ProductInFile p WHERE p.forProduct = :forProduct")})
public class ProductInFile implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @SequenceGenerator(name = "PRODUCT_IN_FILE_SEQ", sequenceName = "PRODUCT_IN_FILE_SEQ")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRODUCT_IN_FILE_SEQ")
    @Basic(optional = false)
    @Column(name = "PRODUCT_IN_FILE_ID")
    private Long productInFileId;
    @Column(name = "FILE_ID")
    private Long fileId;    
    @Column(name = "PRODUCT_NAME")
    private String productName;
    @Column(name = "COMPONENT")
    private String component;
    @Column(name = "MAINLY_TARGET")
    private String mainlyTarget;
    @Column(name = "MANUFACTURE")
    private String manufacture;
    @Column(name = "DEADLINE")
    private String deadline;
    @Column(name = "PACKAGE_RECIPE")
    private String packageRecipe;
    @Column(name = "FOR_PRODUCT")
    private String forProduct;
    @Column(name = "USE_FOR")
    private String useFor;

    public String getUseFor() {
        return useFor;
    }

    public void setUseFor(String useFor) {
        this.useFor = useFor;
    }

    public ProductInFile() {
    }

    public ProductInFile(Long productInFileId) {
        this.productInFileId = productInFileId;
    }

    public Long getProductInFileId() {
        return productInFileId;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public void setProductInFileId(Long productInFileId) {
        this.productInFileId = productInFileId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getMainlyTarget() {
        return mainlyTarget;
    }

    public void setMainlyTarget(String mainlyTarget) {
        this.mainlyTarget = mainlyTarget;
    }

    public String getManufacture() {
        return manufacture;
    }

    public void setManufacture(String manufacture) {
        this.manufacture = manufacture;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getPackageRecipe() {
        return packageRecipe;
    }

    public void setPackageRecipe(String packageRecipe) {
        this.packageRecipe = packageRecipe;
    }

    public String getForProduct() {
        return forProduct;
    }

    public void setForProduct(String forProduct) {
        this.forProduct = forProduct;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productInFileId != null ? productInFileId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductInFile)) {
            return false;
        }
        ProductInFile other = (ProductInFile) object;
        if ((this.productInFileId == null && other.productInFileId != null) || (this.productInFileId != null && !this.productInFileId.equals(other.productInFileId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.viettel.hqmc.BO.ProductInFile[ productInFileId=" + productInFileId + " ]";
    }
    
}
