package application.controle.negocio;

import application.controle.dao.NumeroDao;
import application.modelo.Numero;

public class NumeroNegocio {

	private Numero numero = new Numero();
	
	public int getUltimoNumero() {
		return numero.getUltimoNumero();
	}
	
	public int obterNumero(String documento) {
		NumeroDao dao = new NumeroDao();
		Numero numero = dao.obterNumero(documento);
		if (numero == null) {
			numero = new Numero();
			numero.setDocumento(documento);
			numero.setUltimoNumero(1);
			dao.inserir(numero);
			return 1;
		} else {
			int retorno = numero.getUltimoNumero() + 1;
			numero.setUltimoNumero(retorno);
			dao.atualizar(numero);
			return retorno;
		}
	}
	
}
