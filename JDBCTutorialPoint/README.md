# JAVA_DB Tutorial Points

https://www.javatpoint.com/java-jdbc

[Created on: 27th July 2019]

<h1>What this directory covers:</h1>

<ul>
  <li>Java Database Connectivity (JDBC) - MySQL 
     <ol>
       <li><a href="https://github.com/CurtisNewbie/JAVA_DB/blob/master/Java_DB/persistence/src/main/java/jdbc/JDBCConnection.java">Basic JDBC [DriverManager-> Connection-> Statement/PreparedStatement-> ResultSet]</a></li>
       <li><a href="https://github.com/CurtisNewbie/JAVA_DB/blob/master/Java_DB/persistence/src/main/java/jdbc/FileStorageInDB.java">File Storing and Retrieving using I/O Stream (CharacterStream) and Clob</a></li>
       <li><a href="https://github.com/CurtisNewbie/JAVA_DB/blob/master/Java_DB/persistence/src/main/java/jdbc/ImageStorageInDB.java">Image/Blob Storing and retriving</a></li>
       <li><a href="https://github.com/CurtisNewbie/JAVA_DB/blob/master/Java_DB/persistence/src/main/java/jdbc/MetaDataExample.java">MetaData for database and ResultSet</a></li>
       <li><a href="https://github.com/CurtisNewbie/JAVA_DB/blob/master/Java_DB/persistence/src/main/java/jdbc/StoredProcedure.java">Callable interface for StoredProcedure and StoredFunction</a></li>
       <li><a href="https://github.com/CurtisNewbie/JAVA_DB/blob/master/Java_DB/persistence/src/main/java/jdbc/BatchProcessingExample.java">BatchProcessing in Statement and PreparedStatement</a></li>
       <li><a href="https://github.com/CurtisNewbie/JAVA_DB/blob/master/Java_DB/persistence/src/main/java/jdbc/JdbcRowSetExample.java">JDBC RowSet interface</a></li> 
       <li><a href="https://github.com/CurtisNewbie/JAVA_DB/blob/master/Java_DB/MYSQL%20Queries.sql">MYSQL queries for database, privilege, and tables</a></li>
       <li><a href="https://github.com/CurtisNewbie/JAVA_DB/blob/master/Java_DB/StoredFunction.sql">MYSQL stored function queries</a></li>
       <li><a href="https://github.com/CurtisNewbie/JAVA_DB/blob/master/Java_DB/StoredProcedure.sql">MYSQL stored procedures queries</a></li>  
    </ol>
  </li>
</ul>

<h1>Troubleshooting</h1>

<ul>
  <li>The MYSQL server's time zone must be set, or else the JDBC will fail to connect to the DBMS. To set the time zone, go to MYSQL: <br> check timezone: <br>--SELECT @@global.time_zone; <br>--SELECT @@session.time_zone; <br> set time zone: <br>--SET @@global.time_zone='+1:00'; <br>--SET @@session.time_zone = '+1:00' (ref:<a href="https://stackoverflow.com/questions/19023978/should-mysql-have-its-timezone-set-to-utc">Time Zone StackOverflow</a>) </li>
</ul>
