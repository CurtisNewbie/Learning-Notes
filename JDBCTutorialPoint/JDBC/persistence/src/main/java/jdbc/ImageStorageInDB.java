package jdbc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class is the example of how to handle image in database.
 * 
 * @author Curtis
 *
 */
public class ImageStorageInDB {

    /**
     * Images can be stored in to the database in java using PreparedStatement
     * interface using the setBinaryStream method.
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

	    // Example of Storing image/blob into database
	    storeImage();

	    System.out.println("-----------------------------------");

	    // Example of retrieving image/blob from database
	    retriveImage();

	} catch (ClassNotFoundException e) {
	    System.out.println("Driver not found");
	}

    }

    /**
     * Example of storing image/blob in database using PreparedStatement and
     * BinaryStream.
     * 
     * @throws ClassNotFoundException
     */
    public static void storeImage() throws ClassNotFoundException {

	Class.forName("com.mysql.cj.jdbc.Driver");

	try (Connection conn = DriverManager.getConnection(DB_URL, NAME, PW)) {

	    PreparedStatement stmt = conn.prepareStatement("INSERT INTO image (img_data) VALUES (?)");

	    // get image file
	    FileInputStream fileIn = new FileInputStream("C:/Users/Curtis/Desktop/d.PNG");

	    // set the fileInputStream to this parameterised statement.
	    stmt.setBinaryStream(1, fileIn);

	    stmt.executeUpdate();

	    System.out.println("Image stored");

	} catch (SQLException e) {
	    e.printStackTrace();
	} catch (FileNotFoundException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    /**
     * Example of retrieving image/blob from database
     * 
     * @throws ClassNotFoundException
     */
    public static void retriveImage() throws ClassNotFoundException {

	Class.forName("com.mysql.cj.jdbc.Driver");

	try (Connection conn = DriverManager.getConnection(DB_URL, NAME, PW)) {

	    PreparedStatement stmt = conn.prepareStatement("SELECT img_data FROM image WHERE img_no = 1");

	    ResultSet rs = stmt.executeQuery();

	    if (rs.next()) {
		Blob b = rs.getBlob(1);
		byte[] data = b.getBytes(1, (int) b.length());

		try (FileOutputStream fileOut = new FileOutputStream("C:/Users/Curtis/Desktop/b.PNG")) {

		    fileOut.write(data);

		} catch (IOException e) {
		    e.printStackTrace();
		}
	    }

	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }
}
