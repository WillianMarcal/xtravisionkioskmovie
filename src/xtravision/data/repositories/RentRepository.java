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
import xtravision.data.dtos.Customer;
import xtravision.data.dtos.Rent;

/**
 *
 * @author Tiago
 */
public class RentRepository {
 
    public List<Rent> listAll() {
        List<Rent> rents = new ArrayList<>();
        
        try (Connection connection = ConnectionFactory.getConnection(); 
                Statement statement = connection.createStatement()) {
            
            statement.execute("select * from [movie_record] INNER join [movie] ON movie.id=movie_record.movie_id");
            
            try (ResultSet resultSet = statement.getResultSet()) {
                while(resultSet.next()) {
                    Customer customer = new Customer(resultSet.getString("customer_id"), resultSet.getString("customer_name"));
                    
                    Rent rent = new Rent(resultSet.getInt("record_no"), resultSet.getInt("id"), customer,
                            toBoolean(resultSet.getString("returned")), resultSet.getInt("price"));
                    
                    rents.add(rent);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(RentHomeScreenForm.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Error on retrieving all rents", ex);
        }
        return rents;
    }
    
    public Rent findById(Integer id) {
        Rent rent = null;
        
        String sql = "select * from movie_record where record_no = :id";
        try (Connection connection = ConnectionFactory.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            
            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                Customer customer = new Customer(resultSet.getString("customer_id"), resultSet.getString("customer_name"));
                rent = new Rent(resultSet.getInt("record_no"), resultSet.getInt("Movie_id"), customer,
                            toBoolean(resultSet.getString("returned")), resultSet.getInt("total_amount"));
            }
        } 
        catch (SQLException ex) {
            Logger.getLogger(RentHomeScreenForm.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(String.format("Error on retrieving a rent by id: %s", id), ex);
        }
        return rent;
    }
    
    public boolean save(Rent rent) {
        
        try (Connection connection = ConnectionFactory.getConnection(); 
                Statement statement = connection.createStatement()) {
            
            String sql = String.format("INSERT INTO movie_record(movie_id,customer_name,customer_id,returned,movie_purchased,total_amount) VALUES (%s,'%s','%s','false',1,%s)", 
                    rent.getMovieId(), rent.getCustomer().getName(), rent.getCustomer().getId(), rent.getPrice());
            
            boolean result = statement.execute(sql);
            System.out.println("data is inserted");
            
            return result;
        } 
        catch (SQLException ex) {
            Logger.getLogger(RentHomeScreenForm.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Error on saving the rent", ex);
        }
    }
    
    public boolean deleteById(Integer id) {
        
        String sql = "DELETE FROM movie_record WHERE record_no = :id";
        
        try (Connection connection = ConnectionFactory.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            
            preparedStatement.setInt(1, id);
            
            boolean result = preparedStatement.execute();
            
            return result;
        }catch (SQLException ex) {
            Logger.getLogger(RentHomeScreenForm.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Error on Deleting a rent by id", ex);
        }
    }
    
    private boolean toBoolean(String value) {
        try {
            return Boolean.parseBoolean(value);
        } catch (Exception e) {
            return false;
        }
    }
}
