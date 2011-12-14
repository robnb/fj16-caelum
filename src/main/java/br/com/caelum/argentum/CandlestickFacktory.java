package br.com.caelum.argentum;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CandlestickFacktory {

	public Candlestick constroiCandlestickParaData(Calendar data, List<Negocio> negocios){
		double abertura =  negocios.isEmpty()? 0 : negocios.get(0).getPreco();
		double fechamento = negocios.isEmpty()? 0 : negocios.get(negocios.size()-1).getPreco();
		double minimo = Double.MAX_VALUE;
		double maximo = Double.MIN_VALUE;
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

	public boolean isMesmoDia(Calendar data1, Calendar data2) {
		return data1.get(Calendar.DAY_OF_MONTH) == data2.get(Calendar.DAY_OF_MONTH)
			&& data1.get(Calendar.MONTH) == data2.get(Calendar.MONTH)
			&& data1.get(Calendar.YEAR) == data2.get(Calendar.YEAR);
	}

	public List<Candlestick> constroiCandles(List<Negocio> todosNegocios) {
		
		List<Candlestick> candles = new ArrayList<Candlestick>();
		
		List<Negocio> negociosMesmoDia = new ArrayList<Negocio>();
		Calendar dataPrimeiro = todosNegocios.get(0).getData();
		
		for(Negocio negocio : todosNegocios){
			if(!isMesmoDia(dataPrimeiro, negocio.getData())){
				candles.add(constroiCandlestickParaData(dataPrimeiro, negociosMesmoDia));
				
				negociosMesmoDia = new ArrayList<Negocio>();
				dataPrimeiro = negocio.getData();
			}
			
			negociosMesmoDia.add(negocio);
		}
		
		candles.add(constroiCandlestickParaData(dataPrimeiro, negociosMesmoDia));
		
		return candles;
	}
	
	
}
