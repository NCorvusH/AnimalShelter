package com.AnimalShelter.controller;

import com.AnimalShelter.model.AdoptionApplication;
import com.AnimalShelter.service.AdoptionApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/adoption-applications")
public class AdoptionApplicationController {

    private final AdoptionApplicationService adoptionApplicationService;

    @Autowired
    public AdoptionApplicationController(AdoptionApplicationService adoptionApplicationService) {
        this.adoptionApplicationService = adoptionApplicationService;
    }

    @PostMapping
    public ResponseEntity<AdoptionApplication> submitApplication(@RequestBody AdoptionApplication application) {
        AdoptionApplication createdApplication = adoptionApplicationService.submitApplication(application);
        return ResponseEntity.ok(createdApplication);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdoptionApplication> viewApplication(@PathVariable int id) {
        Optional<AdoptionApplication> application = adoptionApplicationService.viewApplication(id);
        return application.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdoptionApplication> updateApplication(
            @PathVariable int id, @RequestBody AdoptionApplication updateApplication) {
        Optional<AdoptionApplication> updatedApplication = adoptionApplicationService.updateApplication(id, updateApplication);
        return updatedApplication.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApplication(@PathVariable int id) {
        boolean deleted = adoptionApplicationService.deleteApplication(id);
        if (deleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<AdoptionApplication>> listApplications() {
        List<AdoptionApplication> applications = adoptionApplicationService.listApplications();
        return ResponseEntity.ok(applications);
    }
}