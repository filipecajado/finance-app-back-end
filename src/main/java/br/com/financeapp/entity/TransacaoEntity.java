package br.com.financeapp.entity;

import br.com.financeapp.dto.Transacao;
import br.com.financeapp.dto.TransacaoDTO;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "transacao")
@Getter
@Setter
@NoArgsConstructor
public class TransacaoEntity {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String descricao;

	@Column(nullable = false)
	private Double valor;

	@Column(nullable = false)
	private LocalDate data;

	@Column(nullable = false)
	private String tipo;


	public TransacaoEntity(Transacao transacao) {
		this.id = transacao.getId();
		this.descricao = transacao.getDescricao();
		this.valor = transacao.getValor();
		this.tipo = transacao.getTipo();
		this.data = transacao.getData();
	}

	public TransacaoEntity(TransacaoDTO transacao) {
		this.id = transacao.getId();
		this.descricao = transacao.getDescricao();
		this.valor = transacao.getValor();
		this.tipo = transacao.getTipo();
		this.data = transacao.getData();
	}
}
