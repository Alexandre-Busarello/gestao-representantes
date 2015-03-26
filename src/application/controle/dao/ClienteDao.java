package application.controle.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import application.interfaces.ISelecionavel;
import application.modelo.Cliente;
import application.modelo.Produto;

public class ClienteDao extends DaoGenerico<Cliente> implements ISelecionavel {
	
	public ClienteDao() {
		super(Cliente.class);
	}

	public Cliente obter(String codigoCliente) {
		Criteria criteria = getSession().createCriteria(Cliente.class).add(Restrictions.eq("id", codigoCliente));
		return (Cliente) criteria.uniqueResult();		
	}
	
	@Override
	public List<Cliente> getDados() {
		List<?> clientes_ = (List<?>) this.getLista("Cliente", "");
		List<Cliente> clientes = new ArrayList<Cliente>();
		clientes_.forEach(c -> clientes.add((Cliente) c));  
		return clientes;
	}
}
