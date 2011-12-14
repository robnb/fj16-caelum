package br.com.caelum.argentum.ui;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import br.com.caelum.argentum.Negocio;
import br.com.caelum.argentum.reader.LeitorXML;


public class EscolheXML {

	public static void main(String[] args) {

		new EscolheXML().escolher();
	
	}
	
	public void escolher(){
		try {
			JFileChooser chooser = new JFileChooser(".");
			chooser.setFileFilter(new FileNameExtensionFilter("Apenas XML", "xml"));
			int retorno = chooser.showOpenDialog(null);

			if(retorno ==JFileChooser.APPROVE_OPTION){
				FileReader reader = new FileReader(chooser.getSelectedFile());
				List<Negocio> negocios = new LeitorXML().carrega(reader);

				Negocio primeiroNegocio = negocios.get(0);
				String msg = "Primeiro negócio do dia: " + primeiroNegocio.getPreco();
				JOptionPane.showMessageDialog(null, msg);
			}
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Nada foi selecionado.");
		}
	}
}
