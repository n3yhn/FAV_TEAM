/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.BO;

import com.viettel.common.util.StringUtils;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vtit_binhnt53
 */
@Entity
@Table(name = "STANDARD_PRODUCT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StandardProduct.findAll", query = "SELECT s FROM StandardProduct s"),
    @NamedQuery(name = "StandardProduct.findByVietnameseStandardId", query = "SELECT s FROM StandardProduct s WHERE s.vietnameseStandardId = :vietnameseStandardId"),
    @NamedQuery(name = "StandardProduct.findByCategoryName", query = "SELECT s FROM StandardProduct s WHERE s.categoryName = :categoryName")})
public class StandardProduct implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @SequenceGenerator(name = "STANDARD_PRODUCT_SEQ", sequenceName = "STANDARD_PRODUCT_SEQ")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "STANDARD_PRODUCT_SEQ")
    @Basic(optional = false)
    @Column(name = "VIETNAMESE_STANDARD_ID")
    private Long vietnameseStandardId;
    @Column(name = "CATEGORY_NAME")
    private String categoryName;
    @JoinColumn(name = "VIETNAMESE_STANDARD_ID", referencedColumnName = "VIETNAMESE_STANDARD_ID", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private VietnameseStandard vietnameseStandard;

    public StandardProduct() {
    }

    public StandardProduct(Long vietnameseStandardId) {
        this.vietnameseStandardId = vietnameseStandardId;
    }

    public Long getVietnameseStandardId() {
        return vietnameseStandardId;
    }

    public void setVietnameseStandardId(Long vietnameseStandardId) {
        this.vietnameseStandardId = vietnameseStandardId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = StringUtils.removeEventHandlerJS(categoryName);
    }

    public VietnameseStandard getVietnameseStandard() {
        return vietnameseStandard;
    }

    public void setVietnameseStandard(VietnameseStandard vietnameseStandard) {
        this.vietnameseStandard = vietnameseStandard;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vietnameseStandardId != null ? vietnameseStandardId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StandardProduct)) {
            return false;
        }
        StandardProduct other = (StandardProduct) object;
        if ((this.vietnameseStandardId == null && other.vietnameseStandardId != null) || (this.vietnameseStandardId != null && !this.vietnameseStandardId.equals(other.vietnameseStandardId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.viettel.hqmc.BO.StandardProduct[ vietnameseStandardId=" + vietnameseStandardId + " ]";
    }
    
}
