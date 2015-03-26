package application.controle.negocio;

import application.controle.dao.DaoGenerico;
import application.controle.dao.ItemPedidoDao;
import application.controle.dao.ProdutoDao;
import application.enumeradores.StatusPedido;
import application.modelo.ItemPedido;
import application.modelo.Pedido;
import application.modelo.Produto;

public class ItemPedidoNegocio {

	private ItemPedido itemPedido;
	private PedidoNegocio pedidoNegocio;

	protected Pedido getPedido() {
		return itemPedido.getPedido();
	}
	
	public ItemPedidoNegocio(PedidoNegocio pedidoNegocio) {
		if (pedidoNegocio.getPedido().getStatus() != StatusPedido.ABERTO) {
			//TODO - o pedido ja esta fechado e não pode mais receber itens
		}
				
		
		itemPedido = new ItemPedido();
		this.pedidoNegocio = pedidoNegocio;
		itemPedido.setPedido(pedidoNegocio.getPedido());
		pedidoNegocio.adicionarItem(itemPedido);
	}
	
	public ItemPedidoNegocio(ItemPedido itemPedido) {
		this.itemPedido = itemPedido;
	}
	
	private void persistir() {
		ItemPedidoDao dao = new ItemPedidoDao();
		if (DaoGenerico.getSession().getTransaction().isInitiator()) {
			if (itemPedido.getId() < 1) {
				dao.inserir(itemPedido);	
			} else {
				dao.atualizar(itemPedido);
			}
		} else {
			if (itemPedido.getId() < 1) {
				dao.inserirTransacionado(itemPedido);	
			} else {
				dao.atualizarTransacionado(itemPedido);
			}
		}
	}
	
	public static ItemPedidoNegocio ObterItemPedidoNegocio(int idItem) {
		ItemPedidoDao dao = new ItemPedidoDao();
		ItemPedidoNegocio itemPedidoNegocio = new ItemPedidoNegocio(dao.obter(idItem));	
		itemPedidoNegocio.pedidoNegocio = PedidoNegocio.ObterPedidoNegocio(itemPedidoNegocio.getPedido().getId());
		return itemPedidoNegocio;
	}
	
	public PedidoNegocio getPedidoNegocio() {
		return pedidoNegocio;
	}
	
	public void calcularItem() {
		itemPedido.getPedido().getTabelaPreco().getItensTabelaPreco().forEach(i -> { 
			if (i.getProduto().getId() == itemPedido.getProduto().getId()) {
				itemPedido.setValorUnitario(i.getPrecoUnitario());
			}
		});
		itemPedido.setPercentualComissaoRep(itemPedido.getProduto().getPercentualComissaoRep());
		itemPedido.setPercentualComissaoVend(itemPedido.getProduto().getPercentualComissaoVend());
		itemPedido.setPercentualIpi(itemPedido.getProduto().getPercentualIpi());
		itemPedido.setValorFinal(itemPedido.getValorUnitario() * itemPedido.getQuantidadePedida());
	}
	
	public void finalizarItem() {
		persistir();
	}
	
	public Produto getProduto() {
		return itemPedido.getProduto();
	}

	public void setProduto(Produto produto) {
		this.itemPedido.setProduto(produto);
	}	
	
	public void setProduto(String produto) {
		ProdutoDao prodDao = new ProdutoDao();
		this.itemPedido.setProduto(prodDao.buscarProdutoCodigo(produto));
	}		
	
	public double getQuantidadePedida() {
		return itemPedido.getQuantidadePedida();
	}

	public void setQuantidadePedida(double quantidadePedida) {
		this.itemPedido.setQuantidadePedida(quantidadePedida);
	}

	public String getUnidadeMedida() {
		return itemPedido.getUnidadeMedida();
	}

	public void setUnidadeMedida(String unidadeMedida) {
		this.itemPedido.setUnidadeMedida(unidadeMedida);
	}

	public double getPercentualIpi() {
		return itemPedido.getPercentualIpi();
	}

	public void setPercentualIpi(double percentualIpi) {
		this.itemPedido.setPercentualIpi(percentualIpi);
	}

	public double getPercentualComissaoRep() {
		return itemPedido.getPercentualComissaoRep();
	}

	public void setPercentualComissaoRep(double percentualComissaoRep) {
		this.itemPedido.setPercentualComissaoRep(percentualComissaoRep);
	}

	public double getPercentualComissaoVend() {
		return itemPedido.getPercentualComissaoVend();
	}

	public void setPercentualComissaoVend(double percentualComissaoVend) {
		this.itemPedido.setPercentualComissaoVend(percentualComissaoVend);
	}
		
	public double getValorUnitario() {
		return itemPedido.getValorUnitario();
	}

	public void setValorUnitario(double valorUnitario) {
		this.itemPedido.setValorUnitario(valorUnitario);
	}

}
