package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import service.CalculoMediaService;

@Controller
public class MediaPrecosController {

    @Autowired
    private CalculoMediaService calculoMediaService;

    @GetMapping("/media-precos")
    public String exibirMediaPrecos(Model model) {
        double media = calculoMediaService.calcularMediaPrecos();
        model.addAttribute("media", media);
        return "media_precos";
    }
}
