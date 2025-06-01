package com.sdas204221.AlumniManagementSystem.model;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "event_registrations")
public class EventRegistration {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDateTime registrationTime;
    private String notes;

    
}
