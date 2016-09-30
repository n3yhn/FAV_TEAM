/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.email.DAO;

import com.sun.mail.pop3.POP3Folder;
import com.viettel.common.util.LogUtil;
import com.viettel.common.util.UploadFile;
import com.viettel.email.form.EmailForm;
import com.viettel.voffice.database.BO.Email;
import com.viettel.voffice.database.BO.EmailUser;
import com.viettel.voffice.database.BO.VoAttachs;
import com.viettel.voffice.database.DAO.BaseDAO;
import com.viettel.voffice.database.DAO.GridResult;
import com.viettel.email.DAOHE.EmailDAOHE;
import com.viettel.email.DAOHE.EmailUserDAOHE;
import com.viettel.voffice.database.DAO.UploadIframeDAO;
import com.viettel.voffice.database.DAOHibernate.VoAttachsDAOHE;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.FetchProfile;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.UIDFolder;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.hibernate.ObjectNotFoundException;

/**
 *
 * @author dungnt78
 */
public class VoEmailDAO extends BaseDAO {

    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(VoEmailDAO.class);
    private EmailUserDAOHE emaulUserDAO = new EmailUserDAOHE();
    private EmailForm emailformsearch;

    private static final String separatorFile = String.valueOf(File.separatorChar);

    public EmailForm getEmailformsearch() {
        return emailformsearch;
    }

    public void setEmailformsearch(EmailForm emailformsearch) {
        this.emailformsearch = emailformsearch;
    }

    public EmailUserDAOHE getEmaulUserDAO() {
        return emaulUserDAO;
    }

    public void setEmaulUserDAO(EmailUserDAOHE emaulUserDAO) {
        this.emaulUserDAO = emaulUserDAO;
    }
    private String folderName;
    private String host;
    private int port = 995;
    private String protocol;

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public EmailDAOHE getEmailDao() {
        return emailDao;
    }

    public void setEmailDao(EmailDAOHE emailDao) {
        this.emailDao = emailDao;
    }

    public List<Email> getListEmail() {
        return listEmail;
    }

    public void setListEmail(List<Email> listEmail) {
        this.listEmail = listEmail;
    }

    public EmailForm getEmailForm() {
        return emailForm;
    }

    public void setEmailForm(EmailForm emailForm) {
        this.emailForm = emailForm;
    }

    public List<EmailForm> getListMessForm() {
        return listMessForm;
    }

    public void setListMessForm(List<EmailForm> listMessForm) {
        this.listMessForm = listMessForm;
    }

    public EmailForm getSendForm() {
        return sendForm;
    }

    public void setSendForm(EmailForm sendForm) {
        this.sendForm = sendForm;
    }
    EmailDAOHE emailDao = new EmailDAOHE();
    List<Email> listEmail = new ArrayList<Email>();
    EmailForm emailForm;
    private Store store;
    private List<EmailForm> listMessForm;
    EmailForm sendForm;

    public Store getStore() {
        try {
            EmailUser eu = emaulUserDAO.findById(getUserId());
            String username = eu.getEmailAddress();
            String password = eu.getEmailPassword();

            password = decryptDataByUserName((password));
            ResourceBundle rb;
            if (username.contains("viettel")) {
                rb = ResourceBundle.getBundle("email_Viettel");
            } else {
                rb = ResourceBundle.getBundle("email_ATTT");
            }
            String protocol = rb.getString("Iprotocol");
            String host = rb.getString("Ihost");
            int port = Integer.parseInt(rb.getString("Iport"));
            String charset = rb.getString("Icharset");
            String fallback = rb.getString("Ifallback");
            String ssl = rb.getString("SSL");
            String IsocketFactoryClass = rb.getString("IsocketFactory.class");
            String Iconnectiontimeout = rb.getString("Iconnectiontimeout");
            String Itimeout = rb.getString("Itimeout");

            Properties props = System.getProperties();
            props.put("mail.store.protocol", protocol);
            props.put("mail.pop3.port", port);
            props.put("mail.pop3.host", host);
            props.put("mail.mime.charset", charset);
            if (ssl.contains("y")) {
                props.put("mail.pop.socketFactory.port", port);
                props.put("mail.pop3.socketFactory.fallback", fallback);
                props.put("mail.pop3.socketFactory.class", IsocketFactoryClass);
            }
            props.put("mail.pop3.connectiontimeout", Iconnectiontimeout);
            props.put("mail.pop3.timeout", Itimeout);

            Session session = Session.getInstance(props);
            store = session.getStore(protocol);
            store.connect(host, port, username, password);
            return store;
        } catch (javax.mail.AuthenticationFailedException ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            return null;
        } catch (MessagingException ex) {
            //System.out.println(ex.getMessage());
//            log.error(ex.getMessage());
            LogUtil.addLog(ex);//binhnt sonar a160901
            return null;
        } catch (Exception ex) {
            //System.out.println(ex.getMessage());
//            log.error(ex.getMessage());
            LogUtil.addLog(ex);//binhnt sonar a160901
            return null;
        }
    }

