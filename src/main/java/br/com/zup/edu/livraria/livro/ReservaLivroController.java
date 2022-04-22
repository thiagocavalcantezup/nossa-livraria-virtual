package br.com.zup.edu.livraria.livro;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
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
    public ResponseEntity<?> reservar(@PathVariable String isbn) {
        Exemplar exemplar = exemplarRepository.findFirstByReservadoIsFalseAndLivro_Isbn(isbn)
                                              .orElseThrow(
                                                  () -> new ResponseStatusException(
                                                      HttpStatus.NOT_FOUND,
                                                      "Não existe um exemplar disponível para o ISBN fornecido."
                                                  )
                                              );

        exemplar.reservar();

        return ResponseEntity.noContent().build();
    }

}
