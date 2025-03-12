package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Message;

@Service
public class MessageService {

    @Autowired
    public List<Message> getAllMessages()
    {

    }

    @Autowired
    public Message getMessageById(int id)
    {

    }

    @Autowired
    public Message insertMessage(Message message)
    {

    }

    @Autowired
    public Message deleteMessageById(int id)
    {

    }

    @Autowired
    public Message UpdateMessage(int id, Message newText)
    {

    }

    @Autowired
    public List<Message> getAllMessagesFromUser(int userId)
    {
        
    }

}
