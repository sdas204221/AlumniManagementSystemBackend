package com.sdas204221.AlumniManagementSystem.model;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "forum_posts")
public class ForumPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;
    private LocalDateTime postedAt;

    @OneToMany
    @JoinTable(
            name = "forum_post_files",
            joinColumns = @JoinColumn(name = "forum_post_id"),
            inverseJoinColumns = @JoinColumn(name = "file_id")
    )
    @OrderColumn(name = "file_order") 
    private List<FileEntity> fileList; 

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User postedBy;

    
}

