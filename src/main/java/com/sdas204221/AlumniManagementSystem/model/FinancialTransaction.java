package com.sdas204221.AlumniManagementSystem.model;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "financial_transactions")
public class FinancialTransaction {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String transactionType;
    private Double amount;
    private LocalDateTime transactionDate;
    @OneToMany
    private List<FileEntity> files;
    private String remarks;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    
}
