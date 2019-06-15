package br.edu.faculdadedelta.locadorapos.repository;

import br.edu.faculdadedelta.locadorapos.model.Carro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarroRepository extends JpaRepository<Carro, Long> {
}
