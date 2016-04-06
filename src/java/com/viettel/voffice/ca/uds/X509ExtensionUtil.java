/*
 * Copyright (C) 2010 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.voffice.ca.uds;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.util.Vector;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Sequence;




import org.bouncycastle.asn1.DERObjectIdentifier;
import org.bouncycastle.asn1.x509.X509Extensions;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.jce.PrincipalUtil;
import org.bouncycastle.jce.X509Principal;


/**
 *
 * @author : HungND8@viettel.com.vn
 * @Version: 1.0
 * @Since  : version 1.0
 * @Date   : Feb 9, 2011, 4:52:29 PM
 */
public class X509ExtensionUtil {
    /**
     * utility class must get a private constructor
     */
    private X509ExtensionUtil() {
    }

    /**
     * Get subject of certificate
     * @param certificate X509Certificate
     * @return <CODE>String</CODE> name of subject or empty <CODE>String</CODE>
     */
    public static String getSubject(X509Certificate certificate) {
        try {
            X509Principal principal = PrincipalUtil.getSubjectX509Principal(certificate);
            Vector vector =  principal.getValues(X509Principal.CN);
            if (vector.size() != 1) {
                return "";
            }
            return vector.firstElement().toString();
        } catch (CertificateEncodingException ex) {
            return "";
        }
    }

    /**
     * Get organization of certificate
     * @param certificate X509Certificate
     * @return <CODE>String</CODE> name of subject or empty <CODE>String</CODE>
     */
    public static String getOrganization(X509Certificate certificate) {
        try {
            X509Principal principal = PrincipalUtil.getSubjectX509Principal(certificate);
            Vector vector =  principal.getValues(X509Principal.O);
            if (vector.size() != 1) {
                return "";
            }
            return vector.firstElement().toString();
        } catch (CertificateEncodingException ex) {
            return "";
        }
    }

    /**
     * Get unit of certificate
     * @param certificate X509Certificate
     * @return <CODE>String</CODE> name of subject or empty <CODE>String</CODE>
     */
    public static String getOrganizationUnit(X509Certificate certificate) {
        try {
            X509Principal principal = PrincipalUtil.getSubjectX509Principal(certificate);
            Vector vector =  principal.getValues(X509Principal.OU);
            if (vector.size() != 1) {
                return "";
            }
            return vector.firstElement().toString();
        } catch (CertificateEncodingException ex) {
            return "";
        }
    }

    /**
     * Get location of certificate
     * @param certificate X509Certificate
     * @return <CODE>String</CODE> name of subject or empty <CODE>String</CODE>
     */
    public static String getLocation(X509Certificate certificate) {
        try {
            X509Principal principal = PrincipalUtil.getSubjectX509Principal(certificate);
            Vector vector =  principal.getValues(X509Principal.L);
            if (vector.size() != 1) {
                return "";
            }
            return vector.firstElement().toString();
        } catch (CertificateEncodingException ex) {
            return "";
        }
    }

    /**
     * Get location of certificate
     * @param certificate X509Certificate
     * @return <CODE>String</CODE> name of subject or empty <CODE>String</CODE>
     */
    public static String getIssuerName(X509Certificate certificate) {
        try {
            X509Principal principal = PrincipalUtil.getIssuerX509Principal(certificate);
            Vector vector =  principal.getValues(X509Principal.CN);
            if (vector.size() != 1) {
                return "";
            }
            return vector.firstElement().toString();
        } catch (CertificateEncodingException ex) {
            return "";
        }
    }

    /**
     * get the CRL URL from X509 Certificate
     * @param certificate : X509 certificate
     * @return <CODE>String</CODE> : URL of CRL
     * or <CODE>null</CODE> if no extension or exception
     */
//    public static String getCRLURL(X509Certificate certificate) {
//        try {
//            /*
//             * check if has extension or not
//             */
//            DERObject obj = getExtensionValue(certificate, X509Extensions.CRLDistributionPoints.getId());
//            if (obj == null) {
//                return null;
//            }
//
//            ASN1Sequence accessDescriptions = (ASN1Sequence) obj;
//            for (int i = 0; i < accessDescriptions.size(); i++) {
//                ASN1Sequence accessDescription = (ASN1Sequence) accessDescriptions.getObjectAt(i);
//                if (accessDescription.size() != 2) {
//                    continue;
//                } else {
//                    if (accessDescription.getObjectAt(0) instanceof DERObject) {
//                        String accessLocation =  getStringFromGeneralName((DERObject) accessDescription.getObjectAt(0));
//                        if (accessLocation == null) {
//                            return "";
//                        } else {
//                            return accessLocation;
//                        }
//                    }
//                }
//            }
//        } catch (Exception e)  {
//        }
//        return null;
//    }

    /**
     * get the OCSP URL of a X509 certificate
     * @param certificate : X509 certificate
     * @return <CODE>String</CODE> URL of certificate or
     * <CODE>null</CODE> if certificate has no OCSP URL extension or error
     */
//    public static String getOCSPURL(X509Certificate certificate) {
//        try {
//            /*
//             * check if has extension or not
//             */
//            DERObject obj = getExtensionValue(certificate, X509Extensions.AuthorityInfoAccess.getId());
//            if (obj == null) {
//                return null;
//            }
//            ASN1Sequence accessDescriptions = (ASN1Sequence) obj;
//            for (int i = 0; i < accessDescriptions.size(); i++) {
//                ASN1Sequence accessDescription = (ASN1Sequence) accessDescriptions.getObjectAt(i);
//                if (accessDescription.size() != 2) {
//                    continue;
//                } else {
//                    if (accessDescription.getObjectAt(0) instanceof DERObjectIdentifier
//                            && ((DERObjectIdentifier) accessDescription.getObjectAt(0)).getId().equals("1.3.6.1.5.5.7.48.1")) {
//                        String accessLocation =  getStringFromGeneralName((DERObject) accessDescription.getObjectAt(1));
//                        if (accessLocation == null) {
//                            return "";
//                        } else {
//                            return accessLocation;
//                        }
//                    }
//                }
//            }
//        } catch (Exception e) {
//        }
//        return null;
//    }

    /**
     * get the extension value of particular X509 certificate
     * @param cert : X509 certificate
     * @param oid : id of particular type,
     * see <CODE>org.bouncycastle.asn1.x509.X509Extensions</CODE> to get the object id
     * @return <CODE>DERObject</CODE> of
     * @throws IOException
     */
//    private static DERObject getExtensionValue(X509Certificate cert, String oid) throws IOException {
//        byte[] bytes = cert.getExtensionValue(oid);
//        if (bytes == null) {
//            return null;
//        }
//        ASN1InputStream aIn = new ASN1InputStream(new ByteArrayInputStream(bytes));
//        ASN1OctetString octs = (ASN1OctetString) aIn.readObject();
//        aIn = new ASN1InputStream(new ByteArrayInputStream(octs.getOctets()));
//        return aIn.readObject();
//    }
//
//    private static String getStringFromGeneralName(DERObject names) throws IOException {
//        DERTaggedObject taggedObject = (DERTaggedObject) names;
//        return new String(ASN1OctetString.getInstance(taggedObject, false).getOctets(), "ISO-8859-1");
//    }
}
