package com.mercato.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable=false, unique=true)
    private String email;

    @Column(nullable=false)
    private String passowrd;

    @Column(nullable=false)
    private String fullName;

    @Column(nullable=false)
    private String role =  "USER";
}
