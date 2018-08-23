import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Connection;

public class CinemaClass {

	// Cr�ation d'une m�thode pour ouvrir la connection
	public static Connection openConnection(Statement st) {

		Connection cn = null;

		String url = "jdbc:mysql://localhost/cinema?useSSL=false";
		String user = "root";
		String pwd = "rootpwd";

		// R�cup�ration du driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver ok!");

			// R�cup�ration de la connection

			cn = (Connection) DriverManager.getConnection(url, user, pwd);

			// ClassNotFoundException g�re les exceptions de type ClassNotFound
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cn;

	}

	// Cr�ation de la m�thode pour fermer la connexion & le statement

	public static void closeConnection(Connection cn, Statement st) {

		try {
			cn.close();
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection cn = null;
		Statement st = null;

		cn = openConnection(st);
		closeConnection(cn,st);

	}

}
