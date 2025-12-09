package com.backend_API.Yarah.chat;

import com.backend_API.Yarah.profile.Profile;
import com.backend_API.Yarah.profile.ProfileRepository;
import com.backend_API.Yarah.user.User;
import com.backend_API.Yarah.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ChatController {

    private final ChatService chatService;
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;

    /**
     * Send a message (sender is automatically set to current user)
     */
    @PostMapping("/send")
    public ResponseEntity<ChatMessage> sendMessage(
            Authentication auth,
            @RequestBody SendMessageRequest req) {
        
        if (auth == null) {
            return ResponseEntity.status(401).build();
        }

        String email = auth.getName();
        User user = userRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Profile senderProfile = profileRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        try {
            ChatMessage message = chatService.sendMessage(
                senderProfile.getProfileId(),
                req.getReceiverProfileId(),
                req.getMessageText()
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(message);
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        } catch (EntityNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    /**
     * Get conversation between current user and another profile
     */
    @GetMapping("/conversation/{otherProfileId}")
    public ResponseEntity<List<ChatMessage>> getConversation(
            Authentication auth,
            @PathVariable Long otherProfileId) {
        
        if (auth == null) {
            return ResponseEntity.status(401).build();
        }

        String email = auth.getName();
        User user = userRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Profile myProfile = profileRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        try {
            List<ChatMessage> messages = chatService.getConversation(
                myProfile.getProfileId(),
                otherProfileId
            );
            return ResponseEntity.ok(messages);
        } catch (EntityNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    /**
     * Mark a message as read
     */
    @PutMapping("/{messageId}/read")
    public ResponseEntity<ChatMessage> markAsRead(
            Authentication auth,
            @PathVariable Long messageId) {
        
        if (auth == null) {
            return ResponseEntity.status(401).build();
        }

        String email = auth.getName();
        User user = userRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Profile myProfile = profileRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        try {
            ChatMessage message = chatService.markAsRead(messageId, myProfile.getProfileId());
            return ResponseEntity.ok(message);
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, ex.getMessage());
        } catch (EntityNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    /**
     * Get all unread messages for current user
     */
    @GetMapping("/unread")
    public ResponseEntity<List<ChatMessage>> getUnreadMessages(Authentication auth) {
        if (auth == null) {
            return ResponseEntity.status(401).build();
        }

        String email = auth.getName();
        User user = userRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Profile myProfile = profileRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        try {
            List<ChatMessage> messages = chatService.getUnreadMessages(myProfile.getProfileId());
            return ResponseEntity.ok(messages);
        } catch (EntityNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    /**
     * Get all messages sent by current user
     */
    @GetMapping("/sent")
    public ResponseEntity<List<ChatMessage>> getSentMessages(Authentication auth) {
        if (auth == null) {
            return ResponseEntity.status(401).build();
        }

        String email = auth.getName();
        User user = userRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Profile myProfile = profileRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        try {
            List<ChatMessage> messages = chatService.getSentMessages(myProfile.getProfileId());
            return ResponseEntity.ok(messages);
        } catch (EntityNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    /**
     * Get all messages received by current user
     */
    @GetMapping("/received")
    public ResponseEntity<List<ChatMessage>> getReceivedMessages(Authentication auth) {
        if (auth == null) {
            return ResponseEntity.status(401).build();
        }

        String email = auth.getName();
        User user = userRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Profile myProfile = profileRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        try {
            List<ChatMessage> messages = chatService.getReceivedMessages(myProfile.getProfileId());
            return ResponseEntity.ok(messages);
        } catch (EntityNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @Data
    public static class SendMessageRequest {
        private Long receiverProfileId;
        private String messageText;
    }
}