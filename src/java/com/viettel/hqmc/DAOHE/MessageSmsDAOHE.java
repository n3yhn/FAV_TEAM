/*
 * To change this bolate, choose Tools | bolates
 * and open the bolate in the editor.
 */
package com.viettel.hqmc.DAOHE;

import com.viettel.common.util.Constants;
import com.viettel.common.util.LogUtil;
import com.viettel.hqmc.BO.MessageSms;
import com.viettel.voffice.database.DAO.BaseDAO;
import com.viettel.voffice.database.DAOHibernate.GenericDAOHibernate;
import com.viettel.vsaadmin.database.BO.Users;
import com.viettel.vsaadmin.database.DAOHibernate.UsersDAOHE;
import org.apache.log4j.Logger;
import org.hibernate.Query;

/**
 *
 * @author binhnt53
 */
public class MessageSmsDAOHE extends GenericDAOHibernate<MessageSms, Long> {

    private static Logger logger = Logger.getLogger(MessageSmsDAOHE.class);
    private static final Long ZERO = 0L;
    private UsersDAOHE usersDAOHE = new UsersDAOHE();
    private BaseDAO baseDAO = new BaseDAO();

    public MessageSmsDAOHE() {
        super(MessageSms.class);
    }

    /**
     * @param receiveId
     * @param content
     * @return
     */
    /**
     * @param sendId
     * @param receiverList
     * @param content
     * @return
     */
    public boolean sendMessage(Long sendId, String[] receiverList, String content) {
        if ("0".equals(Constants.FLAG_SEND_SMSEMAIL.FLAG_SMS)) {
            return true;
        }
        boolean result = true;
        try {
            for (String id : receiverList) {
                if (id != null && !"".equals(id)) {
                    Long userId = Long.valueOf(id);
                    MessageSms bo = this.entityToBo(sendId, userId, content);
                    this.create(bo);
                }
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            result = false;
        }
        return result;
    }

    /**
     * @param senderId: id nguoi gui
     * @param receiverId
     * @param content: noi dung tin nhan
     * @return
     */
    public MessageSms entityToBo(Long senderId, Long receiverId, String content) {
        MessageSms bo = new MessageSms();
        try {
            Users user = usersDAOHE.findById(receiverId, false);
            bo.setSenderId(senderId);
            bo.setUserId(receiverId);
            bo.setPhoneNumber(user.getCellphone());
            //linhdx
            content = baseDAO.convertVietNameseToNoSign(content);
            if (content.length() > 160) {
                content = content.substring(0, 150) + "...";
            }
            //end linhdx
            bo.setContent(content);
            bo.setIsSent(ZERO);
            bo.setSentTimeReq(getSysdate());
            bo.setSendCount(0L);
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        return bo;
    }

    /**
     * saveMessageSMS
     *
     * @param senderId
     * @param receiverId
     * @param content
     * @return
     */
    public boolean saveMessageSMS(Long senderId, Long receiverId, String content) {
        if ("0".equals(Constants.FLAG_SEND_SMSEMAIL.FLAG_SMS)) {
            return true;
        }
        boolean result = true;
        try {
            String hql = "update MessageSmsFlag m set m.flag = 1";
            Query updateQuery = getSession().createQuery(hql);
            updateQuery.executeUpdate();
            MessageSms bo = this.entityToBo(senderId, receiverId, content);
            this.create(bo);

        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            result = false;
        }
        return result;
    }

    //=========================================================================
    public MessageSms entityToBoGroup(Long receiverId, String cellphone, String content) {
        MessageSms bo = new MessageSms();
        try {
            bo.setSenderId(-1L);
            bo.setUserId(receiverId);
            bo.setPhoneNumber(cellphone);
            content = baseDAO.convertVietNameseToNoSign(content);
            if (content.length() > 160) {
                content = content.substring(0, 150) + "...";
            }
            bo.setContent(content);
            bo.setIsSent(ZERO);
            bo.setSentTimeReq(getSysdate());
            bo.setSendCount(0L);
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        return bo;
    }

    /**
     * saveMessageSMS
     *
     * @param senderId
     * @param receiverId
     * @param content
     * @return
     */
    public boolean saveMessageSMSGroup(Long receiverId, String cellphone, String content) {
        if ("0".equals(Constants.FLAG_SEND_SMSEMAIL.FLAG_SMS)) {
            return true;
        }
        boolean result = true;
        try {
            String hql = "update MessageSmsFlag m set m.flag = 1";
            Query updateQuery = getSession().createQuery(hql);
            updateQuery.executeUpdate();
            MessageSms bo = this.entityToBoGroup(receiverId, cellphone, content);
            this.create(bo);
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            result = false;
        }
        return result;
    }
}
