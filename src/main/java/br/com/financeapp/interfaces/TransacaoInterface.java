package br.com.financeapp.interfaces;

import br.com.financeapp.dto.Transacao;
import br.com.financeapp.dto.TransacaoDTO;
import br.com.financeapp.entity.TransacaoEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TransacaoInterface {

	TransacaoEntity save(Transacao transacao);

	TransacaoEntity update(TransacaoDTO transacao);

	List<TransacaoDTO> getAll();

	void delete(Long id);
	
	void deleteAll();
	
	void changeChecked(Boolean checked, Long id);
}
