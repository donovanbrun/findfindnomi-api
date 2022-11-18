package com.mugiwara.findfindnomi.controller;

import com.mugiwara.findfindnomi.entity.Answer;
import com.mugiwara.findfindnomi.entity.DevilFruitFull;
import com.mugiwara.findfindnomi.entity.DevilFruitHidden;
import com.mugiwara.findfindnomi.entity.GuessResult;
import com.mugiwara.findfindnomi.entity.ingestion.IngestionModel;
import com.mugiwara.findfindnomi.service.DevilFruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
public class DevilFruitController {

    private DevilFruitService devilFruitService;

    @Autowired
    public DevilFruitController(DevilFruitService devilFruitService) {
        this.devilFruitService = devilFruitService;
    }

    @GetMapping("find/{id}")
    public DevilFruitFull get(@PathVariable Long id) {
        return devilFruitService.get(id);
    }

    @GetMapping("random")
    public DevilFruitHidden getRandom() {
        return devilFruitService.getRandom();
    }

    @GetMapping("question")
    public Answer getAnswer(@RequestParam("idDevilFruit") Long idDevilFruit, @RequestParam("idCharacter") Long idCharacter, @RequestParam("idQuestion") Long idQuestion) {
        return devilFruitService.getAnswer(idDevilFruit, idCharacter, idQuestion);
    }

    @GetMapping("guess")
    public GuessResult getAnswer(@RequestParam("idDevilFruit") Long idDevilFruit, @RequestParam("proposition") String proposition) {
        return devilFruitService.guess(idDevilFruit, proposition);
    }

    @PostMapping("load")
    public void load(@RequestBody IngestionModel ingestionModel) {
        devilFruitService.load(ingestionModel);
    }
}
