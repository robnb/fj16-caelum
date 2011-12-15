package br.com.caelum.argentum.ui;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Collections;
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
	
	public List<Negocio> escolher(){
		try {
			JFileChooser chooser = new JFileChooser("/home/lab2335/Desktop");
			chooser.setFileFilter(new FileNameExtensionFilter("Apenas XML", "xml"));
			int retorno = chooser.showOpenDialog(null);

			if(retorno ==JFileChooser.APPROVE_OPTION){
				FileReader reader = new FileReader(chooser.getSelectedFile());
				List<Negocio> negocios = new LeitorXML().carrega(reader);
			}
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Nada foi selecionado.");
		}
		return Collections.emptyList();
	}
}
