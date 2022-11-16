package com.mugiwara.findfindnomi.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Answer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnswerDAO {

    @Id
    private Long id;
    @OneToOne
    private DevilFruitDAO devilFruit;
    @OneToOne
    private CharacterDAO character;
    @OneToOne
    private QuestionDAO question;
    private String answer;
    private int level;
}
