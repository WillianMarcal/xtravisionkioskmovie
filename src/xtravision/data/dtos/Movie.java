/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xtravision.data.dtos;

/**
 *
 * @author Willian
 */
public class Movie {
    
    private Integer id;
    private String name;
    private Integer price;
    private Integer quantity;
    
    public Movie() {
        super();
    }
    
    public Movie(String name, Integer price, Integer quantity) {
        this(null, name, price, quantity);
    }

    public Movie(Integer id, String name, Integer price, Integer quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Movie{" + "name=" + getName() + ", price=" + getPrice() + ", quantity=" + getQuantity() + '}';
    }
}
