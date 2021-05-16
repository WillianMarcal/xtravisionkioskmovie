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
 *
 * @author Willian
 */
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService() {
        this.movieRepository = new MovieRepository();
    }
    
    public List<Movie> listAll() {
        return movieRepository.listAll();
    }
    
    public Movie findById(Integer id) {
        if (id == null) {
            throw new RuntimeException("Movie Id is required");
        }
        return movieRepository.findById(id);
    }
    
    public boolean save(Movie movie) {
        
        validate(movie);
        
        return movieRepository.save(movie);
    }
    
    public void decreaseById(Integer id) {
        Movie movie = findById(id);
        
        int newMovieQuantity = movie.getQuantity() - 1;
        
        if (newMovieQuantity < 0) {
            throw new RuntimeException(String.format("There isn't movie with ID '%s' available", movie.getId()));
        }
        movieRepository.updateQuantity(id, newMovieQuantity);
    }
    
    public void increaseById(Integer id) {
        Movie movie = findById(id);
        
        int newMovieQuantity = movie.getQuantity() + 1;
        
        movieRepository.updateQuantity(id, newMovieQuantity);
    }
    
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
