package com.NajatForeignAffairsMinistry.NajatForeignAffairsMinistry.Repositories;

import com.NajatForeignAffairsMinistry.NajatForeignAffairsMinistry.Models.Dataa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DataaRepository extends JpaRepository<Dataa, Integer> {

    //for jasper report
    List<Dataa> findByStartDateAndEndDate(Date startDate, Date endDate);
}
