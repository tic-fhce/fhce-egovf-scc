package com.fhce.scc.obj;

import com.fhce.scc.model.obsBioModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class obsDtoReporte {
	
	private Long id;
	private String uidobs;
	private String fecha;
	private String detalle;
	private String tipo;
	private obsBioModel obsBioModel;

	
	public obsDtoReporte(String tipo) {
		this.tipo=tipo;
		this.uidobs="";
	}

}
