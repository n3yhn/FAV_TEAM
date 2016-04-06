/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.voffice.client.form;

import com.viettel.common.util.StringUtils;
import com.viettel.flow.BO.Flow;
import java.io.Serializable;

/**
 *
 * @author vtit_havm2
 */
public class FlowForm implements Serializable {
    private Long flowId;
    private String flowName;
    private Long deptId;
    private String deptName;
    private String description;
    private Long flowType;
    private String flowTypeName;
    private Long isActive;

    public FlowForm() {
    }

    public FlowForm(Long flowId) {
        this.flowId = flowId;
    }

    public FlowForm(Long flowId, String flowName, long deptId, long flowType) {
        this.flowId = flowId;
        this.flowName = flowName;
        this.deptId = deptId;
        this.flowType = flowType;
    }
    
    public FlowForm(Flow entity) {
        if(entity == null){
            return;
        }
        this.flowId = entity.getFlowId();
        this.flowName = entity.getFlowName();
        this.deptId = entity.getDeptId();
        this.deptName = entity.getDeptName();
        this.flowType = entity.getFlowType();
        this.flowTypeName = entity.getFlowTypeName();
        this.description = entity.getDescription();
        this.isActive = 1L;
    }
    
    public Flow convertToEntity(){
        Flow entity = new Flow();
        entity.setFlowId(flowId);
        entity.setFlowName(flowName);
        entity.setFlowType(flowType);
        entity.setFlowTypeName(flowTypeName);
        entity.setDeptId(deptId);
        entity.setDeptName(deptName);
        entity.setDescription(description);
        entity.setIsActive(1L);
        return entity;
    }
    

    public Long getFlowId() {
        return flowId;
    }

    public void setFlowId(Long flowId) {
        this.flowId = flowId;
    }

    public String getFlowName() {
        return flowName;
    }

    public void setFlowName(String flowName) {
        this.flowName = flowName;
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

    public Long getFlowType() {
        return flowType;
    }

    public void setFlowType(Long flowType) {
        this.flowType = flowType;
    }

    public String getFlowTypeName() {
        return flowTypeName;
    }

    public void setFlowTypeName(String flowTypeName) {
        this.flowTypeName = flowTypeName;
    }

    public Long getIsActive() {
        return isActive;
    }

    public void setIsActive(Long isActive) {
        this.isActive = isActive;
    }

}
