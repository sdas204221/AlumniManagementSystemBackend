package com.sdas204221.AlumniManagementSystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Listing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String description;

    private LocalDateTime creationDate;
    private LocalDateTime approvalDate;

    private boolean approved;

    @ElementCollection // ✅ Fix for storing List<String> in DB
    private List<String> types;

    private LocalDateTime eventDate;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<FileEntity> files;

    @Override
    public String toString() {
        return "Listing{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", creationDate=" + creationDate +
                ", approvalDate=" + approvalDate +
                ", approved=" + approved +
                ", types=" + types +
                ", eventDate=" + eventDate +
                ", files=" + files +
                '}';
    }
}