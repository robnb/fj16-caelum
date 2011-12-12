package br.com.caelum.argentum;

import java.util.Calendar;
import java.util.List;

public class CandlestickFacktory {

	public Candlestick constroiCandlestickParaData(Calendar data, List<Negocio> negocios){
		double abertura = negocios.get(0).getPreco();
		double fechamento = negocios.get(negocios.size()-1).getPreco();
		double minimo = abertura;
		double maximo = 0;
		double volume = 0;
		
		for(Negocio n : negocios){
			if(n.getPreco() < minimo){
				minimo = n.getPreco();
			}
			if(n.getPreco() > maximo){
				maximo = n.getPreco();
			}
			volume += n.getVolume();
		}
				
		return new Candlestick(abertura, fechamento, minimo, maximo, volume, data);
	}
	
	
}
