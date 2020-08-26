package com.quick.message.listener;


import com.quick.message.MessagingRabbitmqApplication;
import com.quick.message.Model.Message;
import com.quick.message.Model.TransactionType;
import com.quick.message.repository.MessageRepository;
import com.quick.message.service.MessageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    @Autowired
    public MessageServiceImpl messageService;

    public void receiveMessage(String message) {
        System.out.println("Received <" + message + ">");
        String sentMessage = "Hello From quick message 1...";

        Message newMessage = new Message();
        newMessage.setMessage(message);
        newMessage.setMessageHost(MessagingRabbitmqApplication.messageHost);
        newMessage.setTransactionType(TransactionType.RECEIVED_MESSAGE);
        messageService.saveOrUpdate(newMessage);

        messageService.sendMessage(sentMessage);

        Message newSentMessage = new Message();
        newSentMessage.setMessage(sentMessage);
        newSentMessage.setMessageHost(MessagingRabbitmqApplication.messageHost);
        newSentMessage.setTransactionType(TransactionType.SENT_MESSAGE);
        messageService.saveOrUpdate(newSentMessage);
    }

}
