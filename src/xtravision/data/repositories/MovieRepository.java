/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xtravision.data.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import xtravision.view.RentHomeScreenForm;
import xtravision.data.dtos.Movie;

/**
 * Movie data repository responsible for concentrating
 *manipulations with the database for the table:Movie.
 * 
 * @author Willian
 */
public class MovieRepository {
    /**
     * listAll:Returns all record.
     * @return 
     */
    public List<Movie> listAll() {
        List<Movie> movies = new ArrayList<>();
        
        try (Connection connection = ConnectionFactory.getConnection(); 
                Statement statement = connection.createStatement()) {
            
            statement.execute("select * from [Movie]");
            
            try (ResultSet resultSet = statement.getResultSet()) {
                while(resultSet.next()) {
                    Movie movie = new Movie(resultSet.getInt("id"), resultSet.getString("movie"),
                            resultSet.getInt("price"), resultSet.getInt("quantity"));
                    
                    movies.add(movie);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(RentHomeScreenForm.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Error on retrieving all movies", ex);
        }
        return movies;
    }
    /**
     * findById: Returns the record containing the specified ID.
     * @param id
     * @return 
     */
    public Movie findById(Integer id) {
        Movie movie = null;
        String sql = "select * from [Movie] where id = :id";
        try (Connection connection = ConnectionFactory.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            
            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                movie = new Movie();
                movie.setName(resultSet.getString("movie"));
                movie.setPrice(resultSet.getInt("price"));
                movie.setQuantity(resultSet.getInt("quantity"));
                movie.setId(resultSet.getInt("id"));
            }
        } 
        catch (SQLException ex) {
            Logger.getLogger(RentHomeScreenForm.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Error on retrieving a movie by id", ex);
        }
        return movie;
    }
 /**
  * Save: Saves the record.
  * @param movie
  * @return 
  */
    public boolean save(Movie movie) {
        
        try (Connection connection = ConnectionFactory.getConnection(); 
                Statement statement = connection.createStatement()) {
            
            String sql = String.format("INSERT INTO Movie(movie, price, quantity) VALUES ('%s', '%s', '%s')", 
                    movie.getName(), movie.getPrice(), movie.getQuantity());
            
            boolean result = statement.execute(sql);
            System.out.println("data is inserted");
            
            return result;
        } 
        catch (SQLException ex) {
            Logger.getLogger(RentHomeScreenForm.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Error on saving the movie", ex);
        }
    }
    /**
     * UpdateQuantity:updates the number of movies available.
     * @param id
     * @param quantity 
     */
    public void updateQuantity(Integer id, Integer quantity) {
        
        try (Connection connection = ConnectionFactory.getConnection(); 
                Statement statement = connection.createStatement()) {
            
            String sql = String.format("Update movie set quantity = %s where id = %s;", quantity, id); // to be chekced 
            statement.executeUpdate(sql);
        } 
        catch (SQLException ex) {
            Logger.getLogger(RentHomeScreenForm.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Error on updating the movie quantity", ex);
        }
    }
}
