package com.backend_API.Yarah.chat;

import com.backend_API.Yarah.profile.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatRepository extends JpaRepository<ChatMessage, Long> {

    /**
     * Get all messages in a conversation between two profiles, ordered by time
     */
    @Query("SELECT m FROM ChatMessage m WHERE " +
           "(m.senderProfile = :profile1 AND m.receiverProfile = :profile2) OR " +
           "(m.senderProfile = :profile2 AND m.receiverProfile = :profile1) " +
           "ORDER BY m.sentAt ASC")
    List<ChatMessage> findConversation(
        @Param("profile1") Profile profile1,
        @Param("profile2") Profile profile2
    );

    /**
     * Get all unread messages for a specific profile
     */
    List<ChatMessage> findByReceiverProfileAndIsReadFalse(Profile receiverProfile);

    /**
     * Get all messages sent by a profile
     */
    List<ChatMessage> findBySenderProfileOrderBySentAtDesc(Profile senderProfile);

    /**
     * Get all messages received by a profile
     */
    List<ChatMessage> findByReceiverProfileOrderBySentAtDesc(Profile receiverProfile);
}