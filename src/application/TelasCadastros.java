package application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import application.controle.negocio.RepresentadaNegocio;
import application.modelo.Cliente;
import application.modelo.Produto;
import application.modelo.Representada;
import application.modelo.Representante;
import application.modelo.TabelaPreco;
import application.visao.fxml.cadastros.CadastroControle;
import application.visao.fxml.cadastros.IncluirRepresentantesControle;
import application.visao.fxml.cadastros.ManterRepresentantesControle;
import application.visao.fxml.cadastros.clientes.IncluirClientesControle;
import application.visao.fxml.cadastros.clientes.ManterClientesControle;
import application.visao.fxml.cadastros.produtos.IncluirProdutoControle;
import application.visao.fxml.cadastros.produtos.IncluirTabelaPrecoControle;
import application.visao.fxml.cadastros.produtos.ManterProdutoControle;
import application.visao.fxml.cadastros.produtos.ManterTabelaPrecoControle;
import application.visao.fxml.cadastros.representadas.IncluirRepresentadasControle;
import application.visao.fxml.cadastros.representadas.ManterRepresentadasControle;

public class TelasCadastros {
	
	private Stage palcoInicial;
	private BorderPane telaPrincipal;
	private Node nodeAnterior;
	
    public Stage getStage() {
    	return palcoInicial;
    }	
	
	public TelasCadastros(Stage palcoInicial, BorderPane telaPrincipal) {
		this.telaPrincipal = telaPrincipal;
		this.palcoInicial = palcoInicial;
	}
	
