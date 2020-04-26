package com.bot.gachon.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface DustRepository extends JpaRepository<GachonDust, String> {
    @Query("SELECT p FROM GachonDust p ORDER BY p.dataTime")
    List<GachonDust> findAll();

    Optional<List<GachonDust>> findAllByPm10Grade1h(String pm10Grade1h);
}
/*
@Repository
public interface GachonMaskRepository extends JpaRepository<GachonMask, String> {

    @Query("SELECT p FROM GachonMask p ORDER BY p.code")
    List<GachonMask> findAll();

    Optional<List<GachonMask>> findAllByRemainStat(String remainStat);

}

 */