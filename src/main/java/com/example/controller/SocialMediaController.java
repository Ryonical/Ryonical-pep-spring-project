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

import java.util.ArrayList;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */

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
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    
    @GetMapping("/messages")
    private ResponseEntity<?> getAllMessageHandler()
    {
        ResponseEntity<ArrayList<Message>> allMessage = new ResponseEntity(messageService.findAllMessages(), HttpStatus.OK);
        return allMessage;
    }

    @GetMapping("/messages/{message_id}")
    private ResponseEntity<?> getMessageIdHandler(@RequestParam("message_id") int id)
    {
        Message retrivedMessage = messageService.findMessageById(id);
        if(retrivedMessage!= null){
            return new ResponseEntity<>(retrivedMessage, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @DeleteMapping("/messages/{message_id}")
    private ResponseEntity<?> deleteMessageIdHandler(@RequestParam("message_id") int id)
    {
        Integer numRows = messageService.deleteMessageById(id);
        if(numRows != 0){
            return new ResponseEntity<>(numRows, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @PatchMapping("/messages/{message_id}")
    private ResponseEntity<?> patchMessageIdHandler(@RequestBody Message message, @RequestParam("message_id") int id)
    {
        Integer numRows = messageService.updateMessage(id, message);
        if(numRows != 0){
            return new ResponseEntity<>(numRows, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/messages/{user_id}/messages")
    private ResponseEntity<?> getAccountMessageHandler(@RequestParam("user_id") int id)
    {
        ResponseEntity<ArrayList<Message>> allMessage = new ResponseEntity(messageService.findAllMessagesByUser(id), HttpStatus.OK);
        return allMessage;
    }
    
    
}
