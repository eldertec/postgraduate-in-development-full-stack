package br.edu.faculdadedelta.locadorapos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class HomeController {

    public ModelAndView inicio(){
        ModelAndView modelAndView = new ModelAndView("index");
        return modelAndView;
    }
}
