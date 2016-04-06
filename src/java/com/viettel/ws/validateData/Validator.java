/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.ws.validateData;

import com.viettel.voffice.common.util.StringUtils;
import com.viettel.ws.BO.ERRORDto;
import com.viettel.ws.BO.ERRORLIST;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author GPCP_BINHNT53
 */
public class Validator implements IValidator {
//Kiểm tra bắt buộc
//Kiểm tra độ dài	
//Kiểm tra định dạng số	
//kiểm tra định dạng ngày tháng	
//Kiểm tra định dạng mã số thuế
//Kiểm tra định dạng email
//Kiểm tra ký tự đặc biệt	
//Kiểm tra có trong danh mục
//Kiem  tra dang buộc 2 trường thông tin	
//Kiem tra loi nghiep vu
//Lỗi người dùng không có quyền thực hiện	lỗi thông tin được người khác đăng ký
//Lỗi kiểm tra độ dài thông điệp

    @Override
    public String getErrorCode(String ERRORCODE, String NamePaner, String NameColumn) {
        return "";
    }

    @Override
    public ERRORDto validateRequired(String nameField, String value) {
        switch (nameField.toLowerCase()) {
            case "productname":
                if (value.isEmpty()) {
                    ERRORDto eRRORDto = new ERRORDto();
                    eRRORDto.setERRORCODE("");
                    eRRORDto.setERRORID("");
                    eRRORDto.setERRORNAME("");
                    return eRRORDto;
                } else {
                    return null;
                }
            case "announcementno":
                if (value.isEmpty()) {
                    ERRORDto eRRORDto = new ERRORDto();
                    eRRORDto.setERRORCODE("");
                    eRRORDto.setERRORID("");
                    eRRORDto.setERRORNAME("");
                    return eRRORDto;
                } else {
                    return null;
                }
            case "businessname":
                if (value.isEmpty()) {
                    ERRORDto eRRORDto = new ERRORDto();
                    eRRORDto.setERRORCODE("");
                    eRRORDto.setERRORID("");
                    eRRORDto.setERRORNAME("");
                    return eRRORDto;
                } else {
                    return null;
                }
            case "businessaddress":
                if (value.isEmpty()) {
                    ERRORDto eRRORDto = new ERRORDto();
                    eRRORDto.setERRORCODE("");
                    eRRORDto.setERRORID("");
                    eRRORDto.setERRORNAME("");
                    return eRRORDto;
                } else {
                    return null;
                }
            case "businesstelephone":
                if (value.isEmpty()) {
                    ERRORDto eRRORDto = new ERRORDto();
                    eRRORDto.setERRORCODE("");
                    eRRORDto.setERRORID("");
                    eRRORDto.setERRORNAME("");
                    return eRRORDto;
                } else {
                    return null;
                }
            case "manufactureaame":
                if (value.isEmpty()) {
                    ERRORDto eRRORDto = new ERRORDto();
                    eRRORDto.setERRORCODE("");
                    eRRORDto.setERRORID("");
                    eRRORDto.setERRORNAME("");
                    return eRRORDto;
                } else {
                    return null;
                }
            case "manufactureaddress":
                if (value.isEmpty()) {
                    ERRORDto eRRORDto = new ERRORDto();
                    eRRORDto.setERRORCODE("");
                    eRRORDto.setERRORID("");
                    eRRORDto.setERRORNAME("");
                    return eRRORDto;
                } else {
                    return null;
                }
            case "matchingtarget":
                if (value.isEmpty()) {
                    ERRORDto eRRORDto = new ERRORDto();
                    eRRORDto.setERRORCODE("");
                    eRRORDto.setERRORID("");
                    eRRORDto.setERRORNAME("");
                    return eRRORDto;
                } else {
                    return null;
                }
            default:
                return null;
        }
    }

    @Override
    public ERRORDto validateLenght(String value, int lenght) {
        if (value.trim().length() > lenght) {
            ERRORDto eRRORDto = new ERRORDto();
            eRRORDto.setERRORCODE("");
            eRRORDto.setERRORID("");
            eRRORDto.setERRORNAME("");
            return eRRORDto;
        } else {
            return null;
        }
    }

    @Override
    public ERRORDto validateIsLongNumber(String value) {
        try {
            Long.parseLong(value);
            return null;
        } catch (Exception ex) {
            ERRORDto eRRORDto = new ERRORDto();
            eRRORDto.setERRORCODE("");
            eRRORDto.setERRORID("");
            eRRORDto.setERRORNAME("");
            return eRRORDto;
        }
    }

    public ERRORDto validateIsIntNumber(String value) {
        try {
            Integer.parseInt(value);
            return null;
        } catch (Exception ex) {
            ERRORDto eRRORDto = new ERRORDto();
            eRRORDto.setERRORCODE("");
            eRRORDto.setERRORID("");
            eRRORDto.setERRORNAME("");
            return eRRORDto;
        }
    }

