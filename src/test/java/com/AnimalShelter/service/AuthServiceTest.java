package com.AnimalShelter.service;

import com.AnimalShelter.dtos.response.AuthResponse;
import com.AnimalShelter.dtos.resquest.RegisterRequest;
import com.AnimalShelter.model.ERole;
import com.AnimalShelter.model.User;
import com.AnimalShelter.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AuthServiceTest {

    @Mock
    private JwtService jwtService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;  // Mock the PasswordEncoder

    @InjectMocks
    private AuthService authService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void register_ShouldReturnAuthResponse_WhenRegistrationIsSuccessful() {
        // Arrange
        String username = "username";
        String email = "email@example.com";
        String rawPassword = "password";
        ERole role = ERole.USER;

        // Create the RegisterRequest object
        RegisterRequest registerRequest = new RegisterRequest(username, email, rawPassword, role);

        // Mock the password encoding process
        String encodedPassword = "encodedPassword";
        when(passwordEncoder.encode(rawPassword)).thenReturn(encodedPassword);

        User user = User.builder()
                .username(username)
                .email(email)
                .password(encodedPassword)  // Use the mocked encoded password
                .role(role)
                .build();

        String token = "jwtToken";

        // Mock the repository save and JWT token generation
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(jwtService.getTokenService(user)).thenReturn(token);

        // Act
        AuthResponse authResponse = authService.register(registerRequest);

        // Assert
        assertEquals(token, authResponse.getToken());
        assertEquals(role.name(), authResponse.getRole());  // Compare the enum name with the expected string
        verify(userRepository).save(any(User.class));
    }
}