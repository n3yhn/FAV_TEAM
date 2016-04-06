/*
 * To change this bolate, choose Tools | bolates
 * and open the bolate in the editor.
 */
package com.viettel.hqmc.DAOHE;

import com.viettel.common.util.Constants;
import com.viettel.hqmc.BO.MessageEmail;
import com.viettel.voffice.database.DAOHibernate.*;
import com.viettel.voffice.database.DAO.BaseDAO;
import com.viettel.vsaadmin.database.BO.Users;
import com.viettel.vsaadmin.database.DAOHibernate.UsersDAOHE;
import org.apache.log4j.Logger;
import org.hibernate.Query;

/**
 *
 * @author binhnt53
 */
public class MessageEmailDAOHE extends GenericDAOHibernate<MessageEmail, Long> {

    private static Logger logger = Logger.getLogger(MessageEmailDAOHE.class);
    private static final Long ZERO = 0L;
    private UsersDAOHE usersDAOHE = new UsersDAOHE();
    private BaseDAO baseDAO = new BaseDAO();

    public MessageEmailDAOHE() {
        super(MessageEmail.class);
    }

    public MessageEmail entityToBo(Long senderId, Long receiverId, String content) {
        MessageEmail bo = new MessageEmail();
        try {
            Users user = usersDAOHE.findById(receiverId, false);
            bo.setSenderId(senderId);
            bo.setReceiveEmail(user.getEmail());
            bo.setContent(content);
            bo.setIsSent(ZERO);
            bo.setSentTimeReq(getSysdate());
            bo.setSendCount(0L);
        } catch (Exception ex) {
            logger.error(ex);
        }
        return bo;
    }

    /**
     * saveMessageEmail
     *
     * @param senderId
     * @param receiverId
     * @param content
     * @return
     */
    public boolean saveMessageEmail(Long senderId, Long receiverId, String content) {
        if (Constants.FLAG_SEND_SMSEMAIL.FLAG_EMAIL.equals("0")) return true;
        boolean result = true;
        try {
            String hql = "update MessageEmailFlag m set m.flag = 1";
            Query updateQuery = getSession().createQuery(hql);
            int n = updateQuery.executeUpdate();
            MessageEmail bo = new MessageEmail();
            bo = this.entityToBo(senderId, receiverId, content);
            this.create(bo);
        } catch (Exception e) {
            logger.error(e);
            result = false;
        }
        return result;
    }

    //===============================send group user============================
    public MessageEmail entityToBoGroup(Long receiverId, String email, String content) {
        MessageEmail bo = new MessageEmail();
        try {
            bo.setSenderId(-1L);
            bo.setReceiveEmail(email);
            bo.setContent(content);
            bo.setIsSent(ZERO);
            bo.setSentTimeReq(getSysdate());
            bo.setSendCount(0L);
        } catch (Exception ex) {
            logger.error(ex);
        }
        return bo;
    }

    /**
     * saveMessageEmail
     *
     * @param senderId
     * @param receiverId
     * @param content
     * @return
     */
    public boolean saveMessageEmailGroup(Long receiverId, String email, String content) {
        if (Constants.FLAG_SEND_SMSEMAIL.FLAG_EMAIL.equals("0")) return true;
        boolean result = true;
        try {
            String hql = "update MessageEmailFlag m set m.flag = 1";
            Query updateQuery = getSession().createQuery(hql);
            int n = updateQuery.executeUpdate();
            MessageEmail bo = new MessageEmail();
            bo = this.entityToBoGroup(receiverId, email, content);
            this.create(bo);
        } catch (Exception e) {
            logger.error(e);
            result = false;
        }
        return result;
    }
    //    public boolean sendMessage(Long sendId, String[] receiverList, String content) {
//        boolean result = true;
//        try {
//            for (String id : receiverList) {
//                if (id != null && !id.equals("")) {
//                    MessageEmail bo = new MessageEmail();
//                    Long userId = Long.valueOf(id);
//                    bo = this.entityToBo(sendId, userId, content);
//                    this.create(bo);
//                }
//            }
//        } catch (Exception e) {
//            logger.error(e);
//            result = false;
//        }
//        return result;
//    }
}
