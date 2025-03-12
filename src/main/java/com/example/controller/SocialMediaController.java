package com.example.controller;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.entity.Account;
import com.example.service.AccountService;
import com.example.service.MessageService;
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
    MessageService messageService;

    public SocialMediaController(){
        this.accountService = new AccountService();
        this.messageService = new MessageService();
    }

    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    /*
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.post("/register", this::newUserHandler);
        app.post("/login", this::loginHandler);
        app.post("/messages", this::createMessageHandler);
        app.get("/messages", this::getAllMessageHandler);
        app.get("/messages/{message_id}", this::getMessageIdHandler);
        app.delete("/messages/{message_id}", this::deleteMessageIdHandler);
        app.patch("/messages/{message_id}", this::patchMessageIdHandler);
        app.get("/accounts/{account_id}/messages", this::getAccountMessageHandler);

        return app;
    }
    */

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     
    private void exampleHandler(Context context) {
        context.json("sample text");
    }
    */

    @RequestMapping("/regester")
    private void newUserHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(ctx.body(), Account.class);
        Account addedAccount = accountService.insertAccount(account);
        if(addedAccount != null){
            ctx.json(mapper.writeValueAsString(addedAccount));
        }else{
            ctx.status(400);
        }
    }

    @RequestMapping("/login")
    private void loginHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(ctx.body(), Account.class);
        Account loginAccount = accountService.login(account);
        if(loginAccount != null){
            ctx.status(200).json(mapper.writeValueAsString(loginAccount));
        }else{
            ctx.status(401);
        }
    }

    @RequestMapping(value = "/messages", method = RequestMethod.POST)
    private void createMessageHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(ctx.body(), Message.class);
        Message addedMessage = messageService.addMessage(message);
        if(addedMessage != null){
            ctx.status(200).json(mapper.writeValueAsString(addedMessage));
        }else{
            ctx.status(400);
        }
    }

    
    @RequestMapping(value = "/messages", method = RequestMethod.GET)
    private void getAllMessageHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        List<Message> allMessage = messageService.getAllMessages();
        if(allMessage != null){
            ctx.json(mapper.writeValueAsString(allMessage));
        }else{
            ctx.status(400);
        }
    }

    @RequestMapping(value = "/messages/{message_id}", method = RequestMethod.GET)
    private void getMessageIdHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Message retrivedMessage = messageService.getMessageById(Integer.parseInt(ctx.pathParam("message_id")));
        if(retrivedMessage!= null){
            ctx.status(200).json(mapper.writeValueAsString(retrivedMessage));
        }else{
            ctx.status(200);
        }
    }

    @RequestMapping(value = "/messages/{message_id}", method = RequestMethod.DELETE)
    private void deleteMessageIdHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Message deletedMessage = messageService.deleteMessageById(Integer.parseInt(ctx.pathParam("message_id")));
        if(deletedMessage != null){
            ctx.json(mapper.writeValueAsString(deletedMessage));
        }else{
            ctx.status(200);
        }
    }

    @RequestMapping(value = "/messages/{message_id}", method = RequestMethod.PATCH)
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

    @RequestMapping(value = "/messages/{message_id}/messages", method = RequestMethod.GET)
    private void getAccountMessageHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        List<Message> allMessage = messageService.getAllMessagesFromUser(Integer.parseInt(ctx.pathParam("account_id")));
        if(allMessage!=null){
            ctx.json(mapper.writeValueAsString(allMessage));
        }else{
            ctx.status(200);
        }
    }

}
