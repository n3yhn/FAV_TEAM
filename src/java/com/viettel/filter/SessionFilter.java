/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.filter;

import com.viettel.common.util.LogUtil;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import viettel.passport.util.Connector;

/**
 *
 * @author gpdn_havm2
 * @version 1.0
 * @since 1.0
 */
public final class SessionFilter implements Filter {

    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(SessionFilter.class);

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = null;
        HttpServletResponse res = null;
        if (request instanceof HttpServletRequest) {
            req = (HttpServletRequest) request;
        }
        if (response instanceof HttpServletResponse) {
            res = (HttpServletResponse) response;
        }

        Connector cnn = new Connector(req, res);
        req.setAttribute("VSA-IsPassedVSAFilter", "True");

        try {
            if (req.getRequestURL().toString().contains("uploadFile")
                    || req.getRequestURL().toString().endsWith("HomePage.do")
                    || req.getRequestURL().toString().endsWith("filesAction!toLookUpHomePage.do")
                    || req.getRequestURL().toString().endsWith("filesAction!onSearchLookUpHomePage.do")
                    || req.getRequestURL().toString().endsWith("ProcedurePage.do")
                    || req.getRequestURL().toString().endsWith("ContactPage.do")
                    || req.getRequestURL().toString().endsWith("GuideRegisterHtmlPage.do")
                    || req.getRequestURL().toString().endsWith("uploadiframe!openFileUserAttachPdf.do")
                    || req.getRequestURL().toString().endsWith("RegisterPage.do")
                    || req.getRequestURL().toString().endsWith("ResetPasswordPage.do")
                    || req.getRequestURL().toString().endsWith("GuideLinePage.do")
                    || req.getRequestURL().toString().endsWith("VideoGuideLinePage.do")
                    || req.getRequestURL().toString().endsWith("openFileSignPublic.do")
                    || req.getRequestURL().toString().endsWith("registerCreateAction!onInsertHomePage.do")
                    || req.getRequestURL().toString().endsWith("KeypayPage.do")) {
                chain.doFilter(request, response);
                return;
            } else if (!cnn.isAuthenticate()) {
                // Chua dang nhap
                if (cnn.hadTicket()) {
                    // Chua dang nhap, co ticket
                    if (!cnn.getAuthenticate()) {
                        // Thuc hien validate khong thanh cong
                        String redirectUrl = cnn.getErrorUrl() + "?errorCode=" + "AuthenticateFailure";
                        req.setAttribute("VSA-IsPassedVSAFilter", "False");
                        res.setHeader("VSA-Flag", "InPageRedirect");
                        res.setHeader("VSA-Location", redirectUrl);
                        if (req.getHeader("X-Requested-With") != null && req.getHeader("X-Requested-With").length() > 0) {
                            // La request ajax => Cho qua
                            chain.doFilter(request, response);
                        } else {
                            // Redirect to error page
                            res.sendRedirect(redirectUrl);
                        }
                    } else {
                        // Validate thanh cong
                        String redirectUrl = req.getRequestURL().toString() + "Index.do";
                        res.setHeader("VSA-Location", redirectUrl);
                        res.sendRedirect(redirectUrl);
                        chain.doFilter(req, res);
                    }
                } else {
                    //logSave("1. req.getRequestURL() = " + req.getRequestURL() + "; cnn.hadTicket() = false");
                    String[] urlSplit = null;
                    if (req.getRequestURL() != null) {
                        urlSplit = req.getRequestURL().toString().split("/");
                    }
                    if (req.getRequestURL() != null && urlSplit != null && urlSplit.length == 3) {
                        String redirectUrl = req.getRequestURL().toString() + "HomePage.do";
                        req.setAttribute("VSA-IsPassedVSAFilter", "False");
                        res.setHeader("VSA-Flag", "InPageRedirect");
                        res.setHeader("VSA-Location", redirectUrl);
                        res.sendRedirect(redirectUrl);
                    } else {
                        // Chua dang nhap, khong co ticket => redirect toi trang dang nhap tren Passport
                        String redirectUrl = cnn.getPassportLoginURL() + "?appCode=" + cnn.getDomainCode() + "&service=" + URLEncoder.encode(cnn.getServiceURL(), "UTF-8");
                        req.setAttribute("VSA-IsPassedVSAFilter", "False");
                        res.setHeader("VSA-Flag", "InPageRedirect");
                        res.setHeader("VSA-Location", redirectUrl);
                        res.sendRedirect(redirectUrl);
                    }
                }
            } else {
                //logSave("2. req.getRequestURL() = " + req.getRequestURL() + "; cnn.isAuthenticate() = true");
                String[] urlSplit = null;
                if (req.getRequestURL() != null) {
                    urlSplit = req.getRequestURL().toString().split("/");
                }
                if (req.getRequestURL() != null && urlSplit != null && urlSplit.length == 3) {
                    String redirectUrl = req.getRequestURL().toString() + "Login.do";
                    res.setHeader("VSA-Location", redirectUrl);
                    res.sendRedirect(redirectUrl);
                    chain.doFilter(request, res);
                } else {
                    chain.doFilter(request, response);
                }
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            //logSave("SessionFilter, chi tiết lỗi: " + ex.getMessage());
            // EventLogDAOHE edhe = new EventLogDAOHE();
            // edhe.insertEventLog("SessionFilter", "Chi tiết lỗi: " + ex.getMessage(), baseDao.getRequest());
            String redirectUrl = cnn.getPassportLoginURL() + "?appCode=" + cnn.getDomainCode() + "&service=" + URLEncoder.encode(cnn.getServiceURL(), "UTF-8");
            req.setAttribute("VSA-IsPassedVSAFilter", "False");
            res.setHeader("VSA-Flag", "InPageRedirect");
            res.setHeader("VSA-Location", redirectUrl);
            res.sendRedirect(redirectUrl);
        }
    }

    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void destroy() {
    }

    /**
     * Check SessionFilter
     *
     * @author DuND1
     * @version 1.0
     * @since 1.0
     */
    public void logSave1(String content) {
        try {
            File file = new File("c://Temp/logATTP.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedReader br = new BufferedReader(new FileReader(file.getAbsoluteFile()));
            StringBuilder sb = new StringBuilder();
            try {
                String line = br.readLine();

                while (line != null) {
                    sb.append(line);
                    sb.append(System.lineSeparator());
                    line = br.readLine();
                }
            } finally {
                br.close();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            sb.append(System.lineSeparator());
            sb.append((new Date()).toString() + ": " + content);
            bw.write(sb.toString());
            bw.close();

        } catch (IOException ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
    }
}
