package application.controle.dao;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import application.interfaces.ISelecionavel;
import application.modelo.Cliente;
import application.modelo.Produto;

public class ProdutoDao extends DaoGenerico<Produto> implements ISelecionavel {
	
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

	@Override
	public List<?> getDados() {
		List<?> produtos_ = (List<?>) this.getLista("Produto", "");
		List<Produto> produtos = new ArrayList<Produto>();
		produtos_.forEach(c -> produtos.add((Produto) c));  
		return produtos;
	}
	
}
