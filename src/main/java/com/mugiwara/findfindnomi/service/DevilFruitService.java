package com.mugiwara.findfindnomi.service;

import com.mugiwara.findfindnomi.entity.*;
import com.mugiwara.findfindnomi.entity.Answer;
import com.mugiwara.findfindnomi.entity.Character;
import com.mugiwara.findfindnomi.entity.Question;
import com.mugiwara.findfindnomi.dto.*;
import com.mugiwara.findfindnomi.dto.ingestion.AnswerIngestion;
import com.mugiwara.findfindnomi.dto.ingestion.CharacterIngestion;
import com.mugiwara.findfindnomi.dto.ingestion.DevilFruitIngestion;
import com.mugiwara.findfindnomi.dto.ingestion.IngestionModel;
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

    public DevilFruitFullDTO get(Long id) {

        DevilFruit d = this.devilFruitRepository.findById(id).get();

        ArrayList<CharacterDTO> characters = new ArrayList<>();

        for (Character c : this.characterRepository.getAllByDevilFruitId(d)) {
            List<Question> questionDAOList = questionRepository.getAllByDevilFruitAndCharacter(d,c);
            ArrayList<QuestionDTO> questions = new ArrayList<>();
            for (Question q : questionDAOList) {
                Answer a = this.answerRepository.findByDevilFruitAndCharacterAndQuestion(d,c,q);
                if (a != null) questions.add(new QuestionDTO(q,a));
            }
            characters.add(new CharacterDTO(c,questions));
        }

        return new DevilFruitFullDTO(d, characters);
    }

    public DevilFruitHiddenDTO getRandom() {
        int nb = (int) this.devilFruitRepository.count();
        int rnd = (int) (Math.random() * nb);
        if (nb > 0) {
            DevilFruit devilFruit = this.devilFruitRepository.findAll().get(rnd);
            List<Character> characters = this.characterRepository.getAllByDevilFruitId(devilFruit);
            if (!characters.isEmpty()) {
                rnd = (int) (Math.random() * (characters.size()));
                return new DevilFruitHiddenDTO(devilFruit, characters.get(rnd));
            }
            return new DevilFruitHiddenDTO(devilFruit, null);

        }
        return null;
    }

    public void load(IngestionModel ingestionModel) {

        // Transform ingestionModel to DAO
        // ADD SOME VERIFICATIONS
        String[] questionsIngestion = ingestionModel.getQuestions();
        Question[] questions = new Question[questionsIngestion.length];

        for (int i = 0; i < questionsIngestion.length; i++) {
            questions[i] = new Question(generateRandom(), questionsIngestion[i]);
        }

        List<DevilFruitIngestion> devilFruitIngestionList = ingestionModel.getDevilFruits();

        ArrayList<DevilFruit> devilFruits = new ArrayList<>(devilFruitIngestionList.size());
        ArrayList<Character> characters = new ArrayList<>();
        ArrayList<Belong> belongs = new ArrayList<>();
        ArrayList<Answer> answers = new ArrayList<>();

        for (DevilFruitIngestion devilFruitIngestion : devilFruitIngestionList) {
            DevilFruit devilFruitDAO = new DevilFruit(
                    generateRandom(),
                    devilFruitIngestion.getName(),
                    Type.valueOf(devilFruitIngestion.getType()),
                    devilFruitIngestion.getImage()
            );

            devilFruits.add(devilFruitDAO);

            List<CharacterIngestion> characterIngestionList = devilFruitIngestion.getCharacters();

            for (CharacterIngestion characterIngestion : characterIngestionList) {
                Optional<Character> optCharacterDAO = characters.stream()
                        .filter(c -> (c.getName().equals(characterIngestion.getName())))
                        .findFirst();

                Character characterDAO = null;

                if (optCharacterDAO.isPresent()) {
                    characterDAO = optCharacterDAO.get();
                    Belong belongDAO = new Belong(generateRandom(), devilFruitDAO, characterDAO);
                }
                else {
                    characterDAO = new Character(
                            generateRandom(),
                            characterIngestion.getName(),
                            characterIngestion.getImage());
                }

                Belong belongDAO = new Belong(generateRandom(), devilFruitDAO, characterDAO);

                characters.add(characterDAO);
                belongs.add(belongDAO);

                for (int i = 0; i < characterIngestion.getAnswers().length; i++) {
                    AnswerIngestion answerIngestion = characterIngestion.getAnswers()[i];
                    Answer answerDAO = new Answer(generateRandom(),
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

    public GuessResultDTO guess(Long idDevilFruit, String proposition) {
        List<Character> characterDAOList = characterRepository.getAllByDevilFruitId(devilFruitRepository.getReferenceById(idDevilFruit));

        GuessResultDTO result = new GuessResultDTO(idDevilFruit, proposition, false, null);

        for (Character c : characterDAOList) {
            if (c.getName().trim().equalsIgnoreCase(proposition.trim())){
                result.setResult(true);
                result.setCharacter(c);
                return result;
            }
        }
        return result;
    }

    public AnswerDTO getAnswer(Long idDevilFruit, Long idCharacter, Long idQuestion) {

        Answer answerDAO = answerRepository.findByDevilFruitAndCharacterAndQuestion(
                devilFruitRepository.getReferenceById(idDevilFruit),
                characterRepository.getReferenceById(idCharacter),
                questionRepository.getReferenceById(idQuestion)
        );

        return new AnswerDTO(answerDAO.getDevilFruit().getId(),
                answerDAO.getCharacter().getId(),
                answerDAO.getQuestion().getId(),
                answerDAO.getQuestion().getQuestion(),
                answerDAO.getLevel(),
                answerDAO.getAnswer()
        );
    }
}
