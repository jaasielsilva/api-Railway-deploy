package com.api.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

@ControllerAdvice
public class ErrorController {

    @GetMapping("/error")
    public String handleError(Model model, Integer status) {
        String errorMessage = "Erro desconhecido!";
        HttpStatus httpStatus = HttpStatus.resolve(status);
        
        if (httpStatus != null) {
            switch (httpStatus) {
                case NOT_FOUND:
                    errorMessage = "Página não encontrada!";
                    break;
                case INTERNAL_SERVER_ERROR:
                    errorMessage = "Erro interno do servidor!";
                    break;
                case BAD_REQUEST:
                    errorMessage = "Requisição inválida!";
                    break;
                // Adicione outros erros específicos, se necessário
                default:
                    errorMessage = "Erro desconhecido!";
                    break;
            }
        }

        model.addAttribute("error", errorMessage);
        model.addAttribute("status", status);  // Opcional: mostra o código de status
        return "error"; // Página customizada de erro
    }

    // Opcional: Manipular exceções específicas
    @ExceptionHandler(Exception.class)
    public String handleException(Exception ex, Model model) {
        model.addAttribute("error", "Ocorreu um erro inesperado: " + ex.getMessage());
        return "error";
    }
}
