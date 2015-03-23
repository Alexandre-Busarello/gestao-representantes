package application.visao.fxml.cadastros;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Locale;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import application.controle.dao.DaoGenerico;
import application.controle.negocio.SelecionarRegistroItem;

@SuppressWarnings("rawtypes")
public class SelecionarRegistroControle {
	
	private ObservableList<SelecionarRegistroItem> dados;
	private Class classe;
	
    @FXML
    private TableView<SelecionarRegistroItem> tabela;	
	
    @FXML
    protected void initialize() {
    	DaoGenerico<Class> dao = new DaoGenerico<Class>(classe);
		List<?> lista = (List<?>) dao.getLista(classe.getName(), "");
		lista.forEach(r -> {
			Method todosMetodos[] = r.getClass().getDeclaredMethods();
			for (Method m : todosMetodos) {
				if (m.getName().equals("getDadosSeleciornarRegistro")) {
					try {
						m.setAccessible(true);
						Object obj = classe.newInstance();
						SelecionarRegistroItem selRegItem = (SelecionarRegistroItem) m.invoke(obj, new Locale(""));
						dados.add(selRegItem);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}	
		});    
		tabela.setItems(dados);
		tabela.getColumns().add(new TableColumn<SelecionarRegistroItem, String>());
		tabela.getColumns().forEach(c -> c.setCellValueFactory(cellData -> 
		 new SimpleStringProperty(cellData.getValue().getPrimeiraColuna().getValorColuna())
		));
    }
	
}
