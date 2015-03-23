package application.visao.fxml.cadastros;

import application.TelasCadastros;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class CadastroControle {
	// Gerais
	@FXML
	private Button btManterRepresentantes; 
	@FXML
	private Button btManterRepresentadas; 
	@FXML
	private Button btManterCondicoesPagamento; 
	
	// Clientes
	@FXML
	// Manter
	private Button btManterClientes; 
	@FXML
	// Pedidos de cliente
	private Button btPedidosCliente; 
	@FXML
	// Consulta avançada
	private Button btConsultaCliente; 	
	
	// Produtos
	@FXML
	// Manter
	private Button btManterProdutos; 
	@FXML
	// Tabelas de preço
	private Button btTabelasPreco; 
	@FXML
	// Produtos mais vendidos
	private Button btProdutosMaisVendidos; 	
	
	// Vendedores
	@FXML
	// Manter
	private Button btManterVendedores; 
	@FXML
	// Tabelas de preço
	private Button btManterComissoes; 
	@FXML
	// Produtos mais vendidos
	private Button btApuracaoComissoes; 
	
	private TelasCadastros telasCadastros;
	
	public void setTelasCadastros(TelasCadastros telasCadastros) {
		this.telasCadastros = telasCadastros;
	}
	
	public void setBotaoSelecionadoGerais(Button bt) {
	}
	
	// Gerais
	public void pressionouManterRepresentantes() throws Exception {
		setBotaoSelecionadoGerais(btManterRepresentantes);
		telasCadastros.exibeManterRepresentantes();
	}
	
	public void pressionouManterRepresentadas() throws Exception {
		setBotaoSelecionadoGerais(btManterRepresentadas);
		telasCadastros.exibeManterRepresentadas();
	}	
	
	public void pressionouManterCondicoesPagamento() throws Exception {
		setBotaoSelecionadoGerais(btManterCondicoesPagamento);
	}		
	
    // Clientes
	public void pressionouManterCliente() throws Exception {
		setBotaoSelecionadoGerais(btManterClientes);
		telasCadastros.exibeManterClientes();
	}
	
	public void pressionouPedidosCliente() throws Exception {
		setBotaoSelecionadoGerais(btPedidosCliente);
	}	
	
	public void pressionouConsultaCliente() throws Exception {
		setBotaoSelecionadoGerais(btConsultaCliente);
	}		
	
	// Produtos
	public void pressionouManterProdutos() throws Exception {
		setBotaoSelecionadoGerais(btManterProdutos);
		telasCadastros.exibeManterProdutos();
	}
	
	public void pressionouTabelasPreco() throws Exception {
		setBotaoSelecionadoGerais(btTabelasPreco);
		telasCadastros.exibeManterTabelasPreco();
	}	
	
	public void pressionouProdutosMaisVendidos() throws Exception {
		setBotaoSelecionadoGerais(btProdutosMaisVendidos);
	}	
	
	// Vendedores
	public void pressionouManterVendedores() throws Exception {
		setBotaoSelecionadoGerais(btManterVendedores);
	}
	
	public void pressionouManterComissoes() throws Exception {
		setBotaoSelecionadoGerais(btManterComissoes);
	}	
	
	public void pressionouApuracaoComissoes() throws Exception {
		setBotaoSelecionadoGerais(btApuracaoComissoes);
	}		
		
}
