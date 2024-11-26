package br.com.financeapp.service;

import br.com.financeapp.dto.Transacao;
import br.com.financeapp.dto.TransacaoDTO;
import br.com.financeapp.entity.TransacaoEntity;
import br.com.financeapp.interfaces.TransacaoInterface;
import br.com.financeapp.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TransacaoServiceImpl implements TransacaoInterface {

	@Autowired
	private TransacaoRepository repository;

	public TransacaoServiceImpl(TransacaoRepository repository) {
		this.repository = repository;
	}


	@Override
	public TransacaoEntity save(Transacao transacao) {
		validarTransacao(transacao);
		try {
			TransacaoEntity entity = new TransacaoEntity(transacao);
			return repository.save(entity);
		}  catch (Exception e) {
			throw new RuntimeException("Erro inesperado ao salvar a transação: " + e.getMessage(), e);
		}
	}

	@Override
	public TransacaoEntity update(TransacaoDTO transacao) {
		validarTransacao(transacao);
		try {
			if (!repository.existsById(transacao.getId())) {
				throw new IllegalArgumentException("Transação com ID " + transacao.getId() + " não encontrada.");
			}
			TransacaoEntity entity = new TransacaoEntity(transacao);
			return repository.save(entity);
		}  catch (Exception e) {
			throw new RuntimeException("Erro inesperado ao atualizar a transação: " + e.getMessage(), e);
		}
	}

	@Override
	public List<TransacaoDTO> getAll() {
		return repository.findAll()
				.stream()
				.map(TransacaoDTO::new)
				.toList();
	}

	@Override
	public void delete(Long id) {
		repository.deleteById(id);
	}

	@Override
	public void deleteAll() {
		repository.deleteAll();
	}

	private void validarTransacao(Object transacao) {
		if (transacao instanceof TransacaoDTO) {
			TransacaoDTO dto = (TransacaoDTO) transacao;
			validarDescricao(dto.getDescricao());
			validarValor(dto.getValor());
			validarData(dto.getData());
			validarTipo(dto.getTipo());
		} else if (transacao instanceof Transacao) {
			Transacao entity = (Transacao) transacao;
			validarDescricao(entity.getDescricao());
			validarValor(entity.getValor());
			validarData(entity.getData());
			validarTipo(entity.getTipo());
		}
	}

	private void validarDescricao(String descricao) {
		if (descricao == null || descricao.isBlank()) {
			throw new IllegalArgumentException("A descrição é obrigatória.");
		}
		if (descricao.length() < 3 || descricao.length() > 255) {
			throw new IllegalArgumentException("A descrição deve ter entre 3 e 255 caracteres.");
		}
	}

	private void validarValor(double valor) {
		if (valor <= 0) {
			throw new IllegalArgumentException("O valor deve ser maior que zero.");
		}
	}

	private void validarData(LocalDate data) {
		if (data == null) {
			throw new IllegalArgumentException("A data é obrigatória.");
		}
	}

	private void validarTipo(String tipo) {
		if (tipo == null || tipo.isBlank()) {
			throw new IllegalArgumentException("O tipo é obrigatório.");
		}
		if (!tipo.equalsIgnoreCase("RECEITA") && !tipo.equalsIgnoreCase("DESPESA")) {
			throw new IllegalArgumentException("O tipo deve ser 'RECEITA' ou 'DESPESA'.");
		}
	}

}
