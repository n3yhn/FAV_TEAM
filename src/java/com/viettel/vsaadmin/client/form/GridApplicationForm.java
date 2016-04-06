/*    */ package com.viettel.vsaadmin.client.form;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.List;
/*    */ 
/*    */ public class GridApplicationForm
/*    */   implements Serializable
/*    */ {
/*    */   private List appCode;
/*    */   private List appName;
/*    */   private List description;
/*    */   private List status;
/*    */   private List appId;
/*    */   private List isCheck;
/*    */ 
/*    */   public List getAppCode()
/*    */   {
/* 24 */     return this.appCode;
/*    */   }
/*    */ 
/*    */   public void setAppCode(List appCode) {
/* 28 */     this.appCode = appCode;
/*    */   }
/*    */ 
/*    */   public List getAppId() {
/* 32 */     return this.appId;
/*    */   }
/*    */ 
/*    */   public void setAppId(List appId) {
/* 36 */     this.appId = appId;
/*    */   }
/*    */ 
/*    */   public List getAppName() {
/* 40 */     return this.appName;
/*    */   }
/*    */ 
/*    */   public void setAppName(List appName) {
/* 44 */     this.appName = appName;
/*    */   }
/*    */ 
/*    */   public List getDescription() {
/* 48 */     return this.description;
/*    */   }
/*    */ 
/*    */   public void setDescription(List description) {
/* 52 */     this.description = description;
/*    */   }
/*    */ 
/*    */   public List getIsCheck() {
/* 56 */     return this.isCheck;
/*    */   }
/*    */ 
/*    */   public void setIsCheck(List isCheck) {
/* 60 */     this.isCheck = isCheck;
/*    */   }
/*    */ 
/*    */   public List getStatus() {
/* 64 */     return this.status;
/*    */   }
/*    */ 
/*    */   public void setStatus(List status) {
/* 68 */     this.status = status;
/*    */   }
/*    */ }

/* Location:           C:\Program Files\Apache Software Foundation\TomcatVSA\webapps\vsaadminv3\WEB-INF\classes\
 * Qualified Name:     com.viettel.vsaadmin.client.form.GridApplicationForm
 * JD-Core Version:    0.6.0
 */