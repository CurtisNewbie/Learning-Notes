package com.curtisnewbie.model;

/**
 * Representation of Book data model
 */
public class Book {

    private String title;
    private Float price;
    private String description;
    private String number;

    public Book() {

    }

    /**
     * @param title   Title
     * @param price   Price
     * @param descrip Description
     */
    public Book(String title, float price, String descrip) {
        this.title = title;
        this.price = price;
        this.description = descrip;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Book{" +
                "\n      title='" + title + '\'' +
                ", \n      price=" + price +
                ", \n      description='" + description + '\'' +
                ", \n      number='" + number + '\'' +
                '}';
    }
}
