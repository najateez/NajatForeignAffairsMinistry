package com.NajatForeignAffairsMinistry.NajatForeignAffairsMinistry.Repositories;

import com.NajatForeignAffairsMinistry.NajatForeignAffairsMinistry.Models.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface NewsRepository extends JpaRepository<News, Integer> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE News s SET s.title=:title WHERE s.id =:id")
    void updateNewsManagementById(@Param("id") Integer id, @Param("title") String title);

    //deleteById --> makeIsActiveFalse
    @Modifying
    @Transactional
    @Query(value = "UPDATE News s SET s.isActive = false WHERE s.id =:id")
    void deleteNewsManagementById(@Param("id") Integer id);

    //getByColumnName :-  (column name depend on what you write in model package).
    @Query(value = "SELECT s from News s where s.country =:country")
    News getNewsByCountry(@Param("country") String country);

    @Query(value = "SELECT s from News s where s.region =:region")
    News getNewsByRegion(@Param("region") String region);
}
