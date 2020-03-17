package com.bot.gachon.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface GachonYesterdayRepository extends JpaRepository<GachonYesterdayMask,String> {

    @Query("SELECT p FROM GachonYesterdayMask p ORDER BY p.code")
    List<GachonYesterdayMask> findAll();
}
