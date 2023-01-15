package com.mugiwara.findfindnomi.repository;

import com.mugiwara.findfindnomi.entity.Answer;
import com.mugiwara.findfindnomi.entity.Character;
import com.mugiwara.findfindnomi.entity.DevilFruit;
import com.mugiwara.findfindnomi.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {

    Answer findByDevilFruitAndCharacterAndQuestion(DevilFruit devilFruit, Character characterDAO, Question question);
}
