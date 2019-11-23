package jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 * This class presents the example of working with stored procedures and
 * functions in MYSQL. This is done by using the CallableStatement interface.
 * 
 * @author Curtis
 *
 */
public class StoredProcedure {

    /**
     * CallableStatement interface
     * 
     * CallableStatement interface is used to call the stored procedures and
     * functions. stored procedures and functions are the sub-routine defined in
     * DBMS rather than JAVA. They can take parameters and executes in the DBMS.
     * 
     * Similar ideas are used in View, while view is for creating a table that makes
     * life easier. Stored procedures and functions are predefined for doing
     * something. Stored procedures are created to perform business logics, and
     * functions are used for calculation.
     * 
     */

    /**
     * Difference between stored procedures and functions
     * 
     * [Stored Procedure] ____________ [Function]<br>
     * business logic ________________ calculation<br>
     * must not have return type _____ must have return<br>
     * may return 0 or more values ___ may return only one value<br>
     * can call functions ____________ cannot call procedure<br>
     * support i/o param _____________ only support in param<br>
     * support exception handling ____ can't handle exception<br>
     * 
     * 
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
	    /*
	     * example of using CallableStatement interface to work with stored procedure to
	     * insert new Customer
	     */
	    insertCustomer();

	    /*
	     * example of using CallableStatement interface to work with stored function to
	     * calculate the sum.
	     */
	    selectCalSumFunc();

	} catch (ClassNotFoundException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

    }

    /**
     * This is the example of using the CallableStatement to use the stored
     * procedures in the database.
     * 
     * SQL query of this stored procedure is in project directory.
     * 
     * @throws ClassNotFoundException
     */
    public static void insertCustomer() throws ClassNotFoundException {

	Class.forName("com.mysql.cj.jdbc.Driver");

	try (Connection conn = DriverManager.getConnection(DB_URL, NAME, PW)) {

	    // call the stored procedure named "insertCutomer" takes two parameters.
	    CallableStatement stmt = conn.prepareCall("CALL insertCustomer(?,?)");
	    stmt.setString(1, "Laptop");
	    stmt.setString(2, "The Number Two");
	    stmt.execute();

	    System.out.println("Stored Procedure Executed.");

	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }

    /**
     * Example of call the stored function in MYSQL.
     * 
     * SQL query of this stored function is in project directory.
     * 
     * @throws ClassNotFoundException
     */
    public static void selectCalSumFunc() throws ClassNotFoundException {
	Class.forName("com.mysql.cj.jdbc.Driver");

	try (Connection conn = DriverManager.getConnection(DB_URL, NAME, PW)) {

	    // calculate the sum of two var
	    CallableStatement stmt = conn.prepareCall("SELECT calSum(?,?)");

	    stmt.setInt(1, 10);
	    stmt.setInt(2, 10);
	    
	    ResultSet set = stmt.executeQuery();
	    if (set.next())
		System.out.println("10 + 10 = " + set.getInt(1));

	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }

}
