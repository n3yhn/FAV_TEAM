/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.vsaadmin.database.BO;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author gpdn_havm2
 */
@Entity
@Table(name = "DEPARTMENT")
@NamedQueries({
    @NamedQuery(name = "Department.findAll", query = "SELECT d FROM Department d"),
    @NamedQuery(name = "Department.findByDeptId", query = "SELECT d FROM Department d WHERE d.deptId = :deptId"),
    @NamedQuery(name = "Department.findByTelephone", query = "SELECT d FROM Department d WHERE d.telephone = :telephone"),
    @NamedQuery(name = "Department.findByStatus", query = "SELECT d FROM Department d WHERE d.status = :status"),
    @NamedQuery(name = "Department.findByDeptName", query = "SELECT d FROM Department d WHERE d.deptName = :deptName"),
    @NamedQuery(name = "Department.findByAddress", query = "SELECT d FROM Department d WHERE d.address = :address"),
    @NamedQuery(name = "Department.findByDescription", query = "SELECT d FROM Department d WHERE d.description = :description"),
    @NamedQuery(name = "Department.findByDeptCode", query = "SELECT d FROM Department d WHERE d.deptCode = :deptCode"),
    @NamedQuery(name = "Department.findByCreateDate", query = "SELECT d FROM Department d WHERE d.createDate = :createDate"),
    @NamedQuery(name = "Department.findByTin", query = "SELECT d FROM Department d WHERE d.tin = :tin"),
    @NamedQuery(name = "Department.findByEmail", query = "SELECT d FROM Department d WHERE d.email = :email"),
    @NamedQuery(name = "Department.findByContactName", query = "SELECT d FROM Department d WHERE d.contactName = :contactName"),
    @NamedQuery(name = "Department.findByContactTitle", query = "SELECT d FROM Department d WHERE d.contactTitle = :contactTitle"),
    @NamedQuery(name = "Department.findByFax", query = "SELECT d FROM Department d WHERE d.fax = :fax"),
    @NamedQuery(name = "Department.findByTel", query = "SELECT d FROM Department d WHERE d.tel = :tel"),
    @NamedQuery(name = "Department.findByLocationId", query = "SELECT d FROM Department d WHERE d.locationId = :locationId"),
    @NamedQuery(name = "Department.findByDeptLevel", query = "SELECT d FROM Department d WHERE d.deptLevel = :deptLevel"),
    @NamedQuery(name = "Department.findByIp", query = "SELECT d FROM Department d WHERE d.ip = :ip")})
public class Department implements Serializable {

    private static final long serialVersionUID = 1L;
    @SequenceGenerator(name = "DEPARTMENT_SEQ", sequenceName = "DEPARTMENT_SEQ")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DEPARTMENT_SEQ")
    @Basic(optional = false)
    @Column(name = "DEPT_ID", unique = true, nullable = false)
    private Long deptId;
    @Column(name = "TELEPHONE")
    private String telephone;
    @Basic(optional = false)
    @Column(name = "STATUS")
    private Long status;
    @Basic(optional = false)
    @Column(name = "DEPT_NAME")
    private String deptName;
    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "DESCRIPTION")
    private String description;
    @Basic(optional = false)
    @Column(name = "DEPT_CODE")
    private String deptCode;
    @Column(name = "CREATE_DATE")
    @Temporal(TemporalType.DATE)
    private Date createDate;
    @Column(name = "TIN")
    private String tin;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "CONTACT_NAME")
    private String contactName;
    @Column(name = "CONTACT_TITLE")
    private String contactTitle;
    @Column(name = "FAX")
    private String fax;
    @Column(name = "TEL")
    private String tel;
    @Column(name = "LOCATION_ID")
    private Long locationId;
    @Column(name = "DEPT_LEVEL")
    private String deptLevel;
    @Column(name = "IP")
    private String ip;
//    @OneToMany(mappedBy = "parentId")
//    private Collection<Department> departmentCollection;

//    @JoinColumn(name = "PARENT_ID", referencedColumnName = "DEPT_ID")
//    @ManyToOne
//    private Department parent;
//    @JoinColumn(name = "DEPT_TYPE_ID", referencedColumnName = "DEPT_TYPE_ID")
//    @ManyToOne(optional = false)
//    private DeptType deptType;
//    @Column(name = "PARENT_ID", insertable = false, updatable = false)
//    private Long parentId;
//    @Column(name = "DEPT_TYPE_ID", insertable = false, updatable = false)
//    private Long deptTypeId;
    @Column(name = "PARENT_ID")
    private Long parentId;
    @Column(name = "DEPT_TYPE_ID")
    private Long deptTypeId;
    @Column(name = "PROVINCE_ID")
    private Long provinceId;
    @Column(name = "PROVINCE_NAME")
    private String provinceName;

    public Department() {
    }

    public Department(Long deptId) {
        this.deptId = deptId;
    }

    public Department(Long deptId, Long status, String deptName, String deptCode) {
        this.deptId = deptId;
        this.status = status;
        this.deptName = deptName;
        this.deptCode = deptCode;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status; 
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getTin() {
        return tin;
    }

    public void setTin(String tin) {
        this.tin = tin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactTitle() {
        return contactTitle;
    }

    public void setContactTitle(String contactTitle) {
        this.contactTitle = contactTitle;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public String getDeptLevel() {
        return deptLevel;
    }

    public void setDeptLevel(String deptLevel) {
        this.deptLevel = deptLevel;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

//    public Collection<Department> getDepartmentCollection() {
//        return departmentCollection;
//    }
//
//    public void setDepartmentCollection(Collection<Department> departmentCollection) {
//        this.departmentCollection = departmentCollection;
//    }

//    public Department getParent() {
//        return parent;
//    }
//
//    public void setParent(Department parentId) {
//        this.parent = parentId;
//    }
//
//    public DeptType getDeptType() {
//        return deptType;
//    }
//
//    public void setDeptType(DeptType deptTypeId) {
//        this.deptType = deptTypeId;
//    }
    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getDeptTypeId() {
        return deptTypeId;
    }

    public void setDeptTypeId(Long deptTypeId) {
        this.deptTypeId = deptTypeId;
    }

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (deptId != null ? deptId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Department)) {
            return false;
        }
        Department other = (Department) object;
        if ((this.deptId == null && other.deptId != null) || (this.deptId != null && !this.deptId.equals(other.deptId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.viettel.hvct.database.BO.Department[deptId=" + deptId + "]";
    }
}
