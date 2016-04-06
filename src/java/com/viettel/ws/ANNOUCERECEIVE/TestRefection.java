/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.ws.ANNOUCERECEIVE;

import com.viettel.hqmc.FORM.FilesForm;
import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author GPCP_BINHNT53
 */
public class TestRefection {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        FilesForm ff = new FilesForm();
        ANNOUNCESENDDtoType ann = new ANNOUNCESENDDtoType();
        ANNOUNCESENDDmapFILESFORM af = new ANNOUNCESENDDmapFILESFORM();
        //int a = getObject(ann);
        //int b = getObject(wmv);
        //validateObj(ann, af);
//        Class<?> enclosingClass = getClass().getEnclosingClass();
//        if (enclosingClass != null) {
//            System.out.println(enclosingClass.getName());
//        } else {
//            System.out.println(getClass().getName());
//        }
    }

    public static int getObject(Object obj) {
        for (Field field : obj.getClass().getDeclaredFields()) {
            try {
                //field.setAccessible(true); // if you want to modify private fields
                System.out.println(field.getName()
                        + " - " + field.getType().getSimpleName()
                        + " - " + field.get(obj));
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(TestRefection.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(TestRefection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return 0;
    }

    public static void validateObj(Object input, Object compare) {//hàm thực hiện validate obj đối với obj đầu vào obj.
        boolean ck = false;
        try {
            for (Field fieldInput : input.getClass().getDeclaredFields()) {
                //System.out.println(fieldInput.getName());
                for (Field fieldCompare : compare.getClass().getDeclaredFields()) {
                    if (fieldCompare.getName().equals(fieldInput.getName())) {
                        System.out.println(fieldInput.getName() + "---" + fieldCompare.getName());
                    }
                    //System.out.println(fieldCompare.getName());
                }
            }
            System.out.println("===================================================");
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(TestRefection.class.getName()).log(Level.SEVERE, null, ex);
        }
        //list all require here        
    }
}
