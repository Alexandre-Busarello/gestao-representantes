package application.controle.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import application.modelo.Cliente;

public class ClienteDao extends DaoGenerico<Cliente> {
	
	public ClienteDao() {
		super(Cliente.class);
	}
}
