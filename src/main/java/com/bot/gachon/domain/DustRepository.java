package com.bot.gachon.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DustRepository extends JpaRepository<GachonDust, String> {
    @Query("SELECT p FROM GachonMask p ORDER BY p.code")
    List<GachonDust> findAll();
}
