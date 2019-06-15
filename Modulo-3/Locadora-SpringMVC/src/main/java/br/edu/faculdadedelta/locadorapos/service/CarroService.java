package br.edu.faculdadedelta.locadorapos.service;

import br.edu.faculdadedelta.locadorapos.model.Carro;
import br.edu.faculdadedelta.locadorapos.repository.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarroService {

    @Autowired
    private CarroRepository repository;

    public Carro incluir(Carro carro){
        carro.setId(null);
        return repository.save(carro);
    }

    public Carro alterar(Carro carro){
        pesquisarPorId(carro.getId());
        return repository.save(carro);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }

    public List<Carro> listar(){
        return repository.findAll();
    }

    public Carro pesquisarPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(0));
    }
}
