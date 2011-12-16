package br.com.caelum.argentum.indicadores;

import br.com.caelum.argentum.SerieTemporal;

public class MediaMovelSimples implements Indicador  {
	
	private Indicador indicador;
	
	public MediaMovelSimples(Indicador indicador){
		this.indicador = indicador;
	}

	@Override
	public double calcula(int posicao, SerieTemporal serie){
		double soma = 0.0;
		
		for(int i = posicao - 2; i <= posicao; i++){
			soma += indicador.calcula(posicao, serie);
		}
		
		return soma/3;
	}
	
	public String toString(){
		return "Media Movel Simples";
	}
	
}
