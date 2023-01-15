package com.mugiwara.findfindnomi.dto.ingestion;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class IngestionModel {

    private String[] questions;
    private List<DevilFruitIngestion> devilFruits;
}
