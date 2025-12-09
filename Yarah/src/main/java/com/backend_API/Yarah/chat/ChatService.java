package com.backend_API.Yarah.chat;

import com.backend_API.Yarah.profile.Profile;
import com.backend_API.Yarah.profile.ProfileRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ChatService {

    private final ChatRepository chatRepository;
    private final ProfileRepository profileRepository;

    /**
     * Send a message from one profile to another
     */
    public ChatMessage sendMessage(Long senderProfileId, Long receiverProfileId, String messageText) {
        if (messageText == null || messageText.isBlank()) {
            throw new IllegalArgumentException("Message text cannot be empty");
        }

        if (senderProfileId.equals(receiverProfileId)) {
            throw new IllegalArgumentException("Cannot send message to yourself");
        }

        Profile sender = profileRepository.findById(senderProfileId)
                .orElseThrow(() -> new EntityNotFoundException("Sender profile not found"));

        Profile receiver = profileRepository.findById(receiverProfileId)
                .orElseThrow(() -> new EntityNotFoundException("Receiver profile not found"));

        ChatMessage message = new ChatMessage();
        message.setSenderProfile(sender);
        message.setReceiverProfile(receiver);
        message.setMessageText(messageText);
        message.setRead(false);

        return chatRepository.save(message);
    }

    /**
     * Get all messages in a conversation between two profiles
     */
    @Transactional(readOnly = true)
    public List<ChatMessage> getConversation(Long profile1Id, Long profile2Id) {
        Profile profile1 = profileRepository.findById(profile1Id)
                .orElseThrow(() -> new EntityNotFoundException("Profile 1 not found"));

        Profile profile2 = profileRepository.findById(profile2Id)
                .orElseThrow(() -> new EntityNotFoundException("Profile 2 not found"));

        return chatRepository.findConversation(profile1, profile2);
    }

    /**
     * Mark a message as read
     */
    public ChatMessage markAsRead(Long messageId, Long receiverProfileId) {
        ChatMessage message = chatRepository.findById(messageId)
                .orElseThrow(() -> new EntityNotFoundException("Message not found"));

        // Verify that the person marking it as read is the receiver
        if (!message.getReceiverProfile().getProfileId().equals(receiverProfileId)) {
            throw new IllegalArgumentException("You can only mark messages sent to you as read");
        }

        message.setRead(true);
        return chatRepository.save(message);
    }

    /**
     * Get all unread messages for a profile
     */
    @Transactional(readOnly = true)
    public List<ChatMessage> getUnreadMessages(Long profileId) {
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new EntityNotFoundException("Profile not found"));

        return chatRepository.findByReceiverProfileAndIsReadFalse(profile);
    }

    /**
     * Get all messages sent by a profile
     */
    @Transactional(readOnly = true)
    public List<ChatMessage> getSentMessages(Long profileId) {
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new EntityNotFoundException("Profile not found"));

        return chatRepository.findBySenderProfileOrderBySentAtDesc(profile);
    }

    /**
     * Get all messages received by a profile
     */
    @Transactional(readOnly = true)
    public List<ChatMessage> getReceivedMessages(Long profileId) {
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new EntityNotFoundException("Profile not found"));

        return chatRepository.findByReceiverProfileOrderBySentAtDesc(profile);
    }
}