package com.covoiturage.backend.service;

import com.covoiturage.backend.model.Message;
import com.covoiturage.backend.model.Utilisateur;
import com.covoiturage.backend.repository.MessageRepository;
import com.covoiturage.backend.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final UtilisateurRepository utilisateurRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository, UtilisateurRepository utilisateurRepository) {
        this.messageRepository = messageRepository;
        this.utilisateurRepository = utilisateurRepository;
    }

    public Optional<Message> envoyerMessage(Long expediteurId, Long destinataireId, String contenu) {
        Optional<Utilisateur> expediteurOpt = utilisateurRepository.findById(expediteurId);
        Optional<Utilisateur> destinataireOpt = utilisateurRepository.findById(destinataireId);

        if (expediteurOpt.isEmpty() || destinataireOpt.isEmpty()) {
            return Optional.empty();
        }

        Message message = new Message();
        message.setExpediteur(expediteurOpt.get());
        message.setDestinataire(destinataireOpt.get());
        message.setContenu(contenu);
        message.setDateEnvoi(LocalDateTime.now());

        return Optional.of(messageRepository.save(message));
    }

    public List<Message> getMessagesRecus(Long utilisateurId) {
        return utilisateurRepository.findById(utilisateurId)
                .map(messageRepository::findByDestinataire)
                .orElse(List.of());
    }

    public List<Message> getMessagesEnvoyes(Long utilisateurId) {
        return utilisateurRepository.findById(utilisateurId)
                .map(messageRepository::findByExpediteur)
                .orElse(List.of());
    }

    public List<Message> getConversation(Long user1Id, Long user2Id) {
        Optional<Utilisateur> user1Opt = utilisateurRepository.findById(user1Id);
        Optional<Utilisateur> user2Opt = utilisateurRepository.findById(user2Id);

        if (user1Opt.isEmpty() || user2Opt.isEmpty()) {
            return List.of();
        }

        return messageRepository.findByExpediteurAndDestinataireOrDestinataireAndExpediteur(
                user1Opt.get(), user2Opt.get(),
                user1Opt.get(), user2Opt.get()
        );
    }
}
