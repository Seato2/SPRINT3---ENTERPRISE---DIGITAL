package service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import model.Produto;
import repository.ProdutoRepository;

@Service
public class CalculoMediaService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public double calcularMediaPrecos() {
        List<Produto> produtos = produtoRepository.findAll();

        if (produtos.isEmpty()) {
            return 0.0; // 0 se nao tiver produtos
        }

        double somaPrecos = 0.0;
        for (Produto produto : produtos) {
            somaPrecos += produto.getPrice();
        }

        return somaPrecos / produtos.size(); // calculo média
    }
}
