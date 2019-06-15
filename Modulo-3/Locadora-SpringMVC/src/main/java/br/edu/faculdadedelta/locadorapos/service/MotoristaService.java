package br.edu.faculdadedelta.locadorapos.service;

import br.edu.faculdadedelta.locadorapos.model.Motorista;
import br.edu.faculdadedelta.locadorapos.repository.MotoristaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MotoristaService {

    @Autowired
    private MotoristaRepository repository;

    public Motorista incluir(Motorista motorista) {
        motorista.setId(null);
        return repository.save(motorista);
    }

    public Motorista alterar(Motorista motorista) {
        pesquisarPorId(motorista.getId());
        return repository.save(motorista);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }

    public List<Motorista> listar() {
        return repository.findAll();
    }

    public Motorista pesquisarPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(0));
    }
}