    private String saveToVoAttachs(String filename) {
        File f = new File(UploadIframeDAO.getSafeFileName(filename));
        String filePath = UploadFile.uploadFile("", f.getName(), f, null);
        VoAttachsDAOHE daoHe = new VoAttachsDAOHE();
        VoAttachs bo = new VoAttachs();
        bo.setAttachName(f.getName());
        bo.setAttachPath(filePath);
        bo.setIsActive(1L);
        bo.setUserCreateId(this.getUserId());
        bo.setCreateDate(daoHe.getSysdate());
        Long id = daoHe.create(bo);
        String result = id.toString() + "; ";
        f.delete();
        return result;
    }

    private boolean checkContent(Object content) {
        if (content instanceof Multipart) {
            return true;
        } else {
            return false;
        }
    }

    private String fetchText(Part message, boolean escape, boolean showImages) {
        StringWriter sw = new StringWriter(1024);
        boolean html = false;

        try {
            if (message != null && message.getContent() != null) {
                if (message.getContent() instanceof Multipart) {
                    Multipart parts = (Multipart) message.getContent();
                    BodyPart p;
//                    boolean attachment = false;
                    boolean alternative = parts.getContentType().trim().toLowerCase().startsWith("multipart/alternative") ? true : false;

                    InputStreamReader isr;
                    int retrieved;
                    char[] buffer = new char[512];
                    for (int i = 0; i < parts.getCount(); i++) {
                        p = parts.getBodyPart(i);

                        if (p.getContentType().toLowerCase().startsWith("multipart")) {
                            sw.write(fetchText(p, escape, showImages));
                            break;

                        } else if ((Part.INLINE.equalsIgnoreCase(p.getDisposition())
                                || p.getDisposition() == null)
                                && p.getContentType().toLowerCase().startsWith("text")
                                && p.getFileName() == null) {

                            if (InputStream.class
                                    .isInstance(p.getContent())) {
                                InputStream ip = p.getInputStream();
                                StringWriter subwriter = new StringWriter(ip.available());
                                isr = new InputStreamReader(ip);

                                while (isr.ready()) {
                                    retrieved = isr.read(buffer, 0, 512);
                                    subwriter.write(buffer, 0, retrieved);
                                }
                                if (escape) {
                                    sw.write(escapeLineBreaksAndSpacesForHTML(subwriter.toString()));
                                } else {
                                    sw.write(subwriter.toString());
                                }
                            } else {
                                Object content = p.getContent();

                                if (escape) {
                                    if (java.io.ByteArrayInputStream.class
                                            .isInstance(content)) {
                                        int bcount = ((java.io.ByteArrayInputStream) content).available();
                                        byte[] c = new byte[bcount];

                                        ((java.io.ByteArrayInputStream) content).read(c, 0, bcount);
                                        sw.write(escapeLineBreaksAndSpacesForHTML(new String(c)));
                                    } else {
                                        sw.write(escapeLineBreaksAndSpacesForHTML(content.toString()));

                                    }
                                } else if (java.io.ByteArrayInputStream.class
                                        .isInstance(content)) {
                                    int bcount = ((java.io.ByteArrayInputStream) content).available();
                                    byte[] c = new byte[bcount];

                                    ((java.io.ByteArrayInputStream) content).read(c, 0, bcount);
                                    sw.write(
                                            new String(c));
                                } else {
                                    sw.write(content.toString());
                                }
                            }

                            if (p.getContentType().toLowerCase().indexOf("html") > 0) {
                                html = true;
                            }
                            if (alternative && !"".equals(sw.toString().trim())) {
                                break;
                            }
                            if (escape) {
                                sw.write("<br/>");
                            } else {
                                sw.write("\r\n");
                            }
                        } else if (p.getContentType().toLowerCase().startsWith("image") && showImages && !html) {
                            // inline image
                            if (escape && message instanceof MimeMessage) {
                                sw.write("<br/>");
                                sw.write("<img src=\"");
                                sw.write(String.valueOf(i));
                                sw.write("\"><br/>\r\n");
                            }
                        }
                    }
                } else if (message.getContentType().toLowerCase().startsWith("text")) {
                    if (escape) {
                        Object content = message.getContent();

                        if (java.io.ByteArrayInputStream.class
                                .isInstance(content)) {
                            int bcount = ((java.io.ByteArrayInputStream) content).available();
                            byte[] c = new byte[bcount];

                            ((java.io.ByteArrayInputStream) content).read(c, 0, bcount);
                            sw.write(escapeLineBreaksAndSpacesForHTML(new String(c)));
                        } else {
                            sw.write(escapeLineBreaksAndSpacesForHTML(content.toString()));
                        }
                    } else {
                        sw.write(message.getContent().toString());
                    }

                }
            } else {
                System.err.println("Message or message content is null");
            }
        } catch (Exception ioe) {
            System.err.println("Exception reading mail: " + ioe.getMessage());
//            log.error(ioe);
            LogUtil.addLog(ioe);//binhnt sonar a160901
        }
        return sw.toString();
    }