    public void exibeCadastros() {
        try {
        	//JavaFXControle.getInstance().posicionarTelaXemBorderPane(Accordion.class, "visao/fxml/cadastros/cadastros.fxml", m, Posicao.LEFT);
        	
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("visao/fxml/cadastros/cadastros.fxml"));
            Accordion telaCadastros = (Accordion) loader.load();
            
            CadastroControle controle = (CadastroControle) loader.getController();
            controle.setTelasCadastros(this);
            
            // Define o cadastros dentro do root layout.
            telaPrincipal.setLeft(telaCadastros); 
        } catch (Exception e) {
            e.printStackTrace();
        }    	
    }
    
    public void exibeVendas() {
        try {
        	//JavaFXControle.getInstance().posicionarTelaXemBorderPane(Accordion.class, "visao/fxml/cadastros/cadastros.fxml", m, Posicao.LEFT);
        	
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("visao/fxml/cadastros/vendas.fxml"));
            Accordion telaVendas = (Accordion) loader.load();
            
            CadastroControle controle = (CadastroControle) loader.getController();
            controle.setTelasCadastros(this);
            
            // Define o cadastros dentro do root layout.
            telaPrincipal.setLeft(telaVendas); 
        } catch (Exception e) {
            e.printStackTrace();
        }    	
    }    
   
    public void exibeManterRepresentantes() {
        try {
        	//JavaFXControle.getInstance().posicionarTelaXemBorderPane(Accordion.class, "visao/fxml/cadastros/cadastros.fxml", m, Posicao.LEFT);
        	
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ManterRepresentantesControle.class.getResource("manterRepresentantes.fxml"));
            BorderPane telaManterEmpresas = (BorderPane) loader.load();

            ManterRepresentantesControle controle = (ManterRepresentantesControle) loader.getController();
            controle.setTelasCadastros(this);
            
            nodeAnterior = telaPrincipal.getCenter();
            // Define o cadastros dentro do root layout.
            telaPrincipal.setCenter(telaManterEmpresas); 
        } catch (Exception e) {
            e.printStackTrace();
        }    	
    }    
    
    public void exibeIncluirRepresentantes(Representante rep) {
        try {
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(IncluirRepresentantesControle.class.getResource("incluirRepresentantes.fxml"));
	        BorderPane telaIncluirRepresentantes = (BorderPane) loader.load();
	
	        IncluirRepresentantesControle controle = (IncluirRepresentantesControle) loader.getController();
	        controle.setTelasCadastros(this);
	        if (rep != null) {
	        	controle.setRepresentante(rep);
	        }
	        
	        nodeAnterior = telaPrincipal.getCenter(); 
	        telaPrincipal.setCenter(telaIncluirRepresentantes);    	
        } catch (Exception e) {
            e.printStackTrace();
        }    	
    }
    
    public void exibeManterRepresentadas() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ManterRepresentadasControle.class.getResource("manterRepresentadas.fxml"));
            BorderPane telaManterRepresentadas = (BorderPane) loader.load();

            ManterRepresentadasControle controle = (ManterRepresentadasControle) loader.getController();
            controle.setTelasCadastros(this);
            
            nodeAnterior = telaPrincipal.getCenter();
            // Define o cadastros dentro do root layout.
            telaPrincipal.setCenter(telaManterRepresentadas); 
        } catch (Exception e) {
            e.printStackTrace();
        }    	
    }    
    
    public void exibeIncluirRepresentadas(RepresentadaNegocio rep) {
        try {
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(IncluirRepresentadasControle.class.getResource("incluirRepresentadas.fxml"));
	        BorderPane telaIncluirRepresentadas = (BorderPane) loader.load();
	
	        IncluirRepresentadasControle controle = (IncluirRepresentadasControle) loader.getController();
	        controle.setTelasCadastros(this);
	        if (rep != null) {
	        	controle.setRepresentada(rep);
	        }
	        
	        nodeAnterior = telaPrincipal.getCenter(); 
	        telaPrincipal.setCenter(telaIncluirRepresentadas);    	
        } catch (Exception e) {
            e.printStackTrace();
        }    	
    }    
    
    public void exibeManterClientes() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ManterClientesControle.class.getResource("manterClientes.fxml"));
            BorderPane telaManterClientes = (BorderPane) loader.load();

            ManterClientesControle controle = (ManterClientesControle) loader.getController();
            controle.setTelasCadastros(this);
            
            nodeAnterior = telaPrincipal.getCenter();
            // Define o cadastros dentro do root layout.
            telaPrincipal.setCenter(telaManterClientes); 
        } catch (Exception e) {
            e.printStackTrace();
        }    	
    }  
    
    public void exibeIncluirClientes(Cliente cli) {
        try {
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(IncluirClientesControle.class.getResource("incluirClientes.fxml"));
	        BorderPane telaIncluirClientes = (BorderPane) loader.load();
	
	        IncluirClientesControle controle = (IncluirClientesControle) loader.getController();
	        controle.setTelasCadastros(this);
	        if (cli != null) {
	        	controle.setCliente(cli);
	        }
	        
	        nodeAnterior = telaPrincipal.getCenter(); 
	        telaPrincipal.setCenter(telaIncluirClientes);    	
        } catch (Exception e) {
            e.printStackTrace();
        }    	
    }      
    
    public void exibeManterProdutos() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ManterProdutoControle.class.getResource("manterProdutos.fxml"));
            BorderPane telaManterProdutos = (BorderPane) loader.load();

            ManterProdutoControle controle = (ManterProdutoControle) loader.getController();
            controle.setTelasCadastros(this);
            
            nodeAnterior = telaPrincipal.getCenter();
            // Define o cadastros dentro do root layout.
            telaPrincipal.setCenter(telaManterProdutos); 
        } catch (Exception e) {
            e.printStackTrace();
        }    	
    }   
    
    public void exibeIncluirProdutos(Produto pro) {
        try {
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(IncluirProdutoControle.class.getResource("incluirProdutos.fxml"));
	        BorderPane telaIncluirProdutos = (BorderPane) loader.load();
	
	        IncluirProdutoControle controle = (IncluirProdutoControle) loader.getController();
	        controle.setTelasCadastros(this);
	        if (pro != null) {
	        	controle.setProduto(pro);
	        }
	        
	        nodeAnterior = telaPrincipal.getCenter(); 
	        telaPrincipal.setCenter(telaIncluirProdutos);    	
        } catch (Exception e) {
            e.printStackTrace();
        }    	
    }      
    
    public void exibeManterTabelasPreco() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ManterTabelaPrecoControle.class.getResource("manterTabelasPreco.fxml"));
            BorderPane telaManterTabelasPreco = (BorderPane) loader.load();

            ManterTabelaPrecoControle controle = (ManterTabelaPrecoControle) loader.getController();
            controle.setTelasCadastros(this);
            
            nodeAnterior = telaPrincipal.getCenter();
            // Define o cadastros dentro do root layout.
            telaPrincipal.setCenter(telaManterTabelasPreco); 
        } catch (Exception e) {
            e.printStackTrace();
        }    	
    }       
    
    public void exibeIncluirTabelasPreco(TabelaPreco tabelaPreco) {
        try {
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(IncluirTabelaPrecoControle.class.getResource("incluirTabelasPreco.fxml"));
	        BorderPane telaIncluirTabelasPreco = (BorderPane) loader.load();
	
	        IncluirTabelaPrecoControle controle = (IncluirTabelaPrecoControle) loader.getController();
	        controle.setTelasCadastros(this);
	        if (tabelaPreco != null) {
	        	controle.setTabelaPreco(tabelaPreco);
	        }
	        
	        nodeAnterior = telaPrincipal.getCenter(); 
	        telaPrincipal.setCenter(telaIncluirTabelasPreco);    	
        } catch (Exception e) {
            e.printStackTrace();
        }    	
    }       
        
    
    public void exibeNodeAnterior() {
    	telaPrincipal.setCenter(nodeAnterior); 
    }
}
