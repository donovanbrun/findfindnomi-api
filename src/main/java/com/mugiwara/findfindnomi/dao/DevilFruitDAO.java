package com.mugiwara.findfindnomi.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DevilFruit")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DevilFruitDAO {

    @Id
    private Long id;
    private String name;
    private Type type;
    private String image;
}
