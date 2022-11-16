package com.mugiwara.findfindnomi.service;

import com.mugiwara.findfindnomi.dao.AnswerDAO;
import com.mugiwara.findfindnomi.dao.CharacterDAO;
import com.mugiwara.findfindnomi.dao.DevilFruitDAO;
import com.mugiwara.findfindnomi.dao.QuestionDAO;
import com.mugiwara.findfindnomi.entity.Character;
import com.mugiwara.findfindnomi.entity.DevilFruitFull;
import com.mugiwara.findfindnomi.entity.DevilFruitHidden;
import com.mugiwara.findfindnomi.entity.Question;
import com.mugiwara.findfindnomi.repository.AnswerRepository;
import com.mugiwara.findfindnomi.repository.CharacterRepository;
import com.mugiwara.findfindnomi.repository.DevilFruitRepository;
import com.mugiwara.findfindnomi.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DevilFruitService {

    private AnswerRepository answerRepository;
    private DevilFruitRepository devilFruitRepository;
    private QuestionRepository questionRepository;
    private CharacterRepository characterRepository;

    @Autowired
    public DevilFruitService(AnswerRepository answerRepository, DevilFruitRepository devilFruitRepository, QuestionRepository questionRepository, CharacterRepository characterRepository) {
        this.answerRepository = answerRepository;
        this.devilFruitRepository = devilFruitRepository;
        this.questionRepository = questionRepository;
        this.characterRepository = characterRepository;
    }

    public DevilFruitFull get(Long id) {

        DevilFruitDAO d = this.devilFruitRepository.findById(id).get();

        ArrayList<Character> characters = new ArrayList<>();

        for (CharacterDAO c : this.characterRepository.getAllByDevilFruitId(d)) {
            List<QuestionDAO> questionDAOList = questionRepository.getAllByDevilFruitAndCharacter(d,c);
            ArrayList<Question> questions = new ArrayList<>();
            for (QuestionDAO q : questionDAOList) {
                AnswerDAO a = this.answerRepository.findByDevilFruitAndCharacterAndQuestion(d,c,q);
                if (a != null) questions.add(new Question(q,a));
            }
            characters.add(new Character(c,questions));
        }

        return new DevilFruitFull(d, characters);
    }

    public DevilFruitHidden getRandom() {
        int nb = (int) this.devilFruitRepository.count();
        int rnd = (int) (Math.random() * nb);
        if (nb > 0) {
            DevilFruitDAO devilFruit = this.devilFruitRepository.findAll().get(rnd);
            List<CharacterDAO> characters = this.characterRepository.getAllByDevilFruitId(devilFruit);
            if (!characters.isEmpty()) {
                rnd = (int) (Math.random() * (characters.size()));
                return new DevilFruitHidden(devilFruit, characters.get(rnd));
            }
            return new DevilFruitHidden(devilFruit, null);

        }
        return null;
    }
}
