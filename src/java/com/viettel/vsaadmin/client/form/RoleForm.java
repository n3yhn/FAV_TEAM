/* 
 * Copyright (C) 2010 Viettel Telecom. All rights reserved. 
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */ 
package com.viettel.vsaadmin.client.form;
import com.viettel.database.BO.BasicBO;

/** 
 * @author 
 * @since Mon Jan 24 11:56:26 ICT 2011
 * @version 1.0 
 */ 
public class RoleForm extends BasicBO {

	private String description;
	private String roleCode;
	private String roleName;
	private Long status;
	private Long roleId;

	public String getDescription() { 
		return description; 
	} 
	public void setDescription(String description) { 
		this.description = description; 
	} 
	public String getRoleCode() { 
		return roleCode; 
	} 
	public void setRoleCode(String roleCode) { 
		this.roleCode = roleCode; 
	} 
	public String getRoleName() { 
		return roleName; 
	} 
	public void setRoleName(String roleName) { 
		this.roleName = roleName; 
	} 
	public Long getStatus() { 
		return status; 
	} 
	public void setStatus(Long status) { 
		this.status = status; 
	} 
	public Long getRoleId() { 
		return roleId; 
	} 
	public void setRoleId(Long roleId) { 
		this.roleId = roleId; 
	} 
}