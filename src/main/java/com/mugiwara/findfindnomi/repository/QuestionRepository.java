package com.mugiwara.findfindnomi.repository;

import com.mugiwara.findfindnomi.dao.CharacterDAO;
import com.mugiwara.findfindnomi.dao.DevilFruitDAO;
import com.mugiwara.findfindnomi.dao.QuestionDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionDAO, Long> {

    @Query("SELECT A.question " +
            "FROM AnswerDAO A " +
            "WHERE A.character = :character " +
            "AND A.devilFruit = :devilFruit")
    List<QuestionDAO> getAllByDevilFruitAndCharacter(DevilFruitDAO devilFruit, CharacterDAO character);
}
