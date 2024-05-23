package com.funnycode.react_springboot_account.repository;

import com.funnycode.react_springboot_account.entity.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface SizeRepository extends JpaRepository<Size, Integer> {

    public Optional<Size> findAllByNameIgnoreCase(String name);
}
