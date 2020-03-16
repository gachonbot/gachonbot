package com.bot.gachon.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GachonRepository extends JpaRepository<Gachon,String> {

    @Query("SELECT p FROM Gachon p ORDER BY p.code")
    List<Gachon> findAll();

}
