package com.example.repository;


import com.example.entity.Message;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface MessageRepository {

    public List<Message> findAllMessages();
    public Message findMessageById(int id);
    public Message deleteById(int id);
    public Message UpdateMessage(int id, Message newText);
    public List<Message> findAllMessagesByUser(int userId);


}
