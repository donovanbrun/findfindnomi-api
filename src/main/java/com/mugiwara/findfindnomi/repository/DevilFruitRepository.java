package com.mugiwara.findfindnomi.repository;

import com.mugiwara.findfindnomi.dao.DevilFruitDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DevilFruitRepository extends JpaRepository<DevilFruitDAO, Long> {
}
