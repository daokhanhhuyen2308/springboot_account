package com.funnycode.react_springboot_account.repository;

import com.funnycode.react_springboot_account.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account,Integer> {

    Optional<Account> findByEmail(String email);
    Optional<Account> findByUsername(String username);
}
