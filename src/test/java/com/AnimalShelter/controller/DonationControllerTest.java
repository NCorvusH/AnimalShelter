package com.AnimalShelter.controller;

import com.AnimalShelter.model.Donation;
import com.AnimalShelter.service.DonationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
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

@SpringJUnitConfig
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
        donation1.setId(1);
        donation1.setDonorName("Emilia");
        donation1.setMessage("donation for beds");
        donation1.setAmount(new BigDecimal(100));
        donation1.setDate(LocalDate.of(2024,8,26));

        donation2 = new Donation();
        donation2.setId(2);
        donation2.setDonorName("Anonimo");
        donation2.setMessage("Donation to improve the shelter");
        donation2.setAmount(new BigDecimal(10000));
        donation2.setDate(LocalDate.of(2024, 8, 28));

    }
    @Test
    void createDonation() throws Exception {
        when(donationService.createDonation(any(Donation.class))).thenReturn(donation1);

        ObjectMapper objectMapper = new ObjectMapper();

        String donationJson = objectMapper.writeValueAsString(donation1);

        mockMvc.perform(post("/Donation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(donationJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.DonorName").value("Emilia"))
                .andExpect(jsonPath("$.Message").value("donation for beds"))
                .andExpect(jsonPath("$.Amount").value(15))
                .andExpect(jsonPath("$.Date").value("26-08-2024"));
    }
    @Test
    void readDonationById() throws Exception{
        when(donationService.getDonationById(2)).thenReturn(Optional.of(donation2));
        mockMvc.perform(get("/Donation")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.DonorName").value("Anonimo"))
                .andExpect(jsonPath("$.Message").value("Donation to improve the shelter"))
                .andExpect(jsonPath("$.Amount").value(100.000))
                .andExpect(jsonPath("$.Date").value("26-08-2024"));
    }
    @Test
    void deleteDonationById() throws Exception{
        when(donationService.deleteDonation(1)).thenReturn(true);
        mockMvc.perform(delete("/Donation"))
                .andExpect(status().isOk())
                .andExpect(content().string("Donation with id 1" + 1 + "was delete" ));

        when(donationService.deleteDonation(1)).thenReturn(false);
        mockMvc.perform(delete("/Donation"))
                .andExpect(status().isOk())
                .andExpect(content().string("Donation with id 1" + 1 + "not found" ));
    }
    @Test
    void updateDonation() throws Exception{
        doNothing().when(donationService).updateDonation(donation2, 2);

        ObjectMapper objectMapper = new ObjectMapper();
        String donationJson = objectMapper.writeValueAsString(donation2);

        mockMvc.perform(post("/Donation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(donationJson))
                .andExpect(status().isOk());
    }
}
