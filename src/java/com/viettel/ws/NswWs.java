/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.ws;

import com.viettel.ws.validateData.Helper;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Administrator
 */
@WebService(serviceName = "NswWs")
public class NswWs {

    @WebMethod(operationName = "getMessageFromNSW")
    public String getMessageFromNSW(@WebParam(name = "name") String env) {

        Helper hp = new Helper();
        return hp.receiveMs(env);
    }
}
