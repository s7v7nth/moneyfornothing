package com.seventh.moneyfornothing.repo;

import com.seventh.moneyfornothing.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Transaction findByName(String name);
}
