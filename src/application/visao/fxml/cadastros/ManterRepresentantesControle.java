package application.visao.fxml.cadastros;

import java.text.ParseException;
import java.util.List;

import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialog;
import org.controlsfx.dialog.Dialogs;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import application.TelasCadastros;
import application.controle.dao.DaoGenerico;
import application.enumeradores.FiltroRepresentadasEnum;
import application.modelo.Cliente;
import application.modelo.Representante;
import application.util.CampoFiltro;
import application.util.ImageButton;

@SuppressWarnings("deprecation")
public class ManterRepresentantesControle {
    @FXML
    private TableView<Representante> tabela;
    @FXML
    private TableColumn<Representante, String> cnpjColuna;
    @FXML
    private TableColumn<Representante, String> razaoSocialColuna;
    @FXML
    private TableColumn<Representante, String> cidadeColuna;
    @FXML
    private TableColumn<Representante, String> estadoColuna;
    @FXML
    private TableColumn<Representante, String> acoesColuna;    
    
    @FXML
    private TextField filtroValor;
    @FXML
    private ComboBox<FiltroRepresentadasEnum> filtroCombo;

    @FXML
    private Button btIncluir;    
    
    private TelasCadastros telasCadastros;  
    private ObservableList<Representante> empresaDados = FXCollections.observableArrayList();
    
    public void setTelasCadastros(TelasCadastros telasCadastros) {
    	this.telasCadastros = telasCadastros;
    }
    
    @FXML
    private void initialize() {
    	DaoGenerico<Representante> dao = new DaoGenerico<Representante>(Representante.class);    	
		List<?> representantes = (List<?>) dao.getLista("Representante", "");
    	representantes.forEach(r -> empresaDados.add((Representante) r));

    	tabela.setItems(empresaDados);
        // Inicializa a tabela de pessoa com duas colunas.
        cnpjColuna.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCnpjCpf()));
        razaoSocialColuna.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNome()));
        cidadeColuna.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCidade()));
        estadoColuna.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEstado()));
        acoesColuna.setCellValueFactory(cellData -> new SimpleStringProperty(""));
        acoesColuna.setCellFactory(new Callback<TableColumn<Representante,String>, TableCell<Representante,String>>() {		
			@Override
			public TableCell<Representante, String> call(TableColumn<Representante, String> param) {
				TableCell<Representante, String> cell = new TableCell<Representante, String>() {
					@Override
					public void updateItem(String item, boolean empty) {
						if (item != null) {
							HBox hb = new HBox();
							hb.setAlignment(Pos.CENTER);
							
							Label separador = new Label(" | ");
							ImageButton imgBtnAlterar = new ImageButton("/application/imagens/pencilsmall.gif");
							ImageButton imgBtnDeletar = new ImageButton("/application/imagens/erasesmall.png");
							
							hb.getChildren().addAll(imgBtnAlterar, separador, imgBtnDeletar);
							setGraphic(hb);
							
							imgBtnAlterar.setOnAction(pressionouAlterar());
							imgBtnDeletar.setOnAction(pressionouExcluir());
						}
						else 
						{
							setGraphic(null);	
						}
					};
				};
				return cell;
			};
		});
        
        filtroCombo.getItems().addAll(FiltroRepresentadasEnum.CNPJCPF, FiltroRepresentadasEnum.NOME, FiltroRepresentadasEnum.CIDADE, FiltroRepresentadasEnum.ESTADO);
    }
    
    
    // Evento dos botões alterar presente em cada linha da grid
    private EventHandler<ActionEvent> pressionouAlterar() {
    	return new EventHandler<ActionEvent>() {	
			@Override
			public void handle(ActionEvent event) {				
				// Obtem o botão que foi clicado e na sequencia o HBox que se encontra o botão
				ImageButton imgBt = (ImageButton) event.getSource();
				HBox hb = (HBox) imgBt.getParent();
				// Através do HBox obtem o TableCell em que o HBox esta
				TableCell<?, ?> tblCell = (TableCell<?, ?>) hb.getParent();
				// Com o tablecell em mão conseguimos obter o index do representante configurado para a linha do botão
				Representante representante = tabela.getItems().get(tblCell.getIndex());
				// Com o representante da linha do botão alterar clicado, passamos este para a função que ira exibir a tela para fazer as alterações
				telasCadastros.exibeIncluirRepresentantes(representante);
			} 
		};
    }
    
    // Evento dos botões excluir presente em cada linha da grid
    private EventHandler<ActionEvent> pressionouExcluir() {
    	return new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {	
				Action response = Dialogs.create()
						.owner(telasCadastros.getStage())
						.title("Operação de exclusão")
						.masthead("A linha selecionado será excluida.")
						.message("Deseja continuar?")
						.actions(Dialog.ACTION_YES, Dialog.ACTION_NO)
						.showConfirm();   	
						
						if (response == Dialog.ACTION_NO) {
							return;
						} 					
				
				// Obtem o botão que foi clicado e na sequencia o HBox que se encontra o botão
				ImageButton imgBt = (ImageButton) event.getSource();
				HBox hb = (HBox) imgBt.getParent();
				// Através do HBox obtem o TableCell em que o HBox esta
				TableCell<?, ?> tblCell = (TableCell<?, ?>) hb.getParent();
				// Com o tablecell em mão conseguimos obter o index do representante configurado para a linha do botão
				Representante representante = tabela.getItems().get(tblCell.getIndex());				
			
				// Efetua a exclusão na base de dados
				DaoGenerico<Representante> dao = new DaoGenerico<Representante>(Representante.class);
				dao.deletarTransacionado(representante);
				
				// Com o tablecell em mão conseguimos obter o index do representante configurado para a linha do botão		
				tabela.getItems().remove(tblCell.getIndex());
			}
    	};
    }
    
    public void pressionouIncluir() {
    	telasCadastros.exibeIncluirRepresentantes(null);
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
		case CNPJCPF:
			campoFiltro.setCampoLigacao("");
			campoFiltro.setNomeCampo("cnpjCpf");
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
		case NOME:
			campoFiltro.setCampoLigacao("");
			campoFiltro.setNomeCampo("nome");
			campoFiltro.setTipoCampo(String.class);
			campoFiltro.setAlias("");	
			break;			
		}
    	
	    DaoGenerico<Representante> dao = new DaoGenerico<Representante>(Representante.class);    		
		List<?> representantes = null;
		try {
			representantes = (List<?>) dao.filtrarDadosManter(campoFiltro, filtroValor);
		} catch (ParseException e) {
			Dialogs.create()
			.owner(telasCadastros.getStage())
			.title("Filtro")
			.masthead("Não foi possível filtrar os dados.")
			.message(e.getMessage())
			.showError();  	
			e.printStackTrace();
		}
	   representantes.forEach(r -> empresaDados.add((Representante) r));

    	tabela.setItems(empresaDados);
    }
}