    private String escapeLineBreaksAndSpacesForHTML(String value) {
        if (value == null) {
            return "";
        }
        String[] lines = value.split("\r\n|\r|\n");
        StringBuilder tb = new StringBuilder(value.length() + 5 * lines.length);
        int i, j;
        String tline;
        for (i = 0; i < lines.length; i++) {
            if (i > 0) {
                tb.append("<br/>\r\n");
            }
            tline = StringUtils.stripStart(lines[i], " ");
            j = lines[i].length() - tline.length();
            if (j > 0) {
                tb.append(StringUtils.repeat("&nbsp;", j));
            }
            j = tline.length();
            tline = StringUtils.stripEnd(tline, " ");
            j -= tline.length();
            tb.append(tline);
            if (j > 0) {
                tb.append(StringUtils.repeat("&nbsp;", j));
            }
        }
        return tb.toString();
    }

    public String prepare() {
        emaulUserDAO.findById(getUserId());

        try {
//            String s = eu.getEmailAddress();
            String folderName = getRequest().getParameter("folder");
            getRequest().getSession().setAttribute("emailFolder", folderName);
            getRequest().setAttribute("listEmail", SuggestEmail());
            return folderName;
        } catch (ObjectNotFoundException ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            return "fail";
        }
    }

    /*
     * get suggest list HungTQ
     */
    private String SuggestEmail() {
        EmailDAOHE emaildaohe = new EmailDAOHE();
        List<String> suggestString = emaildaohe.findEmailByUserId(getUserId());
        List<Email> suggestList = new ArrayList<Email>();
        for (int i = 0; i < suggestString.size(); i++) {
            Email email = new Email();
            email.setUserId((long) i);
            email.setSender(StringEscapeUtils.escapeHtml(suggestString.get(i) + "; "));
            suggestList.add(email);
        }
        String listEmail = emaildaohe.convertToJSONArray(suggestList, "userId", "sender", "sender");
        return listEmail;
    }

    /**
     * convert message -> Email to store in database
     *
     * @param mess
     * @param uid
     * @param attachId
     * @return
     * @throws IOException
     * @throws MessagingException
     */
    private Email transformEmail(Message mess, String uid, String attachId) throws IOException, MessagingException {
        Email email = new Email();
        email.setUserId(getUserId());
        email.setSender(convertAddressToString(mess.getFrom()));
        if (mess.getFolder() != null) {
            email.setFolder(mess.getFolder().getName());
        } else {
            email.setFolder("outbox");
        }
        //   Address[] replyadd = mess.getReplyTo();
        email.setReceiver(convertAddressToString(mess.getRecipients(Message.RecipientType.TO)));
        email.setBcc(convertAddressToString(mess.getRecipients(Message.RecipientType.BCC)));
        email.setSentDate(mess.getSentDate());
        email.setEmailUid(uid);
        email.setSubject(decodeMess(mess.getSubject()));
        if (mess.getContent() instanceof Multipart) {

            String[] content = getHTMLContentMessage(mess, email.getFolder());

            if (content[0] == null) {
                email.setContent(fetchText((Part) mess, true, true));
                email.setAttachmentId(content[1]);
            } else {
                email.setContent(content[0]);
                email.setAttachmentId(content[1]);
            }

        } else {
            email.setContent(fetchText((Part) mess, true, true));
        }

        if ("outbox".equalsIgnoreCase(email.getFolder())) {
            attachId = attachId.replaceAll(";", "; ");
            email.setAttachmentId(attachId);
        }
        email.setRecipients(convertAddressToString(mess.getRecipients(Message.RecipientType.CC)));
        return email;
    }

