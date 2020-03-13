package com.bot.gachon.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GachonRepository extends JpaRepository<Gachon,String> {

}
