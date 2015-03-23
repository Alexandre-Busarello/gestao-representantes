package application.controle.dao;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import application.modelo.Produto;

public class ProdutoDao extends DaoGenerico<Produto> {
	
	public ProdutoDao() {
		super(Produto.class);
	}
	
	public ObservableList<Produto> buscarTodosProdutos() { 	
		ObservableList<Produto> produtosDados = FXCollections.observableArrayList(); 
		List<?> produtos = (List<?>) this.getLista("Produto", "");
		produtos.forEach(p -> produtosDados.add((Produto) p));  
		return produtosDados;
	}
	
	public Produto buscarProdutoCodigo(String codigoProduto) {
		Criteria criteria = getSession().createCriteria(Produto.class).add(Restrictions.eq("codigoProduto", codigoProduto));
		return (Produto) criteria.uniqueResult();
	}
	
}
