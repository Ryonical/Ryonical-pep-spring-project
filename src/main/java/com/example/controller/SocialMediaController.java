package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;
import com.example.service.MessageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;



 @Controller
 @ComponentScan(basePackages = "com.example")
public class SocialMediaController {
    
    AccountService accountService;
    MessageService messageService;

    @Autowired
    public SocialMediaController(AccountService accountService, MessageService messageService){
        this.accountService = accountService;
        this.messageService = messageService;
    }


    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody Account account)
    {
        if(accountService.findByUsername(account.getUsername()) != null)
        {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        Account addedAccount = accountService.insertAccount(account);
        if(addedAccount != null)
        {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginHandler(@RequestBody Account account)
    {
        Account loginAccount = accountService.loginAccount(account.getUsername(), account.getPassword());
        if(loginAccount != null){
            return new ResponseEntity<Account>(loginAccount, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
    
    @PostMapping("/messages")
    public ResponseEntity<?> createMessageHandler(@RequestBody Message message) 
    {
        Message addedMessage = messageService.insertMessage(message);
        if(addedMessage != null){
            return new ResponseEntity<>(addedMessage, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    
    @GetMapping("/messages")
    private ResponseEntity<?> getAllMessageHandler()
    {
        ResponseEntity<List<Message>> allMessage = new ResponseEntity(messageService.findAllMessages(), HttpStatus.OK);
        return allMessage;
    }

    @GetMapping("/messages/{message_id}")
    private ResponseEntity<?> getMessageIdHandler(@PathVariable("message_id") int id)
    {
        Message retrivedMessage = messageService.findMessageById(id);
        if(retrivedMessage!= null){
            return new ResponseEntity<>(retrivedMessage, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @DeleteMapping("/messages/{message_id}")
    private ResponseEntity<?> deleteMessageIdHandler(@PathVariable("message_id") int id)
    {
        int numRows = messageService.deleteMessageById(id);
        if(numRows != 0){
            return new ResponseEntity<>(numRows, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @PatchMapping("/messages/{message_id}")
    private ResponseEntity<?> patchMessageIdHandler(@RequestBody Message message, @PathVariable("message_id") int id)
    {
        int numRows = messageService.updateMessage(id, message);
        if(numRows != 0){
            return new ResponseEntity<>(numRows, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/accounts/{accountId}/messages")
    private ResponseEntity<?> getAccountMessageHandler(@PathVariable("accountId") int id)
    {
        return new ResponseEntity(messageService.findAllMessagesByUser(id), HttpStatus.OK);
    }
    
    
}
