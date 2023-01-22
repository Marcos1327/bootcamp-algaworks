package com.algaworks.algalog.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algalog.domain.model.Entrega;
import com.algaworks.algalog.domain.model.StatusEntrega;
import com.algaworks.algalog.domain.repository.EntregaRepository;

@Service
public class FinalizacaEntregaService {
	
	@Autowired
	private BuscaEntregaService buscaEntregaService;
	
	@Autowired
	private EntregaRepository entregaRepository;
		
		@Transactional
		public void finalizar(Long entregaId) {
			Entrega entrega = buscaEntregaService.buscar(entregaId);		
			
			entrega.finalizar();
		
			entregaRepository.save(entrega);
		}
}
