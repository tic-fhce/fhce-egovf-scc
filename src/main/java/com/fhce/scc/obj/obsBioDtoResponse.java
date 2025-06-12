package com.fhce.scc.obj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class obsBioDtoResponse {
	private Long id;
	private Long cif;
	private Long idObs;
	private String horaEntrada;
	private int hentrada;
	private int mentrada;
	private String horaSalida;
	private int hsalida;
	private int msalida;
	private int estado;

}
