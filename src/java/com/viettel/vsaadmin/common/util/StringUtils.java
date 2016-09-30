package com.viettel.vsaadmin.common.util;

import com.viettel.common.util.LogUtil;

public final class StringUtils {

    private static String alphabeUpCaseNumber = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int INVOICE_MAX_LENGTH = 7;
    private static final String ZERO = "0";

    public static boolean compareString(String str1, String str2) {
        if (str1 == null) {
            str1 = "";
        }
        if (str2 == null) {
            str2 = "";
        }

        return str1.equals(str2);
    }

    public static String convertFromLongToString(Long lng)
            throws Exception {
        try {
            return Long.toString(lng.longValue());
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            throw ex;
        }
    }

    public static String[] convertFromLongToString(Long[] arrLong)
            throws Exception {
        String[] arrResult = new String[arrLong.length];
        try {
            for (int i = 0; i < arrLong.length; i++) {
                arrResult[i] = convertFromLongToString(arrLong[i]);
            }
            return arrResult;
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            throw ex;
        }
    }

    public static long[] convertFromStringToLong(String[] arrStr)
            throws Exception {
        long[] arrResult = new long[arrStr.length];
        try {
            for (int i = 0; i < arrStr.length; i++) {
                arrResult[i] = Long.parseLong(arrStr[i]);
            }
            return arrResult;
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            throw ex;
        }
    }

    public static long convertFromStringToLong(String value)
            throws Exception {
        try {
            return Long.parseLong(value);
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            throw ex;
        }
    }

    public static boolean checkAlphabeUpCaseNumber(String value) {
        boolean result = true;
        for (int i = 0; i < value.length(); i++) {
            String temp = value.substring(i, i + 1);
            if (alphabeUpCaseNumber.indexOf(temp) == -1) {
                result = false;
                return result;
            }
        }
        return result;
    }

    public static String standardInvoiceString(Long input) {
        if (input == null) /* 140 */ {
            return "";
        }
        String temp = input.toString();
        if (temp.length() <= 7) {
            int count = 7 - temp.length();
            for (int i = 0; i < count; i++) {
                temp = "0" + temp;
            }
        }
        return temp;
    }

    public static boolean validString(String temp) {
        return (temp != null) && (!temp.trim().equals(""));
    }
}

/* Location:           C:\Program Files\Apache Software Foundation\TomcatVSA\webapps\vsaadminv3\WEB-INF\classes\
 * Qualified Name:     com.viettel.vsaadmin.common.util.StringUtils
 * JD-Core Version:    0.6.0
 */
