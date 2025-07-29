package com.example.corporatesolution.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Table(name = "product_audit")
@Entity
public class ProductAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String product;
    private String operationType;
    private String modifiedBy;
    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;
}
