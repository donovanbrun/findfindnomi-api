package com.mugiwara.findfindnomi.repository;

import com.mugiwara.findfindnomi.entity.DevilFruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DevilFruitRepository extends JpaRepository<DevilFruit, Long> {
}
