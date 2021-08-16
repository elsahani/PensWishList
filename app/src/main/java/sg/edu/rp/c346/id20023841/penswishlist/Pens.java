package sg.edu.rp.c346.id20023841.penswishlist;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Pens implements Serializable {
    private int _id;
    private String name;
    private String colour;
    private double price;
    private double nibsize;
    private int stars;

    public Pens(int _id, String name, String colour, double price, double nibsize, int stars) {
        this._id = _id;
        this.name = name;
        this.colour = colour;
        this.price = price;
        this.nibsize = nibsize;
        this.stars = stars;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public double getNibsize() {
        return nibsize;
    }

    public void setNibsize(float nibsize) {
        this.nibsize = nibsize;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int rating) {
        this.stars = rating;

    }

    @NonNull
    @Override
    public String toString() {
        String starsString = "";
        for (int i = 0; i < stars; i++) {
            starsString += "* ";
        }
        return starsString;
    }
}
