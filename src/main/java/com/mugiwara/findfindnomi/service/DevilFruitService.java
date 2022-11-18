package com.mugiwara.findfindnomi.service;

import com.mugiwara.findfindnomi.dao.*;
import com.mugiwara.findfindnomi.entity.*;
import com.mugiwara.findfindnomi.entity.Character;
import com.mugiwara.findfindnomi.entity.ingestion.AnswerIngestion;
import com.mugiwara.findfindnomi.entity.ingestion.CharacterIngestion;
import com.mugiwara.findfindnomi.entity.ingestion.DevilFruitIngestion;
import com.mugiwara.findfindnomi.entity.ingestion.IngestionModel;
import com.mugiwara.findfindnomi.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class DevilFruitService {

    private AnswerRepository answerRepository;
    private DevilFruitRepository devilFruitRepository;
    private QuestionRepository questionRepository;
    private CharacterRepository characterRepository;
    private BelongRepository belongRepository;

    @Autowired
    public DevilFruitService(AnswerRepository answerRepository, DevilFruitRepository devilFruitRepository, QuestionRepository questionRepository, CharacterRepository characterRepository, BelongRepository belongRepository) {
        this.answerRepository = answerRepository;
        this.devilFruitRepository = devilFruitRepository;
        this.questionRepository = questionRepository;
        this.characterRepository = characterRepository;
        this.belongRepository = belongRepository;
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

    public void load(IngestionModel ingestionModel) {

        // Transform ingestionModel to DAO
        // ADD SOME VERIFICATIONS
        String[] questionsIngestion = ingestionModel.getQuestions();
        QuestionDAO[] questions = new QuestionDAO[questionsIngestion.length];

        for (int i = 0; i < questionsIngestion.length; i++) {
            questions[i] = new QuestionDAO(generateRandom(), questionsIngestion[i]);
        }

        List<DevilFruitIngestion> devilFruitIngestionList = ingestionModel.getDevilFruits();

        ArrayList<DevilFruitDAO> devilFruits = new ArrayList<>(devilFruitIngestionList.size());
        ArrayList<CharacterDAO> characters = new ArrayList<>();
        ArrayList<BelongDAO> belongs = new ArrayList<>();
        ArrayList<AnswerDAO> answers = new ArrayList<>();

        for (DevilFruitIngestion devilFruitIngestion : devilFruitIngestionList) {
            DevilFruitDAO devilFruitDAO = new DevilFruitDAO(
                    generateRandom(),
                    devilFruitIngestion.getName(),
                    Type.valueOf(devilFruitIngestion.getType()),
                    devilFruitIngestion.getImage()
            );

            devilFruits.add(devilFruitDAO);

            List<CharacterIngestion> characterIngestionList = devilFruitIngestion.getCharacters();

            for (CharacterIngestion characterIngestion : characterIngestionList) {
                Optional<CharacterDAO> optCharacterDAO = characters.stream()
                        .filter(c -> (c.getName().equals(characterIngestion.getName())))
                        .findFirst();

                CharacterDAO characterDAO = null;

                if (optCharacterDAO.isPresent()) {
                    characterDAO = optCharacterDAO.get();
                    BelongDAO belongDAO = new BelongDAO(generateRandom(), devilFruitDAO, characterDAO);
                }
                else {
                    characterDAO = new CharacterDAO(
                            generateRandom(),
                            characterIngestion.getName(),
                            characterIngestion.getImage());
                }

                BelongDAO belongDAO = new BelongDAO(generateRandom(), devilFruitDAO, characterDAO);

                characters.add(characterDAO);
                belongs.add(belongDAO);

                for (int i = 0; i < characterIngestion.getAnswers().length; i++) {
                    AnswerIngestion answerIngestion = characterIngestion.getAnswers()[i];
                    AnswerDAO answerDAO = new AnswerDAO(generateRandom(),
                            devilFruitDAO,
                            characterDAO,
                            questions[i],
                            answerIngestion.getAnswer(),
                            answerIngestion.getLevel()
                    );

                    answers.add(answerDAO);
                }
            }
        }

        // Clear data from db if everything is good
        belongRepository.deleteAll();
        answerRepository.deleteAll();
        questionRepository.deleteAll();
        devilFruitRepository.deleteAll();
        characterRepository.deleteAll();

        // Load data to db
        devilFruitRepository.saveAll(devilFruits);
        characterRepository.saveAll(characters);
        belongRepository.saveAll(belongs);
        questionRepository.saveAll(Arrays.stream(questions).toList());
        answerRepository.saveAll(answers);
    }

    public Long generateRandom() {
        return (long) (Math.random() * Long.MAX_VALUE);
    }

    public GuessResult guess(Long idDevilFruit, String proposition) {
        List<CharacterDAO> characterDAOList = characterRepository.getAllByDevilFruitId(devilFruitRepository.getReferenceById(idDevilFruit));

        GuessResult result = new GuessResult(idDevilFruit, proposition, false, null);

        for (CharacterDAO c : characterDAOList) {
            if (c.getName().trim().equalsIgnoreCase(proposition.trim())){
                result.setResult(true);
                result.setCharacter(c);
                return result;
            }
        }
        return result;
    }

    public Answer getAnswer(Long idDevilFruit, Long idCharacter, Long idQuestion) {

        AnswerDAO answerDAO = answerRepository.findByDevilFruitAndCharacterAndQuestion(
                devilFruitRepository.getReferenceById(idDevilFruit),
                characterRepository.getReferenceById(idCharacter),
                questionRepository.getReferenceById(idQuestion)
        );

        return new Answer(answerDAO.getDevilFruit().getId(),
                answerDAO.getCharacter().getId(),
                answerDAO.getQuestion().getId(),
                answerDAO.getQuestion().getQuestion(),
                answerDAO.getLevel(),
                answerDAO.getAnswer()
        );
    }
}
