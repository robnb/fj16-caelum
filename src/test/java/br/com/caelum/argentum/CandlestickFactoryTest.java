package br.com.caelum.argentum;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;


public class CandlestickFactoryTest {

	@Test
	public void testSimplesSequenciaDeNegocios(){
		Calendar hoje = Calendar.getInstance();
		
		Negocio negocio1 = new Negocio(40.5, 100, hoje);
		Negocio negocio2 = new Negocio(45.0, 100, hoje);
		Negocio negocio3 = new Negocio(39.8, 100, hoje);
		Negocio negocio4 = new Negocio(42.3, 100, hoje);
		
		List<Negocio> negocios = Arrays.asList(negocio1, negocio2, negocio3, negocio4);
		
		CandlestickFacktory fabrica = new CandlestickFacktory();
		Candlestick candle = fabrica.constroiCandlestickParaData(hoje, negocios);
		
		Assert.assertEquals(40.5, candle.getAbertura(), 0.00001);
		Assert.assertEquals(42.3, candle.getFechamento(), 0.00001);
		Assert.assertEquals(39.8, candle.getMinimo(), 0.00001);
		Assert.assertEquals(45.0, candle.getMaximo(), 0.00001);
		Assert.assertEquals(16760.0, candle.getVolume(), 0.00001);
	}
	
	@Test
	public void testSemNegocios(){
		Calendar hoje = Calendar.getInstance();
		
		List<Negocio> negocios = Arrays.asList();
		
		CandlestickFacktory fabrica = new CandlestickFacktory();
		Candlestick candle = fabrica.constroiCandlestickParaData(hoje, negocios);
		
		Assert.assertEquals(0.0, candle.getVolume(), 0.00001);
	}
	
	@Test
	public void testComApenasUmNegocio(){
		Calendar hoje = Calendar.getInstance();
		
		Negocio negocio1 = new Negocio(40.5, 100, hoje);
		
		List<Negocio> negocios = Arrays.asList(negocio1);
		
		CandlestickFacktory fabrica = new CandlestickFacktory();
		Candlestick candle = fabrica.constroiCandlestickParaData(hoje, negocios);
		
		Assert.assertEquals(40.5, candle.getAbertura(), 0.00001);
		Assert.assertEquals(40.5, candle.getFechamento(), 0.00001);
		Assert.assertEquals(40.5, candle.getMinimo(), 0.00001);
		Assert.assertEquals(40.5, candle.getMaximo(), 0.00001);
		Assert.assertEquals(4050.0, candle.getVolume(), 0.00001);
	}
	
	@Test
	public void testComparaMesmoDiaCalendar(){
		CandlestickFacktory fabrica = new CandlestickFacktory();
		Calendar data1 = new GregorianCalendar(2008, 12, 25, 8, 30);
		Calendar data2 = new GregorianCalendar(2008, 12, 25, 10, 30);
		
		Assert.assertTrue(fabrica.isMesmoDia(data1, data2));
	}
	
}
