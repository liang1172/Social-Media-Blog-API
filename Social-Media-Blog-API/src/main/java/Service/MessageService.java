package Service;

import Model.Message;
import DAO.MessageDAO;
import DAO.AccountDAO;

import java.util.List;

public class MessageService {
    private MessageDAO messageDAO;
    private AccountDAO accountDAO;

    public MessageService() {
        this.messageDAO = new MessageDAO();
        this.accountDAO = new AccountDAO();
    }

    public MessageService(MessageDAO messageDAO, AccountDAO accountDAO) {
        this.messageDAO = messageDAO;
        this.accountDAO = accountDAO;
    }

    public Message createMessage(Message message) {
        if (message.getMessage_text() == null || message.getMessage_text().trim().isEmpty()) {
            return null;
        }

        if(message.getMessage_text().length() > 255) {
            return null;
        }

        if (accountDAO.getAccountById(message.getPosted_by()) == null) {
            return null;
        }

        return messageDAO.insertMessage(message);
    }

    public List<Message> getAllMessages() {
        return messageDAO.getAllMessage();
    }

    public Message getMessageById(int message_id) {
        return messageDAO.getMessageById(message_id);
    }

    public List<Message> getMessagesByAccountId(int account_id) {
        return messageDAO.getMessagesByAccountId(account_id);
    }

    public Message deleteMessageById(int message_id) {
        return messageDAO.deleteMessageById(message_id);
    }

    public Message updateMessageText(int message_id, String newMessage) {
        if (newMessage == null || newMessage.trim().isEmpty() || newMessage.length() > 255) {
            return null;
        }

        return messageDAO.updateMessageText(message_id, newMessage);
    }
}
