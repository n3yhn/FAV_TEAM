package com.kpay.action;

import com.kpay.security.HashFunction;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Hant on 07-Apr-14.
 */
public class Do extends javax.servlet.http.HttpServlet {

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        Map<String, String> fields = new HashMap<String, String>();

        fields.put("version", request.getParameter("version"));
        fields.put("current_locale", request.getParameter("current_locale"));
        fields.put("command", request.getParameter("command"));
        fields.put("merchant_trans_id", request.getParameter("merchant_trans_id"));
        fields.put("merchant_code", request.getParameter("merchant_code"));
        fields.put("country_code", request.getParameter("country_code"));
        fields.put("good_code", request.getParameter("good_code"));
        fields.put("net_cost", request.getParameter("net_cost"));
        fields.put("ship_fee", request.getParameter("ship_fee"));
        fields.put("tax", request.getParameter("tax"));
        fields.put("service_code", "720");
        fields.put("currency_code", request.getParameter("currency_code"));
        fields.put("return_url", request.getParameter("return_url"));

        HashFunction hf = new HashFunction();
        ResourceBundle rb = ResourceBundle.getBundle("config");
        String url_redirect = rb.getString("online_keypay");
        String transKey = rb.getString("transkey");
        String secure_hash = hf.hashAllFields(fields, transKey);
        //secure_hash = hf.hashAllFields(fields,"41025070");

        //String url_redirect = "http://online.keypay.vn/process?";
//        String url_redirect = "http://113.160.58.22:5555/keypay/process?";
        Map params = request.getParameterMap();
        Iterator i = params.keySet().iterator();
        String param = "";
        while (i.hasNext()) {
            String key = (String) i.next();
            String value = ((String[]) params.get(key))[0];
            param += key + "=" + URLEncoder.encode(value) + "&";
        }
        url_redirect += param + "secure_hash=" + secure_hash;

        response.sendRedirect(url_redirect);
    }
}
