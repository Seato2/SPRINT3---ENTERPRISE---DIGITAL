package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import service.CalculoMediaService;

@RestController
public class CalculoMediaController {

    @Autowired
    private CalculoMediaService calculoMediaService;

    @GetMapping("/calcular-media")
    public double calcularMediaPrecos() {
        return calculoMediaService.calcularMediaPrecos();
    }
}
