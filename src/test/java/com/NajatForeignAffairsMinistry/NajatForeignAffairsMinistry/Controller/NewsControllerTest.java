package com.NajatForeignAffairsMinistry.NajatForeignAffairsMinistry.Controller;

import com.NajatForeignAffairsMinistry.NajatForeignAffairsMinistry.Models.News;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class NewsControllerTest {

    @Autowired
    NewsController newsController;

    @Test
    void addNewsManagement() {
        newsController.addNewsManagement("najateez","thailand","alkhatri","enjoooy");
    }

    @Test
    void updateNewsManagementById() {
        newsController.updateNewsManagementById(2,"Areen");
    }

    @Test
    void deleteNewsManagementById() {
        newsController.deleteNewsManagementById(1);
    }

    @Test
    void getNewsByCountry() {
        News country= newsController.getNewsByCountry("canada").getBody();
        Integer newsId=country.getId();
        assertEquals(1,newsId);
    }

    @Test
    void getNewsByRegion() {
        News region= newsController.getNewsByRegion("North America").getBody();
        Integer newsId=region.getId();
        assertEquals(1,newsId);
    }
}