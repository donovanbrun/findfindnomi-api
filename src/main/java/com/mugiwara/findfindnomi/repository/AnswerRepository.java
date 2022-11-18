package com.mugiwara.findfindnomi.repository;

import com.mugiwara.findfindnomi.dao.AnswerDAO;
import com.mugiwara.findfindnomi.dao.CharacterDAO;
import com.mugiwara.findfindnomi.dao.DevilFruitDAO;
import com.mugiwara.findfindnomi.dao.QuestionDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends JpaRepository<AnswerDAO, Long> {

    AnswerDAO findByDevilFruitAndCharacterAndQuestion(DevilFruitDAO devilFruit, CharacterDAO characterDAO, QuestionDAO question);
}
