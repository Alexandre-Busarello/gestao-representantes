package application.controle.negocio;

import java.util.ArrayList;

public class SelecionarRegistroItem {

	private int id;
	private ArrayList<RegistroItem> itens = new ArrayList<RegistroItem>();
	
	public SelecionarRegistroItem addColuna(String nome, String valor) {
		RegistroItem item = new RegistroItem(nome, valor);
		itens.add(item);
		return this;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public RegistroItem getColuna(int indice) {
		return itens.get(indice);
	}
	
	public RegistroItem getColunaCodigo() {	
		for (RegistroItem registroItem : itens) {
			if (registroItem.getNomeColuna().equals("Código")) {
				return registroItem;
			}
		}
		return null;
	}	
	
	public SelecionarRegistroItem(int id) {
		this.id = id;
	}
	
	
}
