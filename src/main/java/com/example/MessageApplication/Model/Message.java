package com.example.MessageApplication.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String sender;
    private String receiver;
    private String message;
    private MessageType status;
    private LocalDateTime timestamp = LocalDateTime.now();

    public enum MessageType {
        JOIN, MESSAGE, LEAVE
    }
}
