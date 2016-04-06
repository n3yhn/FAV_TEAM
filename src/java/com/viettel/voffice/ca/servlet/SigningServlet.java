/*
 * Copyright (C) 2010 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.voffice.ca.servlet;

import com.viettel.voffice.ca.applet.CommDataBean;
import com.viettel.voffice.ca.applet.MessageCode;
import com.viettel.voffice.ca.uds.VTKeyStore;
import com.viettel.voffice.ca.uds.X509KeySelector;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.security.Provider;
import java.security.cert.Certificate;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.X509CRL;
import java.util.Iterator;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import javax.xml.crypto.*;
import javax.xml.crypto.dsig.*;
import javax.xml.crypto.dsig.dom.DOMValidateContext;
import javax.xml.crypto.dsig.keyinfo.*;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import sun.security.x509.X509CertImpl;
import viettel.passport.client.UserToken;

public class SigningServlet extends HttpServlet {

    Locale locale = new Locale("en", "US");
    ResourceBundle bundle = ResourceBundle.getBundle("cas", locale);
    private CommDataBean fromClientBean;
    VTKeyStore keystore = null;
    static Logger log = Logger.getLogger(SigningServlet.class);

    public CommDataBean getFromClientBean() {
        return fromClientBean;
    }

    public void setFromClientBean(CommDataBean fromClientBean) {
        this.fromClientBean = fromClientBean;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CommDataBean toClientBean = new CommDataBean();
        try {
            UserToken userToken = (UserToken) request.getSession().getAttribute("userToken");
            fromClientBean = getSigntureData(request);
            Certificate[] nntCertChain = fromClientBean.getCertChain();

            if (fromClientBean != null && fromClientBean.getType().equals(CommDataBean.TYPE_INIT)) {

                VTKeyStore vtKeyStore = new VTKeyStore();
                keystore = vtKeyStore.getKeyStore(request, vtKeyStore.getIssuerCert(nntCertChain));
                Certificate[] certificateChain = vtKeyStore.getCertChain(keystore);
                certificateChain = vtKeyStore.createNNTCertChain(certificateChain, nntCertChain);
                fromClientBean.setCertChain(certificateChain);
                sendToClient(response, fromClientBean);
            } else if (fromClientBean != null && fromClientBean.getType().equals(CommDataBean.TYPE_SIGN_BAN_POSITION)) {

                KeyInfo keyInfo = validateContentSigned(fromClientBean.getContent());
                String userSigned = null;
                if (keyInfo != null) {
                    try {
                        userSigned = getUserSigned(keyInfo);
                        fromClientBean.setCode(MessageCode.DA_KY_THANHCONG);
                        long id = getObjectId(fromClientBean.getContent(), "jpBanPositionId");
//                        }
                    } catch (CertificateExpiredException expiredEx) {
                        fromClientBean.setCode(MessageCode.CERT_EXPIRED);
                    } catch (CertificateNotYetValidException notYetValidEx) {
                        fromClientBean.setCode(MessageCode.CERT_ISVALID);
                    }
                } else {
                    fromClientBean.setCode(MessageCode.LOI_KY);
                }

                sendToClient(response, fromClientBean);
            }
        } catch (Exception ex) {
            fromClientBean.setCode(MessageCode.LOI_KY);
            try {
                sendToClient(response, toClientBean);
            } catch (Exception ex1) {
                log.error(ex1);
            }
            log.error(ex.getMessage());
        }
    }

    public String getUserSigned(KeyInfo keyInfo) throws Exception {
        String userSigned = "";
        Iterator iter = keyInfo.getContent().iterator();
        X509CertImpl certImpl = null;
        while (iter.hasNext()) {
            XMLStructure kiType = (XMLStructure) iter.next();
            if (kiType instanceof X509Data) {
                X509Data xd = (X509Data) kiType;
                Object[] entries = xd.getContent().toArray();
                X509CRL crl = null;
                for (int i = 0; i < entries.length; i++) {
                    if (entries[i] instanceof X509CRL) {
                        crl = (X509CRL) entries[i];
                    }
                    if (entries[i] instanceof X509CertImpl) {
                        certImpl = (X509CertImpl) entries[i];
//                        try {
//                            certImpl.checkValidity(new Date());
//                        } catch (CertificateExpiredException expiredEx) {
//                            throw expiredEx;
//                        } catch (CertificateNotYetValidException notYetValidEx) {
//                            throw notYetValidEx;
//                        }
                    }
                    if (entries[i] instanceof String) {
                        userSigned += (String) entries[i] + "\n";
                    }
                }
            }
        }
        return userSigned;
    }

    public KeyInfo validateContentSigned(String content) throws Exception {
        DocumentBuilderFactory dbf
                = DocumentBuilderFactory.newInstance();
        KeyInfo keyInfo = null;
        dbf.setNamespaceAware(true);
        Document doc = dbf
                .newDocumentBuilder()
                .parse(new ByteArrayInputStream(content.getBytes("UTF-8")));
        NodeList nl
                = doc.getElementsByTagNameNS(XMLSignature.XMLNS,
                        "Signature");
        if (nl.getLength() == 0) {
            throw new Exception("Cannot find Signature element");
        }

        String providerName = System.getProperty(
                "jsr105Provider",
                "org.jcp.xml.dsig.internal.dom.XMLDSigRI");
        XMLSignatureFactory fac
                = XMLSignatureFactory.getInstance("DOM",
                        (Provider) Class.forName(providerName).newInstance());
        DOMValidateContext valContext = new DOMValidateContext(new X509KeySelector(), nl.item(1));

        XMLSignature signature
                = fac.unmarshalXMLSignature(valContext);
        boolean coreValidity = signature.validate(valContext);

        if (coreValidity == false) {
            System.err.println("Signature failed");
        } else {
            keyInfo = signature.getKeyInfo();
            System.out.println("Signature passed");
        }
        return keyInfo;
    }

    private OutputStream sendToClient(HttpServletResponse response, CommDataBean bean) throws Exception {

        OutputStream outstr = response.getOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(outstr);

        oos.writeObject(bean);
        return oos;
    }

    public Certificate[] getCertChainFromClient(HttpServletRequest request) {
        try {
            Certificate[] nntCertChain = null;
            ObjectInputStream fromClient = new ObjectInputStream(request.getInputStream());
            nntCertChain = (Certificate[]) fromClient.readObject();
            return nntCertChain;
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return null;
        }
    }

    /**
     * Doc du lieu chu ki dien tu tu client
     *
     * @param request
     * @return
     * @throws Exception
     */
    public CommDataBean getSigntureData(HttpServletRequest request) throws Exception {
        CommDataBean bean = null;
        ObjectInputStream fromClient = new ObjectInputStream(request.getInputStream());
        bean = (CommDataBean) fromClient.readObject();
//        fromClient.reset();
//        fromClient.close();
        return bean;
    }

    private long getObjectId(String content, String nameElement) {
        long id = 0l;
        try {
            DocumentBuilderFactory dbf
                    = DocumentBuilderFactory.newInstance();
            Document doc = dbf.newDocumentBuilder().parse(new ByteArrayInputStream(content.getBytes("UTF-8")));
            Element docEle = doc.getDocumentElement();
            NodeList nl = docEle.getElementsByTagName(nameElement);
            if (nl != null && nl.getLength() > 0) {
                for (int i = 0; i < nl.getLength(); i++) {
                    Element el = (Element) nl.item(i);
                    id = Long.parseLong(el.getTextContent());
                }
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return id;
    }
}
