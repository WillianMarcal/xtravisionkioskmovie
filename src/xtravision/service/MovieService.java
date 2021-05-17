/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xtravision.service;

import java.util.List;
import xtravision.data.dtos.Movie;
import xtravision.data.repositories.MovieRepository;
import xtravision.utils.StringUtils;

/**
 *MovieService: Class that concentrates all the business rules of a movie.
 * @author Willian
 */
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService() {
        this.movieRepository = new MovieRepository();
    }
    /**
     * listAll: Returns all records.
     * @return 
     */
    public List<Movie> listAll() {
        return movieRepository.listAll();
    }
    /**
     * findById: Returns the record containing the specified ID.
     * @param id
     * @return 
     */
    public Movie findById(Integer id) {
        if (id == null) {
            throw new RuntimeException("Movie Id is required");
        }
        return movieRepository.findById(id);
    }
    /**
     * save: Validates the informed movie and saves the record.
     * @param movie
     * @return 
     */
    public boolean save(Movie movie) {
        
        validate(movie);
        
        return movieRepository.save(movie);
    }
    /**
     * decreaseById: Decreases the available quantity of the movie with the specified ID.
     * @param id 
     */
    public void decreaseById(Integer id) {
        Movie movie = findById(id);
        
        int newMovieQuantity = movie.getQuantity() - 1;
        
        if (newMovieQuantity < 0) {
            throw new RuntimeException(String.format("There isn't movie with ID '%s' available", movie.getId()));
        }
        movieRepository.updateQuantity(id, newMovieQuantity);
    }
    /**
     * increaseByld: Increments the available quantity of the movie with the specified ID.
     * @param id 
     */
    public void increaseById(Integer id) {
        Movie movie = findById(id);
        
        int newMovieQuantity = movie.getQuantity() + 1;
        
        movieRepository.updateQuantity(id, newMovieQuantity);
    }
    /**
     * validate: Validates whether the movie is valid for saving.
     * @param movie 
     */
    private void validate(Movie movie) {
        StringBuilder errors = new StringBuilder();
        if (StringUtils.isNotBlank(movie.getName())) {
            errors.append("Movie name is required\n");
        }
        if (movie.getPrice()== null) {
            errors.append("Movie price is required\n");
        }
        if (movie.getQuantity()== null) {
            errors.append("Movie quantity is required\n");
        }
        if (StringUtils.isNotBlank(errors.toString())) {
            throw new RuntimeException(errors.toString());
        }
    }
}
