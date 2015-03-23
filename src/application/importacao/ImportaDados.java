package application.importacao;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class ImportaDados {

	public static void main(String[] args) {
		try {
			Statement stmt = null;
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			String url = "jdbc:ucanaccess://C:/projeto-representante/ProjetoRepresentante/src/application/importacao/phngrpr.mdb";
			Connection conexao = DriverManager.getConnection(url, "Admin",
					"040702");
			stmt = (Statement) conexao.createStatement();
			String query = "SELECT * FROM TPEDIDO";
			ResultSet rs = stmt.executeQuery(query);
			int conta = 0;
			ResultSetMetaData meta = rs.getMetaData();
			for (int contador = 1; contador <= meta.getColumnCount(); contador++) {
				System.out.println(meta.getColumnName(contador) + ", "
						+ meta.getColumnTypeName(contador));
			}

			while (rs.next()) {
				System.out.println(rs.getString("CÓDIGO CLIENTE"));
				conta++;
			}
			System.out.println("Total " + conta);

		} catch (ClassNotFoundException Driver) {
			JOptionPane.showMessageDialog(null, "Driver não localizado: "
					+ Driver);
		} catch (SQLException Fonte) {
			JOptionPane.showMessageDialog(null,
					"Deu erro na conexão com a fonte de dados:\n" + Fonte);
		}
	}
}
