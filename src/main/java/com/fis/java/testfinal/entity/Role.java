package com.fis.java.testfinal.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@Table(name = "role")
public class Role {
    @Id
    private String id;
    private String name;
}
