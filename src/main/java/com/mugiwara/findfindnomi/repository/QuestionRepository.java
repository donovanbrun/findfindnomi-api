package com.mugiwara.findfindnomi.repository;

import com.mugiwara.findfindnomi.entity.Character;
import com.mugiwara.findfindnomi.entity.DevilFruit;
import com.mugiwara.findfindnomi.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Query("SELECT A.question " +
            "FROM Answer A " +
            "WHERE A.character = :character " +
            "AND A.devilFruit = :devilFruit")
    List<Question> getAllByDevilFruitAndCharacter(DevilFruit devilFruit, Character character);
}
