package com.algaworks.algalog.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algalog.api.assembler.EntregaAssembler;
import com.algaworks.algalog.domain.model.Entrega;
import com.algaworks.algalog.domain.model.EntregaModel;
import com.algaworks.algalog.domain.model.input.EntregaInput;
import com.algaworks.algalog.domain.repository.EntregaRepository;
import com.algaworks.algalog.domain.service.SolicitacaoEntregaService;

@RestController
@RequestMapping("/entregas")
public class EntregaController {

	@Autowired
	private EntregaRepository entregaRepository;

	@Autowired
	private SolicitacaoEntregaService entregaService;

	@Autowired
	private EntregaAssembler assembler;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EntregaModel solicitar(@Valid @RequestBody EntregaInput entregaInput) {
		Entrega novaEntrega = assembler.toEntity(entregaInput);		
		Entrega entregaSolicitada = entregaService.solicita(novaEntrega);
		
		return assembler.toModel(entregaSolicitada);

	}

	@GetMapping
	public List<EntregaModel> listar() {
		return assembler.toCollectionModel(entregaRepository.findAll());

	}

	@GetMapping("/{entregaId}")
	public ResponseEntity<EntregaModel> buscar(@PathVariable Long entregaId) {
		return entregaRepository.findById(entregaId).map(entrega -> ResponseEntity.ok(assembler.toModel(entrega)))
				.orElse(ResponseEntity.notFound().build());
//					EntregaModel entregaModel = .map(entrega, EntregaModel.class);
//					EntregaModel entregaModel = new EntregaModel();
//					entregaModel.setId(entrega.getId());
//					entregaModel.setNomeCliente(entrega.getCliente().getNome());
//					entregaModel.setDestinatario(new DestinatarioModel());
//					entregaModel.getDestinatario().setNome(entrega.getDestinatario().getNome());
//					entregaModel.getDestinatario().setNumero(entrega.getDestinatario().getNumero());
//					entregaModel.getDestinatario().setComplemento(entrega.getDestinatario().getComplemento());
//					entregaModel.getDestinatario().setLogradouro(entrega.getDestinatario().getLogradouro());
//					entregaModel.getDestinatario().setBairro(entrega.getDestinatario().getBairro());
//					entregaModel.setTaxa(entrega.getTaxa());
//					entregaModel.setDataPedido(entrega.getDataPedido());
//					entregaModel.setDataFinalizacao(entrega.getDataFinalizacao());

	}
}
