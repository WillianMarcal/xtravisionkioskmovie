/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xtravision.service;

import java.util.List;
import xtravision.data.dtos.Rent;
import xtravision.data.repositories.RentRepository;
import xtravision.utils.StringUtils;

/**
 *RentService:Class that concentrates all as business rules of a lease.
 * @author Tiago
 */
public class RentService {
    
    private final RentRepository rentRepository;
    
    private final MovieService movieService;

    public RentService() {
        this.rentRepository = new RentRepository();
        this.movieService = new MovieService();
    }
    /**
     * listAll: Returns all records.
     * @return 
     */
    public List<Rent> listAll() {
        List<Rent> rents = rentRepository.listAll();
        rents.forEach(rent -> {
            rent.setMovie(movieService.findById(rent.getMovieId()));
        });
        return rents;
    }
    /**
     * findById: Returns the record containing the specified ID.
     * @param id
     * @return 
     */
    public Rent findById(Integer id) {
        if (id == null) {
            throw new RuntimeException("Rent Id is required");
        }
        Rent rent = rentRepository.findById(id);
        
        rent.setMovie(movieService.findById(rent.getMovieId()));
        
        return rent;
    }
    /**
     * save: Validates the informed rent, decreases the amount of the film and saves the record.
     * @param rent
     * @return 
     */
    public boolean save(Rent rent) {
        
        validate(rent);
        
        movieService.decreaseById(rent.getMovieId());
        
        return rentRepository.save(rent);
    }
    /**
     * deleteById: Deletes a location that contains the specified ID and increases the available amount of the movie
     * @param id
     * @return 
     */
    public boolean deleteById(Integer id) {
        if (id == null) {
            throw new RuntimeException("Rent Id is required");
        }
        Rent rent = rentRepository.findById(id);
        
        movieService.increaseById(rent.getMovieId());
        
        return rentRepository.deleteById(id);
    }
       /**
        * validate: Validates whether the rent is valid to be saved.
        * @param rent 
        */
    private void validate(Rent rent) {
        StringBuilder errors = new StringBuilder();
        if (rent.getMovieId() == null) {
            errors.append("Movie ID is required");
        }
        if (rent.getCustomer() == null) {
            errors.append("Customer name is required");
            errors.append("Customer ID is required");
        } else {
            if (StringUtils.isNotBlank(rent.getCustomer().getName())) {
                errors.append("Customer name is required");
            }
            if (StringUtils.isNotBlank(rent.getCustomer().getId())) {
                errors.append("Customer ID is required");
            }
        }
        if (rent.getPrice() == null) {
            errors.append("Movie price is required");
        }
    }
}
