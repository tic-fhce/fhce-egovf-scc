package com.fhce.scc.obj;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class marcadoDtoTotalResponse {
	private Long cif;
	private List<formatoReporteDtoResponse> reporteFinal;
	private int retraso;
	private int antisipado;
	private int pena;

}
