package com.funnycode.react_springboot_account.repository;

import com.funnycode.react_springboot_account.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    public Optional<Role> findByNameIgnoreCase(String name);
}
