package com.sdas204221.AlumniManagementSystem.model;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "events")
public class Event {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String eventName;
    private String eventDescription;
    private LocalDateTime eventDate;
    private LocalDateTime creationDate;
    private LocalDateTime approvalDate;
    private boolean approved;

    @ManyToOne
    @JoinColumn(name = "approved_by")
    private User approvedBy; 

    @ElementCollection
    private Set<Integer> graduationYears; 

    @ElementCollection
    private Set<String> streams;
    
}
