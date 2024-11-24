package br.com.financeapp.dto;

import br.com.financeapp.entity.TransacaoEntity;
import lombok.*;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@Setter
public class TransacaoDTO {
    private Long id;
    private String descricao;
    private double valor;
    private LocalDate data;
    private String tipo;


    public TransacaoDTO(TransacaoEntity entity) {
        this.id = entity.getId();
        this.descricao = entity.getDescricao();
        this.valor = entity.getValor();
        this.data = entity.getData();
        this.tipo = entity.getTipo();
    }

}
