package application.controle.negocio;

import java.util.List;


import javafx.collections.ObservableList;

public class SelecionarRegistroNegocio<T> {

	private List<SelecionarRegistroItem> lista;
	
	public List<SelecionarRegistroItem> getListaFiltrada(String filtro) {
		if (filtro.equals("")) {
			return lista;
		} else {
			lista.forEach(i -> {
				if (!i.getPrimeiraColuna().getValorColuna().contains(filtro)) {
					lista.remove(i);
				}
			});
			return lista;
		}
	}

	public void setLista(ObservableList<SelecionarRegistroItem> lista) {
		this.lista = lista;
	}
}
