package com.mugiwara.findfindnomi.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "Belong")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BelongDAO {

    @Id
    private Long id;
    @OneToOne
    private DevilFruitDAO devilFruit;
    @OneToOne
    private CharacterDAO character;
}
