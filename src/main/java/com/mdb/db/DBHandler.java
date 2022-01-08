package com.mdb.db;

import java.sql.Connection;
import java.sql.DriverManager;
import com.mdb.util.DBMethod;

public class DBHandler {
	//LOCAL
	private static final String host = "127.0.0.1";
	private static final String port = "3306";
	private static final String database = "whatsthatmoviedb";
	private static final String username = "root";
	private static final String password = "";
	
	//HEROKU
	private static final String heroku_host = "ec2-107-21-222-42.compute-1.amazonaws.com";
	private static final String heroku_port = "5432";
	private static final String heroku_database = "d68c05uja742sb";
	private static final String heroku_username = "fgwyepitrhkyod";
	private static final String heroku_password = "384f256b5c9b6a2043e243e4acee63fa761fb05a76cb057137ce6091e1031986";
	
	private Connection conn = null;
	
	/**
	 * This method returns a usable JDBC database connection to be used by sub classes in the service/project.
	 * The type of RDB Connection can be requested via the parameter specification. The same DB can be hosted
	 * in several different services and the access can be provided easily based on the connection requested.
	 * 
	 * @param	dbmethod	The Type of RDB Connection
	 * 
	 * @return 	returns a valid SQL connection based on the given connection string
	 */
	public Connection getConnection(DBMethod dbmethod)
	{
		try
		{
			if (dbmethod== DBMethod.LOCAL_MYSQL) {
				Class.forName("com.mysql.cj.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+database, username, password);
			} else if (dbmethod== DBMethod.HEROKU_POSTGRES) {
				Class.forName("org.postgresql.Driver");
				conn = DriverManager.getConnection("jdbc:postgresql://"+heroku_host+":"+heroku_port+"/"+heroku_database, heroku_username, heroku_password);
			}
			
		}
		catch (Exception e)
		{e.printStackTrace();}
		return conn;
	}
}
