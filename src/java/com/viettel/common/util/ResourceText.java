/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.common.util;

import java.util.ResourceBundle;
import java.util.Locale;

/**
 *
 * @author Ebaymark
 */
public class ResourceText {

    private static final String RESOURCE = "com/viettel/config/Language";
    private static Locale local = null;
    private static volatile ResourceBundle languageRb;

    public static String getString(String key) {
        if (local != null) {
            languageRb = ResourceBundle.getBundle(RESOURCE, local);
        } else {
            languageRb = ResourceBundle.getBundle(RESOURCE);
        }
        return languageRb.getString(key);
    }
}
