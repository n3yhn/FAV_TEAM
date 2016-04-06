/*
 * Copyright (C) 2010 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.voffice.ca.applet;

import java.io.Serializable;

/**
 * this class contains result codes of using secure token
 * @author $sonnt38@viettel.com.vn
 * @since 1.0
 * @version 1.0
 */
public class MessageCode implements Serializable {

    /**
     * the driver of USB is not installed
     */
    public static final int NOT_INSTALLED_DRIVER = 0;
    /**
     * not found USB
     */
    public static final int NOT_FOUND_USB = 1;
    /**
     * wrong PIN
     */
    public static final int WRONG_PIN = 2;
    /**
     * get USB successfully
     */
    public static final int SUCCESS = 3;
    /**
     * return result
     */
    public static final int LOI_XAC_THUC = 4;
    public static final int LOI_KY = 5;
    public static final int LOI_EXCEPTION = 6;
    public static final int LOI_SERIAL = 7;
    public static final int BK_SUCCESS = 8;
    public static final int DA_KY_THANHCONG = 9;
    public static final int EXCEL_DA_KY = 10;
    public static final int NOT_FORMAT = 11;
    public static final int NOT_USER = 12;
    public static final int SERIAL_NOT_MATCH = 13;
    public static final int CERT_DUPLICATE = 14;
    public static final int CERT_EXPIRED = 15;
    public static final int CERT_ISVALID = 16;
    
    public String errorMsg;

    private int resultCode;

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public int getResultCode() {
        return resultCode;
    }

    /**
     * get the result message
     * @return <CODE>String</CODE> result
     */
    public String getResultString() {
        switch (this.resultCode) {
            case NOT_FOUND_USB:
                return "Kh\u00f4ng ph\u00e1t hi\u1ec7n thi\u1ebft b\u1ecb USB token";
            case WRONG_PIN:
                return "Sai m\u00e3 s\u1ed1 pin!";
            case SUCCESS:
                return "T\u1edd khai \u0111\u00e3 \u0111\u01b0\u1ee3c g\u1eedi th\u00e0nh c\u00f4ng t\u1edbi VTax";
            case BK_SUCCESS:
                return "Bảng kê đã được ký thành công";
            case LOI_XAC_THUC:
                return "C\u00f3 l\u1ed7i khi x\u00e1c th\u1ef1c ch\u1ee9ng th\u01b0 s\u1ed1";
            case LOI_SERIAL:
                return "Chứng thư số chưa được đăng ký với Vtax.";
            case DA_KY_THANHCONG:
                return "Đã ký thành công";
            case EXCEL_DA_KY:
                return "Bảng kê có nhiều hơn một chữ ký, vui lòng kiểm tra lại.";
            case NOT_FORMAT:
                return "Sai biểu mẫu tờ khai.";
            case NOT_USER:
                return "Tờ khai này không được đăng ký mã số thuế của bạn.";
            case SERIAL_NOT_MATCH:
                return "Vui lòng kiểm tra lại số serial number.";
            case CERT_DUPLICATE:
                return "Bạn phải đăng ký bằng một chứng thư số khác chứng thư cũ.";
            case CERT_EXPIRED:
                return "Chứng thư của bạn đã hết hạn sử dụng.";
            case CERT_ISVALID:
                return "Chứng thư của bạn không hợp lệ.";
            default:
                return "Có lỗi xảy ra trong quá trình ký.";
        }
    }
}
