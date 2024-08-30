package com.AnimalShelter.controller;
import com.AnimalShelter.model.Donation;
import com.AnimalShelter.service.DonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("")
@CrossOrigin

public class DonationController {
    @Autowired
    DonationService donationService;

    @PostMapping(path = "/donation")
         public Donation createDonation(@RequestBody Donation donation) {
        return donationService.createDonation(donation);

    }

    @GetMapping(path = "/donation")
       public List<Donation> getAllDonation(){
        return donationService.getAllDonation();
    }

    @GetMapping(path = "/donation/{id}")
        public Optional<Donation> getDonationId(@PathVariable int id){
        return donationService.getDonationById(id);

    }

    @PutMapping(path = "/donation/{id}")
         public void  updateDonation(@RequestBody Donation donation, @PathVariable int id){
         donationService.updateDonation(donation, id);

    }

    @DeleteMapping(path = "/donation/{id}")
    public String deleteDonationById(@PathVariable int id) {
        boolean ok = donationService.deleteDonation(id);

        if (ok) {
            return "Donation with id " + id + " was deleted";
        } else {
            return "Donation with id " + id + " not found";
        }
    }
}