    @Override
    public ERRORDto validateDateTime(String dateToValidate, String dateFromat) {
        if (dateToValidate == null) {
            ERRORDto eRRORDto = new ERRORDto();
            eRRORDto.setERRORCODE("");
            eRRORDto.setERRORID("");
            eRRORDto.setERRORNAME("");
        }
        SimpleDateFormat sdf = new SimpleDateFormat(dateFromat);
        sdf.setLenient(false);
        try {
            //if not valid, it will throw ParseException
            Date date = sdf.parse(dateToValidate);
        } catch (ParseException e) {
            //e.printStackTrace();
            ERRORDto eRRORDto = new ERRORDto();
            eRRORDto.setERRORCODE("");
            eRRORDto.setERRORID("");
            eRRORDto.setERRORNAME("");
        }
        return null;
    }

    @Override
    public ERRORDto validateTaxCode(String value) {
        if (value.isEmpty()) {
            ERRORDto eRRORDto = new ERRORDto();
            eRRORDto.setERRORCODE("");
            eRRORDto.setERRORID("");
            eRRORDto.setERRORNAME("");
            return eRRORDto;
        } else {
            if (value.length() == 10 || value.length() == 13) {
                return null;
            } else {
                ERRORDto eRRORDto = new ERRORDto();
                eRRORDto.setERRORCODE("");
                eRRORDto.setERRORID("");
                eRRORDto.setERRORNAME("");
                return eRRORDto;
            }
        }
    }

    @Override
    public ERRORDto validateEmailFormat() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ERRORDto validateSpecialCharacter() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ERRORDto validateInCategory() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ERRORDto validateConstraint() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ERRORDto validateBus() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ERRORDto validateRole() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ERRORDto validateMessageAlert() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static boolean isExistSpecialCharacters(String path) {
        if (StringUtils.isNullOrEmpty(path)) {
            return false;
        }
        Pattern p = Pattern.compile("[^<>!/@#$%&*(){}?]+");
        Matcher m = p.matcher(path);

        boolean matchFound = !m.matches();
        return matchFound;
    }

    public static boolean isExistOpenCloseTag(String path) {
        if (StringUtils.isNullOrEmpty(path)) {
            return false;
        }
        Pattern p = Pattern.compile("[^<>]+");
        Matcher m = p.matcher(path);

        boolean matchFound = !m.matches();
        return matchFound;
    }

    public static boolean validateMobileNumber(String mobileNumber) {
           if(mobileNumber == null)
               return false;
        Pattern p = Pattern.compile("^[0-9]{9,14}$");
        Matcher m = p.matcher(mobileNumber);

        boolean matchFound = m.matches();
        return matchFound;
           
//        mobileNumber = mobileNumber.trim();
//        final String prefix849 = "849";
//        final String prefix849plus = "+849";
//        final String prefix841 = "841";
//        final String prefix841plus = "+841";
//        final String prefix09 = "09";
//        final String prefix01 = "01";
//        boolean result = false;
//
//        if (!StringUtils.isNullOrEmpty(mobileNumber)
//                && canBePhoneNumber(mobileNumber)) {
//            int length = mobileNumber.length();
//            switch (length) {
//                case 10:
//                    if (mobileNumber.startsWith(prefix09)) {
//                        result = true;
//                    }
//                    break;
//                case 11:
//                    if (mobileNumber.startsWith(prefix01)
//                            || mobileNumber.startsWith(prefix849)) {
//                        result = true;
//                    }
//                    break;
//                case 12:
//                    if (mobileNumber.startsWith(prefix841)
//                            || mobileNumber.startsWith(prefix849plus)) {
//                        result = true;
//                    }
//                    break;
//                case 13:
//                    if (mobileNumber.startsWith(prefix841plus)) {
//                        result = true;
//                    }
//                    break;
//            }
//        }
//
    }

    public static boolean canBePhoneNumber(String phonenumber) {
        if (StringUtils.isNullOrEmpty(phonenumber)) {
            return false;
        }
        Pattern p = Pattern.compile("^(09|01|849|841|\\+849|\\+841|){0,1}[0-9]+$");
        Matcher m = p.matcher(phonenumber);

        boolean matchFound = m.matches();
        return matchFound;
    }

    public static String parseMobileNumber(String mobileNumber) {
        final String countryCode = "84";
        final String countryCodePlus = "+84";
        final String _9x = "9";
        final String _09x = "09";
        final String _1x = "1";
        final String _01x = "01";

        if (mobileNumber == null) {
            return null;
        }
        mobileNumber = mobileNumber.trim();

        if (mobileNumber.startsWith(countryCodePlus)) {
            mobileNumber = mobileNumber.substring(1);
        }

        String validatePhone = null;

        // phone start with "84" or "+84"
        if (mobileNumber.startsWith(countryCode)) {
            boolean isMobileNumber = (mobileNumber.length() == 11 && mobileNumber.substring(2).startsWith(_9x))
                    || (mobileNumber.length() == 12 && mobileNumber.substring(2).startsWith(_1x));
            if (isMobileNumber) {
                validatePhone = mobileNumber;
            }
        } // phone = "09xxxxxxxx" (like: 0987868686)
        else if (mobileNumber.length() == 10
                && mobileNumber.startsWith(_09x)) {
            validatePhone = countryCode + mobileNumber.substring(1);
        } // phone = "1xxxxxxxx" (like: 01696999999)
        else if (mobileNumber.length() == 11
                && mobileNumber.startsWith(_01x)) {
            validatePhone = countryCode + mobileNumber.substring(1);
        }

        return validatePhone;
    }

