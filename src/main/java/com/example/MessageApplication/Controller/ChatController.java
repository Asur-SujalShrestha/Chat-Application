package com.example.MessageApplication.Controller;





import com.example.MessageApplication.Model.Message;
import com.example.MessageApplication.Repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ChatController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private MessageRepository messageRepository;

    @MessageMapping("/message")
    @SendTo("/chatroom/public")
    public Message broadcastMessage(Message message) {
        messageRepository.save(message);
        return message;
    }

    @MessageMapping("/private-message")
    public void sendPrivateMessage(Message message) {
        messageRepository.save(message);
        messagingTemplate.convertAndSendToUser(message.getReceiver(), "/private", message);
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/messages/public")
    public List<Message> getPublicMessages() {
        return messageRepository.findPublicMessages();
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/messages/private")
    public List<Message> getPrivateMessages(@RequestParam String sender, @RequestParam String receiver) {

        return messageRepository.findPrivateMessages(sender, receiver);
    }
}

