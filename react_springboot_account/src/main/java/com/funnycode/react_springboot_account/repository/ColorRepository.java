package com.funnycode.react_springboot_account.repository;

import com.funnycode.react_springboot_account.entity.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface ColorRepository extends JpaRepository<Color,Integer> {

    public Optional<Color> findAllByNameIgnoreCase(String name);
}
