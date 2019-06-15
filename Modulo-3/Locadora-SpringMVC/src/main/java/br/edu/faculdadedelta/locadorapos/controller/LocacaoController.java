package br.edu.faculdadedelta.locadorapos.controller;

import br.edu.faculdadedelta.locadorapos.model.Carro;
import br.edu.faculdadedelta.locadorapos.model.Locacao;
import br.edu.faculdadedelta.locadorapos.model.Motorista;
import br.edu.faculdadedelta.locadorapos.service.CarroService;
import br.edu.faculdadedelta.locadorapos.service.LocacaoService;
import br.edu.faculdadedelta.locadorapos.service.MotoristaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/locacoes")
public class LocacaoController {

    private static final String LOCACAO_CADASTRO = "locacao";
    private static final String LOCACAO_LISTA = "locacaoLista";

    @Autowired
    private LocacaoService service;

    @Autowired
    private CarroService carroService;

    @Autowired
    private MotoristaService motoristaService;

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

        if (locacao.getId() == null) {
            service.incluir(locacao);
            redirectAttributes.addFlashAttribute("mensagem", "Inclusão realizada com sucesso!");
        } else {
            service.alterar(locacao);
            redirectAttributes.addFlashAttribute("mensagem", "Alteração realizada com sucesso!");
        }

        return new ModelAndView("redirect:/locacoes/novo");
    }

    @GetMapping
    public ModelAndView listar() {
        ModelAndView modelAndView = new ModelAndView(LOCACAO_LISTA);
        modelAndView.addObject("locacoes", service.listar());
        return modelAndView;
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView(LOCACAO_CADASTRO);

        modelAndView.addObject(service.pesquisarPorId(id));

        return modelAndView;
    }

    @GetMapping("/excluir/{id}")
    public ModelAndView excluir(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("redirect:/locacoes");
        service.excluir(id);
        return modelAndView;
    }

    @ModelAttribute(name = "todosMotoristas")
    public List<Motorista> todosMotoristas() {
        return motoristaService.listar();
    }

    @ModelAttribute(name = "todosCarros")
    public List<Carro> todosCarros() {
        return carroService.listar();
    }
}
