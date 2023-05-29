package com.NajatForeignAffairsMinistry.NajatForeignAffairsMinistry.Services;

import com.NajatForeignAffairsMinistry.NajatForeignAffairsMinistry.Models.Policies;
import com.NajatForeignAffairsMinistry.NajatForeignAffairsMinistry.Repositories.PoliciesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class PoliciesService {

    @Autowired
    PoliciesRepository policiesRepository;

    //add
    public void addPolicyManagement (@RequestParam String country, @RequestParam String region, @RequestParam String topic, @RequestParam String details) {

        Policies policyManagementObj = new Policies();
        policyManagementObj.setCountry(country);
        policyManagementObj.setRegion(region);
        policyManagementObj.setTopic(topic);
        policyManagementObj.setDetails(details);
        policiesRepository.save(policyManagementObj);
    }

    //update
    public void updatePolicyManagementById(Integer id,String details) {

        policiesRepository.updatePolicyManagementById(id,details);
    }

    //deleteById --> makeIsActiveFalse
    public void deletePolicyManagementById(Integer id) {

        policiesRepository.deletePolicyManagementById(id);
    }

    //getPoliciesByCountry
    public Policies getPoliciesByCountry(String country) {
        Policies policies = policiesRepository.getPoliciesByCountry(country);
        return policies;
    }

    //getPoliciesByRegion
    public Policies getPoliciesByRegion(String region) {
        Policies policiesRegion = policiesRepository.getPoliciesByRegion(region);
        return policiesRegion;
    }

    //--------------------------------------
    // for cron controller:-
    public Policies savePolicies(Policies policies) {

        return policies;
    }
}
