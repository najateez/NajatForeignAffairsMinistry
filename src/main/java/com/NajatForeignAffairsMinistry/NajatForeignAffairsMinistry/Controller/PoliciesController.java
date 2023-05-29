package com.NajatForeignAffairsMinistry.NajatForeignAffairsMinistry.Controller;

import com.NajatForeignAffairsMinistry.NajatForeignAffairsMinistry.Models.Policies;
import com.NajatForeignAffairsMinistry.NajatForeignAffairsMinistry.Services.PoliciesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
@RestController
public class PoliciesController {

    @Autowired
    PoliciesService policiesService;

    //add + update + delete -> for Policy Management (POST).
    //ResponseEntity (http status code) -> 201

    //exp: localhost:8080/api/policies?country=USA&region=North America&topic=Trade&details=The USA is committed to free and fair trade with all nations.
    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "api/policies", method = RequestMethod.POST)
    public ResponseEntity<String> addPolicyManagement(@RequestParam String country, @RequestParam String region, @RequestParam String topic, @RequestParam String details) {

        policiesService.addPolicyManagement(country,region,topic,details);

        return ResponseEntity.status(HttpStatus.CREATED).body("Policy management added successfully.");
    }

    //exp: localhost:8080/api/policies/updatePolicyManagementById?id=2&details=Najateez Alkhatri
    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "api/policies/updatePolicyManagementById", method = RequestMethod.POST)
    public ResponseEntity<String> updatePolicyManagementById(@RequestParam Integer id,@RequestParam String details) {
        policiesService.updatePolicyManagementById(id,details);

        return ResponseEntity.status(HttpStatus.CREATED).body("Policy management updated successfully.");
    }

    //exp: localhost:8080/api/policies/deletePolicyManagementById?id=1
    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "api/policies/deletePolicyManagementById", method = RequestMethod.POST)
    public ResponseEntity<String> deletePolicyManagementById(@RequestParam Integer id) {
        policiesService.deletePolicyManagementById(id);

        return ResponseEntity.status(HttpStatus.CREATED).body("Policy management deleted successfully.");
    }

    //------------------------------------------------------------
    //get by country, region, or topic -> Policy Retrieval (GET).
    //getByColumnName:-
    //ResponseEntity (http status code) -> 200 OK
    //exp: localhost:8080/api/policies?country=USA
    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "api/policies", method = RequestMethod.GET)
    public ResponseEntity<Policies> getPoliciesByCountry(@RequestParam String country) {
        Policies policies = policiesService.getPoliciesByCountry(country);
        return ResponseEntity.ok(policies);
    }

    //exp: localhost:8080/api/policies/getPoliciesByRegion?region=North America
    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "api/policies/getPoliciesByRegion", method = RequestMethod.GET)
    public ResponseEntity<Policies> getPoliciesByRegion(@RequestParam String region) {
        Policies policiesRegion = policiesService.getPoliciesByRegion(region);
        return ResponseEntity.ok(policiesRegion);
    }
    //-----------------------------------------------------

    //for cron:-
    //POST + Response entity 200 OK
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("api/cron/updatePolicies")
    public ResponseEntity<Policies> cronForPolicies(@RequestBody Policies policies) {
        // Validate required fields
        if (policies.getCountry() == null || policies.getTopic().isEmpty() || policies.getRegion() == null || policies.getDetails().isEmpty() ) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); // Return a 400 Bad Request response
        }
        // Save the policies to the database
        Policies savedPolicies = policiesService.savePolicies(policies);
        return ResponseEntity.status(HttpStatus.OK).body(savedPolicies);
    }

}
