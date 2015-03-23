package application.visao.fxml.cadastros.produtos;

import java.text.ParseException;
import java.util.List;

import org.controlsfx.dialog.Dialogs;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import application.TelasCadastros;
import application.controle.dao.DaoGenerico;
import application.controle.dao.ProdutoDao;
import application.enumeradores.FiltroProdutosEnum;
import application.modelo.Produto;
import application.modelo.Representada;
import application.util.CampoFiltro;
import application.visao.fxml.cadastros.ManterCadastroControle;

@SuppressWarnings("deprecation")
public class ManterProdutoControle extends ManterCadastroControle {
    @FXML
    private TableView<Produto> tabela;
    @FXML
    private TableColumn<Produto, String> representadaColuna;    
    @FXML
    private TableColumn<Produto, String> codigoColuna; 
    @FXML
    private TableColumn<Produto, String> descricaoColuna; 
    @FXML
    private TableColumn<Produto, String> complementoColuna;
    @FXML
    private TableColumn<Produto, String> percentualIpiColuna;    
    @FXML
    private TableColumn<Produto, String> ativoColuna; 
    @FXML
    private TableColumn<Produto, String> acoesColuna;    
    
    @FXML
    private TextField filtroValor;
    @FXML
    private ComboBox<FiltroProdutosEnum> filtroCombo;

    @FXML
    private Button btIncluir;
    
    private ObservableList<Produto> produtosDados = FXCollections.observableArrayList();
    
    public void setTelasCadastros(TelasCadastros telasCadastros) {
    	this.telasCadastros = telasCadastros;
    }
   
    @FXML
    protected void initialize() {
    	ProdutoDao dao = new ProdutoDao();
    	produtosDados = dao.buscarTodosProdutos();

    	tabela.setItems(produtosDados);    	
    	
    	representadaColuna.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRepresentada().getNomeAbreviado()));
    	codigoColuna.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCodigoProduto()+""));
    	descricaoColuna.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescricao()));
        complementoColuna.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getComplemento()));
        percentualIpiColuna.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPercentualIpi()+""));    	
        ativoColuna.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().isAtivo()? "Sim" : "Não"));

        super.adicionarAcoes(true, true);   
        
        filtroCombo.getItems().addAll(FiltroProdutosEnum.values());
    }
    
    // Evento dos botões alterar presente em cada linha da grid
    @Override
    protected EventHandler<ActionEvent> pressionouAlterarItem() {
    	return new EventHandler<ActionEvent>() {	
			@Override
			public void handle(ActionEvent event) {								
				TableCell<?, ?> tblCell = pressionouAlterarItem(event);
				// Com o tablecell em mão conseguimos obter o index do representante configurado para a linha do botão
				Produto produto = tabela.getItems().get(tblCell.getIndex());
				// Com o produto da linha do botão alterar clicado, passamos este para a função que ira exibir a tela para fazer as alterações
				telasCadastros.exibeIncluirProdutos(produto);
			} 
		};
    }
    
    // Evento dos botões excluir presente em cada linha da grid
    @Override
	protected EventHandler<ActionEvent> pressionouExcluirItem() {
    	return new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {	
				TableCell<?, ?> tblCell = pressionouExcluirItem(event);
				if (tblCell == null) { return; }
				
				// Com o tablecell em mão conseguimos obter o index do produto configurado para a linha do botão
				Produto produto = tabela.getItems().get(tblCell.getIndex());				
			
				// Efetua a exclusão na base de dados
				DaoGenerico<Produto> dao = new DaoGenerico<Produto>(Produto.class);
				dao.deletarTransacionado(produto);
				
				// Com o tablecell em mão conseguimos obter o index do produto configurado para a linha do botão		
				tabela.getItems().remove(tblCell.getIndex());
			}
    	};
    }   
    
    public void pressionouFiltrar() {
    	if (filtroCombo.selectionModelProperty().get().isEmpty()) {
			Dialogs.create()
			.owner(telasCadastros.getStage())
			.title("Filtro")
			.masthead("Não foi possível filtrar os dados.")
			.message("Para filtrar os dados é necessário informar uma coluna.")
			.showWarning();    	
			return;
    	}
    	

    	String filtroValor = this.filtroValor.getText();

    	CampoFiltro campoFiltro = new CampoFiltro();
    	campoFiltro.setEntidade(Produto.class);    	
    	switch (filtroCombo.selectionModelProperty().get().getSelectedItem()) {
		case REPRESENTADA:
			campoFiltro.setCampoLigacao("representada");
			campoFiltro.setNomeCampo("nomeAbreviado");
			campoFiltro.setTipoCampo(Representada.class);
			campoFiltro.setAlias("r.");			
			break;
		case CODIGO:
			campoFiltro.setCampoLigacao("");
			campoFiltro.setNomeCampo("codigoProduto");
			campoFiltro.setTipoCampo(String.class);
			campoFiltro.setAlias("");					
			break;	
		case DESCRICAO:
			campoFiltro.setCampoLigacao("");
			campoFiltro.setNomeCampo("descricao");
			campoFiltro.setTipoCampo(String.class);
			campoFiltro.setAlias("");	
			break;	
		case COMPLEMENTO:
			campoFiltro.setCampoLigacao("");
			campoFiltro.setNomeCampo("complemento");
			campoFiltro.setTipoCampo(String.class);
			campoFiltro.setAlias("");	
			break;	
		case IPI:
			campoFiltro.setCampoLigacao("");
			campoFiltro.setNomeCampo("percentualIpi");
			campoFiltro.setTipoCampo(Double.class);
			campoFiltro.setAlias("");				
			break;	
		case ATIVO:
			campoFiltro.setCampoLigacao("");
			campoFiltro.setNomeCampo("ativo");
			campoFiltro.setTipoCampo(Boolean.class);
			campoFiltro.setAlias("");	
			break;					
		}
    	
    	produtosDados.clear();
    	if (filtroValor.equals("")) {
    		ProdutoDao dao = new ProdutoDao();
    		produtosDados = dao.buscarTodosProdutos();   		
    	}
    	else
    	{
	    	ProdutoDao dao = new ProdutoDao();  
	    	List<?> produtos = null; 		
	    	try {
				produtos = (List<?>) dao.filtrarDadosManter(campoFiltro, filtroValor);
			} catch (ParseException e) {
				Dialogs.create()
				.owner(telasCadastros.getStage())
				.title("Filtro")
				.masthead("Não foi possível filtrar os dados.")
				.message(e.getMessage())
				.showError();  					
				e.printStackTrace();
			}

	    	produtos.forEach(p -> produtosDados.add((Produto) p));
    	}

    	tabela.setItems(produtosDados);
    }    
    
    public void pressionouIncluir() {
    	telasCadastros.exibeIncluirProdutos(null);
    }	    
    
}
