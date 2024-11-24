package br.com.financeapp.dto;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public abstract class Transacao {
    private Long id;
    private String descricao;
    private double valor;
    private LocalDate data;

    public Transacao(String descricao, double valor, LocalDate data) {
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
    }

    public abstract String getTipo();

}
