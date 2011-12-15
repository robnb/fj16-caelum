package br.com.caelum.argentum.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ArgentumUI {
	
	private JFrame janela;
	private JPanel painelPrincipal;
	
	public static void main(String[] args) {
		
		new ArgentumUI().montaTela();
	}

	public void montaTela(){
		montaJanela();
		montaPainelPrincipal();
		montaBotaoCarregar();
		montaBotaoSair();
		mostraJanela();
	}

	private void mostraJanela() {
		
		janela.pack();
		janela.setSize(540,540);
		janela.setVisible(true);
		
	}

	private void montaBotaoSair() {
		
		JButton botaoSair = new JButton("Sair");
		botaoSair.addActionListener(new EventoSair());
		painelPrincipal.add(botaoSair);
		
	}

	private void montaBotaoCarregar() {
		
		JButton botaoCarregar = new JButton("Carregar XML");
		botaoCarregar.addActionListener(new EventoCarregar());
		painelPrincipal.add(botaoCarregar);
		
	}

	private void montaPainelPrincipal() {
		
		painelPrincipal = new JPanel();
		janela.add(painelPrincipal);
		
	}

	private void montaJanela() {
		
		janela = new JFrame("Argentum");
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
}
