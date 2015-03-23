package application.visao.fxml.cadastros;

import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialog;
import org.controlsfx.dialog.Dialogs;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import application.TelasCadastros;
import application.modelo.Representante;
import application.util.ImageButton;

@SuppressWarnings("deprecation")
public class ManterCadastroControle {
	@FXML
	private TableColumn<Representante, String> acoesColuna; 
	
    protected TelasCadastros telasCadastros;  
	
	protected void adicionarAcoes(boolean isBtnAlterar, boolean isBtnDeletar) {
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
							ImageButton imgBtnAlterar = new ImageButton("/application/imagens/pencilsmall.png");
							ImageButton imgBtnDeletar = new ImageButton("/application/imagens/erasesmall.png");
							
							if (isBtnAlterar) { 
								hb.getChildren().add(imgBtnAlterar);
								if (isBtnDeletar) {
									hb.getChildren().add(separador);
								}
							}
							if (isBtnDeletar) {
								hb.getChildren().add(imgBtnDeletar);
							}
							setGraphic(hb);
							
							imgBtnAlterar.setOnAction(pressionouAlterarItem());
							imgBtnDeletar.setOnAction(pressionouExcluirItem());
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
	}
	
	protected TableCell<?, ?> pressionouAlterarItem(ActionEvent event) {
		// Obtem o botão que foi clicado e na sequencia o HBox que se encontra o botão
		ImageButton imgBt = (ImageButton) event.getSource();
		HBox hb = (HBox) imgBt.getParent();
		// Através do HBox obtem o TableCell em que o HBox esta
		TableCell<?, ?> tblCell = (TableCell<?, ?>) hb.getParent();	
		
		return tblCell;
	}
	
	protected EventHandler<ActionEvent> pressionouAlterarItem() {
		return null;
	}

	protected TableCell<?, ?> pressionouExcluirItem(ActionEvent event) {
		Action response = Dialogs.create()
				.owner(telasCadastros.getStage())
				.title("Operação de exclusão")
				.masthead("A linha selecionado será excluida.")
				.message("Deseja continuar?")
				.actions(Dialog.ACTION_YES, Dialog.ACTION_NO)
				.showConfirm();   	
				
				if (response == Dialog.ACTION_NO) {
					return null;
				} 					
		
		// Obtem o botão que foi clicado e na sequencia o HBox que se encontra o botão
		ImageButton imgBt = (ImageButton) event.getSource();
		HBox hb = (HBox) imgBt.getParent();
		// Através do HBox obtem o TableCell em que o HBox esta
		TableCell<?, ?> tblCell = (TableCell<?, ?>) hb.getParent();
		
		return tblCell;
	}	

	protected EventHandler<ActionEvent> pressionouExcluirItem() {
		return null;
	}

}
