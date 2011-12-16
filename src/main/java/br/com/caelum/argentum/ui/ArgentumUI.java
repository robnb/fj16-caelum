package br.com.caelum.argentum.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import br.com.caelum.argentum.Candle;
import br.com.caelum.argentum.CandlestickFacktory;
import br.com.caelum.argentum.Negocio;
import br.com.caelum.argentum.SerieTemporal;
import br.com.caelum.argentum.grafico.GeradorDeGrafico;
import br.com.caelum.argentum.indicadores.IndicadorFechamento;
import br.com.caelum.argentum.indicadores.MediaMovelSimples;

public class ArgentumUI {

	private JFrame janela;
	private JPanel painelPrincipal;
	private JTable tabela;
	private JPanel painelBotoes;
	private JTabbedPane abas;

	public static void main(String[] args) {

		new ArgentumUI().montaTela();
	}

	public void montaTela(){
		montaJanela();
		montaPainelPrincipal();
		montaTitulo();
		montaAbas();
		montaTabelaComScroll();
		montaPainelBotoes();
		montaBotaoCarregar();
		montaBotaoSair();
		mostraJanela();
	}

	private void montaAbas(){
		abas = new JTabbedPane();
		abas.addTab("Tabela de Negócios", null);
		abas.addTab("Gráfico", null);
		painelPrincipal.add(abas);
	}
	
	private void montaPainelBotoes() {
		painelBotoes = new JPanel(new GridLayout());
		painelPrincipal.add(painelBotoes, BorderLayout.SOUTH);
	}

	private void montaTitulo() {
		
		JLabel titulo = new JLabel("Lista de Negócios");
		titulo.setFont(new Font("Verdana", Font.BOLD, 25));
		titulo.setForeground(new Color(50, 50, 100));
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		painelPrincipal.add(titulo, BorderLayout.NORTH);
		
	}

	private void mostraJanela() {

		janela.pack();
		janela.setSize(540,540);
		janela.setVisible(true);

	}

	private void montaBotaoSair() {

		JButton botaoSair = new JButton("Sair");
		botaoSair.addActionListener(new EventoSair());
		painelBotoes.add(botaoSair);

	}

	private void montaBotaoCarregar() {

		JButton botaoCarregar = new JButton("Carregar XML");
		botaoCarregar.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
//				ANTES
//				List<Negocio> negocios = new EscolheXML().escolher();
//				NegociosTableModel ntm = new NegociosTableModel(negocios);
//				tabela.setModel(ntm);
//				DEPOIS
				carregarDados();
			}
		});
		painelBotoes.add(botaoCarregar);

	}

	private void montaPainelPrincipal() {

		painelPrincipal = new JPanel();
		painelPrincipal.setLayout(new BorderLayout());
		janela.add(painelPrincipal);

	}

	private void montaJanela() {

		janela = new JFrame("Argentum");
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	private void montaTabelaComScroll(){

		tabela = new JTable();
		tabela.setBorder(new LineBorder(Color.magenta));
		tabela.setGridColor(Color.red);
		tabela.setShowGrid(true);

		JScrollPane scroll = new JScrollPane();
		scroll.getViewport().setBorder(null);
		scroll.getViewport().add(tabela);
		scroll.setSize(450, 450);

//		retirado
//		painelPrincipal.add(scroll, BorderLayout.CENTER);
//		adicionado
		abas.setComponentAt(0, scroll);
	}
	
	private void carregarDados(){
		List<Negocio> negocios = new EscolheXML().escolher();
		
		//atualizaTabela
		NegociosTableModel ntm = new NegociosTableModel(negocios);
		this.tabela.setModel(ntm);
		
		//geraSerieTemporal
		CandlestickFacktory candlestickFacktory = new CandlestickFacktory();
		List<Candle> candles = candlestickFacktory.constroiCandles(negocios);
		SerieTemporal serie = new SerieTemporal(candles);
		
		//mostraGrafico
		GeradorDeGrafico geradorDeGrafico = new GeradorDeGrafico(serie, 2, serie.getTotal()-1);
		geradorDeGrafico.criaGrafico("Média Móvel Simples");
		geradorDeGrafico.plotaIndicador(new MediaMovelSimples(new IndicadorFechamento()));
		JPanel grafico = geradorDeGrafico.getPanel();
		this.abas.setComponentAt(1, grafico);
	}
}
