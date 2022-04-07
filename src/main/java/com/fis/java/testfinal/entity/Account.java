package com.fis.java.testfinal.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;
@Entity
@Table(name = "account")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, unique = true, length = 13)
    private String accountNumber;
    @Column(nullable = false)
    private Double balance;
    @Column(nullable = false)
    private Integer status;
    @Column(nullable = false)
    private LocalDateTime createDateTime;
    @Column(nullable = false)
    private LocalDateTime updateDateTime;
    @JsonIgnore
    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @JsonIgnore
    @OneToMany(mappedBy = "fromAccount")
    private Set<Transaction> transactionsFrom;
    @JsonIgnore
    @OneToMany(mappedBy = "toAccount")
    private Set<Transaction> transactionsTo;

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber='" + accountNumber + '\'' +
                '}';
    }
}
