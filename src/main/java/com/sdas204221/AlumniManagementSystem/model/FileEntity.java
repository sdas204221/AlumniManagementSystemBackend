package com.sdas204221.AlumniManagementSystem.model;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "files")
public class FileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(nullable = false)
    private byte[] fileData; 

    private String fileName;
    private String fileType; 
    private LocalDateTime uploadedAt = LocalDateTime.now();

    
}