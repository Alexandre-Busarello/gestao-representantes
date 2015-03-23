package application.util;

import javafx.scene.control.TableView;

public class JavaFX {
	
	public static void refreshTableView(TableView<?> tbl) {
		tbl.getColumns().get(0).setVisible(false);
		tbl.getColumns().get(0).setVisible(true);
	}

}
