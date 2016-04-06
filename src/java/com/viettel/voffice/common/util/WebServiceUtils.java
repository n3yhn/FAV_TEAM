/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.voffice.common.util;

import java.net.MalformedURLException;
import java.net.URL;
import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

/**
 *
 * @author HuanNN
 */
public class WebServiceUtils {

    public static int serverID() {
        return 2;//0:localhost  -   1:LTDV  -   2:LTCQ
    }


}
