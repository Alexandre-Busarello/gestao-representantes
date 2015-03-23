package application.controle.negocio;

import java.util.ArrayList;

public class SelecionarRegistroItem {

	private int id;
	private ArrayList<RegistroItem> itens;
	
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

	public RegistroItem getPrimeiraColuna() {
		return itens.get(0);
	}
	
	public SelecionarRegistroItem(int id) {
		this.id = id;
	}
	
	
}