    private String[] getHTMLContentMessage(Part message, String folder) throws IOException {
        ResourceBundle rb = ResourceBundle.getBundle("config");
        String dir = rb.getString("tempDirectory");
        String[] result = new String[2];
        try {
            String attachid = "";
            List<String> listImage = new ArrayList<String>();
            List<String> attachs = new ArrayList<String>();

            Multipart multi = (Multipart) message.getContent();
            for (int i = 0; i < multi.getCount(); i++) {
                BodyPart body = multi.getBodyPart(i);
                if (body.getContentType().startsWith("multipart")) {
                    result = getHTMLContentMessage(body, folder);
                } else if (body.getContentType().startsWith("text/html")) {
                    result[0] = (String) body.getContent();
                } else if (body.getContentType().startsWith("image")) {
                    String s = body.getFileName();
                    saveFile(dir + separatorFile + decodeMess(s), body.getInputStream());
                    String att = saveToVoAttachs(dir + separatorFile + decodeMess(s));
                    attachid += att;
                    attachs.add(att.substring(0, att.indexOf(";")));

                    String Content_ID[] = body.getHeader("Content-ID");

                    if (Content_ID != null && Content_ID.length > 0) {
                        String cid_name = Content_ID[0].replaceAll("<", "").replaceAll(">", "");
                        listImage.add(cid_name);
                    }

                } else if (body.getContentType().startsWith("application")) {
                    if (!"outbox".equalsIgnoreCase(folder)) {
                        String s = body.getFileName();
                        saveFile(dir + separatorFile + decodeMess(s), body.getInputStream());
                        attachid += saveToVoAttachs(dir + separatorFile + decodeMess(s));
                    }
                } else if (body.getContentType().startsWith("message/rfc822")) {
                    Message bo = (Message) body.getContent();
                    getHTMLContentMessage(bo, folder);
                }
            }
            result[1] = attachid;
            if (listImage.size() > 0) {
                for (int i = 0; i < listImage.size(); i++) {

                    result[0] = result[0].replaceAll("cid:" + listImage.get(i),
                            "uploadiframe!openFile.do?attachId=" + attachs.get(i));
                }

            }
            return result;
        } catch (MessagingException ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            result[0] = "<h1>Khong doc duoc mail</h1>";
            result[1] = null;
            return result;
        }
    }

    /**
     * To decode String
     *
     * @param s String input
     * @return String da duoc decode
     */
    private String decodeMess(String s) {
        String output;
        if (s != null) {
            try {

                output = MimeUtility.decodeText(s);
            } catch (UnsupportedEncodingException ex) {
                LogUtil.addLog(ex);//binhnt sonar a160901
                // Don't care
                output = s;
            }
        } else {
            return null;
        }

        return output;
    }

    private void saveFile(String filename,
            InputStream input) throws IOException {
        ResourceBundle rb = ResourceBundle.getBundle("config");
        String dir = rb.getString("tempDirectory");
        if (decodeMess(filename) == null) {
            filename = File.createTempFile("xx", ".out").getName();
        }
        File f = new File(dir + separatorFile);
        if (!f.exists()) {
            f.mkdir();
        }
        // Do no overwrite existing file
        File file = new File(decodeMess(filename));
        FileOutputStream fos = new FileOutputStream(file);
        BufferedOutputStream bos = new BufferedOutputStream(fos);

        BufferedInputStream bis = new BufferedInputStream(input);
        int aByte;
        while ((aByte = bis.read()) != -1) {
            bos.write(aByte);
        }
        bos.flush();
        bos.close();
        bis.close();
    }

    private List<String> handleMultipart(Multipart multipart) throws MessagingException, IOException {
        List<String> result = new ArrayList<String>();
        for (int i = 0; i < multipart.getCount(); i++) {
            result.add(handlePart(multipart.getBodyPart(i)));
        }
        return result;
    }

    private String handlePart(Part part)
            throws MessagingException, IOException {
        ResourceBundle rb = ResourceBundle.getBundle("config");
        String dir = rb.getString("tempDirectory");
        String disposition = part.getDisposition();
//        String contentType = decodeMess(part.getContentType());
        if (disposition == null) {
        } else if (disposition.equalsIgnoreCase(Part.ATTACHMENT)) {
            String s = part.getFileName();
            saveFile(dir + separatorFile + decodeMess(s), part.getInputStream());
            return dir + separatorFile + part.getFileName();
        } else if (disposition.equalsIgnoreCase(Part.INLINE)) {
            saveFile(dir + separatorFile + decodeMess(part.getFileName()), part.getInputStream());
            return dir + separatorFile + decodeMess(part.getFileName());
        }
        return null;
    }

    /**
     * load bo cac phan tu null trong array
     *
     * @param input
     * @return
     */
    private String[] fixStringArray(String[] input) {

        List<String> loutput = new ArrayList<String>();

        for (String st : input) {
            if (!"".equalsIgnoreCase(st)) {
                loutput.add(st);
            }
        }
        String[] output = new String[loutput.size()];
        for (int i = 0; i < loutput.size(); i++) {
            output[i] = loutput.get(i);
        }
        return output;
    }

