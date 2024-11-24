package br.com.financeapp.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Despesa extends Transacao {

    public Despesa(String descricao, double valor, LocalDate data) {
        super(descricao, valor, data);
    }

    @Override
    public String getTipo() {
        return "DESPESA";
    }
}
