package br.com.financeapp.controller;

import br.com.financeapp.dto.Despesa;
import br.com.financeapp.dto.Receita;
import br.com.financeapp.dto.Transacao;
import br.com.financeapp.dto.TransacaoDTO;
import br.com.financeapp.entity.TransacaoEntity;
import br.com.financeapp.interfaces.TransacaoInterface;
import jakarta.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/transacoes")
@CrossOrigin("*")
public class TransacaoController {

	@Autowired
	private TransacaoInterface transacaoService;

	@GetMapping()
	public ResponseEntity<List<TransacaoDTO>> getAll() {
		return ResponseEntity.ok(transacaoService.getAll());
	}

	@PostMapping()
	public ResponseEntity<TransacaoEntity> save(@RequestBody TransacaoDTO transacao) {
		Transacao novaTransacao;
		if (transacao.getTipo().equals("RECEITA")) {
			novaTransacao = new Receita(transacao.getDescricao(), transacao.getValor(), transacao.getData());
		} else if (transacao.getTipo().equals("DESPESA")) {
			novaTransacao = new Despesa(transacao.getDescricao(), transacao.getValor(), transacao.getData());
		} else {
			throw new IllegalArgumentException("Tipo inválido: " + transacao.getTipo());
		}
		return ResponseEntity.ok(transacaoService.save(novaTransacao));
	}
	
	@PutMapping()
	public ResponseEntity<TransacaoEntity> update(@RequestBody TransacaoDTO task) {
		
		return ResponseEntity.ok(transacaoService.update(task));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> delete(@PathVariable @Nonnull Long id) {
		transacaoService.delete(id);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping()
	public ResponseEntity<HttpStatus> deleteAll() {
		transacaoService.deleteAll();
		return ResponseEntity.ok().build();
	}

	@PutMapping("/checked")
	public ResponseEntity<HttpStatus> changeChecked(@RequestParam  Boolean checked, @RequestParam  Long id) {
		transacaoService.changeChecked(checked, id);
		return ResponseEntity.ok().build();
	}

}
