import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Connection;

public class CinemaClass {

	// Cr�ation d'une m�thode pour ouvrir la connection
	public static Connection openConnection(Statement st) {

		Connection cn = null;

		String url = "jdbc:mysql://localhost/cinema?useSSL=false";
		String user = "root";
		String pwd = "Artichaut07";

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

	// Cr�ation d'une m�thode pour executer une requ�te sql select *

	public static Statement selectAll(Connection cn) {

		Statement st = null;

		// Cr�ation du statement

		try {
			st = (Statement) cn.createStatement();

			String sql = "SELECT titre FROM film;";
			String sql2 = "SELECT * FROM film\r\n" + 
					"WHERE film.longueur > 180";

			// Classe ResultSet qui a une m�thode executeQuery cette m�thode retourne un
			// objet de type ResultSet et execute le Statement

			ResultSet result = (ResultSet) st.executeQuery(sql);
			

			// On fait une boucle while pour afficher le result
			// Tant que result a un resultat, la boucle continue

			// Variables qui vont nous servir � r�cup�rer les valeurs

			String titre;

			while (result.next()) {

				// L'argument c'est le nom de la colonne de notre table entre double quotes.

				titre = result.getString("titre");
				System.out.println("Les titres du film sont : " + titre);
			}
			
			ResultSet result2 = (ResultSet) st.executeQuery(sql2);

			int longueur;
			while (result2.next()) {

				// L'argument c'est le nom de la colonne de notre table entre double quotes.

				longueur = result2.getInt("longueur");
				System.out.println("Les titres du film  avec une longueur d�passant 180 sont : " + longueur);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return st;

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Connection cn = null;
		Statement st = null;

		cn = openConnection(st);
		

		st = selectAll(cn);
		
		
		closeConnection(cn, st);

	}

}
