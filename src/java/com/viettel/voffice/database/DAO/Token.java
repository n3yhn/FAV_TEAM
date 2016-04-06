/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.voffice.database.DAO;

import com.viettel.database.DAO.BaseDAOMDBAction;
import viettel.passport.util.Connector;

/**
 * Struts token dao
 * @author cuongnx8@viettel.com.vn
 * @version 1.0
 * @since 1.0
 */
public class Token extends BaseDAOMDBAction {

    /**
     * Reload struts token
     * @return token
     */
    public String reloadToken() {
		String referer = getRequest().getHeader("referer");
        if (referer != null && referer.indexOf(Connector.serviceURL) != 0) {
            return "none";
        }
        return "token";
    }
}
