package Model;

public class Product implements HasID{
    private int id;
    private String name;
    private double price;
    private String region;

    public Product(int id, String name, double price, String region) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.region = region;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getRegion() {
        return region;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Override
    public Integer getID() {
        return id;
    }
    @Override
    public String toString(){
        return id + " " + name + " " + price + " " + region;
    }
}
