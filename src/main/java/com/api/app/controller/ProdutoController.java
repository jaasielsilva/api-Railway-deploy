package com.api.app.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.api.app.model.Produto;
import com.api.app.repository.ProdutoRepository;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("/")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    // Método GET para exibir a página de cadastro de produtos e lista de produtos
    @GetMapping("/cadastro-produtos")
    public String mostrarPaginaCadastroProdutos(Model model) {
        model.addAttribute("produtos", produtoRepository.findAll()); // Lista de produtos
        return "cadastro_produtos";  // Exibe a página de cadastro
    }

    // Método POST para salvar um novo produto
    @PostMapping("produtos/salvar")
    public String salvarProduto(@RequestParam String nome, 
                                @RequestParam String descricao, 
                                @RequestParam Double preco,
                                Model model) {
        Produto produto = new Produto(null, nome, descricao, preco);
        produtoRepository.save(produto);  // Salva o produto no banco
        System.out.println("Produto cadastrado !");
        model.addAttribute("sucesso", "Produto cadastrado com sucesso!");
        return "redirect:/produtos";  
    }


    @GetMapping("/editar/{id}")
    public String editarProduto(@PathVariable Long id, Model model) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado: " + id));
        model.addAttribute("produto", produto);  // Passa o produto para a página
        return "editar_produto";  // Retorna a página de edição
    }


    @PostMapping("/editar/{id}")
    public String salvarAlteracoesProduto(@PathVariable Long id, @ModelAttribute Produto produto) {
    // Atribui o ID ao produto para garantir que a atualização seja no produto correto
    produto.setId(id);  
    
    // Atualiza o produto no banco de dados
    produtoRepository.save(produto);  
    
    // Redireciona para a página de listagem de produtos
    return "redirect:/produtos/cadastro-produtos";  
    }

    // Método GET para deletar um produto
    @GetMapping("/deletar/{id}")
    public String deletarProduto(@PathVariable Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto inválido"));
        produtoRepository.delete(produto);  // Deleta o produto do banco
        return "redirect:/produtos/cadastro-produtos";  // Redireciona para a lista de produtos
    }

    // Página de Cadastro de Produtos
    @GetMapping("/produtos")
    public String ExibirTelaProdutos() {
        return "produtos";  // Retorna a view de cadastro de produtos
    }
    // Método GET para exibir a página de cadastro de produtos
    @GetMapping("/produtos/cadastro-produtos")
    public String mostrarPaginaCadastroProdutos() {
        return "cadastro_produtos";  // Exibe a página de cadastro
    }
    @PostMapping("/produtos/cadastro_produtos")
    public String ExibirTelaCadastroProdutos(@RequestBody String entity) {
        
        return "redirect:/produtos/cadastro-produtos";  
    }
    // Método GET para listar todos os produtos e buscar
    @GetMapping("/lista-produtos")
    public String listarProdutos(@RequestParam(value = "", required = false) String search, Model model) {
        List<Produto> produtos;

        if (search != null && !search.isEmpty()) {
            // Se há um termo de busca, filtra os produtos pelo nome ou descrição
            produtos = produtoRepository.findByNomeContainingOrDescricaoContaining(search, search);
        } else {
            // Caso contrário, lista todos os produtos
            produtos = produtoRepository.findAll();
        }

        model.addAttribute("produtos", produtos);
        model.addAttribute("search", search); // Para preencher o campo de busca
        return "lista-produtos";  // A página de listagem de produtos
    }
     // Método GET para exibir a página de cadastro de produtos e lista de produtos
     @GetMapping("/produtos/remocao-produtos")
     public String mostrarPaginaRemoverCadastroProdutos(Model model) {
         model.addAttribute("produtos", produtoRepository.findAll()); // Lista de produtos
         return "remover_produtos";  // Exibe a página de cadastro
     }
}
