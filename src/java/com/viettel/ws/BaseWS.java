/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.ws;

import com.viettel.common.util.Constants;
import com.viettel.hqmc.BO.AnnouncementReceiptPaper;
import com.viettel.hqmc.BO.Procedure;
import com.viettel.hqmc.DAOHE.ProcedureDAOHE;
import com.viettel.voffice.database.BO.Category;
import com.viettel.voffice.database.DAOHibernate.CategoryDAOHE;
import com.viettel.vsaadmin.database.BO.Department;
import com.viettel.vsaadmin.database.DAOHibernate.DepartmentDAOHE;
import com.viettel.vsaadmin.database.DAOHibernate.UsersDAOHE;
import com.viettel.ws.BO.ANNOUNCERESULTDto;
import com.viettel.ws.BO.FILERESULTSDto;
import com.viettel.ws.BO.SENDRESPONSEDto;
import com.viettel.ws.FORM.ANNOUCE_RECEIVE;
import com.viettel.ws.FORM.ASSIGNMENT_QUERY;
import com.viettel.ws.FORM.RESULT;
import com.viettel.ws.FORM.RE_ANNOUNCE;
import com.viettel.ws.FORM.TEST_REGISTRATIONType;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

/**
 *
 * @author HaVM2
 */
public class BaseWS {

    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(BaseWS.class);

    public boolean checkUser(String userName, String password) {
        UsersDAOHE udhe = new UsersDAOHE();
        boolean bReturn = udhe.checkUserPass(userName, password);
        return bReturn;
    }

