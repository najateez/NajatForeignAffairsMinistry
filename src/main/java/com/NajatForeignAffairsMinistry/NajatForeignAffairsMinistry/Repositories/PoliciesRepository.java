package com.NajatForeignAffairsMinistry.NajatForeignAffairsMinistry.Repositories;

import com.NajatForeignAffairsMinistry.NajatForeignAffairsMinistry.Models.Policies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface PoliciesRepository extends JpaRepository<Policies, Integer> {

    //update
    @Modifying
    @Transactional
    @Query(value = "UPDATE Policies s SET s.details=:details WHERE s.id =:id")
    void updatePolicyManagementById(@Param("id") Integer id, @Param("details") String details);

    //deleteById --> makeIsActiveFalse
    @Modifying
    @Transactional
    @Query(value = "UPDATE Policies s SET s.isActive = false WHERE s.id =:id")
    void deletePolicyManagementById(@Param("id") Integer id);

    //getByColumnName :-  (column name depend on what you write in model package).
    @Query(value = "SELECT s from Policies s where s.country =:country")
    Policies getPoliciesByCountry(@Param("country") String country);

    @Query(value = "SELECT s from Policies s where s.region =:region")
    Policies getPoliciesByRegion(@Param("region") String region);
}
