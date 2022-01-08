package com.mdb.db;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mdb.api.comm.MLServiceCommHandler;
import com.mdb.util.DBMethod;
import com.mdb.util.JsonResponseBuilder;
import com.mdb.util.Resources;

public class MovieDBHandler extends DBHandler{
	MLServiceCommHandler mlServiceCommHandler;
	
	public JsonObject insertMovie(String name, String genre, String year, String desc, String thumbnail) {
		try {
			Connection conn = getConnection(DBMethod.HEROKU_POSTGRES);
			if (conn == null) {
				return new JsonResponseBuilder().getJsonErrorResponse("Operation has been terminated due to a database connectivity issue.");
			}

			String query = "INSERT INTO movies(moviename, \"desc\", genre, \"year\", thumbnail) VALUES(?,?,?,?,null);";
			PreparedStatement preparedStmt = conn.prepareStatement(query);

			preparedStmt.setString(1, name);
			preparedStmt.setString(2, desc);
			preparedStmt.setString(3, genre);
			preparedStmt.setString(4, year);

			int status = preparedStmt.executeUpdate();
			conn.close();

			if(status > 0) {
				return new JsonResponseBuilder().getJsonSuccessResponse("Movie details inserted successfully");
			} else {
				return new JsonResponseBuilder().getJsonFailedResponse("Error inserting the movie details");
			}
		}
		catch (Exception ex) {
			System.err.println(ex.getMessage());
			return new JsonResponseBuilder().getJsonExceptionResponse("Error occurred while inserting movie details. Exception Details:" + ex.getMessage());
		}
	}
	
	public JsonObject getMovies() {		
		JsonObject result = null;
		JsonArray resultArray = new JsonArray();

		try
		{
			Connection conn = getConnection(DBMethod.HEROKU_POSTGRES);
			if (conn == null) {
				return new JsonResponseBuilder().getJsonErrorResponse("Operation has been terminated due to a database connectivity issue."); 
			}

			// LIMITING
			//String query = "SELECT * FROM movies ORDER BY RAND() LIMIT 9;";
			//String query = "SELECT * FROM movies LIMIT 9;";
			//String query = "SELECT * FROM movies WHERE movieid=200119;";
			
			// NON LIMITING
			String query = "SELECT * FROM movies;";
			
			PreparedStatement preparedStmt = conn.prepareStatement(query);

			ResultSet rs = preparedStmt.executeQuery();

			if(!rs.isBeforeFirst()) {
				return new JsonResponseBuilder().getJsonFailedResponse("No Movies found.");
			}

			while (rs.next())
			{
				JsonObject recordObject = new JsonObject();
				recordObject.addProperty("movieid", rs.getString("movieid"));
				recordObject.addProperty("name", rs.getString("moviename"));
				recordObject.addProperty("year", rs.getString("year"));
				recordObject.addProperty("genre", rs.getString("genre"));
				recordObject.addProperty("desc", rs.getString("desc"));
				
				//byte[] blobbytes = rs.getBytes("thumbnail");
				//recordObject.addProperty("thumbnail", new String(blobbytes));
				
				byte[] thumbnail = getThumbBytes(rs.getString("moviename"));
				recordObject.addProperty("thumbnail", new String(thumbnail));
				
				resultArray.add(recordObject);
				
			}
			conn.close();

			result = new JsonObject();

			result.add("movies", resultArray);

		}
		catch (Exception ex)
		{
			result = new JsonResponseBuilder().getJsonExceptionResponse("Error occurred while retrieving movie statistics. Exception Details:" + ex.getMessage());
			System.err.println(ex.getMessage());
		}
		return result;
	}
	
