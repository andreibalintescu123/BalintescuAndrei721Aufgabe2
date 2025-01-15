package Model;

import java.util.ArrayList;
import java.util.List;

public class Character implements HasID{
    private int id;
    private String name;
    private String origin;
    List<Product> products;
    public Character(int id, String name, String origin) {
        this.id = id;
        this.name = name;
        this.origin = origin;
        this.products = new ArrayList<>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getOrigin() {
        return origin;
    }

    public String getName() {
        return name;
    }

    public List<Product> getProducts() {
        return products;
    }

    @Override
    public Integer getID() {
        return id;
    }

    @Override
    public String toString(){
        return id + " " + name + " " + origin;
    }
}
