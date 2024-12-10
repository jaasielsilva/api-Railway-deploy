package com.api.app.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.app.model.ActionHistory;  // Importando o modelo ActionHistory
import com.api.app.model.User;
import com.api.app.repository.UserRepository;
import com.api.app.repository.ActionHistoryRepository;  // Repositório para ActionHistory

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ActionHistoryRepository actionHistoryRepository; // Repositório para gravar o histórico de ações

    // Método para salvar o usuário
    public User saveUser(User user) {
        User savedUser = userRepository.save(user);
        registrarAcao(savedUser, "CREATE", "User ID " + savedUser.getId(), "Usuário criado.");
        return savedUser;
    }

    // Método para excluir o usuário
    public void deleteUser(Long userId, Long adminId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        registrarAcao(user, "DELETE", "User ID " + userId, "Usuário excluído por admin.");
        userRepository.delete(user); // Exclui o usuário
    }

    // Método para atualizar o role de um usuário
    public void atualizarRoleUsuario(String username, String role) {
        User usuario = userRepository.findByUsername(username);
        if (usuario != null) {
            String oldRole = usuario.getRole();
            usuario.setRole(role);
            userRepository.save(usuario);  // Salva a alteração no banco de dados
            registrarAcao(usuario, "UPDATE", "Role " + oldRole + " -> " + role, "Role atualizado.");
        }
    }

    // Método para registrar ações no histórico
    private void registrarAcao(User user, String action, String target, String details) {
        ActionHistory actionHistory = new ActionHistory();
        actionHistory.setUser(user);
        actionHistory.setAction(action);
        actionHistory.setTarget(target);
        actionHistory.setDetails(details);
        actionHistory.setTimestamp(LocalDateTime.now()); // Data e hora da ação
        
        actionHistoryRepository.save(actionHistory); // Salva o histórico no banco de dados
    }

    // Método para obter o total de pessoas cadastradas
    public long getTotalPessoasCadastradas() {
        return userRepository.count(); // Retorna o total de usuários cadastrados
    }

    // Método para obter a última atividade
    public LocalDateTime getLastActivity() {
        User lastUser = userRepository.findTopByOrderByCreatedAtDesc();
        if (lastUser != null) {
            return lastUser.getCreatedAt(); // Retorna o campo createdAt do último usuário
        }
        return null; // Caso não exista usuários, retorna null
    }

    // Método para buscar novos usuários cadastrados após uma data específica (uma semana atrás)
    public List<User> getNewUsersSince(LocalDateTime date) {
        return userRepository.findByCreatedAtAfter(date); // Retorna lista de usuários após a data informada
    }

    // Método para buscar o usuário pelo nome
    public User buscarPorUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // Método para atualizar o último acesso
    public void updateLastAccess(Long userId) {
        userRepository.updateLastAccess(userId);  // Chama o método no repositório para atualizar no banco de dados
    }

    // Método para obter o último acesso de um usuário
    public LocalDateTime getLastAccess(Long userId) {
        User user = userRepository.findById(userId).orElse(null);  // Procura o usuário no banco de dados
        if (user != null) {
            return user.getUltimoAcesso();  // Aqui usamos 'getUltimoAcesso' para acessar o campo correto
        }
        return null;
    }
    
    public List<User> findAll() {
        return userRepository.findAll();
    }

    // Método no UserService para buscar todos os usernames
    public List<String> buscarTodosUsernames() {
        return userRepository.findAll().stream()
            .map(User::getUsername) // Pega o username de cada User
            .collect(Collectors.toList());
    }

    // Método para buscar todas as permissões (roles) dos usuários
    public List<String> buscarTodosRoles() {
        return userRepository.findAll().stream()
            .map(User::getRole)  // Obtém o role de cada usuário
            .distinct()          // Evita roles duplicadas
            .collect(Collectors.toList());  // Retorna uma lista de roles
    }

}
