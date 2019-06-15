package br.edu.faculdadedelta.locadorapos.service;

import br.edu.faculdadedelta.locadorapos.model.Modelo;
import br.edu.faculdadedelta.locadorapos.repository.ModeloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModeloService {

    @Autowired
    private ModeloRepository repository;

    public Modelo incluir(Modelo modelo){
        modelo.setId(null);
        return repository.save(modelo);
    }

    public Modelo alterar(Modelo modelo){
        pesquisarPorId(modelo.getId());
        return repository.save(modelo);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }

    public List<Modelo> listar(){
        return repository.findAll();
    }

    public Modelo pesquisarPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(0));
    }
}
