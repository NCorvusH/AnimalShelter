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

    @PostMapping(path = "/Donation")
         public Donation createDonation(@RequestBody Donation donation) {
        return donationService.createDonation(donation);

    }

    @GetMapping(path = "")
       public List<Donation> getAllDonation(){
        return donationService.getAllDonation();
    }

    @GetMapping(path = "/Donation/{id}")
        public Optional<Donation> getDonationId(@PathVariable int id){
        return donationService.getDonationByID(id);

    }

    @PutMapping(path = "/Donation/{id}")
         public void  updateDonation(@RequestBody Donation donation, @PathVariable int id){
        return donationService.updateDonation(donation);

    }

    @DeleteMapping(path = "/Donation/{id}")
    public String deleteDonationById(@PathVariable int id) {
        boolean ok = donationService.deleteDonation(id);

        if (ok) {
            return "Donation with id " + id + " was deleted";
        } else {
            return "Donation with id " + id + " not found";
        }
    }

}
