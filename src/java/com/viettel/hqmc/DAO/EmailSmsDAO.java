/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.DAO;

import com.viettel.hqmc.BO.MessageGroup;
import com.viettel.hqmc.BO.MessageGroupPerson;
//import com.viettel.hqmc.DAOHE.MessageEmailDAOHE;
import com.viettel.hqmc.DAOHE.MessageGroupDAOHE;
import com.viettel.hqmc.DAOHE.MessageGroupPersonDAOHE;
import com.viettel.hqmc.DAOHE.MessageSmsDAOHE;
import com.viettel.voffice.database.DAO.BaseDAO;
import java.util.List;

/**
 *
 * @author binhnt53
 */
public class EmailSmsDAO extends BaseDAO {
    
    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(EmailSmsDAO.class);

    /**
     * thong bao toi lanh dao phong da co ho so tiep nhan yeu cau phan cong ho
     * so
     *
     * @param deptId
     * @param fileCode
     * @param businessName
     * @return
     */
    public boolean alertLeaderOfStaffAssignFiles(Long deptId, String fileCode, String businessName) {
        boolean check = true;
        try {
            MessageGroupDAOHE mgdaohe = new MessageGroupDAOHE();
            MessageGroup mgbo = mgdaohe.returnMessageGroupByCodeDeptId("LOS", deptId);//lay danh sach lanh dao cua don vi leader of staff
            if (mgbo != null) {
                MessageGroupPersonDAOHE mgpdaohe = new MessageGroupPersonDAOHE();
                List<MessageGroupPerson> lstMgp = mgpdaohe.findAllByMessageGroupId(mgbo.getMesssageGroupId());
                String msgs = "Hồ sơ mã: " + fileCode + " của Doanh nghiệp: " + businessName + ", vừa được văn thư tiếp nhận. Cảm ơn!";
                if (lstMgp != null && lstMgp.size() > 0) {
                    for (int i = 0; i < lstMgp.size(); i++) {
                        //sms
                        MessageSmsDAOHE msdhe = new MessageSmsDAOHE();
                        msdhe.saveMessageSMSGroup(lstMgp.get(i).getMessageGroupPersonId(), lstMgp.get(i).getPersonPhoneNumber(), msgs);
                    }
                }
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return false;
        }

        return check;
    }
}
