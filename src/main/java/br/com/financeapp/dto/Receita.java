package br.com.financeapp.dto;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class Receita extends Transacao{

    public Receita(String descricao, double valor, LocalDate data) {
        super(descricao, valor, data);
    }

    @Override
    public String getTipo() {
        return "RECEITA";
    }
}
