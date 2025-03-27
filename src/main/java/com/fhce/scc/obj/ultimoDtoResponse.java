package com.fhce.scc.obj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ultimoDtoResponse {
	
	private Long cif;
	private Long id;
	private String fecha;
	private String hora;

}
