package dw.randomizer.controller;

import dw.randomizer.model.Danger;
import dw.randomizer.service.DangerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/danger")
public class DangerController {
    @Autowired
    private DangerService dangerService;

    @PostMapping
    public Danger create() {
        Danger danger = new Danger();
        dangerService.rollDanger(danger);
        return danger;
    }
}
