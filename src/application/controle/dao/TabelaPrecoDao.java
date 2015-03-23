package application.controle.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import application.modelo.ItemTabelaPreco;
import application.modelo.TabelaPreco;
public class TabelaPrecoDao extends DaoGenerico<TabelaPreco> {
	
	public TabelaPrecoDao() {
		super(TabelaPreco.class);
	}	
	
	public ObservableList<ItemTabelaPreco> buscarItensTabelaPreco(int idTabelaPreco) {
		ObservableList<ItemTabelaPreco> retorno = FXCollections.observableArrayList();
		Criteria criteria = getSession().createCriteria(TabelaPreco.class).add(Restrictions.eq("id", idTabelaPreco));
		criteria.createAlias("itensTabelaPreco", "i");
		TabelaPreco tabelaPreco = (TabelaPreco) criteria.uniqueResult();		
		tabelaPreco.getItensTabelaPreco().forEach(i -> retorno.add(i));
		if (getSession().contains(tabelaPreco)) {
			getSession().evict(tabelaPreco);
		}			
		return retorno;
	}
	
	public ObservableList<ItemTabelaPreco> filtrarItensTabelaPreco(int idTabelaPreco, String filtro) {
		if (filtro.equals("")) {
			return buscarItensTabelaPreco(idTabelaPreco);
		}
		
		ObservableList<ItemTabelaPreco> retorno = FXCollections.observableArrayList();
		Criteria criteria = getSession().createCriteria(TabelaPreco.class);
		criteria.createAlias("itensTabelaPreco", "i");
		criteria.createAlias("i.produto", "p");
		criteria.add(Restrictions.eq("id", idTabelaPreco));
		criteria.add(Restrictions.ilike("p.codigoProduto", "%" + filtro + "%"));
		TabelaPreco tabelaPreco = (TabelaPreco) criteria.uniqueResult(); 
		tabelaPreco.getItensTabelaPreco().forEach(i -> retorno.add(i));
		if (getSession().contains(tabelaPreco)) {
			getSession().evict(tabelaPreco);
		}				
		return retorno;		
	}
	
}
