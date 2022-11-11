package com.mugiwara.findfindnomi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "DevilFruit")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DevilFruit {

    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private UUID userId;
    private Type type;
}