    /**
     * convert array Address to String: nguyenthedung <dungnt78@viettel.com.vn>;
     * <hungtq@viettel.com.vn>;
     *
     * @param add
     * @return
     */
    private String convertAddressToString(Address[] add) {
        String temp = "";
        if (add != null) {
            int j;
            InternetAddress in;
            if (add.length != 1) {
                for (j = 0; j < add.length; j++) {
                    in = (InternetAddress) add[j];
                    if (in.getPersonal() != null) {
                        temp += decodeMess(in.getPersonal().toString()) + "<" + decodeMess(in.getAddress().toString() + ">; ");
                    } else {
                        temp += decodeMess(in.getAddress().toString() + "; ");
                    }
                }
            } else if (add.length == 1) {
                for (j = 0; j < add.length; j++) {
                    in = (InternetAddress) add[j];
                    if (in.getPersonal() != null) {
                        temp += decodeMess(in.getPersonal().toString()) + "<" + decodeMess(in.getAddress().toString() + ">");
                    } else {
                        temp += decodeMess(in.getAddress().toString());
                    }
                }
            }
        }
        return temp;
    }

    private InternetAddress[] convertStringToAddress(String list) throws Exception {
        String[] temp;
        if (!"".equals(list)) {
            temp = list.split(";");
            for (int i = 0; i < temp.length; i++) {
                temp[i] = temp[i].trim();
            }
            temp = fixStringArray(temp);
            InternetAddress[] interAdd = new InternetAddress[temp.length];
            for (int i = 0; i < temp.length; i++) {
                if (temp[i].contains("<")) {
                    int k = temp[i].indexOf('<');
                    InternetAddress in = new InternetAddress(temp[i].substring(k + 1, temp[i].length() - 1).trim(), temp[i].substring(0, k - 1).trim());
                    interAdd[i] = in;
                } else {
                    interAdd[i] = new InternetAddress(temp[i]);
                }
            }
            return interAdd;
        }

        return null;
    }

    /**
     * get email to display in grid
     *
     * @return
     */
//    public String getMail() {
//        String folderName = getRequest().getParameter("folder");
//        if (folderName == null) {
//            folderName = getRequest().getSession().getAttribute("emailFolder").toString();
//        } else {
//            getRequest().getSession().setAttribute("emailFolder", folderName);
//        }
//        getGridInfo();
//        List lst = new ArrayList<EmailForm>();
//        listEmail = new ArrayList<Email>();
//
//        listEmail = emailDao.findEmailByUserId(getUserId(), folderName);
//
//        for (int i = 0; i < listEmail.size(); i++) {
//            EmailForm form = new EmailForm();
//            try {
//                form.setEmailId(listEmail.get(i).getEmailId());
//                form.setUserId(listEmail.get(i).getUserId());
//                form.setSender(listEmail.get(i).getSender());
//                form.setReceiver(listEmail.get(i).getReceiver());
//                form.setReplyTo(listEmail.get(i).getReplyTo());
//                form.setBcc(listEmail.get(i).getBcc());
//                form.setReceiveDate(listEmail.get(i).getReceiveDate());
//                form.setSentDate(listEmail.get(i).getSentDate());
//                form.setFlag(listEmail.get(i).getFlag());
//                form.setFolder(listEmail.get(i).getFolder());
//                form.setAttachmentId(listEmail.get(i).getAttachmentId());
//                form.setEmailUid(listEmail.get(i).getEmailUid());
//                form.setSubject(StringEscapeUtils.escapeHtml(listEmail.get(i).getSubject()));
//                form.setContent(listEmail.get(i).getContent());
//                form.setEmailAdress(listEmail.get(i).getEmailAdress());
//                form.setRecipients(listEmail.get(i).getRecipients());
//                if (listEmail.get(i).getAttachmentId() != null) {
//                    String[] attachArray = fixStringArray(listEmail.get(i).getAttachmentId().split("; "));
//                    List<String> listAttachName = new ArrayList<String>();
//                    String idSession = (String) getRequest().getSession().getAttribute("idSession");
//                    if (idSession == null) {
//                        idSession = "";
//                    }
//                    for (String aid : attachArray) {
//                        VoAttachsDAOHE vo = new VoAttachsDAOHE();
//                        VoAttachs v = vo.findById(Long.valueOf(aid));
//                        idSession += v.getAttachId() + ";";
//                        listAttachName.add(v.getAttachName());
//                    }
//                    form.setAttachmentIDArray(attachArray);
//                    form.setAttachmentNameArray(listAttachName);
//                    getRequest().getSession().setAttribute("idSession", idSession);
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            lst.add(form);
//        }
//        int countReturn = start + count;
//        if (countReturn >= listEmail.size()) {
//            countReturn = listEmail.size();
//        }
//        List lstReturn = lst.subList(start, countReturn);
//        jsonDataGrid.setItems(lstReturn);
//        jsonDataGrid.setTotalRows(listEmail.size());
//        return GRID_DATA;
//    }
    public void readMail() {
        Email mess = emailDao.findById(emailForm.getEmailId());
        mess.setFlag("read");
        emailDao.update(mess);
    }

