package com.mugiwara.findfindnomi.repository;

import com.mugiwara.findfindnomi.dao.BelongDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BelongRepository extends JpaRepository<BelongDAO, Long> {
}