	public JsonObject getMovie(String movieId) {		
		JsonObject result = null;
		JsonObject movie = new JsonObject();

		try
		{
			Connection conn = getConnection(DBMethod.HEROKU_POSTGRES);
			if (conn == null) {
				return new JsonResponseBuilder().getJsonErrorResponse("Operation has been terminated due to a database connectivity issue."); 
			}

			String query = "SELECT * FROM movies WHERE movieid=?;";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			
			preparedStmt.setString(1, movieId);

			ResultSet rs = preparedStmt.executeQuery();

			if(!rs.isBeforeFirst()) {
				return new JsonResponseBuilder().getJsonFailedResponse("No Movies found under the given ID.");
			}

			while (rs.next())
			{
				movie.addProperty("movieid", rs.getString("movieid"));
				movie.addProperty("name", rs.getString("moviename"));
				movie.addProperty("year", rs.getString("year"));
				movie.addProperty("genre", rs.getString("genre"));
				movie.addProperty("desc", rs.getString("desc"));
				
				byte[] thumbnail = getThumbBytes(rs.getString("moviename"));
				movie.addProperty("thumbnail", new String(thumbnail));

			}
			conn.close();

			result = new JsonObject();
			result.add("movieinfo", movie);
		}
		catch (Exception ex)
		{
			result = new JsonResponseBuilder().getJsonExceptionResponse("Error occurred while retrieving movie data. Exception Details:" + ex.getMessage());
			System.err.println(ex.getMessage());
		}
		return result;
	}

	public JsonObject updateMovie(String movieid, String name, String genre, String desc, String year, String thumbnail) {
		JsonObject result = null;
		
		try {
			Connection conn = getConnection(DBMethod.HEROKU_POSTGRES);
			if (conn == null) {
				return new JsonResponseBuilder().getJsonErrorResponse("Operation has been terminated due to a database connectivity issue."); 
			}

			String query = "UPDATE movies SET moviename=?, genre=?, \"desc\"=?, \"year\"=?, thumbnail=null WHERE movieid=?;";
			PreparedStatement preparedStmt = conn.prepareStatement(query);

			preparedStmt.setInt(5, Integer.parseInt(movieid));
			preparedStmt.setString(1, name);
			preparedStmt.setString(2, genre);
			preparedStmt.setString(3, desc);
			preparedStmt.setString(4, year);

			int status = preparedStmt.executeUpdate();
			conn.close();

			result = new JsonObject();

			if(status > 0) {
				result = new JsonResponseBuilder().getJsonSuccessResponse("Movie " + movieid + " was updated Successfully.");
			} else {
				result = new JsonResponseBuilder().getJsonFailedResponse("Unable to update the movie " + movieid);
			}
		}
		catch (Exception ex) {
			result = new JsonResponseBuilder().getJsonExceptionResponse("Error occurred while updating movie " +movieid + ". Exception Details:" + ex.getMessage());
			System.err.println(ex.getMessage());
		}
		return result;
	}

	public JsonObject deleteMovie(String movieid) {
		try {
			Connection conn = getConnection(DBMethod.HEROKU_POSTGRES);
			if (conn == null) {
				return new JsonResponseBuilder().getJsonErrorResponse("Operation has been terminated due to a database connectivity issue.");
			}

			String query = "DELETE FROM movies WHERE movieid=?;";
			PreparedStatement preparedStmt = conn.prepareStatement(query);

			preparedStmt.setInt(1, Integer.parseInt(movieid));

			int status = preparedStmt.executeUpdate();
			conn.close();

			if(status > 0) {
				return new JsonResponseBuilder().getJsonSuccessResponse("Movie " + movieid + " deleted successfully.");
			} else {
				return new JsonResponseBuilder().getJsonFailedResponse("Unable to find the Movie " + movieid);
			}
		}
		catch (Exception ex) {
			System.err.println(ex.getMessage());
			return new JsonResponseBuilder().getJsonExceptionResponse("Error occurred while deleting Movie " + movieid + ". Exception Details:" + ex);
		}
	}
	
	public byte[] getThumbBytes(String name) throws FileNotFoundException, IOException {
		try {
			String thumbname = name.replaceAll("[-+^:#@!~` '\"\\/+-.,)(&%$]*", "");
			System.out.println("Thumbname is: " + thumbname);
			Path path = Paths.get("C:\\whatsthatmovie_thumbnails\\" + thumbname);
		    return Files.readAllBytes(path);
		} catch (IOException ex) {
			return Resources.defaultThumbnail.getBytes();
		}
	}
}
