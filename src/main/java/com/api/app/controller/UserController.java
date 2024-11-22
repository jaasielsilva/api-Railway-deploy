package com.api.app.controller;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.api.app.model.User;
import com.api.app.repository.UserRepository;
import com.api.app.service.StatusService;
import com.api.app.service.UserService;


@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StatusService statusService;

    // Mapeia a URL '/Usuario' para a página de cadastro de usuários
    @GetMapping("/Usuarios")
    public String showRegistrationForm() {
        return "Usuarios"; // Nome da view, que será o arquivo register.html
    }
    
    // Método para buscar o status de um usuário pelo nome de usuário
    @GetMapping("/usuario/buscar")
    public String buscarUsuario(@RequestParam("username") String username, Model model) {
        // Buscar o usuário pelo nome de usuário
        User usuario = userRepository.findByUsername(username);
        
        // Se o usuário for encontrado, buscamos o status
        if (usuario != null) {
            // Passa os dados do usuário e status para o model
            String status = statusService.getStatusByUsername(username);
            model.addAttribute("usuario", usuario);
            model.addAttribute("status", status);
            
        } else {
            // Se não encontrar, envia a mensagem de erro
            model.addAttribute("error", "Usuário não encontrado");
        }

        // Retorna a página de detalhes do usuário
        return "detalhes-usuario";
    }

    @GetMapping("/register")
    public String showRegistrationFor() {
        return "register"; // Nome da view, que será o arquivo register.html
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user) {
        userService.saveUser(user); // Salva o usuário
        return "redirect:/relatorios"; // Redireciona para a página de login
    }

    // Outros métodos...
    @PostMapping("/cadastrar")
    public User cadastrar(@RequestBody User user) {
        return userService.saveUser(user); // Chama o serviço para salvar o usuário
    }

    private Map<String, Object> getDashboardInfo() {
        Map<String, Object> dashboardInfo = new HashMap<>();
        long totalPessoas = userService.getTotalPessoasCadastradas();
        LocalDateTime lastActivity = userService.getLastActivity();
        LocalDateTime oneWeekAgo = LocalDateTime.now().minusWeeks(1);
        List<User> newUsersList = userService.getNewUsersSince(oneWeekAgo);

        int newUsers = newUsersList.size();
        String lastActivityMessage = "Sem atividade recente";
        if (lastActivity != null) {
            long minutesAgo = ChronoUnit.MINUTES.between(lastActivity, LocalDateTime.now());
            lastActivityMessage = minutesAgo + " minutos atrás";
        }

        dashboardInfo.put("totalPessoas", totalPessoas);
        dashboardInfo.put("lastActivityMessage", lastActivityMessage);
        dashboardInfo.put("newUsers", newUsers);
        return dashboardInfo;
    }

    @GetMapping("/home")
    public String home(Model model) {
        model.addAllAttributes(getDashboardInfo());
        return "home";
    }

    @GetMapping("/relatorios")
    public String showRelatorios(Model model) {
        model.addAllAttributes(getDashboardInfo());
        return "relatorios";
    }

    @GetMapping("/verificacao-usuario")
    public String mostrarFormulario() {
        return "verificacao_usuario"; // Nome da página HTML (verificacao-usuario.html)
    }

    // Método para verificar o usuário
    @PostMapping("/verificar")
    public String verificarUsuario(@RequestParam("user-id") String username, Model model) {
    User user = userService.buscarPorUsername(username);
    
    // Se o usuário for encontrado
    if (user != null) {
        model.addAttribute("usuario", user); // Adiciona o usuário ao modelo
        return "detalhes-usuario"; // Nome da página para mostrar os dados do usuário
    } else {
        // Se o usuário não for encontrado
        model.addAttribute("erro", "Usuário não encontrado"); // Adiciona a mensagem de erro
        return "verificacao_usuario"; // Retorna à página de verificação se não encontrar o usuário
        }
    }
 
    @GetMapping("/lista-usuarios")
    public String mostrarListaUsuarios(Model model) {
        model.addAttribute("usuarios", userService.findAll()); // Corrigido para apenas uma chamada
    return "lista-usuarios";
    }

    // Método para retornar os usuários no formato JSON
    @GetMapping("/api/usuarios")
    public ResponseEntity<List<User>> findAll(){
        List<User> list = userService.findAll();
        return ResponseEntity.ok().body(list);
    }
}
