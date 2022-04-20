package br.com.zup.edu.livraria;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.com.zup.edu.livraria.livro.ExemplarRepository;
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

    }

}
