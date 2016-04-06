/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.ws.validateData;

import com.viettel.ws.BO.ERRORDto;

/**
 *
 * @author GPCP_BINHNT53
 */
public interface IValidator {

    public String getErrorCode(String ERRORCODE, String NamePaner, String NameColumn);

    //Kiểm tra bắt buộc
    public ERRORDto validateRequired(String nameField ,String value);
//Kiểm tra độ dài	

    public ERRORDto validateLenght(String value, int lenght);
//Kiểm tra định dạng số	

    public ERRORDto validateIsLongNumber(String value);
//kiểm tra định dạng ngày tháng	

    public ERRORDto validateDateTime(String dateToValidate, String dateFromat);
//Kiểm tra định dạng mã số thuế

    public ERRORDto validateTaxCode(String value);
//Kiểm tra định dạng email

    public ERRORDto validateEmailFormat();
//Kiểm tra ký tự đặc biệt	

    public ERRORDto validateSpecialCharacter();
//Kiểm tra có trong danh mục

    public ERRORDto validateInCategory();
//Kiem  tra dang buộc 2 trường thông tin	

    public ERRORDto validateConstraint();
//Kiem tra loi nghiep vu

    public ERRORDto validateBus();
//Lỗi người dùng không có quyền thực hiện	lỗi thông tin được người khác đăng ký

    public ERRORDto validateRole();
//Lỗi kiểm tra độ dài thông điệp

    public ERRORDto validateMessageAlert();
}
