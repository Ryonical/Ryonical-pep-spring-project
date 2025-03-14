package com.example.controller;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.entity.Account;
import com.example.service.AccountService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
    //MessageService messageService;

    public SocialMediaController(){
        this.accountService = new AccountService();
        //this.messageService = new MessageService();
    }


    @PostMapping("/regester")
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
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
    /*
    @PostMapping("/messages")
    public ResponseEntity<?> createMessageHandler(@RequestBody Message message) 
    {
        Message addedMessage = messageService.addMessage(message);
        if(addedMessage != null){
            ctx.status(200).json(mapper.writeValueAsString(addedMessage));
        }else{
            ctx.status(400);
        }
    }

    
    @GetMapping("/messages")
    private void getAllMessageHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        List<Message> allMessage = messageService.getAllMessages();
        if(allMessage != null){
            ctx.json(mapper.writeValueAsString(allMessage));
        }else{
            ctx.status(400);
        }
    }

    @GetMapping("/messages/{message_id}")
    private void getMessageIdHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Message retrivedMessage = messageService.getMessageById(Integer.parseInt(ctx.pathParam("message_id")));
        if(retrivedMessage!= null){
            ctx.status(200).json(mapper.writeValueAsString(retrivedMessage));
        }else{
            ctx.status(200);
        }
    }

    @DeleteMapping("/messages/{message_id}")
    private void deleteMessageIdHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Message deletedMessage = messageService.deleteMessageById(Integer.parseInt(ctx.pathParam("message_id")));
        if(deletedMessage != null){
            ctx.json(mapper.writeValueAsString(deletedMessage));
        }else{
            ctx.status(200);
        }
    }

    @PatchMapping("/messages/{message_id}")
    private void patchMessageIdHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(ctx.body(), Message.class);
        Message changedMessage = messageService.updateMessageById(Integer.parseInt(ctx.pathParam("message_id")), message);
        if(changedMessage != null){
            ctx.json(mapper.writeValueAsString(changedMessage));
        }else{
            ctx.status(400);
        }
    }

    @GetMapping("/messages/{message_id}/messages")
    private void getAccountMessageHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        List<Message> allMessage = messageService.getAllMessagesFromUser(Integer.parseInt(ctx.pathParam("account_id")));
        if(allMessage!=null){
            ctx.json(mapper.writeValueAsString(allMessage));
        }else{
            ctx.status(200);
        }
    }
        */

}
