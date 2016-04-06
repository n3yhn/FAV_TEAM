package com.viettel.vsaadmin.client.form;

import com.viettel.common.util.StringUtils;
import com.viettel.vsaadmin.database.BO.Department;
import java.util.List;

public class DepartmentForm {

    private String address;
    private String deptCode;
    private String contactName;
    private String contactTitle;
    private String createDate;
    private Long deptId;
    private String deptName;
    private String description;
    private String email;
    private String fax;
    private String locationId;
    private String status;
    private String tel;
    private String telephone;
    private String tin;
    private Long deptTypeId;
    private Long parentId;
    private String userId;
    private String deptParentName;
    private Long provinceId;
    private String provinceName;
    // Thong tin ho so cua don vi
    private List recordList;
    private int stt;

    public int getStt() {
        return stt;
    }

    public DepartmentForm() {
    }

    public DepartmentForm(Department entity) {
        if (entity == null) {
            return;
        }
        this.deptId = entity.getDeptId();

        this.deptName = entity.getDeptName();
        deptName = StringUtils.escapeHtml(deptName);
        if (deptName.length() > 300) {
            deptName = deptName.substring(0, 299);
        }
        this.parentId = entity.getParentId();
        this.description = entity.getDescription();
        this.status = entity.getStatus().toString();
        this.address = entity.getAddress();
        this.deptCode = entity.getDeptCode();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
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

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getTin() {
        return tin;
    }

    public void setTin(String tin) {
        this.tin = tin;
    }

    public Long getDeptTypeId() {
        return deptTypeId;
    }

    public void setDeptTypeId(Long deptTypeId) {
        this.deptTypeId = deptTypeId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDeptParentName() {
        return deptParentName;
    }

    public void setDeptParentName(String deptParentName) {
        this.deptParentName = deptParentName;
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

    public List getRecordList() {
        return recordList;
    }

    public void setRecordList(List recordList) {
        this.recordList = recordList;
    }
}

/* Location:           C:\Program Files\Apache Software Foundation\TomcatVSA\webapps\vsaadminv3\WEB-INF\classes\
 * Qualified Name:     com.viettel.vsaadmin.client.form.DepartmentForm
 * JD-Core Version:    0.6.0
 */
