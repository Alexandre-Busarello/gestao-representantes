package application.controle.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import application.modelo.Numero;

public class NumeroDao extends DaoGenerico<Numero> {
	
	public NumeroDao() {
		super(Numero.class);
	}
	
	public Numero obterNumero(String documento) {
		Criteria criteria = getSession().createCriteria(Numero.class).add(Restrictions.eq("documento", documento));
		return (Numero) criteria.uniqueResult();
	}	
}
