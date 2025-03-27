package com.fhce.scc.obj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class obsBioDtoRequest {
	
	private Long cif;
	private Long idObs;
	private String horaEntrada;
	private int hEntrada;
	private int mEntrada;
	private String horaSalida;
	private int hSalida;
	private int mSalida;
	private int estado;

}
