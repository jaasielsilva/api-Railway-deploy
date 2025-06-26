package com.api.app.controller;

import java.util.List;
import java.util.Optional;

import com.api.app.model.Produto;
import com.api.app.repository.ProdutoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    // Página principal de cadastro + listagem
    @GetMapping("/cadastro-produtos")
    public String mostrarPaginaCadastroProdutos(Model model) {
        model.addAttribute("produtos", produtoRepository.findAll());
        return "cadastro_produtos";
    }

    // Salvar novo produto
    @PostMapping("produtos/salvar")
    public String salvarProduto(@RequestParam String nome,
                                @RequestParam String descricao,
                                @RequestParam Double preco,
                                @RequestParam String codigoBarras,
                                Model model) {
        Produto produto = new Produto(null, nome, descricao, preco, codigoBarras);
        produtoRepository.save(produto);
        model.addAttribute("sucesso", "Produto cadastrado com sucesso!");
        return "redirect:/produtos";
    }

    // Editar produto - exibir formulário
    @GetMapping("/editar/{id}")
    public String editarProduto(@PathVariable Long id, Model model) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado: " + id));
        model.addAttribute("produto", produto);
        return "editar_produto";
    }

    // Salvar alterações do produto
    @PostMapping("/editar/{id}")
    public String salvarAlteracoesProduto(@PathVariable Long id, @ModelAttribute Produto produto) {
        produto.setId(id);
        produtoRepository.save(produto);
        return "redirect:/produtos/cadastro-produtos";
    }

    // Deletar produto
    @GetMapping("/deletar/{id}")
    public String deletarProduto(@PathVariable Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto inválido"));
        produtoRepository.delete(produto);
        return "redirect:/produtos/cadastro-produtos";
    }

    // Exibir tela inicial de produtos
    @GetMapping("/produtos")
    public String exibirTelaProdutos() {
        return "produtos";
    }

    // Reexibir tela de cadastro (evita duplicidade de rotas)
    @GetMapping("/produtos/cadastro-produtos")
    public String mostrarTelaCadastro() {
        return "cadastro_produtos";
    }

    // Receber dados via POST (não usado diretamente no momento)
    @PostMapping("/produtos/cadastro_produtos")
    public String exibirTelaCadastroProdutos(@RequestBody String entity) {
        return "redirect:/produtos/cadastro-produtos";
    }

    // Listar produtos com filtro de busca
    @GetMapping("/lista-produtos")
    public String listarProdutos(@RequestParam(value = "search", required = false) String search, Model model) {
        List<Produto> produtos;
        if (search != null && !search.isEmpty()) {
            produtos = produtoRepository.findByNomeContainingOrDescricaoContaining(search, search);
        } else {
            produtos = produtoRepository.findAll();
        }
        model.addAttribute("produtos", produtos);
        model.addAttribute("search", search);
        return "lista-produtos";
    }

    // Tela para remoção de produtos
    @GetMapping("/produtos/remocao-produtos")
    public String mostrarPaginaRemoverProdutos(Model model) {
        model.addAttribute("produtos", produtoRepository.findAll());
        return "remover_produtos";
    }

    // 🔄 Buscar produto por código de barras (AJAX)
    @GetMapping("/produtos/buscar-por-codigo")
    @ResponseBody
    public ResponseEntity<?> buscarPorCodigo(@RequestParam String codigo) {
        Optional<Produto> produto = produtoRepository.findByCodigoBarras(codigo);
        if (produto.isPresent()) {
            return ResponseEntity.ok(produto.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
        }
    }
}
