package com.AnimalShelter.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JwtServiceTest {

    private JwtService jwtService;
    private UserDetails userDetails;

    @BeforeEach
    void setUp() {
        jwtService = new JwtService();
        userDetails = mock(UserDetails.class);
    }

    @Test
    void test_Get_Token_Service() {
        when(userDetails.getUsername()).thenReturn("testUser");

        String token = jwtService.getTokenService(userDetails);

        assertNotNull(token, "The token should not be null");
        assertFalse(token.isEmpty(), "The token should not be empty");
    }

    @Test
    void test_Get_Token_With_Claims() {
        when(userDetails.getUsername()).thenReturn("testUser");

        Map<String, Object> claims = new HashMap<>();
        claims.put("role", "ROLE_USER");

        String token = jwtService.getToken(claims, userDetails);

        assertNotNull(token, "The token should not be null");
        assertFalse(token.isEmpty(), "The token should not be empty");

        String[] parts = token.split("\\.");
        assertEquals(3, parts.length, "The JWT token should have 3 parts");
    }

    @Test
    void test_Get_Username_From_Token() {
        when(userDetails.getUsername()).thenReturn("testUser");

        String token = jwtService.getTokenService(userDetails);
        String username = jwtService.getUsernameFromToken(token);

        assertEquals("testUser", username);
    }

    @Test
    void test_Is_Token_Valid() {
        when(userDetails.getUsername()).thenReturn("testUser");

        String token = jwtService.getTokenService(userDetails);

        boolean isValid = jwtService.isTokenValid(token, userDetails);

        assertTrue(isValid);
    }

    @Test
    void test_Is_Token_Expired() throws InterruptedException {
        when(userDetails.getUsername()).thenReturn("testUser");

        String token = jwtService.getToken(new HashMap<>(), userDetails);


        Thread.sleep(2000);

        boolean isExpired = jwtService.isTokenExpired(token);

        assertFalse(isExpired);
    }
}
