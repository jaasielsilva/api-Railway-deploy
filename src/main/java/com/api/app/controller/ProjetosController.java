package com.api.app.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProjetosController {

    @GetMapping("/gestao-projetos")
    public String projetos() {
        return "gestao_projetos";
    }
    
    
}
