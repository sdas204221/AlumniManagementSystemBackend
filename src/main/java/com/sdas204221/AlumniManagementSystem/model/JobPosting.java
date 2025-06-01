package com.sdas204221.AlumniManagementSystem.model;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "job_postings")
public class JobPosting {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private boolean approved;

    private LocalDateTime creationDate;
    private LocalDateTime approvalDate;

    @OneToMany
    private List<FileEntity> files;

    @ManyToOne
    @JoinColumn(name = "approved_by")
    private User approvedBy; 

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User postedBy;

    
}
