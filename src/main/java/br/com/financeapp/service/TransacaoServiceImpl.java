package br.com.financeapp.service;

import br.com.financeapp.dto.Transacao;
import br.com.financeapp.dto.TransacaoDTO;
import br.com.financeapp.entity.TransacaoEntity;
import br.com.financeapp.interfaces.TransacaoInterface;
import br.com.financeapp.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

		TransacaoEntity entity = new TransacaoEntity(transacao);

		return repository.save(entity);
	}

	@Override
	public TransacaoEntity update(TransacaoDTO transacao) {
		TransacaoEntity entity = new TransacaoEntity(transacao);

		return repository.save(entity);
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

	@Override
	public void changeChecked(Boolean checked, Long id) {

	}
}
