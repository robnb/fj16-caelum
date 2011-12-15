package br.com.caelum.argentum.ui;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.com.caelum.argentum.Negocio;

public class NegociosTableModel extends AbstractTableModel{

	private final List<Negocio> negocios;
	private final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

	
	public NegociosTableModel(List<Negocio> negocios) {
		this.negocios = negocios;
	}

	@Override
	public int getRowCount() {
		return negocios.size();
	}

	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Negocio n = negocios.get(rowIndex);
		switch(columnIndex){
		case 0:
			return n.getPreco();
		case 1:
			return n.getQuantidade();
		case 2:
			return dateFormat.format(n.getData().getTime());
		}
		return null;
	}
	
}
