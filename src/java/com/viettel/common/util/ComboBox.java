/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.common.util;

import com.viettel.database.DAO.BaseDAOMDBAction;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import java.util.Calendar;

/**
 *
 * @author Ebaymark
 */
public class ComboBox extends BaseDAOMDBAction {

//    public List<ComboBoxItem> getParameter(String typeCode) {
//        Session sess = getSession();
//        String hqlQuery1 = "from Parameter where typeCode='" + typeCode + "' and isActive='1'";
//        Query hqlQuery = sess.createQuery(hqlQuery1);
//        //List<Parameter> lst = hqlQuery.list();
//        List<ComboBoxItem> lstComboBox = new ArrayList<ComboBoxItem>();
//        lstComboBox.add(new ComboBoxItem(0L, "---Chọn---"));
//        for (int i = 0; i < lst.size(); i++) {
//            lstComboBox.add(new ComboBoxItem(lst.get(i).getParamterId(), lst.get(i).getParameterName()));
//        }
//        return lstComboBox;
//    }
//
//    public List<ComboBoxItem> getParameter(String typeCode, Boolean select) {
//        Session sess = getSession();
//        String hqlQuery1 = "from Parameter where typeCode='" + typeCode + "' and isActive='1'";
//        Query hqlQuery = sess.createQuery(hqlQuery1);
//        List<Parameter> lst = hqlQuery.list();
//        List<ComboBoxItem> lstComboBox = new ArrayList<ComboBoxItem>();
//
//            lstComboBox.add(new ComboBoxItem(0L, "---Chọn---"));
//
//        for (int i = 0; i < lst.size(); i++) {
//            lstComboBox.add(new ComboBoxItem(lst.get(i).getParamterId(), lst.get(i).getParameterName()));
//        }
//        return lstComboBox;
//    }
//
//    public List<ComboBoxItem> getPosition(Boolean select) {
//        String hqlQuery1 = "from PositionBO where isActive='1'";
//        Query hqlQuery = getSession().createQuery(hqlQuery1);
//        List<PositionBO> lst = hqlQuery.list();
//        List<ComboBoxItem> lstComboBox = new ArrayList<ComboBoxItem>();
//
//        lstComboBox.add(new ComboBoxItem(0L, "---Chọn---"));
//
//        for (int i = 0; i < lst.size(); i++) {
//            lstComboBox.add(new ComboBoxItem(lst.get(i).getPositionId(), lst.get(i).getPositionName()));
//        }
//        return lstComboBox;
//    }
//
//    public List<ComboBoxItem> getCommentType() {
//        String[] lst = CategoryConstant.COMMENT_TYPE;
//        List<ComboBoxItem> lstComboBox = new ArrayList<ComboBoxItem>();
//        lstComboBox.add(new ComboBoxItem(0L, "---Chọn---"));
//        for (int i = 1; i <= 4; i++) {
//            lstComboBox.add(new ComboBoxItem(Long.valueOf(i), lst[i - 1]));
//        }
//        return lstComboBox;
//    }

    public List<ComboBoxItem> getYear() {
        List<ComboBoxItem> lstComboBox = new ArrayList<ComboBoxItem>();
        lstComboBox.add(new ComboBoxItem(0L, "---Chọn năm---"));
        int year = Calendar.getInstance().get(Calendar.YEAR);

        for (int i = year; i > 1950; i--) {
            lstComboBox.add(new ComboBoxItem(Long.valueOf(i), String.valueOf(i)));
        }
        return lstComboBox;
    }

    public List<ComboBoxItem> getMonth() {
        List<ComboBoxItem> lstComboBox = new ArrayList<ComboBoxItem>();
        lstComboBox.add(new ComboBoxItem(0L, "---Chọn tháng---"));
        for (int i = 1; i < 13; i++) {
            lstComboBox.add(new ComboBoxItem(Long.valueOf(i), String.valueOf(i)));
        }
        return lstComboBox;
    }
}
