package application.controle.negocio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;

import application.controle.dao.ClienteDao;
import application.controle.dao.PedidoDao;
import application.enumeradores.StatusPedido;
import application.modelo.Cliente;
import application.modelo.ItemPedido;
import application.modelo.Pedido;
import application.modelo.Representada;
import application.modelo.TabelaPreco;

public class PedidoNegocio {
	
	private Pedido pedido;
	
	protected Pedido getPedido() {
		return pedido;
	}
	
	public PedidoNegocio(boolean iniciarPedido) {
		pedido = new Pedido();
		pedido.setStatus(StatusPedido.NENHUM);
		if (iniciarPedido) {
			iniciarPedido();
		}
	}
	
	public PedidoNegocio(Pedido pedido) {
		this.pedido = pedido;
	}
	
	public static PedidoNegocio ObterPedidoNegocio(int id) {
		PedidoDao dao = new PedidoDao();
		PedidoNegocio pedidoNegocio = new PedidoNegocio(dao.obter(id));
		return pedidoNegocio;
	}	
	
	public void iniciarPedido() {
		if (pedido.getStatus() != StatusPedido.NENHUM) {
			//TODO - exceção, pedido ja iniciado
		}			
		pedido.setNumeroPedido(new NumeroNegocio().obterNumero("PEDIDO"));
		pedido.setStatus(StatusPedido.ABERTO);
		//TODO - atribuir usuário logado
	}
	
	public void setRepresentada(Representada rep) {
		pedido.setRepresentada(rep);
	}
	
	public void setRepresentada(String codigoRepresentada) {
		
	}	
	
	public void setCliente(Cliente cliente) {
		pedido.setCliente(cliente);
	}	
	
	public void setCliente(String codigoCliente) {
		ClienteDao dao = new ClienteDao();
		pedido.setCliente(dao.obter(codigoCliente));
	}	

	public void setTabelaPreco(TabelaPreco tabelaPreco) {
		pedido.setTabelaPreco(tabelaPreco);
	}
	
	public void setTabelaPreco(String codigoTabelaPreco) {
		
	}	

	public void setVendedor(String codigoVendedor) {
		
	}			
	
	protected void adicionarItem(ItemPedido item) {
		pedido.getItensPedido().add(item);
	}
	
	private void persistir() {
		PedidoDao dao = new PedidoDao();
		if (pedido.getId() < 1) {
			dao.inserir(pedido);	
		} else {
			dao.atualizar(pedido);
		}
	}
	
	public void finalizarDigitacao() {
		PedidoDao dao = new PedidoDao();
		dao.iniciarTransacao();	
		try {
			persistir();
			
			// Calcula todos os itens e finaliza todos
			for (ItemPedido itemPedido : pedido.getItensPedido()) {
				ItemPedidoNegocio itemPedidoNegocio = ItemPedidoNegocio.ObterItemPedidoNegocio(itemPedido.getId());
				itemPedidoNegocio.calcularItem();
				itemPedidoNegocio.finalizarItem();
			}		
			dao.commit();
		} catch (Exception e) {
			dao.rollback();
			throw e;
		}					
	}
	
	public void fecharPedido() {
		pedido.setDataEmissao(new Date());
		pedido.setStatus(StatusPedido.FECHADO);
		
		PedidoDao dao = new PedidoDao();
		dao.iniciarTransacao();	
		try {		
			persistir();
			dao.commit();
		} catch (Exception e) {
			dao.rollback();
			throw e;
		}					
	}
	
	public void reabrirPedido() {
		pedido.setDataEmissao(null);
		pedido.setStatus(StatusPedido.ABERTO);
		PedidoDao dao = new PedidoDao();
		dao.iniciarTransacao();	
		try {				
			persistir();
			dao.commit();
		} catch (Exception e) {
			dao.rollback();
			throw e;
		}			
	}
	
	public List<ItemPedidoNegocio> obterItens() {
		List<ItemPedidoNegocio> lista = new ArrayList<ItemPedidoNegocio>();
		for (ItemPedido itemPedido : pedido.getItensPedido()) {
			lista.add(ItemPedidoNegocio.ObterItemPedidoNegocio(itemPedido.getId()));
		}
		return lista;
	}
	
	public void recalcularTodosItens() {
		List<ItemPedidoNegocio> lista = obterItens();
		for (ItemPedidoNegocio itemPedidoNegocio : lista) {
			itemPedidoNegocio.calcularItem();
		}
	}
	
}
