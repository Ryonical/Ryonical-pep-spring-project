package com.example.repository;


import com.example.entity.Message;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository {

    public List<Message> getAllMessages();
    public Message getMessageById(int id);
    public Message insertMessage(Message message);
    public Message deleteMessageById(int id);
    public Message UpdateMessage(int id, Message newText);
    public List<Message> getAllMessagesFromUser(int userId);


}
