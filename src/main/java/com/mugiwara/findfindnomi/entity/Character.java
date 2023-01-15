package com.mugiwara.findfindnomi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Character")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Character {

    @Id
    private Long id;
    private String name;
    private String image;
}
