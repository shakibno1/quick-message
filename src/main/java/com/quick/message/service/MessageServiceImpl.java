package com.quick.message.service;

import com.quick.message.Model.Message;
import com.quick.message.repository.MessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    private static final Logger log = LoggerFactory.getLogger(MessageServiceImpl.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    MessageRepository messageRepository;

    @Override
    public List<Message> listAll() {
        List<Message> messages = new ArrayList<>();
        messageRepository.findAll().forEach(messages::add);
        return messages;
    }

    @Override
    public Message getById(Long id) {
        return messageRepository.findById(id).orElse(null);
    }

    @Override
    public Message saveOrUpdate(Message message) {
        messageRepository.save(message);
        return message;
    }

    @Override
    public void delete(Long id) {
        messageRepository.deleteById(id);

    }

    @Override
    public String sendMessage(String message) {
        log.info("Sending the index request through queue message");
        rabbitTemplate.convertAndSend("spring-boot-2", message);
        return "message sent!!! "+message;
    }
}
