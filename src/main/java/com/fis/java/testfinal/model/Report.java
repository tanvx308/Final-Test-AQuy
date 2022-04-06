package com.fis.java.testfinal.model;

import com.fis.java.testfinal.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Report implements Serializable {
    @Id
    private LocalDateTime dateTime;
    private Customer.CustomerType customerType;
    private Integer status;
    private Double total;
    private Long count;
}
