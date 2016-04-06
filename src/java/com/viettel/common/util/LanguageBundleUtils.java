/*
 * Copyright (C) 2010 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.common.util;

import java.util.ResourceBundle;
import java.util.Locale;

/**
 *
 * @author thangdd8@viettel.com.vn
 * @since Apr 12, 2010
 * @version 1.0
 */
public class LanguageBundleUtils {

    private LanguageBundleUtils() {
    }
    /** RESOURCE.*/
    private static final String RESOURCE = "com/viettel/config/Language";
    /** local.*/
    private static Locale local = null;
    /** languageRb.*/
    private static ResourceBundle languageRb = null;

    /** .*/
    /**
     * getString
     * @param key String
     * @return value
     */
    public static String getString(String key) {
        if (local != null) {
            languageRb = ResourceBundle.getBundle(RESOURCE, local);
        } else {
            languageRb = ResourceBundle.getBundle(RESOURCE);
        }
        return languageRb.getString(key);
    }
}
