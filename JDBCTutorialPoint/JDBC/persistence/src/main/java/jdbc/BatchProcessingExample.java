package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This class presents the examples of Batch Processing. Batch Processing
 * improves performance by adding a number of queries into the batch. One
 * transaction is a single unit of work, statements are executed at the end of
 * the transaction of when commit method is called.
 * 
 * Batch Processing solves the execution of multiple queries by adding them to a
 * batch. It is achieved through the addBatch method and executeBatch method of
 * the Statement interface.
 * 
 * @author Curtis
 *
 */
public class BatchProcessingExample {

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
	     * This is the example of adding numerous rows of data using Batch Processing
	     * and Statement interface.
	     */
	    addBatchOfDataStmt();

	    /*
	     * This is the example of adding numerous rows of data using Batch Processing
	     * and PreparedStatement interface.
	     */
	    addBatchOfDataPreStmt();

	} catch (ClassNotFoundException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    /**
     * This is the example of adding numerous rows of data using Batch Processing.
     * 
     * @exception ClassNotFoundException
     */
    public static void addBatchOfDataStmt() throws ClassNotFoundException {

	// Table: [firstName] [lastName] [custID] (custID is unsigned and
	// auto_increment)
	String queryOne = "INSERT INTO customer (firstName, lastName) VALUES('NonBoOne', 'OneBoNon')";
	String queryTwo = "INSERT INTO customer (firstName, lastName) VALUES('NonBoTwo', 'TwoBoNon')";
	String queryThree = "INSERT INTO customer (firstName, lastName) VALUES('NonBoThree', 'ThreeBoNon')";

	Class.forName("com.mysql.cj.jdbc.Driver");

	try (Connection conn = DriverManager.getConnection(DB_URL, NAME, PW)) {

	    Statement stmt = conn.createStatement();

	    // add query into batch.
	    stmt.addBatch(queryOne);
	    stmt.addBatch(queryTwo);
	    stmt.addBatch(queryThree);

	    // execute the batch of queries
	    stmt.executeBatch();

	    System.out.println("Statement - Batch of Quries Executed");

	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    /**
     * This is the example of adding numerous rows of data using Batch Processing
     * and PreparedStatement interface.
     * 
     * @throws ClassNotFoundException
     */
    public static void addBatchOfDataPreStmt() throws ClassNotFoundException {

	// Parameters in PreparedStatement
	String firstNOne = "fNameOne";
	String lastNOne = "lNameOne";

	String firstNTwo = "fNameTwo";
	String lastNTwo = "lNameTwo";

	String firstNThree = "fNameThree";
	String lastNThree = "lNameThree";

	try (Connection conn = DriverManager.getConnection(DB_URL, NAME, PW)) {

	    // use preparedStatement to achieve batch processing
	    PreparedStatement preStmt = conn
		    .prepareStatement("INSERT INTO customer (firstName, lastName) VALUES(?, ?)");

	    // add the three queries into the batch
	    preStmt.setString(1, firstNOne);
	    preStmt.setString(2, lastNOne);
	    preStmt.addBatch();

	    preStmt.setString(1, firstNTwo);
	    preStmt.setString(2, lastNTwo);
	    preStmt.addBatch();
	    
	    preStmt.setString(1, firstNThree);
	    preStmt.setString(2, lastNThree);
	    preStmt.addBatch();
	    
	    // execute the batch
	    preStmt.executeBatch();
	    
	    System.out.println("PreparedStatement - Batch of Queries executed");
	    
	} catch (SQLException e) {
	    e.printStackTrace();
	}

    }
}
