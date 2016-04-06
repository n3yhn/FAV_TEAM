/*
 * Copyright (C) 2010 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.voffice.common.util;

/**
 *
 * @author huannn
 * @since 13,Nov,2011
 * @version 1.0
 */
public final class NumberUtils {

    /** Creates a new instance of StringUtils */
    private NumberUtils() {
    }

    /*
     * @author: huannn
     * @purpose: convert sang dang so int
     */
    public static int convertStringToInt(String input) {

        int reVal = 0;
        try {
            reVal = Integer.parseInt(input);
        } catch (Exception ex) {
            reVal = 0;
        }
        return reVal;
    }

    /*
     * @author: huannn
     * @purpose: convert sang dang so long
     */
    public static long convertStringToLong(String input) {

        long reVal = 0;
        try {
            reVal = Long.parseLong(input);
        } catch (Exception ex) {
            reVal = 0;
        }
        return reVal;
    }
}
