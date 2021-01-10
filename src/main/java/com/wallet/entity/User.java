package com.wallet.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class User implements Serializable {

    private static final long serialVersionUID = -2580568643119155156L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long Id;

    @Column(nullable = false)
    private String Name;

    @Column(nullable = false)
    private String Email;

    @Column(nullable = false)
    private String password;
}
