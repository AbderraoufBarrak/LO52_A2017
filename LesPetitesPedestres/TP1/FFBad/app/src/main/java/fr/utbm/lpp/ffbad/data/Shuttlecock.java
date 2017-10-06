package fr.utbm.lpp.ffbad.data;

public class Shuttlecock {
    private long id;
    private String brand;
    private String reference;
    private int rating;
    private int stock;
    private String icon;

    public Shuttlecock(long id, String brand, String reference, int rating, int stock) {
        setId(id);
        setIcon(brand + "-" + reference);
        setBrand(brand);
        setReference(reference);
        setRating(rating);
        setStock(stock);
    }

    public long getId() {
        return id;
    }

    private void setId(long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        if (rating < 0) {
            this.rating = 0;
        } else if (rating > 5) {
            this.rating = 5;
        } else {
            this.rating = rating;
        }
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        if (stock < 0) {
            this.stock = 0;
        } else {
            this.stock = stock;
        }
    }

    public void setIcon(String icon) {this.icon = icon;}

    public String getIcon() { return icon; }
}
