package com.viettel.vsaadmin.client.form;

public class ObjectsForm {

    private Long appId;
    private String description;
    private Long objectId;
    private String objectName;
    private Long objectType;
    private String objectUrl;
    private Long ord;
    private Long parentId;
    private Long status;
    private String isCheck = "";
    private String roleId;
    private String appInfo;
    private String objectCode;
    private String parentIdStr = "";
    private String appName;
    private String appCode;

    public ObjectsForm() {
    }

    public ObjectsForm(Long appId, String description, Long objectId, String objectName, Long objectType, String objectUrl, Long status, String objectCode, String appName, String appCode) {
        this.appId = appId;
        this.description = description;
        this.objectId = objectId;
        this.objectName = objectName;
        this.objectType = objectType;
        this.objectUrl = objectUrl;
        this.status = status;
        this.objectCode = objectCode;
        this.appName = appName;
        this.appCode = appCode;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getObjectCode() {
        return this.objectCode;
    }

    public void setObjectCode(String objectCode) {
        this.objectCode = objectCode;
    }

    public String getParentIdStr() {
        return this.parentIdStr;
    }

    public void setParentIdStr(String parentIdStr) {
        this.parentIdStr = parentIdStr;
    }

    public String getAppInfo() {
        return this.appInfo;
    }

    public void setAppInfo(String appInfo) {
        this.appInfo = appInfo;
    }

    public String getRoleId() {
        return this.roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public Long getAppId() {
        return this.appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getObjectId() {
        return this.objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    public String getObjectName() {
        return this.objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public Long getObjectType() {
        return this.objectType;
    }

    public void setObjectType(Long objectType) {
        this.objectType = objectType;
    }

    public String getObjectUrl() {
        return this.objectUrl;
    }

    public void setObjectUrl(String objectUrl) {
        this.objectUrl = objectUrl;
    }

    public Long getOrd() {
        return this.ord;
    }

    public void setOrd(Long ord) {
        this.ord = ord;
    }

    public Long getParentId() {
        return this.parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getStatus() {
        return this.status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public String getIsCheck() {
        return this.isCheck;
    }

    public void setIsCheck(String isCheck) {
        this.isCheck = isCheck;
    }
}


/* Location:           C:\Program Files\Apache Software Foundation\TomcatVSA\webapps\vsaadminv3\WEB-INF\classes\
 * Qualified Name:     com.viettel.vsaadmin.client.form.ObjectsForm
 * JD-Core Version:    0.6.0
 */
