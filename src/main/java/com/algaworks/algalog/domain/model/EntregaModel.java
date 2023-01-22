package com.algaworks.algalog.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntregaModel {
	
	private Long id;
	private CLienteResumoModel cliente;
	private DestinatarioModel destinatario;
	private BigDecimal taxa;
	private OffsetDateTime dataPedido;
	private OffsetDateTime dataFinalizacao;

}
