package com.covoiturage.backend.controller;

import com.covoiturage.backend.model.Message;
import com.covoiturage.backend.service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/messages")
@CrossOrigin(origins = "*")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/envoyer")
    public ResponseEntity<Message> envoyerMessage(@RequestParam Long expediteurId,
                                                  @RequestParam Long destinataireId,
                                                  @RequestParam String contenu) {
        Optional<Message> messageOpt = messageService.envoyerMessage(expediteurId, destinataireId, contenu);
        return messageOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/recus/{utilisateurId}")
    public ResponseEntity<List<Message>> getMessagesRecus(@PathVariable Long utilisateurId) {
        List<Message> messages = messageService.getMessagesRecus(utilisateurId);
        return ResponseEntity.ok(messages);
    }

    @GetMapping("/envoyes/{utilisateurId}")
    public ResponseEntity<List<Message>> getMessagesEnvoyes(@PathVariable Long utilisateurId) {
        List<Message> messages = messageService.getMessagesEnvoyes(utilisateurId);
        return ResponseEntity.ok(messages);
    }

    @GetMapping("/conversation")
    public ResponseEntity<List<Message>> getConversation(@RequestParam Long user1Id, @RequestParam Long user2Id) {
        List<Message> conversation = messageService.getConversation(user1Id, user2Id);
        return ResponseEntity.ok(conversation);
    }
}
