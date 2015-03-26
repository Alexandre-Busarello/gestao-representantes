package application.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "numeros", uniqueConstraints = { @UniqueConstraint(columnNames = "id") })
public class Numero {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private int id;
	
	@Column(name = "documento", unique = false, nullable = false)	
	private String documento;
	
	@Column(name = "ultimonumero", unique = false, nullable = false)	
	private int ultimoNumero;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public int getUltimoNumero() {
		return ultimoNumero;
	}

	public void setUltimoNumero(int ultimoNumero) {
		this.ultimoNumero = ultimoNumero;
	}

}
