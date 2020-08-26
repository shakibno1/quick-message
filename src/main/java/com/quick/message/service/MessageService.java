package com.quick.message.service;

import com.quick.message.Model.Message;

import java.util.List;

public interface MessageService {

    List<Message> listAll();

    Message getById(Long id);

    Message saveOrUpdate(Message message);

    void delete(Long id);

    String sendMessage(String message);
}
