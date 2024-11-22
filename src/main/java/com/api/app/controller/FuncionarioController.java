package com.api.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FuncionarioController {

    // Página inicial do funcionário com o menu
    @GetMapping("/menufuncionario")
    public String menuFuncionario() {
        return "menufuncionario";  // Exibe o menu
    }
    // Visualizar relatórios
    @GetMapping("/relatoriosparainiciante")
    public String visualizarRelatorios(Model model) {
        // Aqui você pode adicionar lógica para obter relatórios de um banco de dados ou serviço
        model.addAttribute("relatorios", new String[]{"Relatório 1", "Relatório 2", "Relatório 3"});
        return "relatoriosparainiciante";  // Exibe a página de relatórios
    }

    // Configurações do funcionário
    @GetMapping("/configuracoes")
    public String configuracoes(Model model) {
        // Adiciona dados iniciais ao modelo, como configurações do funcionário
        model.addAttribute("email", "usuario@exemplo.com");
        return "configuracoes";  // Exibe o formulário de configurações
    }

    // Salvar configurações
    @PostMapping("/salvarConfiguracoes")
    public String salvarConfiguracoes(String email, String senha) {
        // Lógica para salvar as configurações no banco de dados ou em algum serviço
        System.out.println("E-mail: " + email + " | Senha: " + senha);
        return "redirect:/menufuncionario";  // Redireciona de volta ao menu
    }
}
