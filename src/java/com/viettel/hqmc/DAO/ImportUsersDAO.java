/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.DAO;

import com.viettel.voffice.database.BO.VoAttachs;
import com.viettel.voffice.database.DAO.BaseDAO;
//import com.viettel.vsaadmin.client.form.UsersForm;
import com.viettel.vsaadmin.database.BO.Users;
import com.viettel.vsaadmin.database.DAOHibernate.UsersDAOHE;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 *
 * @author binhnt53
 */
public class ImportUsersDAO extends BaseDAO {

    /**
     * nhat user tu exel
     * @return
     * @throws Exception
     */
    public String importStaffFromExcel() throws Exception {
        List customInfo = new ArrayList();//lst users
        Long attachId = Long.parseLong(getRequest().getParameter("attachId"));//get attactId
        VoAttachs att = (VoAttachs) getSession().get("com.viettel.hvct.database.BO.Attach", attachId);//Attachs BO
        if (att == null) {// if att null return error users
            customInfo.add("error");
        } else {
            String srcFile = att.getAttachPath();//get path attach file
            InputStream myxls = new FileInputStream(srcFile);
            HSSFWorkbook wb = new HSSFWorkbook(myxls);
            HSSFSheet sheet = wb.getSheetAt(0);
            HSSFRow row;
            HSSFRow firstRow = sheet.getRow(0);
            int rowNums = sheet.getLastRowNum();
            UsersDAOHE sdhe = new UsersDAOHE();
            SimpleDateFormat formatter = new SimpleDateFormat("dd/mm/yyyy");
            for (int i = 4; i <= rowNums; i++) {
                row = sheet.getRow(i);
                if (row != null) {
                    Users entity = new Users();
                    HSSFCell cellSTT = row.getCell((short) 0);
                    HSSFCell cellUserName = row.getCell((short) 1);
                    HSSFCell cellFullName = row.getCell((short) 2);
                    HSSFCell cellPassword = row.getCell((short) 3);
                    HSSFCell cellEmail = row.getCell((short) 4);
                    HSSFCell cellCellPhone = row.getCell((short) 5);
                    HSSFCell cellDeptId = row.getCell((short) 6);
                    HSSFCell cellBusinessName = row.getCell((short) 7);
                    HSSFCell cellPosId = row.getCell((short) 8);
                    HSSFCell cellStatus = row.getCell((short) 9);
                    HSSFCell cellAliasName = row.getCell((short) 10);
                    HSSFCell cellGender = row.getCell((short) 11);
                    HSSFCell cellTelephone = row.getCell((short) 12);
                    HSSFCell cellFax = row.getCell((short) 13);
                    HSSFCell cellDateOfBirth = row.getCell((short) 14);
                    HSSFCell cellBirthPlace = row.getCell((short) 15);
                    HSSFCell cellStaffCode = row.getCell((short) 16);
                    HSSFCell cellIdentityCard = row.getCell((short) 17);
                    HSSFCell cellIssueDateIdent = row.getCell((short) 18);
                    HSSFCell cellIssuePlaceIdent = row.getCell((short) 19);
                    HSSFCell cellDescription = row.getCell((short) 20);

                    entity.setUserName(cellUserName.getStringCellValue());
                    entity.setFullName(cellFullName.getStringCellValue());
                    entity.setPassword(cellPassword.getStringCellValue());
                    entity.setEmail(cellEmail.getStringCellValue());
                    entity.setCellphone(cellCellPhone.getStringCellValue());
                    entity.setDeptId(Long.parseLong(cellDeptId.getStringCellValue()));
                    entity.setBusinessName(cellBusinessName.getStringCellValue());
                    entity.setPosId(Long.parseLong(cellPosId.getStringCellValue()));
                    entity.setStatus(Long.parseLong(cellStatus.getStringCellValue()));
                    entity.setAliasName(cellAliasName.getStringCellValue());
                    entity.setGender(Long.parseLong(cellGender.getStringCellValue()));
                    entity.setTelephone(cellTelephone.getStringCellValue());
                    entity.setFax(cellFax.getStringCellValue());
                    entity.setDateOfBirth(formatter.parse(cellDateOfBirth.getStringCellValue()));                   
                    entity.setBirthPlace(cellBirthPlace.getStringCellValue());
                    entity.setStaffCode(cellStaffCode.getStringCellValue());
                    entity.setIdentityCard(cellIdentityCard.getStringCellValue());
                    entity.setIssueDateIdent(formatter.parse(cellIssueDateIdent.getStringCellValue()));
                    entity.setIssuePlaceIdent(cellIssuePlaceIdent.getStringCellValue());
                    entity.setDescription(cellDescription.getStringCellValue());


                    String gender = cellGender.getStringCellValue();
                    if (gender == null || gender.trim().length() == 0) {
                        return "err";
                    } else {
                        gender = gender.trim().toLowerCase();
                        if (gender.contains("nam") || gender.contains("man") || gender.contains("male")) {
                            entity.setGender(0L);
                        } else {
                            entity.setGender(1L);
                        }
                    }                    
                    Long id = sdhe.getUserByStaffCode(entity.getStaffCode());
                    if (id != null && id > 0) {
                        entity.setUserId(id);
                        getSession().update(entity);
                    } else {
                        getSession().save(entity);
                    }
                    customInfo.add("success");

                } // end if row != null
            } // end loop
        } // end if att != null
        this.jsonData.setCustomInfo(customInfo);
        return "gridData";
    }
}
