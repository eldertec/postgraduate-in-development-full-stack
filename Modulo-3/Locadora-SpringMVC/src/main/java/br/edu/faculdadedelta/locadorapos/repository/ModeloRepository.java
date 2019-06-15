package br.edu.faculdadedelta.locadorapos.repository;

import br.edu.faculdadedelta.locadorapos.model.Modelo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModeloRepository extends JpaRepository<Modelo, Long> {
}
