package com.api.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RecursosHumanosController {

     // Alterar a rota para "/recursos-humanos"
     @GetMapping("/recursos-humanos")
     public String mostrarGestaoRH() {
         // Retorna o nome do template (HTML) que será exibido
         return "gestao-rh";  // Assumindo que o template se chama 'gestao-rh.html'
     }
}
