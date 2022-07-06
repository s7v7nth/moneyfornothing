package com.seventh.moneyfornothing.controller;

import com.seventh.moneyfornothing.model.Wallet;
import com.seventh.moneyfornothing.repo.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;

@RestController
public class WalletController {
    private final WalletRepository walletRepository;

    @Autowired
    public WalletController(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @GetMapping("/wallets")
    Collection<Wallet> getWallets() {
        return walletRepository.findAll();
    }

    @GetMapping("/wallets/{id}")
    ResponseEntity<Wallet> getWallet(@PathVariable Long id) {
        Optional<Wallet> wallet = walletRepository.findById(id);
        return wallet.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/wallets")
    ResponseEntity<Wallet> createWallet(@Valid @RequestBody Wallet wallet) throws URISyntaxException {
        Wallet result = walletRepository.save(wallet);
        return ResponseEntity.created(new URI("/wallets/" + result.getId())).body(result);
    }

    @PutMapping("/wallets/{id}")
    ResponseEntity<Wallet> updateWallet(@Valid @RequestBody Wallet wallet, @PathVariable Long id) {
        Wallet result = walletRepository.save(wallet);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/wallets/{id}")
    ResponseEntity<Wallet> deleteWallet(@PathVariable Long id) {
        walletRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
