package com.api.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.api.app.model.User;
import com.api.app.service.LoginService;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping("/")
    public String showLoginPage(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", "Usuário ou senha inválidos. Tente novamente.");
        }
        return "login"; // Retorna o template login.html
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        boolean isAuthenticated = loginService.login(username, password);

        if (isAuthenticated) {
            // Buscar o usuário com base no nome de usuário
            User user = loginService.findUserByUsername(username);

            // Verifica se o papel do usuário é ADMIN
            if (user != null && "ADMIN".equals(user.getRole())) {
                return "redirect:/relatorios"; // Se o usuário for ADMIN, redireciona para home
            } else {
                return "redirect:/menuiniciante"; // Se não for ADMIN, redireciona para o menuiniciante
            }
        } else {
            return "redirect:/?error"; // Redireciona de volta ao login com erro
        }
    }

    // Para /menuiniciante
    @GetMapping("/menuiniciante")
    public String showMenuIniciante() {
        return "menuiniciante"; // Retorna o template menuiniciante.html
    }

}
