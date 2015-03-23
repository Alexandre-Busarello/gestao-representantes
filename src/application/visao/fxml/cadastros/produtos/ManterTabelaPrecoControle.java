package application.visao.fxml.cadastros.produtos;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

import org.controlsfx.dialog.Dialogs;

import application.TelasCadastros;
import application.controle.dao.DaoGenerico;
import application.controle.dao.TabelaPrecoDao;
import application.enumeradores.FiltroTabelaPrecoEnum;
import application.modelo.Representada;
import application.modelo.TabelaPreco;
import application.util.CampoFiltro;
import application.visao.fxml.cadastros.ManterCadastroControle;

@SuppressWarnings("deprecation")
public class ManterTabelaPrecoControle extends ManterCadastroControle {
    @FXML
    private TableView<TabelaPreco> tabela;
    @FXML
    private TableColumn<TabelaPreco, String> representadaColuna;    
    @FXML
    private TableColumn<TabelaPreco, String> codigoColuna; 
    @FXML
    private TableColumn<TabelaPreco, String> descricaoColuna; 
    @FXML
    private TableColumn<TabelaPreco, String> dataInicio;
    @FXML
    private TableColumn<TabelaPreco, String> dataValidade;    
    @FXML
    private TableColumn<TabelaPreco, String> ativoColuna; 
    @FXML
    private TableColumn<TabelaPreco, String> acoesColuna;    
    
    @FXML
    private TextField filtroValor;
    @FXML
    private ComboBox<FiltroTabelaPrecoEnum> filtroCombo;

    @FXML
    private Button btIncluir;
    
    private ObservableList<TabelaPreco> tabelasDados = FXCollections.observableArrayList();
    
    public void setTelasCadastros(TelasCadastros telasCadastros) {
    	this.telasCadastros = telasCadastros;
    }
    
    private void buscarTodasTabelasPreco() {
    	DaoGenerico<TabelaPreco> dao = new DaoGenerico<TabelaPreco>(TabelaPreco.class);    	
		List<?> tabelasPreco = (List<?>) dao.getLista("TabelaPreco", "");
		tabelasPreco.forEach(p -> tabelasDados.add((TabelaPreco) p));  
	}
   
    @FXML
    protected void initialize() {
    	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    	
    	buscarTodasTabelasPreco();

    	tabela.setItems(tabelasDados);    	
    	
    	representadaColuna.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRepresentada().getNomeAbreviado()));
    	codigoColuna.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCodigoTabela()));
    	descricaoColuna.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescricao()));
    	dataInicio.setCellValueFactory(cellData -> new SimpleStringProperty(sdf.format(cellData.getValue().getDataInicio())));
    	dataValidade.setCellValueFactory(cellData -> new SimpleStringProperty(sdf.format(cellData.getValue().getDataValidade())));    	
        ativoColuna.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().isAtivo()? "Sim" : "Não"));

        super.adicionarAcoes(true, true);   
        
        filtroCombo.getItems().addAll(FiltroTabelaPrecoEnum.values());
    }
    
    // Evento dos botões alterar presente em cada linha da grid
    @Override
    protected EventHandler<ActionEvent> pressionouAlterarItem() {
    	return new EventHandler<ActionEvent>() {	
			@Override
			public void handle(ActionEvent event) {								
				TableCell<?, ?> tblCell = pressionouAlterarItem(event);
				// Com o tablecell em mão conseguimos obter o index do representante configurado para a linha do botão
				TabelaPreco tabelaPreco = tabela.getItems().get(tblCell.getIndex());
				// Com o produto da linha do botão alterar clicado, passamos este para a função que ira exibir a tela para fazer as alterações
				telasCadastros.exibeIncluirTabelasPreco(tabelaPreco);
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
				TabelaPreco tabelaPreco = tabela.getItems().get(tblCell.getIndex());				
			
				// Efetua a exclusão na base de dados
				DaoGenerico<TabelaPreco> dao = new DaoGenerico<TabelaPreco>(TabelaPreco.class);
				dao.deletarTransacionado(tabelaPreco);
				
				// Com o tablecell em mão conseguimos obter o index do produto configurado para a linha do botão		
				tabela.getItems().remove(tblCell.getIndex());
			}
    	};
    }   
    
    @SuppressWarnings("unused")
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
    	
    	String filtroCampo = "";
    	String filtroValor = this.filtroValor.getText();
    	Class<?> c = String.class;

    	CampoFiltro campoFiltro = new CampoFiltro();
    	campoFiltro.setEntidade(TabelaPreco.class);
    	switch (filtroCombo.selectionModelProperty().get().getSelectedItem()) {
		case REPRESENTADA:
			campoFiltro.setCampoLigacao("representada");
			campoFiltro.setNomeCampo("nomeAbreviado");
			campoFiltro.setTipoCampo(Representada.class);
			campoFiltro.setAlias("r.");
			filtroCampo = "nomeAbreviado";
			break;
		case CODIGO:
			campoFiltro.setCampoLigacao("");
			campoFiltro.setNomeCampo("codigoTabela");
			campoFiltro.setTipoCampo(String.class);
			campoFiltro.setAlias("");			
			filtroCampo = "codigoTabela";
			break;	
		case DESCRICAO:
			campoFiltro.setCampoLigacao("");
			campoFiltro.setNomeCampo("descricao");
			campoFiltro.setTipoCampo(String.class);
			campoFiltro.setAlias("");			
			filtroCampo = "descricao";
			break;	
		case DATAINICIO:
			campoFiltro.setCampoLigacao("");
			campoFiltro.setNomeCampo("dataInicio");
			campoFiltro.setTipoCampo(Date.class);
			campoFiltro.setAlias("");			
			filtroCampo = "dataInicio";
			break;	
		case DATAVALIDADE:
			campoFiltro.setCampoLigacao("");
			campoFiltro.setNomeCampo("dataValidade");
			campoFiltro.setTipoCampo(Date.class);
			campoFiltro.setAlias("");						
			filtroCampo = "dataValidade";
			c = Double.class;
			break;	
		case ATIVO:
			campoFiltro.setCampoLigacao("");
			campoFiltro.setNomeCampo("ativo");
			campoFiltro.setTipoCampo(Boolean.class);
			campoFiltro.setAlias("");				
			filtroCampo = "ativo";
			break;					
		}
    	
    	tabelasDados.clear();
    	if (filtroValor.equals("")) {
    		buscarTodasTabelasPreco();   		
    	}
    	else
    	{
	    	TabelaPrecoDao dao = new TabelaPrecoDao();  
	    	List<?> tabelas = null; 		
	    	try {
				tabelas = (List<?>) dao.filtrarDadosManter(campoFiltro, filtroValor);
			} catch (ParseException e) {
				Dialogs.create()
				.owner(telasCadastros.getStage())
				.title("Filtro")
				.masthead("Não foi possível filtrar os dados.")
				.message(e.getMessage())
				.showError();  				
				e.printStackTrace();
			}
			
	    	tabelas.forEach(p -> tabelasDados.add((TabelaPreco) p));
    	}

    	tabela.setItems(tabelasDados);
    }    
    
    public void pressionouIncluir() {
    	telasCadastros.exibeIncluirTabelasPreco(null);;
    }	    
    

}
