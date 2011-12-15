package br.com.caelum.argentum.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventoCarregar implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		new EscolheXML().escolher();
	}

}
