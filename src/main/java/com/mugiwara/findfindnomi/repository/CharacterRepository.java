package com.mugiwara.findfindnomi.repository;

import com.mugiwara.findfindnomi.entity.Character;
import com.mugiwara.findfindnomi.entity.DevilFruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Long> {

    @Query("SELECT C " +
            "FROM Character C, Belong B " +
            "WHERE C = B.character " +
            "AND B.devilFruit = :devilFruit ")
    List<Character> getAllByDevilFruitId(@Param("devilFruit") DevilFruit devilFruit);
}
