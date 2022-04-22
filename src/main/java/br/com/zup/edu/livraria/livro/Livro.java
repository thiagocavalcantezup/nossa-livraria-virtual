package br.com.zup.edu.livraria.livro;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.validator.constraints.ISBN;
import org.hibernate.validator.constraints.ISBN.Type;

@Entity
@Table(name = "livros")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, columnDefinition = "text")
    @Lob
    private String resumo;

    @Column(nullable = false)
    private String autor;

    @Column(nullable = false)
    @ISBN(type = Type.ANY)
    private String isbn;

    /**
     * @deprecated Construtor de uso exclusivo do Hibernate
     */
    @Deprecated
    public Livro() {}

    public Livro(String nome, String resumo, String autor, String isbn) {
        this.nome = nome;
        this.resumo = resumo;
        this.autor = autor;
        this.isbn = isbn;
    }

    public Long getId() {
        return id;
    }

}
