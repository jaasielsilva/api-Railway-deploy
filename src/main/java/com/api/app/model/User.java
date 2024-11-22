package com.api.app.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String role;
    private String status;
    private String nome;
    private String email;
    private String telefone;
    private String endereco;

    @Column(nullable = false)
    private LocalDateTime ultimaAcao;

    @Column(nullable = false)
    private LocalDateTime ultimoAcesso;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    // Define a data de criação automaticamente sem segundos
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES); // Trunca para minutos
        this.ultimoAcesso = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        this.ultimaAcao = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
    }

    // Atualiza `ultimaAcao` automaticamente antes de salvar
    @PreUpdate
    protected void onUpdate() {
        this.ultimaAcao = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES); // Trunca para minutos
    }

    // Método público para atualizar o último acesso
    public void atualizarUltimoAcesso() {
        this.ultimoAcesso = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES); // Trunca para minutos
    }
}
