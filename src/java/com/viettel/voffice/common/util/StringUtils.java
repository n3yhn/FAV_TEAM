/*
 * Copyright (C) 2010 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.voffice.common.util;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import com.viettel.common.util.LogUtil;

/**
 *
 * @author thienkq1@viettel.com.vn
 * @since 12,Apr,2010
 * @version 1.0
 */
public final class StringUtils {

    /**
     * alphabeUpCaseNumber.
     */
    private static String alphabeUpCaseNumber = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    /**
     * INVOICE_MAX_LENGTH.
     */
    private static final int INVOICE_MAX_LENGTH = 7;
    /**
     * ZERO.
     */
    private static final String ZERO = "0";

    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(StringUtils.class);

    /**
     * Creates a new instance of StringUtils
     */
    private StringUtils() {
    }

    /**
     * method compare two string
     *
     * @param str1 String
     * @param str2 String
     * @return boolean
     */
    public static boolean compareString(String str1, String str2) {
        if (str1 == null) {
            str1 = "";
        }
        if (str2 == null) {
            str2 = "";
        }

        if (str1.equals(str2)) {
            return true;
        }
        return false;
    }

    /**
     * method convert long to string
     *
     * @param lng Long
     * @return String
     * @throws abc Exception
     */
    public static String convertFromLongToString(Long lng) throws Exception {
        try {
            return Long.toString(lng);
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            throw ex;
        }
    }

    /*
     *  @todo: convert from Long array to String array
     */
    public static String[] convertFromLongToString(Long[] arrLong) throws Exception {
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

    /*
     *  @todo: convert from String array to Long array
     */
    public static long[] convertFromStringToLong(String[] arrStr) throws Exception {
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

    /*
     *  @todo: convert from String value to Long value
     */
    public static long convertFromStringToLong(String value) throws Exception {
        try {
            return Long.parseLong(value);
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            throw ex;
        }
    }


    /*
     * Check String that containt only AlphabeUpCase and Number
     * Return True if String was valid, false if String was not valid
     */
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
        String temp;
        if (input == null) {
            return "";
        }
        temp = input.toString();
        if (temp.length() <= INVOICE_MAX_LENGTH) {
            int count = INVOICE_MAX_LENGTH - temp.length();
            for (int i = 0; i < count; i++) {
                temp = ZERO + temp;
            }
        }
        return temp;
    }

    public static boolean validString(String temp) {
        if (temp == null || "".equals(temp.trim())) {
            return false;
        }
        return true;
    }

    /*
     * @author: huannn
     * @purpose: escape all special character in SQL parameter of query string
     * @input: parameter to escape
     * @return: escaped string
     */
    public static String escapeSql(String input) {

        if (isNullOrEmpty(input)) {
            return "";
        }
        input = input.toLowerCase();

        return input.trim().replace("/", "//").replace("_", "/_").replace("%", "/%");
    }

    /*
     * @author: huannn
     * @purpose: xac dinh chuoi la null hoac rong
     */
    public static boolean isNullOrEmpty(String input) {

        if (input == null || "".equals(input.trim())) {
            return true;
        }

        return false;
    }

    public static String returnBlankIfNull(String input) {
        if (input == null) {
            return "";
        }

        return input;
    }

    public static String escapeHTML(String aText) {
        if (aText == null) {
            return null;
        }
        final StringBuilder result = new StringBuilder();
        final StringCharacterIterator iterator = new StringCharacterIterator(aText);
        char character = iterator.current();
        while (character != CharacterIterator.DONE) {
            if (character == '<') {
                result.append("&lt;");
            } else if (character == '>') {
                result.append("&gt;");
            } else if (character == '&') {
                result.append("&amp;");
            } else if (character == '\"') {
                result.append("&quot;");
            } else if (character == '\t') {
                addCharEntity(9, result);
            } //            } else if (character == '!') {
            ////                addCharEntity(33, result);
            //            } else if (character == '#') {
            ////                addCharEntity(35, result);
            //            } else if (character == '$') {
            ////                addCharEntity(36, result);
            //            } else if (character == '%') {
            ////                addCharEntity(37, result);
            else if (character == '\'') {
                addCharEntity(39, result);
            }// else if (character == '(') {
            ////                addCharEntity(40, result);
            //            } else if (character == ')') {
            ////                addCharEntity(41, result);
            //            } else if (character == '*') {
            ////                addCharEntity(42, result);
            //            } else if (character == '+') {
            ////                addCharEntity(43, result);
            //            } else if (character == ',') {
            ////                addCharEntity(44, result);
            //            } else if (character == '-') {
            ////                addCharEntity(45, result);
            //            } else if (character == '.') {
            ////                addCharEntity(46, result);
            //            } else if (character == '/') {
            ////                addCharEntity(47, result);
            //            } else if (character == ':') {
            ////                addCharEntity(58, result);
            //            } else if (character == ';') {
            ////                addCharEntity(59, result);
            //            } else if (character == '=') {
            ////                addCharEntity(61, result);
            //            } else if (character == '?') {
            ////                addCharEntity(63, result);
            //            } else if (character == '@') {
            ////                addCharEntity(64, result);
            //            } else if (character == '[') {
            ////                addCharEntity(91, result);
            //            } else if (character == '\\') {
            ////                addCharEntity(92, result);
            //            } else if (character == ']') {
            ////                addCharEntity(93, result);
            //            } else if (character == '^') {
            ////                addCharEntity(94, result);
            //            } else if (character == '_') {
            ////                addCharEntity(95, result);
            //            } else if (character == '`') {
            ////                addCharEntity(96, result);
            //            } else if (character == '{') {
            ////                addCharEntity(123, result);
            //            } else if (character == '|') {
            ////                addCharEntity(124, result);
            //            } else if (character == '}') {
            ////                addCharEntity(125, result);
            //            } else if (character == '~') {
            ////                addCharEntity(126, result);
            //            } 
            else {
                //the char is not a special one
                //add it to the result as is
                result.append(character);
            }
            character = iterator.next();
        }
        return result.toString();
    }

    private static void addCharEntity(Integer aIdx, StringBuilder aBuilder) {
        String padding = "";
        if (aIdx <= 9) {
            padding = "00";
        } else if (aIdx <= 99) {
            padding = "0";
        } else {
            //no prefix
        }
        String number = padding + aIdx.toString();
        aBuilder.append("&#" + number + ";");
    }
}
