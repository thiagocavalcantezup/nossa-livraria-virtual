package br.com.zup.edu.livraria.livro;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExemplarRepository extends JpaRepository<Exemplar, Long> {

    Optional<Exemplar> findFirstByReservadoisFalseANDLivro_ISBNequals(String isbn);

}
