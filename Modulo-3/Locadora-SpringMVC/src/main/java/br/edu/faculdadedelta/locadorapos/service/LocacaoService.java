package br.edu.faculdadedelta.locadorapos.service;

import br.edu.faculdadedelta.locadorapos.model.Locacao;
import br.edu.faculdadedelta.locadorapos.repository.LocacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class LocacaoService {

    @Autowired
    private LocacaoRepository repository;

    public Locacao incluir(Locacao locacao) {
        locacao.setId(null);
        locacao.setValorTotal(calcularTotal(locacao));
        return repository.save(locacao);
    }

    public Locacao alterar(Locacao locacao) {
        pesquisarPorId(locacao.getId());
        locacao.setValorTotal(calcularTotal(locacao));
        return repository.save(locacao);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }

    public List<Locacao> listar() {
        return repository.findAll();
    }

    public Locacao pesquisarPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(0));
    }

    public BigDecimal calcularTotal(Locacao locacao){
        int dias = (int) ((locacao.getDataDevolucao().getTime() - locacao.getDataLocacao().getTime()) / 86400000);
        return locacao.getCarro().getValorDiaria().multiply(new BigDecimal(dias));
    }
}
