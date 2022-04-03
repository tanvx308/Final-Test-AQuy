package com.fis.java.testfinal.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "transaction")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private LocalDateTime transactionDate;
    @ManyToOne
    @JoinColumn(name = "from_account")
    private Account fromAccount;
    @ManyToOne
    @JoinColumn(name = "to_account")
    private Account toAccount;
    @Column(nullable = false)
    private Double amount;
    private Integer status;
    private String content;
    private String errorReason;
}
