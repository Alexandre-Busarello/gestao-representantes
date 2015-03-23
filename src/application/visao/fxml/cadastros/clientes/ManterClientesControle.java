package application.visao.fxml.cadastros.clientes;

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
import application.controle.dao.ClienteDao;
import application.controle.dao.DaoGenerico;
import application.enumeradores.FiltroClientesEnum;
import application.modelo.Cliente;
import application.modelo.Representada;
import application.util.CampoFiltro;
import application.visao.fxml.cadastros.ManterCadastroControle;

@SuppressWarnings("deprecation")
public class ManterClientesControle extends ManterCadastroControle {
    @FXML
    private TableView<Cliente> tabela;
    @FXML
    private TableColumn<Cliente, String> representadaColuna;    
    @FXML
    private TableColumn<Cliente, String> idColuna; 
    @FXML
    private TableColumn<Cliente, String> nomeColuna; 
    @FXML
    private TableColumn<Cliente, String> cidadeColuna;
    @FXML
    private TableColumn<Cliente, String> estadoColuna;    
    @FXML
    private TableColumn<Cliente, String> documentoColuna; 
    @FXML
    private TableColumn<Cliente, String> fabricaColuna;     
    @FXML
    private TableColumn<Cliente, String> acoesColuna;    
    
    @FXML
    private TextField filtroValor;
    @FXML
    private ComboBox<FiltroClientesEnum> filtroCombo;

    @FXML
    private Button btIncluir;    
    
    private ObservableList<Cliente> clientesDados = FXCollections.observableArrayList();
    
    public void setTelasCadastros(TelasCadastros telasCadastros) {
    	this.telasCadastros = telasCadastros;
    }
   
    @FXML
    protected void initialize() {
    	DaoGenerico<Cliente> dao = new DaoGenerico<Cliente>(Cliente.class);    	
		List<?> clientes = (List<?>) dao.getLista("Cliente", "");
		clientes.forEach(r -> clientesDados.add((Cliente) r));

    	tabela.setItems(clientesDados);

    	
    	representadaColuna.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRepresentada().getNomeAbreviado()));
    	idColuna.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()+""));
    	nomeColuna.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNome()));
        cidadeColuna.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCidade()));
        estadoColuna.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEstado()));    	
        documentoColuna.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDocumento()));
        fabricaColuna.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCodigoFabrica()));

        super.adicionarAcoes(true, true);
           
        filtroCombo.getItems().addAll(FiltroClientesEnum.values());
    }
    
    
    // Evento dos botões alterar presente em cada linha da grid
    @Override
    protected EventHandler<ActionEvent> pressionouAlterarItem() {
    	return new EventHandler<ActionEvent>() {	
			@Override
			public void handle(ActionEvent event) {								
				TableCell<?, ?> tblCell = pressionouAlterarItem(event);
				// Com o tablecell em mão conseguimos obter o index do representante configurado para a linha do botão
				Cliente cliente = tabela.getItems().get(tblCell.getIndex());
				// Com o representante da linha do botão alterar clicado, passamos este para a função que ira exibir a tela para fazer as alterações
				telasCadastros.exibeIncluirClientes(cliente);
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
				
				// Com o tablecell em mão conseguimos obter o index do representante configurado para a linha do botão
				Cliente cliente = tabela.getItems().get(tblCell.getIndex());				
			
				// Efetua a exclusão na base de dados
				DaoGenerico<Cliente> dao = new DaoGenerico<Cliente>(Cliente.class);
				dao.deletarTransacionado(cliente);
				
				// Com o tablecell em mão conseguimos obter o index do representante configurado para a linha do botão		
				tabela.getItems().remove(tblCell.getIndex());
			}
    	};
    }
    
    public void pressionouIncluir() {
    	telasCadastros.exibeIncluirClientes(null);
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
    	
    	CampoFiltro campoFiltro = new CampoFiltro();
    	campoFiltro.setEntidade(Cliente.class);    	
    	String filtroValor = this.filtroValor.getText();
    	switch (filtroCombo.selectionModelProperty().get().getSelectedItem()) {
		case REPRESENTADA:
			campoFiltro.setCampoLigacao("representada");
			campoFiltro.setNomeCampo("nomeAbreviado");
			campoFiltro.setTipoCampo(Representada.class);
			campoFiltro.setAlias("r.");		
			break;
		case NOME:
			campoFiltro.setCampoLigacao("");
			campoFiltro.setNomeCampo("nome");
			campoFiltro.setTipoCampo(String.class);
			campoFiltro.setAlias("");		
			break;	
		case CIDADE:
			campoFiltro.setCampoLigacao("");
			campoFiltro.setNomeCampo("cidade");
			campoFiltro.setTipoCampo(String.class);
			campoFiltro.setAlias("");		
			break;	
		case ESTADO:
			campoFiltro.setCampoLigacao("");
			campoFiltro.setNomeCampo("estado");
			campoFiltro.setTipoCampo(String.class);
			campoFiltro.setAlias("");		
			break;	
		case CNPJCPF:
			campoFiltro.setCampoLigacao("");
			campoFiltro.setNomeCampo("documento");
			campoFiltro.setTipoCampo(String.class);
			campoFiltro.setAlias("");		
			break;	
		case FABRICA:
			campoFiltro.setCampoLigacao("");
			campoFiltro.setNomeCampo("codigoFabrica");
			campoFiltro.setTipoCampo(String.class);
			campoFiltro.setAlias("");		
			break;					
		}
    	
    	clientesDados.clear();
    	if (filtroValor.equals("")) {
        	DaoGenerico<Cliente> dao = new DaoGenerico<Cliente>(Cliente.class);    	
    		List<?> clientes = (List<?>) dao.getLista("Cliente", "");
        	clientes.forEach(r -> clientesDados.add((Cliente) r));    		
    	}
    	else
    	{
	    	ClienteDao dao = new ClienteDao();  
	    	List<?> clientes = null; 		
	    	try {
				clientes = (List<?>) dao.filtrarDadosManter(campoFiltro, filtroValor);
			} catch (ParseException e) {
				Dialogs.create()
				.owner(telasCadastros.getStage())
				.title("Filtro")
				.masthead("Não foi possível filtrar os dados.")
				.message(e.getMessage())
				.showError();  	
				e.printStackTrace();
			}
			
			clientes.forEach(r -> clientesDados.add((Cliente) r));
    	}

    	tabela.setItems(clientesDados);
    }
}
