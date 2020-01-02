package com.curtisnewbie.model;

import io.swagger.annotations.ApiModel;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.*;

@ApiModel("Book Representation")

/**
 * Persistent Model
 */
@Entity
public class Book {

    @Id
    @GeneratedValue
    private Long id;

    // bean validation, it cannot be null, and have a len of 1-200
    @NotNull
    @Size(min = 1, max = 200)
    // use annotation to define size of this varchar
    @Column(length = 200)
    private String title;

    @Size(min = 1, max = 1000)
    @Column(length = 1000)
    private String description;

    @NotNull @Min(1)
    @Column(name = "unit_cost")
    private Float unitCost;

    @NotNull
    private String isbn;

    @Column(name = "publication_date")
    @Temporal(TemporalType.DATE)
    // it has to be in the past
    @Past
    private Date publicationDate;

    @Column(name = "num_of_pages")
    private Integer numOfPages;

    @Column(name = "image_url")
    private String imageUrl;

    private Language language;

    public Book() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(Float unitCost) {
        this.unitCost = unitCost;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Integer getNumOfPages() {
        return numOfPages;
    }

    public void setNumOfPages(Integer numOfPages) {
        this.numOfPages = numOfPages;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }
}
