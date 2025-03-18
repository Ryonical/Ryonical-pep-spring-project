package com.example.service;

import java.util.List;
import java.util.Optional;

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
        if(message.getMessageText().length() <= 255 && message.getMessageText() != null && !message.getMessageText().isEmpty() && messageRepository.existsById(message.getPostedBy()))
        {
            return messageRepository.save(message);
        }
        return null;
    }

    public int deleteMessageById(int id)
    {
        Message message = messageRepository.findById(id);
        if(message != null)
        {
            messageRepository.deleteById(id);
            return 1;
        }
        return 0;
    }

    public int updateMessage(int id, Message newText)
    {
        if(newText.getMessageText().length() > 255 || newText.getMessageText().length() == 0)
        {
            return 0;
        }
        Message message = messageRepository.findById(id);
        if(message != null)
        {
            message.setMessageText(newText.getMessageText());
            return 1;
        }
        return 0;
    }

    public List<Message> findAllMessagesByUser(int userId)
    {
        return messageRepository.findByPostedBy(userId);
    }
    

}
