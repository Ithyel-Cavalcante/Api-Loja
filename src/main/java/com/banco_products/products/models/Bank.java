package com.banco_products.products.models;

import com.banco_products.products.repositories.BankRepository;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "products")
@EqualsAndHashCode(of = "id")

public class Bank implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private int qtd_products;

    @Column(nullable = false)
    private String description;

    @Lob
    @Column(nullable = false)
    private byte[] image;


    public Bank() {}
}

