package com.NajatForeignAffairsMinistry.NajatForeignAffairsMinistry.Models;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Dataa {

    //for jasper report
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    private Integer totalRequests;
    private Integer totalErrors;
    private Integer responseTime;

    @ManyToOne //many Dataa, one News
    @JoinColumn(name= "news_id" ,referencedColumnName = "id")
    News news;

    @ManyToOne  //many Dataa, one policy
    @JoinColumn(name= "policies_id" ,referencedColumnName = "id")
    Policies policies;

    @CreatedDate
    Date startDate; //for jasper report
    @UpdateTimestamp
    Date endDate;  //for jasper report


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTotalRequests() {
        return totalRequests;
    }

    public void setTotalRequests(Integer totalRequests) {
        this.totalRequests = totalRequests;
    }

    public Integer getTotalErrors() {
        return totalErrors;
    }

    public void setTotalErrors(Integer totalErrors) {
        this.totalErrors = totalErrors;
    }

    public Integer getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(Integer responseTime) {
        this.responseTime = responseTime;
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

    public Policies getPolicies() {
        return policies;
    }

    public void setPolicies(Policies policies) {
        this.policies = policies;
    }

    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
