package application.visao.fxml.cadastros.representadas;

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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import application.TelasCadastros;
import application.controle.dao.DaoGenerico;
import application.enumeradores.FiltroRepresentadasEnum;
import application.modelo.Representada;
import application.util.CampoFiltro;
import application.util.ImageButton;
import application.visao.fxml.cadastros.ManterCadastroControle;

@SuppressWarnings("deprecation")
public class ManterRepresentadasControle extends ManterCadastroControle {
    @FXML
    private TableView<Representada> tabela;
    @FXML
    private TableColumn<Representada, String> cnpjColuna;
    @FXML
    private TableColumn<Representada, String> nomeColuna;
    @FXML
    private TableColumn<Representada, String> cidadeColuna;
    @FXML
    private TableColumn<Representada, String> estadoColuna;
    @FXML
    private TableColumn<Representada, String> acoesColuna;    
    
    @FXML
    private TextField filtroValor;
    @FXML
    private ComboBox<FiltroRepresentadasEnum> filtroCombo;

    @FXML
    private Button btIncluir;    
    
    private ObservableList<Representada> representadasDados = FXCollections.observableArrayList();
    
    public void setTelasCadastros(TelasCadastros telasCadastros) {
    	this.telasCadastros = telasCadastros;
    }
   

    @FXML
    protected void initialize() {
    	DaoGenerico<Representada> dao = new DaoGenerico<Representada>(Representada.class);    	
		List<?> representadas = (List<?>) dao.getLista("Representada", "");
		representadas.forEach(r -> representadasDados.add((Representada) r));

    	tabela.setItems(representadasDados);
        // Inicializa a tabela de pessoa com duas colunas.
        cnpjColuna.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCnpj()));
        nomeColuna.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNome()));
        cidadeColuna.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCidade()));
        estadoColuna.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEstado()));

        super.adicionarAcoes(true, true);
        
        filtroCombo.getItems().addAll(FiltroRepresentadasEnum.CNPJCPF, FiltroRepresentadasEnum.NOME, FiltroRepresentadasEnum.CIDADE, FiltroRepresentadasEnum.ESTADO);
    }
    
    
    // Evento dos botões alterar presente em cada linha da grid
    @Override
    protected EventHandler<ActionEvent> pressionouAlterarItem() {
    	return new EventHandler<ActionEvent>() {	
			@Override
			public void handle(ActionEvent event) {				
				// Obtem o botão que foi clicado e na sequencia o HBox que se encontra o botão
				ImageButton imgBt = (ImageButton) event.getSource();
				HBox hb = (HBox) imgBt.getParent();
				// Através do HBox obtem o TableCell em que o HBox esta
				TableCell<?, ?> tblCell = (TableCell<?, ?>) hb.getParent();
				// Com o tablecell em mão conseguimos obter o index do representante configurado para a linha do botão
				Representada representada = tabela.getItems().get(tblCell.getIndex());
				// Com o representante da linha do botão alterar clicado, passamos este para a função que ira exibir a tela para fazer as alterações
				telasCadastros.exibeIncluirRepresentadas(representada);
			} 
		};
    }
    
    // Evento dos botões excluir presente em cada linha da grid
    @Override
	protected EventHandler<ActionEvent> pressionouExcluirItem() {
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
				Representada representada = tabela.getItems().get(tblCell.getIndex());				
			
				// Efetua a exclusão na base de dados
				DaoGenerico<Representada> dao = new DaoGenerico<Representada>(Representada.class);
				dao.deletarTransacionado(representada);
				
				// Com o tablecell em mão conseguimos obter o index do representante configurado para a linha do botão		
				tabela.getItems().remove(tblCell.getIndex());
			}
    	};
    }
    
    public void pressionouIncluir() {
    	telasCadastros.exibeIncluirRepresentadas(null);
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
    	campoFiltro.setEntidade(Representada.class);    	
    	String filtroValor = this.filtroValor.getText();
    	switch (filtroCombo.selectionModelProperty().get().getSelectedItem()) {
		case CNPJCPF:
			campoFiltro.setCampoLigacao("");
			campoFiltro.setNomeCampo("cnpj");
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
    	
	    DaoGenerico<Representada> dao = new DaoGenerico<Representada>(Representada.class);    		
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
	    representantes.forEach(r -> representadasDados.add((Representada) r));

    	tabela.setItems(representadasDados);
    }
}
