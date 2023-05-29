package com.NajatForeignAffairsMinistry.NajatForeignAffairsMinistry.Services;

import com.NajatForeignAffairsMinistry.NajatForeignAffairsMinistry.Models.News;
import com.NajatForeignAffairsMinistry.NajatForeignAffairsMinistry.Repositories.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class NewsService {

    @Autowired
    NewsRepository newsRepository;

    //add
    public void addNewsManagement (@RequestParam String title, @RequestParam String country, @RequestParam String region, @RequestParam String details) {

        News newsManagementObj = new News();
        newsManagementObj.setTitle(title);
        newsManagementObj.setCountry(country);
        newsManagementObj.setRegion(region);
        newsManagementObj.setDetails(details);
        newsRepository.save(newsManagementObj);
    }

    //update
    public void updateNewsManagementById(Integer id,String title) {

        newsRepository.updateNewsManagementById(id,title);

    }

    //deleteById --> makeIsActiveFalse
    public void deleteNewsManagementById(Integer id) {

        newsRepository.deleteNewsManagementById(id);
    }

    //getNewsByCountry
    public News getNewsByCountry(String country) {
        News news = newsRepository.getNewsByCountry(country);
        return news;
    }

    //getNewsByRegion
    public News getNewsByRegion(String region) {
        News newsRegion = newsRepository.getNewsByRegion(region);
        return newsRegion;
    }

    //--------------------------------------
    // for cron controller:-
    public News saveNews(News news) {

        return news;
    }
}
