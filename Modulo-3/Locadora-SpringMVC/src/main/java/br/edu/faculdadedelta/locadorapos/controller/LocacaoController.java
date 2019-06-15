package br.edu.faculdadedelta.locadorapos.controller;

import br.edu.faculdadedelta.locadorapos.model.Carro;
import br.edu.faculdadedelta.locadorapos.model.Locacao;
import br.edu.faculdadedelta.locadorapos.model.Motorista;
import br.edu.faculdadedelta.locadorapos.repository.CarroRepository;
import br.edu.faculdadedelta.locadorapos.repository.LocacaoRepository;
import br.edu.faculdadedelta.locadorapos.repository.MotoristaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@RequestMapping("/locacoes")
public class LocacaoController {

    private static final String LOCACAO_CADASTRO = "locacao";
    private static final String LOCACAO_LISTA = "locacaoLista";

    @Autowired
    private LocacaoRepository repository;

    @Autowired
    private CarroRepository carroRepository;

    @Autowired
    private MotoristaRepository motoristaRepository;

    @RequestMapping("/novo")
    public ModelAndView novo() {
        ModelAndView modelAndView = new ModelAndView(LOCACAO_CADASTRO);
        modelAndView.addObject(new Locacao());
        return modelAndView;
    }

    @PostMapping
    public ModelAndView salvar(@Valid Locacao locacao, Errors errors, RedirectAttributes redirectAttributes) {

        if (errors.hasErrors()) {
            return new ModelAndView(LOCACAO_CADASTRO);
        }

        int dias = (int) ((locacao.getDataDevolucao().getTime() - locacao.getDataLocacao().getTime()) / 86400000);
        locacao.setValorTotal(locacao.getCarro().getValorDiaria().multiply(new BigDecimal(dias)));

        if (locacao.getId() == null) {
            repository.save(locacao);
            redirectAttributes.addFlashAttribute("mensagem", "Inclusão realizada com sucesso!");
        } else {
            repository.save(locacao);
            redirectAttributes.addFlashAttribute("mensagem", "Alteração realizada com sucesso!");
        }

        return new ModelAndView("redirect:/locacoes/novo");
    }

    @GetMapping
    public ModelAndView listar() {
        ModelAndView modelAndView = new ModelAndView(LOCACAO_LISTA);
        modelAndView.addObject("locacoes", repository.findAll());
        return modelAndView;
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView(LOCACAO_CADASTRO);

        modelAndView.addObject(repository
                .findById(id).orElseThrow(()
                        -> new EmptyResultDataAccessException(0)));

        return modelAndView;
    }

    @GetMapping("/excluir/{id}")
    public ModelAndView excluir(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("redirect:/locacoes");
        repository.deleteById(id);
        return modelAndView;
    }

    @ModelAttribute(name = "todosMotoristas")
    public List<Motorista> todosMotoristas(){
        return motoristaRepository.findAll();
    }

    @ModelAttribute(name = "todosCarros")
    public List<Carro> todosCarros(){
        return carroRepository.findAll();
    }
}
