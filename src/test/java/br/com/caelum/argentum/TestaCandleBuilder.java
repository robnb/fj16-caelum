package br.com.caelum.argentum;

import java.util.GregorianCalendar;

public class TestaCandleBuilder {

	public static void main(String[] args) {
		Candlestick candle = new CandleBuilder()
		.abertura(40.5).fechamento(42.3).minimo(39.8)
		.maximo(45).volume(145234.20)
		.data(new GregorianCalendar(2008,8,12,0,0,0)).geraCandle();
		
		System.out.println(candle);
	}
}
