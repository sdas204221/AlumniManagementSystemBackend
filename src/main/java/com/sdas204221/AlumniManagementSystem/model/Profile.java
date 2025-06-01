package com.sdas204221.AlumniManagementSystem.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "profiles")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String education;
    private String jobDetails;
    private String skills;
    private String achievements;
    @OneToOne
    private FileEntity profilePicture;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    
}
