package br.com.zup.edu.livraria.livro;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

import br.com.zup.edu.livraria.exceptions.ExemplarReservadoException;

@Entity
@Table(name = "exemplares")
@OptimisticLocking(type = OptimisticLockType.ALL)
@DynamicUpdate
public class Exemplar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Livro livro;

    @Column(nullable = false)
    private boolean reservado = false;

    /**
     * @deprecated Construtor de uso exclusivo do Hibernate
     */
    @Deprecated
    public Exemplar() {}

    public Exemplar(Livro livro) {
        this.livro = livro;
    }

    public void reservar() {
        if (isReservado()) {
            throw new ExemplarReservadoException("Este exemplar já está reservado.");
        }

        this.reservado = true;
    }

    public Long getId() {
        return id;
    }

    public boolean isReservado() {
        return reservado;
    }

}
