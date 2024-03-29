package br.com.caelum.argentum;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CandlestickFacktory {

	public Candle constroiCandlestickParaData(Calendar data, List<Negocio> negocios){
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
				
		return new Candle(abertura, fechamento, minimo, maximo, volume, data);
	}

	public boolean isMesmoDia(Calendar data1, Calendar data2) {
		return data1.get(Calendar.DAY_OF_MONTH) == data2.get(Calendar.DAY_OF_MONTH)
			&& data1.get(Calendar.MONTH) == data2.get(Calendar.MONTH)
			&& data1.get(Calendar.YEAR) == data2.get(Calendar.YEAR);
	}

	public List<Candle> constroiCandles(List<Negocio> todosNegocios) {
		
		List<Candle> candles = new ArrayList<Candle>();
		
		List<Negocio> negociosMesmoDia = new ArrayList<Negocio>();
		Calendar dataPrimeiro = todosNegocios.get(0).getData();
		
		for(Negocio negocio : todosNegocios){
			if(!isMesmoDia(dataPrimeiro, negocio.getData())){
				fechaCandle(candles, negociosMesmoDia, dataPrimeiro);
				
				negociosMesmoDia = new ArrayList<Negocio>();
				dataPrimeiro = negocio.getData();
			}
			
			negociosMesmoDia.add(negocio);
		}
		
		fechaCandle(candles, negociosMesmoDia, dataPrimeiro);
		
		return candles;
	}

	private boolean fechaCandle(List<Candle> candles,
			List<Negocio> negociosMesmoDia, Calendar dataPrimeiro) {
		return candles.add(constroiCandlestickParaData(dataPrimeiro, negociosMesmoDia));
	}
	
	
}
