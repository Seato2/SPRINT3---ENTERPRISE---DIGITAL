package service;

import java.io.FileReader;
import java.io.IOException;
import org.springframework.stereotype.Service;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import model.Produto;
import org.springframework.util.ResourceUtils;
import repository.ProdutoRepository;

@Service
public class ImportacaoCSVService {

    private final ProdutoRepository produtoRepository;

    public ImportacaoCSVService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public void importarCSV() {
        String caminhoArquivo = "classpath:notebook.csv"; // Defina o caminho do arquivo aqui
        try (CSVReader reader = new CSVReader(new FileReader(ResourceUtils.getFile(caminhoArquivo)))) {
            String[] linha;
            while ((linha = reader.readNext()) != null) {
                try {
                    String nome = linha[0];
                    double preco = Double.parseDouble(linha[1]);

                    Produto produto = new Produto(nome, preco);

                    produtoRepository.save(produto);
                } catch (NumberFormatException e) {
                    System.err.println("Erro ao converter preço para double: " + e.getMessage());
                }
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }
}
