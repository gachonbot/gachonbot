package com.bot.gachon.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GachonMaskRepository extends JpaRepository<GachonMask, String> {

    @Query("SELECT p FROM GachonMask p ORDER BY p.remainStat desc")
    List<GachonMask> findAll();

    Optional<List<GachonMask>> findAllByRemainStat(String remainStat);

}
