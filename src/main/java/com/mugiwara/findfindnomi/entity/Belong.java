package com.mugiwara.findfindnomi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Belong")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Belong {

    @Id
    private Long id;
    @OneToOne
    private DevilFruit devilFruit;
    @OneToOne
    private Character character;
}
