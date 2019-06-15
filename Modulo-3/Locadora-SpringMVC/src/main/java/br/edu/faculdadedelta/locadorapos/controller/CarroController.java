package br.edu.faculdadedelta.locadorapos.controller;

import br.edu.faculdadedelta.locadorapos.model.Carro;
import br.edu.faculdadedelta.locadorapos.model.Modelo;
import br.edu.faculdadedelta.locadorapos.repository.CarroRepository;
import br.edu.faculdadedelta.locadorapos.repository.ModeloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/carros")
public class CarroController {

    private static final String CARRO_CADASTRO = "carro";
    private static final String CARRO_LISTA = "carroLista";

    @Autowired
    private CarroRepository repository;

    @Autowired
    private ModeloRepository modeloRepository;

    @RequestMapping("/novo")
    public ModelAndView novo() {
        ModelAndView modelAndView = new ModelAndView(CARRO_CADASTRO);
        modelAndView.addObject(new Carro());
        return modelAndView;
    }

    @PostMapping
    public ModelAndView salvar(@Valid Carro carro, Errors errors, RedirectAttributes redirectAttributes) {

        if (errors.hasErrors()) {
            return new ModelAndView(CARRO_CADASTRO);
        }

        if (carro.getId() == null) {
            repository.save(carro);
            redirectAttributes.addFlashAttribute("mensagem", "Inclusão realizada com sucesso!");
        } else {
            repository.save(carro);
            redirectAttributes.addFlashAttribute("mensagem", "Alteração realizada com sucesso!");
        }

        return new ModelAndView("redirect:/carros/novo");
    }

    @GetMapping
    public ModelAndView listar() {
        ModelAndView modelAndView = new ModelAndView(CARRO_LISTA);
        modelAndView.addObject("carros", repository.findAll());
        return modelAndView;
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView(CARRO_CADASTRO);

        modelAndView.addObject(repository
                .findById(id).orElseThrow(()
                        -> new EmptyResultDataAccessException(0)));

        return modelAndView;
    }

    @GetMapping("/excluir/{id}")
    public ModelAndView excluir(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("redirect:/carros");
        repository.deleteById(id);
        return modelAndView;
    }

    @ModelAttribute(name = "todosModelos")
    public List<Modelo> todosModelos(){
        return modeloRepository.findAll();
    }
}
