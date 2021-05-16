/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xtravision.data.dtos;

/**
 *
 * @author Tiago
 */
public class Rent 
{
    private Integer id;
    private Integer movieId;
    private Movie movie;
    private Customer customer;
    private boolean returned;
    private Integer price;
    
    public Rent() {
    }

    public Rent(Integer movieId, Customer customer, boolean returned, Integer price) {
        this(null, movieId, customer, returned, price);
    }

    public Rent(Integer id, Integer movieId, Customer customer, boolean returned, Integer price) {
        this.id = id;
        this.movieId = movieId;
        this.customer = customer;
        this.returned = returned;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Rent{" + "id=" + id + ", movieId=" + movieId + ", movie=" + movie + ", customer=" + customer + ", returned=" + returned + ", price=" + price + '}';
    }
}
