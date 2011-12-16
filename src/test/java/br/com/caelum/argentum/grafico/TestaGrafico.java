package br.com.caelum.argentum.grafico;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.JFrame;

import br.com.caelum.argentum.Candle;
import br.com.caelum.argentum.SerieTemporal;
import br.com.caelum.argentum.indicadores.IndicadorFechamento;
import br.com.caelum.argentum.indicadores.MediaMovelSimples;

public class TestaGrafico {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		SerieTemporal serie = criaSerie(1,2,3,4,5);
		GeradorDeGrafico g = new GeradorDeGrafico(serie, 3, 32);
		g.criaGrafico("Media movel simples do fechamento das acoes");
		g.plotaIndicador(new MediaMovelSimples(new IndicadorFechamento()));
		g.salvar(new FileOutputStream("grafico.png"));
		
		JFrame frame = new JFrame("Minha Janela");
		
		frame.add(g.getPanel());
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	private static SerieTemporal criaSerie(double... valores){
		List<Candle> candles = new ArrayList<Candle>();
		for(double d : valores){
			candles.add(new Candle(d, d, d, d, 1000, Calendar.getInstance()));
		}
		return new SerieTemporal(candles);
	}

}
