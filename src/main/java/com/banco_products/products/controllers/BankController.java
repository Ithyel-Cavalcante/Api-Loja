package com.banco_products.products.controllers;

import com.banco_products.products.models.Bank;
import com.banco_products.products.repositories.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")

public class BankController {

    @Autowired
    BankRepository bankRepository;

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Bank> getAllBanks() {
        return bankRepository.findAll();
    }

    @PostMapping(value = "/createProduct", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Bank createNewProduct(@RequestBody Bank Product){
        Bank newProduct = new Bank();

        newProduct.setId(Product.getId());
        newProduct.setName(Product.getName());
        newProduct.setPrice(Product.getPrice());
        newProduct.setQtd_products(Product.getQtd_products());
        newProduct.setDescription(Product.getDescription());
        newProduct.setImage(Product.getImage());

        return bankRepository.save(newProduct);
    }

    @PutMapping(value = "/updateProduct", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Bank updateProduct(@RequestBody Bank Product){

        try {
            Bank getProduct = bankRepository.findById(Product.getId()).orElseThrow();

            Bank updateProduct = new Bank();

            updateProduct.setId(Product.getId());
            updateProduct.setName(Product.getName());
            updateProduct.setPrice(Product.getPrice());
            updateProduct.setQtd_products(Product.getQtd_products());
            updateProduct.setDescription(Product.getDescription());
            updateProduct.setImage(Product.getImage());

            return bankRepository.save(updateProduct);

        }catch(Exception e){

            System.out.println(e.getMessage());

        }

        return null;
    }

    @DeleteMapping(value = "/deleteProduct/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Bank deleteProduct(@PathVariable long id){
        Bank getProduct = bankRepository.findById(id).orElseThrow();
        bankRepository.delete(getProduct);
        return getProduct;
    }

}


