package application.controle.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.criterion.Restrictions;

import application.modelo.Representada;
import application.util.CampoFiltro;

@SuppressWarnings("deprecation")
public class DaoGenerico<T> {

	@SuppressWarnings("rawtypes")
	private static final ThreadLocal session = new ThreadLocal();
	private Class<?> c;

	private static final SessionFactory sessionFactory =
		new AnnotationConfiguration().configure().buildSessionFactory();
	
	public DaoGenerico(Class<?> c) {
		this.c = c;
	}
	
	@SuppressWarnings("unchecked")
	public static Session getSession() {
		Session session = (Session) DaoGenerico.session.get();
		
		if (session == null) {
			session = sessionFactory.openSession();
			DaoGenerico.session.set(session);
		}
		
		return session;
	}

	
	// Métodos CRUD
	public void inserir(T obj) {
		getSession().save(obj);
	}

	public void atualizar(T obj) {
		getSession().update(obj);
	}

	public void deletar(T obj) {
		getSession().delete(obj);
	}
	
	public void inserirTransacionado(T obj) {
    	iniciarTransacao();
    	try {
    		inserir(obj);
    		commit();
    		finalizarSessao();
    	} catch (Exception e) {
    		rollback();
    		finalizarSessao();
    		throw e;
    	}
	}

	public void atualizarTransacionado(T obj) {
    	iniciarTransacao();
    	try {
    		atualizar(obj);
    		commit();
    		finalizarSessao();
    	} catch (Exception e) {
    		rollback();
    		finalizarSessao();
    		throw e;
    	}		
	}

	public void deletarTransacionado(T obj) {
    	iniciarTransacao();
    	try {
    		deletar(obj);
    		commit();
    		finalizarSessao();
    	} catch (Exception e) {
    		rollback();
    		finalizarSessao();
    		throw e;
    	}			
	}	
	
	
	// Métodos de Busca
	public List<?> getLista(String classe, String condicao) {
		String hql = "";
		if (condicao.equals("")) {
			hql = "FROM " + classe;
		} else {
			hql = "FROM " + classe + " WHERE " + condicao;
		}
		Query query = getSession().createQuery(hql);
		return query.list();		
	}
	
	@SuppressWarnings("unchecked")
	public T obter(int pk) {
		Criteria criteria = getSession().createCriteria(c).add(Restrictions.eq("id", pk));
		return (T) criteria.uniqueResult();
	}	
	
	// Retorna uma lista com os dados após aplicado o filtro em uma tela de manter cadastros
	public List<?> filtrarDadosManter(CampoFiltro campoFiltro, String valor) throws ParseException {
		Criteria criteria = getSession().createCriteria(campoFiltro.getEntidade());
		Class<?> classe = campoFiltro.getTipoCampo();
		if (classe == Representada.class) {
			criteria.createAlias(campoFiltro.getCampoLigacao(), campoFiltro.getAlias().replace(".", "").trim());
			criteria.add(Restrictions.ilike(campoFiltro.getAlias()+campoFiltro.getNomeCampo(), "%" + valor + "%"));
		} else if (classe == String.class) {
			criteria.add(Restrictions.ilike(campoFiltro.getAlias()+campoFiltro.getNomeCampo(), "%" + valor + "%"));
		} else if (classe == Double.class) {
			
		} else if (classe == Date.class) {
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");  
			criteria.add(Restrictions.eq(campoFiltro.getAlias()+campoFiltro.getNomeCampo(), format.parse(valor)));
		}
		return criteria.list();			
	}
	
	// Métodos para Controle de Transação
	public void iniciarTransacao() {
		getSession().beginTransaction();
	}

	public void commit() {
		getSession().getTransaction().commit();	
	}

	@SuppressWarnings("unchecked")
	public void rollback() {
		try {
			getSession().getTransaction().rollback();
		} catch( HibernateException e ) {
			System.out.println("Não foi possível fazer rollback da transação");
		}

		try {
			getSession().close();
		} catch( HibernateException e ) {
			System.out.println("Não foi possível fazer fechar a sessão");
		}

		DaoGenerico.session.set(null);
		
	}

	@SuppressWarnings("unchecked")
	public void finalizarSessao() {
		getSession().close();
		DaoGenerico.session.set(null);		
	}


}
