package br.edu.faculdadedelta.locadorapos.service;

import br.edu.faculdadedelta.locadorapos.model.Fabricante;
import br.edu.faculdadedelta.locadorapos.repository.FabricanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FabricanteService {

    @Autowired
    private FabricanteRepository repository;

    public Fabricante incluir(Fabricante fabricante){
        fabricante.setId(null);
        return repository.save(fabricante);
    }

    public Fabricante alterar(Fabricante fabricante){
        pesquisarPorId(fabricante.getId());
        return repository.save(fabricante);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }

    public List<Fabricante> listar(){
        return repository.findAll();
    }

    public Fabricante pesquisarPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(0));
    }
}