    public static boolean validateEmail(String value) {
        Pattern p = Pattern.compile("^([a-zA-Z0-9_\\.\\-])+\\@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,4})+$");
        Matcher m = p.matcher(value);

        boolean matchFound = m.matches();
        return matchFound;
    }

    public static boolean validateUrl(String value) {
        Pattern p = Pattern.compile("(http|https|ftp):\\/\\/(([A-Z0-9][A-Z0-9_-]*)(\\.[A-Z0-9][A-Z0-9_-]*)+)(:(\\d+))?\\/?");
        Matcher m = p.matcher(value);

        boolean matchFound = m.matches();
        return matchFound;
    }

    public static boolean validateNumber(String value) {
        Pattern p = Pattern.compile("^\\d+$");
        Matcher m = p.matcher(value);

        boolean matchFound = m.matches();
        return matchFound;
    }

    public static boolean validateUsername(String name) {
        Pattern p = Pattern.compile("^[A-Za-z0-9]+([_][A-Za-z0-9]+)*$");
        Matcher m = p.matcher(name);

        boolean matchFound = m.matches();
        return matchFound;
    }

    public static boolean isMobiPhoneNumber(String phoneNumber) {
        boolean match = false;
        if (canBePhoneNumber(phoneNumber)) {
            String formatedPhone = parseMobileNumber(phoneNumber);
            if (formatedPhone != null) {
                Pattern p = Pattern.compile("^84(90|93|120|121|122|126|128)[0-9]*$");
                Matcher m = p.matcher(formatedPhone);
                match = m.matches();
            }
        }
        return match;
    }

    public static boolean isEmail(String text) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);;
        Matcher matcher = pattern.matcher(text);
        return matcher.matches();

    }

    public List<ERRORDto> validateObj(Object input, Object compare) {//hàm thực hiện validate obj đối với obj đầu vào obj.
        List<ERRORDto> eRRORLIST = new ArrayList<ERRORDto>();
        try {
            for (Field fieldInput : input.getClass().getDeclaredFields()) {
                for (Field fieldCompare : compare.getClass().getDeclaredFields()) {
                    if (fieldInput.getName().toLowerCase().equals(fieldCompare.getName().toLowerCase())) {
                        //validate requied
                        ERRORDto eRRORDto = null;
                        eRRORDto = this.validateRequired(fieldInput.getName(), fieldInput.get(input).toString());
                        if (eRRORDto != null) {
                            eRRORLIST.add(eRRORDto);
                            eRRORDto = null;
                        }
                        //validate datatype
                        String dateType = fieldCompare.getType().getSimpleName();
                        eRRORDto = this.validateDateType(dateType, fieldInput.get(input).toString());
                        if (eRRORDto != null) {
                            eRRORLIST.add(eRRORDto);
                        }
                        //validate plus
                        //add to form
                    }
                }
            }
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(Validator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Validator.class.getName()).log(Level.SEVERE, null, ex);
        }
        //list all require here        
        return eRRORLIST;
    }

    public ERRORDto validateDateType(String type, String value) {//return lỗi
        ERRORDto eRRORDto = new ERRORDto();
        if (!value.isEmpty()) {
            switch (type) {
                case "int":
                    eRRORDto = this.validateIsIntNumber(value);
                    break;
                case "String":
                    return null;
                case "Long":
                    eRRORDto = this.validateIsLongNumber(value);
                    break;
                case "Date":
                    eRRORDto = this.validateDateTime(value, "dd/MM/yyyy");
                    break;
                default:
                    //System.out.println("DANH SACH");
                    return null;

            }
        } else {
            eRRORDto.setERRORCODE("");
            eRRORDto.setERRORID("");
            eRRORDto.setERRORNAME("");
            return eRRORDto;
        }
        return eRRORDto;
    }

    public static int getObject(Object obj) {
        for (Field field : obj.getClass().getDeclaredFields()) {
            try {
                //field.setAccessible(true); // if you want to modify private fields
                System.out.println(field.getName()
                        + " - " + field.getType()
                        + " - " + field.get(obj));
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(Validator.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(Validator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return 0;
    }

    public static int validateRequired(Object obj) {
//        Class<?> enclosingClass = getClass().getEnclosingClass();
//        if (enclosingClass != null) {
//            System.out.println(enclosingClass.getName());
//        } else {
//            System.out.println(getClass().getName());
//        }
        for (Field field : obj.getClass().getDeclaredFields()) {
            try {
                //field.setAccessible(true); // if you want to modify private fields
                System.out.println(field.getName()
                        + " - " + field.getType()
                        + " - " + field.get(obj));
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(Validator.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(Validator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return 0;
    }
}
