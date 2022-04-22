package br.com.zup.edu.livraria;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.com.zup.edu.livraria.livro.Exemplar;
import br.com.zup.edu.livraria.livro.ExemplarRepository;
import br.com.zup.edu.livraria.livro.Livro;
import br.com.zup.edu.livraria.livro.LivroRepository;

@Component
public class DataLoader implements CommandLineRunner {

    private final LivroRepository livroRepository;
    private final ExemplarRepository exemplarRepository;

    public DataLoader(LivroRepository livroRepository, ExemplarRepository exemplarRepository) {
        this.livroRepository = livroRepository;
        this.exemplarRepository = exemplarRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (livroRepository.count() == 0) {
            load();
        }
    }

    private void load() {
        Livro livro = new Livro(
            "Harry Potter and the Philosopher's Stone",
            "Harry Potter and the Philosopher's Stone is a fantasy novel written by "
                    + "British author J. K. Rowling. The first novel in the Harry Potter series "
                    + "and Rowling's debut novel, it follows Harry Potter, a young wizard who "
                    + "discovers his magical heritage on his eleventh birthday, when he receives "
                    + "a letter of acceptance to Hogwarts School of Witchcraft and Wizardry. Harry "
                    + "makes close friends and a few enemies during his first year at the school, "
                    + "and with the help of his friends, he faces an attempted comeback by the "
                    + "dark wizard Lord Voldemort, who killed Harry's parents, but failed to kill "
                    + "Harry when he was just 15 months old.",
            "J. K. Rowling", "978-0-7475-3269-9"
        );

        livroRepository.save(livro);

        Exemplar exemplar1 = new Exemplar(livro);
        Exemplar exemplar2 = new Exemplar(livro);

        exemplarRepository.save(exemplar1);
        exemplarRepository.save(exemplar2);
    }

}
