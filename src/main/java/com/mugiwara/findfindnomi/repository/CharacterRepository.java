package com.mugiwara.findfindnomi.repository;

import com.mugiwara.findfindnomi.dao.CharacterDAO;
import com.mugiwara.findfindnomi.dao.DevilFruitDAO;
import com.mugiwara.findfindnomi.entity.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharacterRepository extends JpaRepository<CharacterDAO, Long> {

    @Query("SELECT C " +
            "FROM CharacterDAO C, BelongDAO B " +
            "WHERE C = B.character " +
            "AND B.devilFruit = :devilFruit ")
    List<CharacterDAO> getAllByDevilFruitId(@Param("devilFruit") DevilFruitDAO devilFruit);
}
