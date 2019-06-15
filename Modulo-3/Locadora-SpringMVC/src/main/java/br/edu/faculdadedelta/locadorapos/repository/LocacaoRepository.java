package br.edu.faculdadedelta.locadorapos.repository;

import br.edu.faculdadedelta.locadorapos.model.Locacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocacaoRepository extends JpaRepository<Locacao, Long> {
}
