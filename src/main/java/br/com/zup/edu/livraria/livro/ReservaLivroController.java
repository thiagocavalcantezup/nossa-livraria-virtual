package br.com.zup.edu.livraria.livro;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import javax.transaction.Transactional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/livros/{isbn}")
public class ReservaLivroController {

    private final ExemplarRepository exemplarRepository;

    public ReservaLivroController(ExemplarRepository exemplarRepository) {
        this.exemplarRepository = exemplarRepository;
    }

    @PatchMapping
    @Transactional
    public ResponseEntity<?> reservar(@PathVariable String ISBN) {
        Exemplar exemplar = exemplarRepository.findFirstByReservadoisFalseANDLivro_ISBNequals(ISBN)
                                              .orElseThrow(
                                                  () -> new ResponseStatusException(
                                                      NOT_FOUND,
                                                      "Não existe exemplar cadastrado para este ISBN"
                                                  )
                                              );

        exemplar.reservar();

        exemplarRepository.save(exemplar);

        return ResponseEntity.noContent().build();
    }

}
