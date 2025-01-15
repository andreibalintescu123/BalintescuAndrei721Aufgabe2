package Model;

import java.util.ArrayList;
import java.util.List;

public class Character implements HasID{
    private int id;
    private String name;
    private String origin;
    List<Product> products;
    public Character(int id, String name, String origin, List<Product> products) {
        this.id = id;
        this.name = name;
        this.origin = origin;
        this.products = products;
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
