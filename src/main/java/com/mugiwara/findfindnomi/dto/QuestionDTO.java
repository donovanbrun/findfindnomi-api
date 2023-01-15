package com.mugiwara.findfindnomi.dto;

import com.mugiwara.findfindnomi.entity.Answer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class QuestionDTO {

    private Long id;
    private String question;
    private int level;
    private String answer;

    public QuestionDTO(com.mugiwara.findfindnomi.entity.Question questionDAO, Answer answerDAO) {
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
