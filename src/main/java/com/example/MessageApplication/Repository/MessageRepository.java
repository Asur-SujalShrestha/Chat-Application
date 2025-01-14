package com.example.MessageApplication.Repository;

import com.example.MessageApplication.Model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query("SELECT m FROM Message m WHERE m.receiver IS NULL")
    List<Message> findPublicMessages();

    @Query("SELECT m FROM Message m WHERE (m.sender = :user1 AND m.receiver = :user2) OR (m.sender = :user2 AND m.receiver = :user1) ")
    List<Message> findPrivateMessages(@Param("user1") String user1, @Param("user2") String user2);
}

