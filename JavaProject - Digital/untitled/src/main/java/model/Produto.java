package model;
import jakarta.persistence.*;



@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private double price;

    public Produto() {}

    public Produto(String title, double price) {
        this.title = title;
        this.price = price;
    }

    // Getters e setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return title;
    }

    public void setNome(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPreco(double preco) {
        this.price = price;
    }
}
