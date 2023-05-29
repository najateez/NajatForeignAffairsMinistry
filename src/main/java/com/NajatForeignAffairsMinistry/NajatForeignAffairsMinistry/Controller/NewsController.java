package com.NajatForeignAffairsMinistry.NajatForeignAffairsMinistry.Controller;

import com.NajatForeignAffairsMinistry.NajatForeignAffairsMinistry.Models.News;
import com.NajatForeignAffairsMinistry.NajatForeignAffairsMinistry.Services.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
public class NewsController {

    @Autowired
    NewsService newsService;

    //add + update + delete -> for News Management (PUT).

    //add
    //exp: localhost:8080/api/news/{newsId}?title=USA and Canada sign new trade agreement&country=Canada&region=North America&details=The USA and Canada have signed a new trade agreement, promoting greater economic cooperation.
//    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "api/news/{newsId}", method = RequestMethod.PUT)
    public ResponseEntity<String> addNewsManagement(@RequestParam String title, @RequestParam String country, @RequestParam String region, @RequestParam String details) {

        newsService.addNewsManagement(title, country, region, details);

        return ResponseEntity.ok("News management added successfully.");
    }

 //   @PreAuthorize("hasRole('USER')")
    //update
    //exp: localhost:8080/api/news/{newsId}/updateNewsManagementById?id=2&title=shambaqbaq
    @RequestMapping(value = "api/news/{newsId}/updateNewsManagementById", method = RequestMethod.PUT)
    public ResponseEntity<String> updateNewsManagementById(@RequestParam Integer id, @RequestParam String title) {
        newsService.updateNewsManagementById(id, title);

        return ResponseEntity.ok("News management updated successfully.");
    }

    //delete
    //exp: localhost:8080/api/news/{newsId}/deleteNewsManagementById?id=1
//    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "api/news/{newsId}/deleteNewsManagementById", method = RequestMethod.PUT)
    public ResponseEntity<String> deleteNewsManagementById(@RequestParam Integer id) {
        newsService.deleteNewsManagementById(id);

        return ResponseEntity.ok("News management deleted successfully.");
    }
    //----------------------------------------------------------------------------------

    //get by country, region, or topic -> News Retrieval (GET).
    //getByColumnName:-
    //ResponseEntity (http status code) -> 200 OK
    //exp:localhost:8080/api/news/getNewsByCountry?country=canada
//    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "api/news/getNewsByCountry", method = RequestMethod.GET)
    public ResponseEntity<News> getNewsByCountry(@RequestParam String country) {
        News news = newsService.getNewsByCountry(country);
        return ResponseEntity.ok(news);
    }

    //exp: localhost:8080/api/news?region=North America
//    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "api/news", method = RequestMethod.GET)
    public ResponseEntity<News> getNewsByRegion(@RequestParam String region) {
        News newsRegion = newsService.getNewsByRegion(region);
        return ResponseEntity.ok(newsRegion);
    }
}
