package com.springboot.sqlite.Entities;

import jdk.jfr.DataAmount;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="customer")
public class Customer {
    @Id
    private Integer id;
    @Column(name="name")
    private String name;
    @Column(name="phone")
    private String phone;

    @Transient
    private String country;
    @Transient
    private Boolean state;
    @Transient
    private String countryCode;

}