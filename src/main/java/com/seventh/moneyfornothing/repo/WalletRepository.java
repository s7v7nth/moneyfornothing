package com.seventh.moneyfornothing.repo;

import com.seventh.moneyfornothing.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Long> {

    Wallet findByName(String name);

}
