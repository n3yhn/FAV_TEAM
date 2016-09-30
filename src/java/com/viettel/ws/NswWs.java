/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.ws;

import com.viettel.common.util.ResourceBundleUtil;
import com.viettel.ws.validateData.Helper;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

/**
 *
 * @author Administrator
 */
@WebService(serviceName = "NswWs")
public class NswWs {

    WebServiceContext wsContext;

    @WebMethod(operationName = "getMessageFromNSW")
    public String getMessageFromNSW(@WebParam(name = "name") String env) {
        MessageContext mc = wsContext.getMessageContext();
        HttpServletRequest req = (HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST);
        String Ip = req.getRemoteAddr();
        String url = ResourceBundleUtil.getString("nsw_wsdl");
        if (url.indexOf(Ip) < 0) {
            return "Người dùng không có quyền truy cập ";
        }
        Helper hp = new Helper();
        return hp.receiveMs(env);
    }
}
