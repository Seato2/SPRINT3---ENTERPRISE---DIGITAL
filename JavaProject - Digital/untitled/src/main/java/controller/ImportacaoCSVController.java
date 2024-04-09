package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import service.ImportacaoCSVService;

@Controller
public class ImportacaoCSVController {

    @Autowired
    private ImportacaoCSVService importacaoCSVService;

    @PostMapping("/importar-csv")
    public String importarCSV() {
        importacaoCSVService.importarCSV();
        return "redirect:/media-precos";
    }
}
