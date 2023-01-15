package com.mugiwara.findfindnomi.entity;

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
public class Answer {

    @Id
    private Long id;
    @OneToOne
    private DevilFruit devilFruit;
    @OneToOne
    private Character character;
    @OneToOne
    private Question question;
    private String answer;
    private int level;
}
