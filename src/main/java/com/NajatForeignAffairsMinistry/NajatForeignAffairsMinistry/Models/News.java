package com.NajatForeignAffairsMinistry.NajatForeignAffairsMinistry.Models;

import javax.persistence.*;

@Entity
public class News extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    private String title;
    private String country;
    private String region;
    private String details;

    @ManyToOne  //many News, one policy
    @JoinColumn(name= "policies_id" ,referencedColumnName = "id")
    Policies policies;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
