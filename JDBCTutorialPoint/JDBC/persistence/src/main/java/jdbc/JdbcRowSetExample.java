package jdbc;

import java.sql.SQLException;

import javax.sql.RowSetEvent;
import javax.sql.RowSetListener;
import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

/**
 * This class presents the examples of JDBC RowSet interface. JDBC RowSet is the
 * wrapper of ResultSet, which is very flexible and easy to use.
 * 
 * @author Curtis
 *
 */
public class JdbcRowSetExample {

	public static final String DBMS = "mysql";
	public static final String LOCALHOST = "localhost";
	public static final String port = "3306";
	public static final String DB = "hiber";
	public static final String NAME = "hiber";
	public static final String PW = "hiberPW";

	public static final String DB_URL = "jdbc" + ":" + DBMS + "://" + LOCALHOST + ":" + port + "/" + DB;

	public static void main(String[] args) {

		try {

			/*
			 * This is an example to connect database using RowSet interface
			 */
			connectDBWithRowSet();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	/**
	 * This is an example to connect database using RowSet interface, which very
	 * flexible and easy to use. Not need to close the connection as well.
	 * 
	 * @throws ClassNotFoundException
	 */
	public static void connectDBWithRowSet() throws ClassNotFoundException {

		Class.forName("com.mysql.cj.jdbc.Driver");

		try {

			// create RowSetFactory
			RowSetFactory rsFactory = RowSetProvider.newFactory();

			// use the RowSetFactory to create JdbcRowSet instance
			JdbcRowSet rowSet = rsFactory.createJdbcRowSet();

			// setup connections in the rowSet.
			rowSet.setUrl(DB_URL);
			rowSet.setUsername(NAME);
			rowSet.setPassword(PW);

			rowSet.setCommand("SELECT * FROM customer");
			rowSet.execute();

			/*
			 * RowSet can also add Listener for handling the events when cursorMoved,
			 * rowChanged and rowSetChanged.
			 */
			rowSet.addRowSetListener(new RowSetEventHandler());

			// get the resultSet in a very simple way
			int n = 0;
			while (rowSet.next()) {
				System.out.println(
						"[" + n + "] " + rowSet.getString(1) + " " + rowSet.getString(2) + " " + rowSet.getString(3));
				n++;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

class RowSetEventHandler implements RowSetListener {

	public RowSetEventHandler() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void cursorMoved(RowSetEvent arg0) {
		System.out.println("Cursor Moved");
	}

	@Override
	public void rowChanged(RowSetEvent arg0) {
		System.out.println("Row changed");
	}

	@Override
	public void rowSetChanged(RowSetEvent arg0) {
		System.out.println("RowSet Changed");
	}

}
