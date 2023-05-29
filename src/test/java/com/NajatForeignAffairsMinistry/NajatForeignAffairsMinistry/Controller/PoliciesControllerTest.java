package com.NajatForeignAffairsMinistry.NajatForeignAffairsMinistry.Controller;

import com.NajatForeignAffairsMinistry.NajatForeignAffairsMinistry.Models.Policies;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PoliciesControllerTest {

    @Autowired
    PoliciesController policiesController;

    @Test
    void addPolicyManagement() {
        policiesController.addPolicyManagement("oman","abc","another topic","jhdjghdfkjhgkjdfhgjdfkdgs");
    }

    @Test
    void updatePolicyManagementById() {
        policiesController.updatePolicyManagementById(2,"dkjgkfdjgkdfjgldf");
    }

    @Test
    void deletePolicyManagementById() {
        policiesController.deletePolicyManagementById(1);
    }

    @Test
    void getPoliciesByCountry() {
        Policies country= policiesController.getPoliciesByCountry("USA").getBody();
        Integer policiesId=country.getId();
        assertEquals(1,policiesId);
    }

    @Test
    void getPoliciesByRegion() {
        Policies region= policiesController.getPoliciesByRegion("North America").getBody();
        Integer policiesId=region.getId();
        assertEquals(1,policiesId);
    }
}