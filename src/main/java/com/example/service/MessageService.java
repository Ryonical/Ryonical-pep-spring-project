package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.repository.MessageRepository;


import com.example.repository.AccountRepository;


import org.springframework.data.jpa.repository.JpaRepository;

@Service
public class MessageService 
{
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private AccountRepository accountRepository;

    public List<Message> findAllMessages()
    {
        return messageRepository.findAll();
    }

    public Message findMessageById(int id)
    {
        return messageRepository.findById(id);
    }

    public Message insertMessage(Message message)
    {
        if(message.getMessageText().length() <= 255 && accountRepository.findById(message.getPostedBy()) != null)
        {
            return messageRepository.save(message);
        }
        return null;
    }

    public Message deleteMessageById(int id)
    {
        return messageRepository.deleteById(id);
    }

    public Message UpdateMessage(int id, Message newText)
    {
        Message message = messageRepository.findById(id);
        message.setMessageText(newText.getMessageText());
        return message;
    }

    public List<Message> findAllMessagesByUser(int userId)
    {
        return messageRepository.findByPostedBy(userId);
    }
    

}
