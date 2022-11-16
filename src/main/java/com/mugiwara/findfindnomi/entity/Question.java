package com.mugiwara.findfindnomi.entity;

import com.mugiwara.findfindnomi.dao.AnswerDAO;
import com.mugiwara.findfindnomi.dao.QuestionDAO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Question {

    private Long id;
    private String question;
    private int level;
    private String answer;

    public Question(QuestionDAO questionDAO, AnswerDAO answerDAO) {
        if (questionDAO != null) {
            this.id = questionDAO.getId();
            this.question = questionDAO.getQuestion();
        }
        if (answerDAO != null) {
            this.level = answerDAO.getLevel();
            this.answer = answerDAO.getAnswer();
        }
    }
}
