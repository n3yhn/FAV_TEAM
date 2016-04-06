/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.FORM;

import com.viettel.hqmc.BO.Parameter;
import java.io.Serializable;

/**
 *
 * @author vtit_binhnt53
 */
public class ParameterForm implements Serializable {

  private Long parameterId;
  private String parameterCode;
  private String parameterName;
  private String parameterValue;
  private Long parameterDataTypeId;
  private String parameterDataTypeName;
  private Long isActive;
  private String description;

  public ParameterForm() {
  }

  public ParameterForm(Parameter para) {
  }

  public ParameterForm(Long parameterId, String parameterCode, String parameterName, String patameterValue, Long parameterDataTypeId, String parameterDataTypeName, Long isActive, String description) {
    this.parameterId = parameterId;
    this.parameterCode = parameterCode;
    this.parameterName = parameterName;
    this.parameterValue = patameterValue;
    this.parameterDataTypeId = parameterDataTypeId;
    this.parameterDataTypeName = parameterDataTypeName;
    this.isActive = isActive;
    this.description = description;
  }
  public Parameter convertToEntity(){
    
    Parameter item = new Parameter();
    item.setDescription(description);
    item.setIsActive(isActive);
    item.setParameterCode(parameterCode);
    item.setParameterDataTypeId(parameterDataTypeId);
    item.setParameterDataTypeName(parameterDataTypeName);
    item.setParameterId(parameterId);
    item.setParameterName(parameterName);
    item.setParameterValue(parameterValue);
    
    return item;
  }
  public Long getParameterId() {
    return parameterId;
  }

  public void setParameterId(Long parameterId) {
    this.parameterId = parameterId;
  }

  public String getParameterCode() {
    return parameterCode;
  }

  public void setParameterCode(String parameterCode) {
    this.parameterCode = parameterCode;
  }

  public String getParameterName() {
    return parameterName;
  }

  public void setParameterName(String parameterName) {
    this.parameterName = parameterName;
  }

  public String getPatameterValue() {
    return parameterValue;
  }

  public void setPatameterValue(String patameterValue) {
    this.parameterValue = patameterValue;
  }

  public Long getParameterDataTypeId() {
    return parameterDataTypeId;
  }

  public void setParameterDataTypeId(Long parameterDataTypeId) {
    this.parameterDataTypeId = parameterDataTypeId;
  }

  public String getParameterDataTypeName() {
    return parameterDataTypeName;
  }

  public void setParameterDataTypeName(String parameterDataTypeName) {
    this.parameterDataTypeName = parameterDataTypeName;
  }

  public Long getIsActive() {
    return isActive;
  }

  public void setIsActive(Long isActive) {
    this.isActive = isActive;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getParameterValue() {
    return parameterValue;
  }

  public void setParameterValue(String parameterValue) {
    this.parameterValue = parameterValue;
  }
  
}
