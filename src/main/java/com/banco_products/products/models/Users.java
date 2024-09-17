package com.banco_products.products.models;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "Users")
@EqualsAndHashCode(of = "id_user")

public class Users implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_user;

    @Column(nullable = false)
    private String name_user;

    @Column(nullable = false)
    private String password_user;

    public Users() {}
}