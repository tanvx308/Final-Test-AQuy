package com.fis.java.testfinal.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fis.java.testfinal.constant.CustomerType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "customer")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, length = 100)
    private String name;
    @Column(nullable = false)
    private LocalDateTime birthday;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false, unique = true, length = 10)
    private String identityNo;
    @Column(nullable = true, length = 10)
    private String mobile;
    @Column(nullable = false)
    private String customerType;
    @Column(nullable = false)
    private Integer status;
    @Column(nullable = false)
    private LocalDateTime createDateTime;
    @Column(nullable = false)
    private LocalDateTime updateDateTime;
    @OneToMany(mappedBy = "customer")
    private Set<Account> accounts;
}