    public String syncEmail() throws MessagingException, IOException, InterruptedException {
        // get all UID from database, storing in a list
        int num = 0;
        String folderName = "inbox";
        List<String> listEmailUIDDatabase = emailDao.getAllUIDByUserID(getUserId(), folderName);
        // get all UID from mail server storing in a list

        List<String> listEmailUIDServer = new ArrayList<String>();

        Store store = getStore();
        if (store != null) {
            POP3Folder fol = (POP3Folder) store.getFolder(folderName);
            fol.open(Folder.READ_WRITE);
            FetchProfile profile = new FetchProfile();
            profile.add(UIDFolder.FetchProfileItem.UID);
            Message[] messages = fol.getMessages();
            fol.fetch(messages, profile);
            int messNum = fol.getMessageCount();
            for (int i = 1; i <= messNum; i++) {
//                if ((i % 30) == 0) {
//                    fol.close(true);
//                    store.close();
//                    //  Thread.sleep(2000);
//                    store = getStore();
//                    fol = (POP3Folder) store.getFolder(folderName);
//                    fol.open(Folder.READ_WRITE);
//                    fol.fetch(fol.getMessages(), profile);
//                }
                try {
                    Message me = fol.getMessage(i);
                    String uid = fol.getUID(me);
                    if (uid != null) {
                        listEmailUIDServer.add(uid);
                        if (!listEmailUIDDatabase.contains(uid)) {
                            emailDao.insert(transformEmail(me, uid, null));
                            num++;
                        }
                    }
                } catch (Exception ex) {
                    LogUtil.addLog(ex);//binhnt sonar a160901
                    //System.out.println(en.getMessage());
//                    log.error(ex.getMessage());
                }
                if (i != messNum) {
                    jsonDataGrid.setCustomInfo("still have");
                } else {
                    jsonDataGrid.setCustomInfo("finish");
                }
                if (num == 5) {
                    break;
                }
            }
//        for (int i = 0; i < messages.length; i++) {
//            String uid = fol.getUID(messages[i]);
//            listEmailUIDServer.add(uid);
//            //find a new mail
//            if (!listEmailUIDDatabase.contains(uid)) {
//
//                emailDao.insert(transformEmail(messages[i], uid));
//
//            }
//        }
            // find deleted mail
            for (int i = 0; i < listEmailUIDDatabase.size(); i++) {
                String st = listEmailUIDDatabase.get(i);
                if (!listEmailUIDServer.contains(st)) {
                    // neu uid ko co tren server --> deleted
                    emailDao.deleteByUid(getUserId(), st, folderName);
                }
            }
            fol.close(true);
            store.close();
        } else {
            jsonDataGrid.setCustomInfo("fail connect to server");
        }
        onSearch();
        return GRID_DATA;
    }

    public String deleteSelected() {
        listMessForm = new ArrayList<EmailForm>();
//        EmailForm m = emailForm;
        listMessForm.add(emailForm);
        onDelete();
        return "gridData";
    }

