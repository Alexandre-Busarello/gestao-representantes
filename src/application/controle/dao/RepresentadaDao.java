package application.controle.dao;

import java.util.List;

import application.modelo.Representada;

public class RepresentadaDao extends DaoGenerico<Representada> {

	public RepresentadaDao() {
		super(Representada.class);
	}
	
	@Override
	public Representada obter(int pk) {
		return super.obter(pk);
	}
	
	@SuppressWarnings("unchecked")
	public List<Representada> getLista() {
		List<Representada> representadas = (List<Representada>) getLista("Representada", "");
		return representadas;
	}
	
}
