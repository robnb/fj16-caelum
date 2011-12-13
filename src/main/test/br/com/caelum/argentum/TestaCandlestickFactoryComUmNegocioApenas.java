package br.com.caelum.argentum;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class TestaCandlestickFactoryComUmNegocioApenas {


	public static void main(String[] args) {
		Calendar hoje = Calendar.getInstance();
		Negocio negocio1 = new Negocio(40.5, 100, hoje);

		List<Negocio> negocios = Arrays.asList(negocio1);

		CandlestickFacktory fabrica = new CandlestickFacktory();
		Candlestick candle = fabrica.constroiCandlestickParaData(hoje, negocios);

		System.out.println(candle.getAbertura());
		System.out.println(candle.getFechamento());
		System.out.println(candle.getMinimo());
		System.out.println(candle.getMaximo());
		System.out.println(candle.getVolume());

		System.out.println(candle.toString());

	}


}
