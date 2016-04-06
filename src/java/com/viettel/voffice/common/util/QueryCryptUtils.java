/*
 * Copyright (C) 2010 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.voffice.common.util;

import com.guhesan.querycrypt.QueryCrypt;
import com.guhesan.querycrypt.beans.RequestParameterObject;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author sondm2@viettel.com.vn
 * @since Apr,12,2010
 * @version 1.0
 */
public class QueryCryptUtils {
    /** CRYPT_PREFIX.*/
    public static final String CRYPT_PREFIX = "_vt";

    /**
     * private Constructor
     */
    private QueryCryptUtils() {
    }

    /**
     *
     * @param req request
     * @param parameterName name
     * @return String
     * @throws Exception if error
     */
    public static String getParameter(HttpServletRequest req, String parameterName) throws Exception {
        String checkCrypt = req.getParameter(CRYPT_PREFIX);
        RequestParameterObject reqCrypt = null;
        if (checkCrypt != null) {
            reqCrypt = QueryCrypt.getInstance().decrypt(req);
        }

        String strResult = null;
        if (checkCrypt != null) {
            strResult = reqCrypt.getParameter(parameterName);
        } else {
            strResult = req.getParameter(parameterName);
        }

        return strResult;
    }
}
