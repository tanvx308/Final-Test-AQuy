package com.fis.java.testfinal.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;
@NamedQuery(
        name = "searchByKeyword",
        query = "SELECT c FROM Customer c WHERE c.status =: key or c.customerType = :key or c.name like :word or c.address like :word or c.mobile like :word or c.identityNo like :word"
)
@Entity
@Table(name = "customer")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer implements Serializable {
    public enum CustomerType {
        INDIVIDUAL, CORPORATE
    }
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
    @Enumerated(EnumType.STRING)
    private CustomerType customerType;
    @Column(nullable = false)
    private Integer status;
    @Column(nullable = false)
    private LocalDateTime createDateTime;
    @Column(nullable = false)
    private LocalDateTime updateDateTime;
    @JsonIgnore
    @OneToMany(mappedBy = "customer")
    private Set<Account> accounts;

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", customerType=" + customerType +
                '}';
    }
}
