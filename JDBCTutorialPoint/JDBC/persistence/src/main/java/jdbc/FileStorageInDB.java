package jdbc;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;

/**
 * More examples on how to store and retrieve files in DB.
 * 
 * 
 * @author Curtis
 *
 */
public class FileStorageInDB {

    public static final String DBMS = "mysql";
    public static final String LOCALHOST = "localhost";
    public static final String port = "3306";
    public static final String DB = "hiber";
    public static final String NAME = "hiber";
    public static final String PW = "hiberPW";

    public static final String DB_URL = "jdbc" + ":" + DBMS + "://" + LOCALHOST + ":" + port + "/" + DB;

    public static void main(String[] args) {

	try {
	    // an example of creating a table
	    createTable();

	    // This is an example of reading texts from files and save files in database.
	    saveFile();

	    // an example of retrieving file from db.
	    retrieveFile();
	} catch (ClassNotFoundException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

    }

    /**
     * This method creates a new table.
     * 
     * @throws ClassNotFoundException
     */
    public static void createTable() throws ClassNotFoundException {

	Class.forName("com.mysql.cj.jdbc.Driver");

	try (Connection conn = DriverManager.getConnection(DB_URL, NAME, PW)) {

	    Statement stmt = conn.createStatement();

	    // check if there is already a table called file
	    ResultSet table = stmt.executeQuery("SHOW TABLES");

	    boolean hasTable = false;
	    while (table.next()) {
		if (table.getString(1).equals("file")) {
		    hasTable = true;
		}
	    }

	    // if not having this table, create this table
	    if (!hasTable) {

		// for testing only
		String tableQuery = "CREATE TABLE file( fileName VARCHAR(15), data TEXT(100000) NOT NULL, PRIMARY KEY (fileName));";

		stmt.executeUpdate(tableQuery);

		System.out.println("'file' table created");
	    } else {
		System.out.println("'file' table exists");
	    }

	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }

    /**
     * This is an example of reading texts from files and save files in database.
     * This example uses the table 'file' that is created by the creatTable()
     * method.
     * 
     * @throws ClassNotFoundException
     */
    public static void saveFile() throws ClassNotFoundException {

	Class.forName("com.mysql.cj.jdbc.Driver");

	try (Connection conn = DriverManager.getConnection(DB_URL, NAME, PW)) {

	    // using the table - file as an example
	    PreparedStatement stmt = conn.prepareStatement("INSERT INTO file (fileName, data) VALUES(?,?)");

	    // set parameters
	    File file = new File("src/main/resources/fruits.txt");
	    try (FileReader fileIn = new FileReader(file)) {

		stmt.setString(1, file.getName());
		stmt.setCharacterStream(2, fileIn);
		stmt.executeUpdate();
		
		System.out.println("File saved");

	    } catch (IOException e) {
		e.printStackTrace();
	    }

	} catch (SQLException e) {
	    
	    if(e instanceof SQLIntegrityConstraintViolationException)
		System.out.println("Entity Integrity Violated - this file is already in the database");
	}
    }

    /**
     * This is an example of retrieving texts from database and save these text in
     * files.This example uses the table 'file' that is created by the creatTable()
     * method.
     * 
     * @throws ClassNotFoundException
     */
    public static void retrieveFile() throws ClassNotFoundException {

	Class.forName("com.mysql.cj.jdbc.Driver");

	try (Connection conn = DriverManager.getConnection(DB_URL, NAME, PW)) {

	    Statement stmt = conn.createStatement();
	    ResultSet set = stmt.executeQuery("SELECT data FROM file WHERE fileName LIKE 'fruits%'");

	    // cos we already know there is only one record in the db.
	    if (set.next()) {

		// Clob for textual data - TEXT datatype in mysql
		Clob clob = set.getClob(1);

		try (Reader reader = clob.getCharacterStream()) {

		    StringBuffer buffer = new StringBuffer();

		    // reader read char as int
		    int c;
		    while ((c = reader.read()) != -1) {
			buffer.append((char) c);
		    }

		    try (FileWriter writer = new FileWriter("src/main/resources/Outfruits.txt")) {

			writer.write(buffer.toString());
			
			System.out.println("File read");

		    } catch (IOException e) {
			e.printStackTrace();
		    }

		} catch (IOException e1) {
		    // TODO Auto-generated catch block
		    e1.printStackTrace();
		}

	    }

	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }
}
