package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Message;


import org.springframework.data.jpa.repository.JpaRepository;

@Service
public class MessageService {
    /*
    @AutoWired
    private MessageRepository messageRepositiory;

    public List<Message> findAllMessages()
    {
        return messageRepository.findAllMessages();
    }

    public Message findMessageById(int id)
    {
        return messageRepository.findMessageById(id);
    }

    public Message insertMessage(Message message)
    {
        return messageRepository.save(message);

    }

    public Message deleteMessageById(int id)
    {
        return messageRepository.deleteById(id);
    }

    public Message UpdateMessage(int id, Message newText)
    {
        Message message = messageRepository.findMessageById(id);
        message.setMessageText(newText.getMessageText());
        return message;
    }

    public List<Message> findAllMessagesByUser(int userId)
    {
        return messageRepository.findAllMessagesByUser(userId);
    }
    */

}
