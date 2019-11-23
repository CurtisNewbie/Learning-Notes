package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * DB connection using JDBC
 * 
 * @author Curtis
 *
 */
public class JDBCConnection {

    /**
     * 5 Steps to connect database using JDBC<br>
     * 1. Register Driver class<br>
     * 2. Create connection<br>
     * 3. Create statement<br>
     * 4. Execute queries<br>
     * 5. Close connection<br>
     */

    /**
     * DriverManager class
     * 
     * The DriverManager class acts as an interface between user and drivers. It
     * keeps track of the drivers that are available and handles establishing a
     * connection between a database and the appropriate driver.
     *
     * Basically DriverManager is responsible for registering given driver and
     * creating Connection.
     * 
     */

    /**
     * Connection interface
     * 
     * A connection is the session between java application and database. The
     * Connection interface is a factory of Statement, PreparedStatement, and
     * DatabaseMetaData.
     * 
     * Connection object can be used to get Statement objects and DatabaseMetaData
     * objects. Example of methods in Connection interface includes: commit(),
     * rollback() etc.
     */

    /**
     * Statement interface
     * 
     * Statement interface provides methods to execute queries with the database.
     * executeQuery method is for SELECT query. For example, executeUpdate method is
     * for create, drop, insert, update, delete queries.
     */

    /**
     * ResultSet interface
     * 
     * ResultSet interface maintains a cursor pointing to a row of the table that is
     * returned by the queries. Methods for moving the cursor back and forth
     * include: next(), previous(), first(), last() etc.
     */

    /**
     * PreparedStatement interface
     * 
     * PreparedStatement interface is a sub-interface of Statement interface. It is
     * used to execute parameterised query. Parameterised query: e.g., "INSERT INTO
     * table VALUES (?, ?, ?)"
     * 
     * Using PreparedStatement interface improves performance if query is compiled
     * only once. Instance of PreparedStatement is created using prepareStatement
     * method by Connection object. Using methods such as setInt(int paramIndex, int
     * value), setString(... , ....) to set parameters, and call executeUpdate() or
     * executeQuery() method to get resultSet or update db.
     */

    public static final String DBMS = "mysql";
    public static final String LOCALHOST = "localhost";
    public static final String port = "3306";
    public static final String DB = "hiber";
    public static final String NAME = "hiber";
    public static final String PW = "hiberPW";

    public static final String DB_URL = "jdbc" + ":" + DBMS + "://" + LOCALHOST + ":" + port + "/" + DB;

    public static void main(String[] args) {
	// load the driver class
	try {

	    // example of getting resultSet by executing simple query.
	    getCustomers();

	    System.out.println("-----------------------------");

	    // example of using PreparedStatement to find an instance
	    findCurtis();

	    System.out.println("-----------------------------");

	    // example of using PreparedStatement to update attribute.
	    updateName();

	    System.out.println("-----------------------------");

	    // example of using PreparedStatement to insert rows.
	    insertNewCustomer();

	    System.out.println("-----------------------------");

	    // Example of removing an record in the database using preparedStatment
	    removeCustomer();

	} catch (ClassNotFoundException e) {
	    System.out.println("MYSQL JDBC Driver not found");
	}
    }

    /**
     * Example of the simple way to connect to the MYSQL and get ResultSet.
     * 
     * @throws ClassNotFoundException
     */
    public static void getCustomers() throws ClassNotFoundException {

	// 1. register driver class (optional)
	Class.forName("com.mysql.cj.jdbc.Driver");

	// 2. get connection
	try (Connection conn = DriverManager.getConnection(DB_URL, NAME, PW)) {

	    // 3. create statement
	    Statement stmt = conn.createStatement();

	    String query = "SELECT * from customer";

	    // 4. Execute query
	    ResultSet set = stmt.executeQuery(query);

	    System.out.println("[firstName:] " + "[lastName:] " + "[custId:]");
	    while (set.next()) {
		System.out.println(set.getString(1) + " " + set.getString(2) + " " + set.getInt(3));
	    }
	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

    }

    /**
     * Example of using PreparedStatement to get ResultSet.
     * 
     * @throws ClassNotFoundException
     */
    public static void findCurtis() throws ClassNotFoundException {

	// 1. register driver class
	Class.forName("com.mysql.cj.jdbc.Driver");

	// 2. Get connection
	try (Connection conn = DriverManager.getConnection(DB_URL, NAME, PW)) {

	    // 3. create preparedStatement
	    PreparedStatement preStmt = conn.prepareStatement("SELECT * FROM CUSTOMER WHERE firstName=?");

	    preStmt.setString(1, "Curtis");

	    ResultSet set = preStmt.executeQuery();
	    while (set.next()) {
		System.out.println(set.getString(1) + " " + set.getString(2) + " " + set.getInt(3));
	    }

	} catch (SQLException e) {
	    e.printStackTrace();
	}

    }

    /**
     * Example of using PreparedStatement to update attributes.
     * 
     * @throws ClassNotFoundException
     */
    public static void updateName() throws ClassNotFoundException {

	Class.forName("com.mysql.cj.jdbc.Driver");

	try {
	    Connection conn = DriverManager.getConnection(DB_URL, NAME, PW);

	    PreparedStatement preStmt = conn.prepareStatement("UPDATE CUSTOMER SET lastName=? WHERE custId=?");
	    preStmt.setString(1, "Sharon");
	    preStmt.setInt(2, 4);

	    preStmt.executeUpdate();

	    System.out.println("custId:4 name updated");

	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

    }

    /**
     * Example of using PreparedStatement to insert row.
     * 
     * @throws ClassNotFoundException
     */
    public static void insertNewCustomer() throws ClassNotFoundException {

	Class.forName("com.mysql.cj.jdbc.Driver");

	// connection
	try (Connection conn = DriverManager.getConnection(DB_URL, NAME, PW)) {

	    // preparedStatement
	    PreparedStatement preStmt = conn
		    .prepareStatement("INSERT INTO customer (lastName, firstName) VALUES (?,?)");
	    preStmt.setString(1, "Screw");
	    preStmt.setString(2, "Driver");

	    preStmt.executeUpdate();

	    System.out.println("New Customer (Screw Driver) Inserted");

	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    /**
     * Example of removing an record in the database using preparedStatment
     * 
     * @throws ClassNotFoundException
     */
    public static void removeCustomer() throws ClassNotFoundException {

	Class.forName("com.mysql.cj.jdbc.Driver");

	try (Connection conn = DriverManager.getConnection(DB_URL, NAME, PW)) {

	    PreparedStatement preStmt = conn.prepareStatement("DELETE FROM customer WHERE lastName=? AND firstName=?");
	    preStmt.setString(1, "Screw");
	    preStmt.setString(2, "Driver");

	    preStmt.executeUpdate();
	    
	    System.out.println("Customer (Screw Driver) DELETED");

	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

    }

}