    public String onDelete() {
        String folderName = getRequest().getSession().getAttribute("emailFolder").toString();
        List resultMessage = new ArrayList();
        try {
            if (listMessForm == null) {
                resultMessage.add("0");
                resultMessage.add("Xóa dữ liệu không thành công");
            } else {
                for (EmailForm form : listMessForm) {
                    long emailId = form.getEmailId();
                    Email bo = emailDao.findById(emailId);
                    emailDao.delete(bo);
                    String attachments = bo.getAttachmentId();
                    if (attachments != null) {
                        String[] attachmentArray = fixStringArray(attachments.split("; "));
                        VoAttachsDAOHE vo = new VoAttachsDAOHE();
                        //only delete data in Vo_attachments table
                        for (String a : attachmentArray) {
                            VoAttachs v = vo.findById(Long.parseLong(a));
                            vo.delete(v);
                        }
                    }
                    if ("inbox".equalsIgnoreCase(folderName)) {
                        Store store = getStore();
                        POP3Folder fol = (POP3Folder) store.getFolder(folderName);
                        fol.open(Folder.READ_WRITE);
                        FetchProfile profile = new FetchProfile();
                        profile.add(UIDFolder.FetchProfileItem.UID);
                        Message[] messages = fol.getMessages();
                        fol.fetch(messages, profile);
                        for (int i = 0; i < messages.length; i++) {
                            String uid = fol.getUID(messages[i]);
                            if (uid.equalsIgnoreCase(bo.getEmailUid())) {
                                messages[i].setFlag(Flags.Flag.DELETED, true);
                            }
                        }
                        fol.close(true);
                    }
                }
                resultMessage.add("1");
                resultMessage.add("Xóa dữ liệu thành công");
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            log.error(ex.getMessage());
            resultMessage.add("0");
            resultMessage.add("Xóa dữ liệu không thành công");
            getSession().getTransaction().rollback();
        }
        jsonDataGrid.setCustomInfo(resultMessage);
        return "gridData";
    }

    private Session config(String username) {
        String[] temp = username.split("@");
        String hostname = temp[temp.length - 1];
        if (hostname.contains("gmail")) {
            return setProperties("smtp", "smtp.gmail.com", "587");
        } else if (hostname.contains("yahoo")) {
            return setProperties("smtp", "smtp.mail.yahoo.com", "587");
        } else if (hostname.contains("viettel")) {
            ResourceBundle rb = ResourceBundle.getBundle("email_Viettel");
            String protocol = rb.getString("Oprotocol");
            String host = rb.getString("Ohost");
            String port = rb.getString("Oport");
            return setProperties(protocol, host, port);
        } else if (hostname.contains("vfa.gov")) {
            ResourceBundle rb = ResourceBundle.getBundle("email_ATTT");
            String protocol = rb.getString("Oprotocol");
            String host = rb.getString("Ohost");
            String port = rb.getString("Oport");
            return setProperties(protocol, host, port);
        } else {
            System.out.println("Cau hinh host bang tay");
        }
        return null;

    }

    private Session setProperties(String protocol, String host, String port) {

        EmailUser eu = emaulUserDAO.findById(getUserId());
        String username = eu.getEmailAddress();
        //String password = eu.getEmailPassword();
        ResourceBundle rb;
        if (username.contains("viettel")) {
            rb = ResourceBundle.getBundle("email_Viettel");
        } else {
            rb = ResourceBundle.getBundle("email_ATTT");
        }
        String star = rb.getString("Ostarttls.enable");
        String authen = rb.getString("Oauth");
        String charset = rb.getString("Ocharset");
        String fallback = rb.getString("Ofallback");
        String socketFactoryClass = rb.getString("OsocketFactory.class");
        String ssl = rb.getString("SSL");
        Properties props = System.getProperties();
        props.put("mail.transport.protocol", protocol);
        props.put("mail.smtp.starttls.enable", star);
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.auth", authen);
        // use your gmail account username here
        props.put("mail.smtp.user", username);
        props.put("mail.smtp.port", port);
        props.put("mail.mime.charset", charset);
        if (ssl.contains("y")) {
            props.put("mail.smtp.socketFactory.port", port);
            props.put("mail.smtp.socketFactory.fallback", fallback);
            props.put("mail.smtp.socketFactory.class", socketFactoryClass);
        }
        Authenticator auth = new SMTPAuthenticator();
        return Session.getInstance(props, auth);
    }

    private List<String> convertFilePath(String[] idPath) {
        VoAttachsDAOHE attDaoHe = new VoAttachsDAOHE();
        List path = new ArrayList<String>();
        VoAttachs att;
        ResourceBundle rb = ResourceBundle.getBundle("config");
        String dir = rb.getString("directory");
        for (int i = 0; i < idPath.length; i++) {
            att = attDaoHe.getById("attachId", Long.valueOf(idPath[i]));
            path.add(dir + att.getAttachPath());
        }
        return path;
    }

    public String sendEmail() throws Exception {
        EmailUser eu = emaulUserDAO.findById(getUserId());
        String username = eu.getEmailAddress();
        //String password = eu.getEmailPassword();
        List<String> path = new ArrayList<String>();
        Message msg = new MimeMessage((config(username)));
        msg.setFrom(new InternetAddress(username));
        //msg.setRecipient(Message.RecipientType.TO, new InternetAddress(getTo()));

        msg.setRecipients(Message.RecipientType.TO, convertStringToAddress(sendForm.getReceiver()));
        msg.setRecipients(Message.RecipientType.CC, convertStringToAddress(sendForm.getRecipients()));
        msg.setRecipients(Message.RecipientType.BCC, convertStringToAddress(sendForm.getBcc()));
        msg.setSubject(sendForm.getSubject());
//        String s1 = msg.getSubject();
        Multipart multipart = new MimeMultipart();
        BodyPart part1 = new MimeBodyPart();
        part1.setContent(decodeMess(sendForm.getContent()), "text/html; charset=UTF-8");
        multipart.addBodyPart(part1);

        if (!"".equals(sendForm.getReceiver())) {
            if (!"".equals(sendForm.getAttachmentId())) {
                path = convertFilePath(sendForm.getAttachmentId().split(";"));
            }
        }
        if (path != null) {
            //String pathOne;
            for (int i = 0; i < path.size(); i++) {
                multipart.addBodyPart(attachFile(path.get(i)));
            }
        }
        msg.setContent(multipart);
        msg.setSentDate(new Date());
        msg.saveChanges();
        try {
            Transport.send(msg);
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            log.error(ex.getMessage());
        }
        System.out.println("Message sent to " + sendForm.getReceiver() + " OK.");
        Email email = transformEmail(msg, null, sendForm.getAttachmentId());
        emailDao.insert(email);
        System.out.println("Store sucessful");
        return "gridData";
    }

    private BodyPart attachFile(String filename) {
        try {
            BodyPart part2 = new MimeBodyPart();
            DataSource source = new FileDataSource(decodeMess(filename));
            part2.setDataHandler(new DataHandler(source));
            String[] name = decodeMess(filename).split("_");
            part2.setFileName(name[name.length - 1]);
            return part2;
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        return null;

    }

    private class SMTPAuthenticator extends javax.mail.Authenticator {

        @Override
        public PasswordAuthentication getPasswordAuthentication() {
            EmailUser eu = emaulUserDAO.findById(getUserId());
            String username = eu.getEmailAddress();
            String password = eu.getEmailPassword();
            password = decryptDataByUserName(password);
            return new PasswordAuthentication(username, password);
        }
    }

    public String onSearch() {

        List<Email> listEmail = new ArrayList<Email>();
        List<EmailForm> list = new ArrayList<EmailForm>();
        try {
            String folderName = getRequest().getParameter("folder");
            if (folderName != null) {
                getRequest().getSession().setAttribute("emailFolder", folderName);
            }

            //binhnt comment fix sonar
//            else {
//                folderName = getRequest().getSession().getAttribute("emailFolder").toString();
//            }
            getGridInfo();
            EmailDAOHE emaildaohe = new EmailDAOHE();
            GridResult gridResult = emaildaohe.search(emailformsearch, getUserId());
            listEmail = gridResult.getLstResult();
            for (int i = 0; i < listEmail.size(); i++) {
                EmailForm form = new EmailForm();
                try {
                    form.setEmailId(listEmail.get(i).getEmailId());
                    form.setUserId(listEmail.get(i).getUserId());
                    form.setSender(listEmail.get(i).getSender());
                    form.setReceiver(listEmail.get(i).getReceiver());
                    form.setReplyTo(listEmail.get(i).getReplyTo());
                    form.setBcc(listEmail.get(i).getBcc());
                    form.setReceiveDate(listEmail.get(i).getReceiveDate());
                    form.setSentDate(listEmail.get(i).getSentDate());
                    form.setFlag(listEmail.get(i).getFlag());
                    form.setFolder(listEmail.get(i).getFolder());
                    form.setAttachmentId(listEmail.get(i).getAttachmentId());
                    form.setEmailUid(listEmail.get(i).getEmailUid());
                    form.setSubject(StringEscapeUtils.escapeHtml(listEmail.get(i).getSubject()));
                    form.setContent(listEmail.get(i).getContent());
                    form.setEmailAdress(listEmail.get(i).getEmailAdress());
                    form.setRecipients(listEmail.get(i).getRecipients());
                    if (listEmail.get(i).getAttachmentId() != null) {
                        String[] attachArray = fixStringArray(listEmail.get(i).getAttachmentId().split("; "));
                        List<String> listAttachName = new ArrayList<String>();
                        String idSession = (String) getRequest().getSession().getAttribute("idSession");
                        if (idSession == null) {
                            idSession = "";
                        }
                        for (String aid : attachArray) {
                            VoAttachsDAOHE vo = new VoAttachsDAOHE();
                            VoAttachs v = vo.findById(Long.valueOf(aid));
                            idSession += v.getAttachId() + ";";
                            listAttachName.add(v.getAttachName());
                        }
                        form.setAttachmentIDArray(attachArray);
                        form.setAttachmentNameArray(listAttachName);
                        getRequest().getSession().setAttribute("idSession", idSession);
                    }
                } catch (Exception ex) {
                    LogUtil.addLog(ex);//binhnt sonar a160901
//                    log.error(e);
                }
                list.add(form);
            }
            jsonDataGrid.setItems(list);
            jsonDataGrid.setTotalRows(list.size());
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        int countReturn = start + count;
        if (countReturn >= list.size()) {
            countReturn = list.size();
        }
        List lstReturn = list.subList(start, countReturn);
        jsonDataGrid.setItems(lstReturn);
        jsonDataGrid.setTotalRows(list.size());
        return GRID_DATA;
    }
}
