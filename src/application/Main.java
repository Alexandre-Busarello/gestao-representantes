package application;
	


import application.controle.dao.ClienteDao;
import application.controle.dao.DaoGenerico;
import application.controle.dao.ProdutoDao;
import application.controle.negocio.ItemPedidoNegocio;
import application.controle.negocio.PedidoNegocio;
import application.modelo.Cliente;
import application.modelo.Representada;
import application.modelo.TabelaPreco;
import application.visao.fxml.TelaPaiControle;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

	
public class Main extends Application {
	
	private Stage palcoInicial;
	private BorderPane telaPrincipal;	
	private TelaPaiControle telaPaiControle;
    
	
	@Override
	public void start(Stage primaryStage) {			
		try {
			this.palcoInicial = primaryStage;
			this.palcoInicial.setTitle("Principal");
			this.palcoInicial.setMaximized(true);
			
			exibeTelaPai();
			TelasCadastros telasCadastros = new TelasCadastros(palcoInicial, telaPrincipal);
			telasCadastros.exibeCadastros();
			
			telaPaiControle.setTelaCadastros(telasCadastros);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
    public void exibeTelaPai() {
        try {
        	this.telaPrincipal = new BorderPane();
    		FXMLLoader loader = new FXMLLoader();
    		loader.setLocation(Main.class.getResource("visao/fxml/telaPai.fxml"));
    		telaPrincipal = (BorderPane) loader.load();
    		
    		telaPaiControle = (TelaPaiControle) loader.getController();

            // Mostra a scene (cena) contendo o root layout.
            Scene scene = new Scene(telaPrincipal);
            palcoInicial.setScene(scene);
            palcoInicial.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }	
    
    
    public void limparPrincipalCenter() {
    	telaPrincipal.setCenter(new Pane());
    }
	
	public static void main(String[] args) {  		
		//PedidoNegocio pedidoNegocio = new PedidoNegocio(true);
		//pedidoNegocio.setCliente(new ClienteDao().obter(1));
		//pedidoNegocio.setRepresentada(new DaoGenerico<Representada>(Representada.class).obter(1));
		//pedidoNegocio.setTabelaPreco(new DaoGenerico<TabelaPreco>(TabelaPreco.class).obter(1));
		//pedidoNegocio.finalizarDigitacao();
		//ItemPedidoNegocio item = new ItemPedidoNegocio(PedidoNegocio.ObterPedidoNegocio(18));
		//item.setProduto(new ProdutoDao().obter(1));
		//item.setQuantidadePedida(7);
		//item.setUnidadeMedida("UN");
		//item.calcularItem();
		//item.finalizarItem();
		launch(args);
	}
}
