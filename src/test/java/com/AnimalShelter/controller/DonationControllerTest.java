package com.AnimalShelter.controller;

import com.AnimalShelter.model.Donation;
import com.AnimalShelter.service.DonationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class DonationControllerTest {

    @Mock
    private DonationService donationService;
    private MockMvc mockMvc;
    private Donation donation1;
    private Donation donation2;

    @InjectMocks
    private DonationController donationController;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(donationController).build();

        donation1 = new Donation();
        donation1.setDonationId(1);
        donation1.setDonorName("Emilia");
        donation1.setMessage("donation for beds");
        donation1.setAmount(new BigDecimal(100));
        donation1.setDate(LocalDate.of(2024,3,8));

        donation2 = new Donation();
        donation2.setDonationId(2);
        donation2.setDonorName("anonymous");
        donation2.setMessage("Donation to improve the shelter");
        donation2.setAmount(new BigDecimal(10000));
        donation2.setDate( LocalDate.of(2024,3,7));

    }
    @Test
    void createDonation() throws Exception {
        when(donationService.createDonation(any(Donation.class))).thenReturn(donation1);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        String donationJson = objectMapper.writeValueAsString(donation1);

        mockMvc.perform(post("/api/v1/donations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(donationJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.donationId").value(1))
                .andExpect(jsonPath("$.donorName").value("Emilia"))
                .andExpect(jsonPath("$.message").value("donation for beds"))
                .andExpect(jsonPath("$.amount").value(100))
                .andExpect(jsonPath("$.date[0]").value(2024)) // Año
                .andExpect(jsonPath("$.date[1]").value(3))   // Mes
                .andExpect(jsonPath("$.date[2]").value(8));
    }
    @Test
    void readDonationById() throws Exception{
        when(donationService.getDonationById(2)).thenReturn(Optional.of(donation2));

        mockMvc.perform(get("/api/v1/donations/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.donationId").value(2))
                .andExpect(jsonPath("$.donorName").value("anonymous"))
                .andExpect(jsonPath("$.message").value("Donation to improve the shelter"))
                .andExpect(jsonPath("$.amount").value(10000))
                .andExpect(jsonPath("$.date[0]").value(2024)) // Año
                .andExpect(jsonPath("$.date[1]").value(3))   // Mes
                .andExpect(jsonPath("$.date[2]").value(7));
    }
    @Test
    void deleteDonationById() throws Exception{
        when(donationService.deleteDonation(1)).thenReturn(true);

        mockMvc.perform(delete("/api/v1/donations/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Donation with id 1 was deleted" ));

        when(donationService.deleteDonation(1)).thenReturn(false);

        mockMvc.perform(delete("/api/v1/donations/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Donation with id 1 not found" ));
    }
    @Test
    void updateDonation() throws Exception{
        doNothing().when(donationService).updateDonation(any(Donation.class),any(Integer.class));

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        String donationJson = objectMapper.writeValueAsString(donation2);

        mockMvc.perform(put("/api/v1/donations/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(donationJson))
                .andExpect(status().isOk());
    }
}
