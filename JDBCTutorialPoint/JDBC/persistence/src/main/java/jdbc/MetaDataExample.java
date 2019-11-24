package jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.ResultSetMetaData;

public class MetaDataExample {

	/**
	 * ResultSetMetaData interface
	 * 
	 * ResultSetMetaData interface have methods to ge metadata of a table like total
	 * number of column, column name, column type etc. This interface is useful
	 * because it provides methods to get metadata from the ResultSet object.
	 * 
	 * Methods include: getColumnCount(), getColumnName method, getColumnTypeName
	 * method, getTableName method and etc.
	 */

	/**
	 * DatabaseMetaData interface
	 * 
	 * DatabaseMetaData interface provides methods to get meta data of a database
	 * such as database product name, database product version, driver name, name of
	 * total number of tables, name of total number of views etc.
	 * 
	 * Methods include: getDriverName method, getDriverVersion method, getUserName
	 * method and getDatabaseProductName.
	 */

	public static final String DBMS = "mysql";
	public static final String LOCALHOST = "localhost";
	public static final String port = "3306";
	public static final String DB = "hiber";
	public static final String NAME = "hiber";
	public static final String PW = "hiberPW";

	public static final String DB_URL = "jdbc" + ":" + DBMS + "://" + LOCALHOST + ":" + port + "/" + DB;

	public static void main(String[] args) {

		try {

			// Example of get metadata of ResultSet using ResultSetMetaData interface
			getMetaData();

			System.out.println("----------------------------------");

			// Example of get metadata of the database using DatabaseMetaData interface
			getDBMetaData();

		} catch (ClassNotFoundException e) {
			System.out.println("Driver not found.");
		}
	}

	/**
	 * Example of getting the metadata of the ResultSet using ResultSetMetaData
	 * interface.
	 */
	public static void getMetaData() throws ClassNotFoundException {

		Class.forName("com.mysql.cj.jdbc.Driver");

		try (Connection conn = DriverManager.getConnection(DB_URL, NAME, PW)) {

			PreparedStatement preStmt = conn.prepareStatement("SELECT * FROM customer");

			ResultSet set = preStmt.executeQuery();

			// get ResultSetMetaData from ResultSet
			ResultSetMetaData meta = set.getMetaData();

			System.out.println("Table Name: " + meta.getTableName(1));
			System.out.println("Total Columns: " + meta.getColumnCount());
			System.out.println("First Column Name: " + meta.getColumnName(1));
			System.out.println("First Column Type: " + meta.getColumnTypeName(1));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Example of getting the metadata of the database using DatabaseMetaData
	 * interface
	 * 
	 * @throws ClassNotFoundException
	 */
	public static void getDBMetaData() throws ClassNotFoundException {

		Class.forName("com.mysql.cj.jdbc.Driver");

		try (Connection conn = DriverManager.getConnection(DB_URL, NAME, PW)) {

			DatabaseMetaData dbmd = conn.getMetaData();
			System.out.println("Driver Name: " + dbmd.getDriverName());
			System.out.println("Driver Version: " + dbmd.getDriverVersion());
			System.out.println("User Name: " + dbmd.getUserName());
			System.out.println("Database Product Name: " + dbmd.getDatabaseProductName());
			System.out.println("Database Product Version: " + dbmd.getDatabaseProductVersion());

			System.out.println("Total Number of Tables:");
			String table[] = { "TABLE" };
			ResultSet set = dbmd.getTables(null, null, null, table);

			while (set.next()) {
				// not sure why it's index 3
				System.out.println(set.getString(3));
			}

			System.out.println("Total Number of Views:");
			String view[] = { "VIEW" };
			ResultSet setView = dbmd.getTables(null, null, null, view);

			while (setView.next()) {
				// not sure why it's index 3 as the one for table above
				System.out.println(setView.getString(3));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
