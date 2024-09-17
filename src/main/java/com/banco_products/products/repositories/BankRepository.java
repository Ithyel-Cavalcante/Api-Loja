package com.banco_products.products.repositories;

import com.banco_products.products.models.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankRepository extends JpaRepository<Bank, Long> {

}
