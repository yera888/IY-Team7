package com.backend_API.Yarah.chat;

import com.backend_API.Yarah.profile.Profile;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "chat_messages")
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private Long messageId;

    /**
     * Profile sending the message
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "sender_profile_id", nullable = false)
    private Profile senderProfile;

    /**
     * Profile receiving the message
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "receiver_profile_id", nullable = false)
    private Profile receiverProfile;

    @NotBlank
    @Column(name = "message_text", nullable = false, columnDefinition = "TEXT")
    private String messageText;

    @Column(name = "is_read", nullable = false)
    private boolean isRead = false;

    @CreationTimestamp
    @Column(name = "sent_at", nullable = false, updatable = false)
    private LocalDateTime sentAt;
}