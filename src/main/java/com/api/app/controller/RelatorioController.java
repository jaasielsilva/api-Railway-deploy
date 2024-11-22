package com.api.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RelatorioController {

    // Mapeia a página principal dos relatórios
    @GetMapping("/relatorio-sistemas")
    public String mostrarRelatorio(Model model) {
        // Se necessário, você pode adicionar atributos ao modelo
        model.addAttribute("mensagem", "Bem-vindo ao Relatório de Sistemas!");
        
        // Retorna o nome do template para Thymeleaf renderizar
        return "relatorio-sistemas";
    }

    // Relatório Financeiro
    @GetMapping("/relatorio_financeiro")
    public String relatorioFinanceiro(Model model) {
    model.addAttribute("relatorio", "Relatório Financeiro");
        return "relatorios_financeiros"; // Nome do template
    }


    // Relatório de Usuários Cadastrados
    @GetMapping("/relatorios-usuarios-cadastrados")
    public String exibirUsuariosCadastrados(Model model) {
    model.addAttribute("relatorio", "Relatório de Usuários Cadastrados");  
        return "relatorios_usuarios_cadastrados";  // Nome do arquivo HTML (sem a extensão .html)
    }


    // Relatório de Usuários Demitidos
    @GetMapping("/relatorios-usuarios-demitidos")
    public String exibirUsuariosDemitidos() {
        return "relatorios_usuarios_demitidos";  // Nome do arquivo HTML
    }

    // Relatório de Vendas
    @GetMapping("/relatorios-vendas")
    public String exibirRelatorioVendas() {
        return "relatorios_vendas";  // Nome do arquivo HTML
    }

    // Relatório de Produtos
    @GetMapping("/relatorios-produtos")
    public String exibirRelatorioProdutos() {
        return "relatorios_produtos";  // Nome do arquivo HTML
    }
    // Relatório de Clientes
    @GetMapping("/relatorios-clientes")
    public String exibirRelatorioClientes() {
        return "relatorios_clientes";  // Nome do arquivo HTML
    }

    // Relatório de Desempenho
    @GetMapping("/relatorios-desempenho")
    public String exibirRelatorioDesempenho() {
        return "relatorios_desempenho";  // Nome do arquivo HTML
    }
    // Relatório de Estoque
    @GetMapping("/relatorios-estoque")
    public String exibirRelatorioEstoque() {
        return "relatorios_estoque";  // Nome do arquivo HTML
    }

    // Análise de Projetos
    @GetMapping("/analise-de-projetos")
    public String exibirAnaliseDeProjetos() {
        return "analise_de_projetos";  // Nome do arquivo HTML
    }

    // Gestão de Riscos
    @GetMapping("/gestao-de-riscos")
    public String exibirGestaoDeRiscos() {
        return "gestao_de_riscos";  // Nome do arquivo HTML
    }

    // Controle de Estoque
    @GetMapping("/controle-de-estoque")
    public String exibirControleDeEstoque() {
        return "controle_de_estoque";  // Nome do arquivo HTML
    }

    // Performance da Empresa
    @GetMapping("/performance-da-empresa")
    public String exibirPerformanceDaEmpresa() {
        return "performance_da_empresa";  // Nome do arquivo HTML
    }

    // Visão Geral de Projetos
    @GetMapping("/visao-geral-de-projetos")
    public String exibirVisaoGeralDeProjetos() {
        return "visao_geral_de_projetos";  // Nome do arquivo HTML
    }

    }

