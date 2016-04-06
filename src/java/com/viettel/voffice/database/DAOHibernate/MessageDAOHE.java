/*
 * To change this bolate, choose Tools | bolates
 * and open the bolate in the editor.
 */
package com.viettel.voffice.database.DAOHibernate;

import com.viettel.common.util.Constants;
import com.viettel.voffice.database.BO.Message;
import com.viettel.voffice.database.DAO.BaseDAO;
import com.viettel.vsaadmin.database.BO.Users;
import com.viettel.vsaadmin.database.DAOHibernate.UsersDAOHE;
import org.apache.log4j.Logger;
import org.hibernate.Query;

/**
 *
 * @author sytv
 */
public class MessageDAOHE extends GenericDAOHibernate<Message, Long> {
    
    private static Logger logger = Logger.getLogger(MessageDAOHE.class);
    private static final Long ZERO = 0L;
    private UsersDAOHE usersDAOHE = new UsersDAOHE();
    private BaseDAO baseDAO = new BaseDAO();
    
    public MessageDAOHE() {
        super(Message.class);
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
        if (Constants.FLAG_SEND_SMSEMAIL.FLAG_SMS.equals("0")) return true;
        boolean result = true;
        try {
            for (String id : receiverList) {
                if (id != null && !id.equals("")) {
                    Message bo = new Message();
                    Long userId = Long.valueOf(id);
                    bo = this.entityToBo(sendId, userId, content);
                    this.create(bo);
                }
            }
        } catch (Exception e) {
            logger.error(e);
            result = false;
        }
        return result;
    }

    /**
     * @param senderId: id nguoi gui
     * @param receiveId: id nguoi nhan
     * @param content: noi dung tin nhan
     * @return
     */
    public Message entityToBo(Long senderId, Long receiverId, String content) {
        Message bo = new Message();
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
        } catch (Exception ex) {
            logger.error(ex);
        }
        return bo;
    }
    
    public boolean saveMessage(Long senderId, Long receiverId, String content, Long status) {
        if (Constants.FLAG_SEND_SMSEMAIL.FLAG_SMS.equals("0")) return true;
        boolean result = true;
        try {
            Message bo = new Message();
            bo = this.entityToBo(senderId, receiverId, content);
            this.create(bo);
        } catch (Exception e) {
            logger.error(e);
            result = false;
        }
        return result;
    }
}
