/*     */ package com.viettel.vsaadmin.common.util;
/*     */
/*     */ public final class StringUtils /*     */ {
    /*  19 */ private static String alphabeUpCaseNumber = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    /*     */ private static final int INVOICE_MAX_LENGTH = 7;
    /*     */ private static final String ZERO = "0";
    /*     */
    /*     */ public static boolean compareString(String str1, String str2) /*     */ {
        /*  39 */ if (str1 == null) /*     */ {
            /*  41 */ str1 = "";
            /*     */        }
        /*  43 */ if (str2 == null) /*     */ {
            /*  45 */ str2 = "";
            /*     */        }
        /*     */
        /*  50 */ return str1.equals(str2);
        /*     */    }
    /*     */
    /*     */ public static String convertFromLongToString(Long lng)
            /*     */ throws Exception /*     */ {
        /*     */ try /*     */ {
            /*  62 */ return Long.toString(lng.longValue());
            /*     */        } /*     */ catch (Exception ex) /*     */ {
            System.out.println(ex.getMessage());
            throw ex;
            /*  67 */        }
        /*     */    }
    /*     */
    /*     */ public static String[] convertFromLongToString(Long[] arrLong)
            /*     */ throws Exception /*     */ {
        /*  76 */ String[] arrResult = new String[arrLong.length];
        /*     */ try /*     */ {
            /*  79 */ for (int i = 0; i < arrLong.length; i++) /*     */ {
                /*  81 */ arrResult[i] = convertFromLongToString(arrLong[i]);
                /*     */            }
            /*  83 */ return arrResult;
            /*     */        } /*     */ catch (Exception ex) /*     */ {
            System.out.println(ex.getMessage());
            throw ex;
            /*  88 */        }
        /*     */    }
    /*     */
    /*     */ public static long[] convertFromStringToLong(String[] arrStr)
            /*     */ throws Exception /*     */ {
        /*  96 */ long[] arrResult = new long[arrStr.length];
        /*     */ try {
            /*  98 */ for (int i = 0; i < arrStr.length; i++) {
                /*  99 */ arrResult[i] = Long.parseLong(arrStr[i]);
                /*     */            }
            /* 101 */ return arrResult;
            /*     */        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            throw ex;
            /* 104 */        }
        /*     */    }
    /*     */
    /*     */ public static long convertFromStringToLong(String value)
            /*     */ throws Exception /*     */ {
        /*     */ try /*     */ {
            /* 113 */ return Long.parseLong(value);
            /*     */        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            throw ex;
            /* 116 */        }
        /*     */    }
    /*     */
    /*     */ public static boolean checkAlphabeUpCaseNumber(String value) /*     */ {
        /* 126 */ boolean result = true;
        /* 127 */ for (int i = 0; i < value.length(); i++) {
            /* 128 */ String temp = value.substring(i, i + 1);
            /* 129 */ if (alphabeUpCaseNumber.indexOf(temp) == -1) {
                /* 130 */ result = false;
                /* 131 */ return result;
                /*     */            }
            /*     */        }
        /* 134 */ return result;
        /*     */    }
    /*     */
    /*     */ public static String standardInvoiceString(Long input) /*     */ {
        /* 139 */ if (input == null) /* 140 */ {
            return "";
        }
        /* 141 */ String temp = input.toString();
        /* 142 */ if (temp.length() <= 7) {
            /* 143 */ int count = 7 - temp.length();
            /* 144 */ for (int i = 0; i < count; i++) {
                /* 145 */ temp = "0" + temp;
                /*     */            }
            /*     */        }
        /* 148 */ return temp;
        /*     */    }
    /*     */
    /*     */ public static boolean validString(String temp) /*     */ {
        /* 153 */ return (temp != null) && (!temp.trim().equals(""));
        /*     */    }
    /*     */ }

/* Location:           C:\Program Files\Apache Software Foundation\TomcatVSA\webapps\vsaadminv3\WEB-INF\classes\
 * Qualified Name:     com.viettel.vsaadmin.common.util.StringUtils
 * JD-Core Version:    0.6.0
 */