    public String assignmentQueryToXml(ANNOUNCERESULTDto obj) {
        String result = "";
        java.io.StringWriter sw = new StringWriter();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(ANNOUNCERESULTDto.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
// output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(obj, sw);
            result = sw.toString();
        } catch (JAXBException ex) {
            Logger.getLogger(BaseWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public String ARPToXml(AnnouncementReceiptPaper obj) {
        String result = "";
        java.io.StringWriter sw = new StringWriter();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(AnnouncementReceiptPaper.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
// output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(obj, sw);
            result = sw.toString();
        } catch (JAXBException ex) {
            Logger.getLogger(BaseWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public String senddresponsedToToXml(SENDRESPONSEDto obj) {
        String result = "";
        java.io.StringWriter sw = new StringWriter();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(SENDRESPONSEDto.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
// output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(obj, sw);
            result = sw.toString();
        } catch (JAXBException ex) {
            Logger.getLogger(BaseWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public RE_ANNOUNCE xmlToRE_ANNOUNCE(String xml) {
        RE_ANNOUNCE objResult = null;
        try {
            JAXBContext jc = JAXBContext.newInstance(RE_ANNOUNCE.class);
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            StreamSource streamSource = new StreamSource(new StringReader(xml));
            JAXBElement<RE_ANNOUNCE> je = unmarshaller.unmarshal(streamSource,
                    RE_ANNOUNCE.class);
            objResult = (RE_ANNOUNCE) je.getValue();
        } catch (JAXBException ex) {
            Logger.getLogger(BaseWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return objResult;
    }

    public ASSIGNMENT_QUERY xmlToASSIGNMENT_QUERY(String xml) {
        try {
            JAXBContext jc = JAXBContext.newInstance(ASSIGNMENT_QUERY.class);
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            StreamSource streamSource = new StreamSource(new StringReader(xml));
            JAXBElement<ASSIGNMENT_QUERY> je = unmarshaller.unmarshal(streamSource,
                    ASSIGNMENT_QUERY.class);
            return (ASSIGNMENT_QUERY) je.getValue();
        } catch (JAXBException e) {
            log.error(e);
            return null;
        }
    }

    public Procedure getFileType(String type) {
        Procedure bo = new Procedure();
        try {
            ProcedureDAOHE prodaohe = new ProcedureDAOHE();
            Procedure pro = new Procedure();
            bo = prodaohe.getProcedureByDescription(type);
        } catch (Exception ex) {
        }
        return bo;
    }

    public String annouceReceiveToXml(ANNOUCE_RECEIVE obj) {
        String result = "";
        java.io.StringWriter sw = new StringWriter();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(ANNOUCE_RECEIVE.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(obj, sw);
            result = sw.toString();
        } catch (JAXBException e) {
            log.error(e);
        }
        return result;
    }

    public String errorToXml(RESULT obj) {
        String result = "";
        java.io.StringWriter sw = new StringWriter();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(RESULT.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(obj, sw);
            result = sw.toString();
        } catch (JAXBException ex) {
            Logger.getLogger(BaseWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public TEST_REGISTRATIONType xmlToTEST_REGISTRATIONType(String xml) {
        try {
            JAXBContext jc = JAXBContext.newInstance(TEST_REGISTRATIONType.class);
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            StreamSource streamSource = new StreamSource(new StringReader(xml));
            JAXBElement<TEST_REGISTRATIONType> je = unmarshaller.unmarshal(streamSource,
                    TEST_REGISTRATIONType.class);
            return (TEST_REGISTRATIONType) je.getValue();
        } catch (JAXBException e) {
            log.error(e);
            return null;
        }
    }

    public FILERESULTSDto xmlToFILERESULTSDto(String xml) {
        try {
            JAXBContext jc = JAXBContext.newInstance(FILERESULTSDto.class);
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            StreamSource streamSource = new StreamSource(new StringReader(xml));
            JAXBElement<FILERESULTSDto> je = unmarshaller.unmarshal(streamSource,
                    FILERESULTSDto.class);
            return (FILERESULTSDto) je.getValue();
        } catch (JAXBException e) {
            log.error(e);
            return null;
        }
    }

    public String getNationCode(String nationName) {
        Category category = new Category();
        CategoryDAOHE categorydaohe = new CategoryDAOHE();
        category = categorydaohe.findCategoryByName(Constants.CATEGORY_TYPE.NATIONAL, nationName);
        if (category != null && category.getCode() != null) {
            return category.getCode();
        }
        return "";
    }

    public String getProvinceCode(String provinceName) {
        Category category = new Category();
        CategoryDAOHE categorydaohe = new CategoryDAOHE();
        category = categorydaohe.findCategoryByName(Constants.CATEGORY_TYPE.PROVINCE, provinceName);
        if (category != null && category.getCode() != null) {
            return category.getCode();
        }
        return "";
    }

    public Department getDepartment(Long id) {
        Department department = new Department();
        DepartmentDAOHE departmentdaohe = new DepartmentDAOHE();
        department = departmentdaohe.getDeptById(id);
        if (department != null) {
            return department;

        }
        return null;
    }

//    public byte[] downloadPdfByte() throws IOException {
//        ResourceBundle rb1 = ResourceBundle.getBundle("config");
//        String PATH1 = rb1.getString("sign_download");
//        File folder = new File(PATH1);
//        File[] listOfFiles = folder.listFiles();
//        byte[] pdf = null;
//        for (File file : listOfFiles) {
//            if (file.isFile()) {
//                pdf = read(file);
//            }
//        }
//        //TODO write your implementation code here:
//        return pdf;
//    }
    public Boolean deleteFileSign(String userId, String fileId, String signType, Integer indexFile) {
        Boolean noError = true;
        ResourceBundle rb1 = ResourceBundle.getBundle("config");
        String PATH1 = rb1.getString("sign_download");
        File folder = new File(PATH1);
        File[] listOfFiles = folder.listFiles();
        for (File file : listOfFiles) {
            if (file.isFile()) {
                try {
                    // Check file name
                    String[] splitName = file.getName().split("_");
                    if (splitName.length != 4 && splitName.length != 5) {
                        continue;
                    }

                    // Compare User Id
                    if (!splitName[1].equals(userId)) {
                        continue;
                    }
                    // Compare User Id
                    if (!splitName[2].equals(fileId)) {
                        continue;
                    }
                    // Check sign Type
                    String signTypeSplit = splitName[3];
                    if (splitName.length == 4) {
                        String splitTypeStr = signTypeSplit.substring(0, signTypeSplit.indexOf(".pdf"));
                        if (!splitTypeStr.startsWith(signType)) {
                            continue;
                        }
                    } else if (splitName.length == 5) {
                        if (!signTypeSplit.startsWith(signType)) {
                            continue;
                        }
                    }

                    // Check file number for sign
                    if (splitName.length == 5) {
                        Integer indexFileSplit = Integer.parseInt(splitName[4].substring(0, splitName[4].indexOf(".pdf")));
                        if (!indexFileSplit.equals(indexFile))
                        {
                            continue;
                        }
                    }
                    file.delete();
                    //break;
                } catch (Exception ex) {
                    Logger.getLogger(FilesWS.class.getName()).log(Level.SEVERE, null, "FileWS: Có lỗi xảy ra trong quá trình xóa File tạm trên Applet: " + ex.getMessage());
                    noError = false;
                    continue;
                }
            }
        }
        return noError;
    }

    // doc file ra mang byte
    public byte[] read(File file) throws IOException {
        ByteArrayOutputStream ous = null;
        InputStream ios = null;
        try {
            byte[] buffer = new byte[4096];
            ous = new ByteArrayOutputStream();
            ios = new FileInputStream(file);
            int read = 0;
            while ((read = ios.read(buffer)) != -1) {
                ous.write(buffer, 0, read);
            }
        } finally {
            try {
                if (ous != null) {
                    ous.close();
                }
            } catch (IOException e) {
            }

            try {
                if (ios != null) {
                    ios.close();
                }
            } catch (IOException e) {
            }
        }
        return ous.toByteArray();
    }
}
