package com.api.app.controller;

import com.api.app.model.ActionHistory;
import com.api.app.repository.ActionHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/action-history")
public class ActionHistoryController {

    @Autowired
    private ActionHistoryRepository actionHistoryRepository;

    // Endpoint para retornar todos os registros de action_history
    @GetMapping
    public List<ActionHistory> getAllActionHistory() {
        return actionHistoryRepository.findAll(); // Retorna todos os registros
    }

    // Endpoint para retornar histórico de ações de um usuário específico
    @GetMapping("/user")
    public List<ActionHistory> getActionHistoryByUser(@RequestParam Long userId) {
        return actionHistoryRepository.findByUserId(userId); // Retorna registros filtrados pelo ID do usuário
    }

    // Endpoint para retornar histórico de ações com base em um filtro opcional
    @GetMapping("/filter")
    public List<ActionHistory> getFilteredActionHistory(@RequestParam Optional<Long> userId, @RequestParam Optional<String> action) {
        if (userId.isPresent() && action.isPresent()) {
            return actionHistoryRepository.findByUserIdAndAction(userId.get(), action.get());
        } else if (userId.isPresent()) {
            return actionHistoryRepository.findByUserId(userId.get());
        } else if (action.isPresent()) {
            return actionHistoryRepository.findByAction(action.get());
        } else {
            return actionHistoryRepository.findAll(); // Retorna todos os registros se nenhum filtro for fornecido
        }
    }
}
