package com.example.repository;

import java.util.Optional;

import com.example.entity.Message;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface MessageRepository {

    public List<Message> findAllMessages();
    public Message findMessageById(int id);
    public Message deleteById(int id);
    @Modifying
    @Transactional
    @Query("UPDATE Message m SET m.messageText = :messageText WHERE m.messageId = :id")
    public void updateMessage(@Param("id") int id, @Param("messageText") String messageText);
    public List<Message> findByPostedBy(int userId);


}
