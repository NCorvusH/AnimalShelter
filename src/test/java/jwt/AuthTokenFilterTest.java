package jwt;

import com.AnimalShelter.jwt.AuthTokenFilter;
import com.AnimalShelter.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.context.SecurityContextHolder;

import java.lang.reflect.Method;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AuthTokenFilterTest {

    @Mock
    private final HttpServletRequest request = mock(HttpServletRequest.class);
    private final HttpServletResponse response = mock(HttpServletResponse.class);
    private final FilterChain filterChain = mock(FilterChain.class);
    private final JwtService jwtService = mock(JwtService.class);
    private final UserDetailsService userDetailsService = mock(UserDetailsService.class);
    private final AuthTokenFilter authTokenFilter = new AuthTokenFilter(jwtService, userDetailsService);

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        SecurityContextHolder.clearContext();
    }

    @Test
    public void test_Do_Filter_Internal_With_Valid_Token() throws Exception {
        String token = "validToken";
        String username = "user";
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                username, "password", Collections.singletonList(new SimpleGrantedAuthority("USER")));

        when(request.getHeader("Authorization")).thenReturn("Bearer " + token);
        when(jwtService.getUsernameFromToken(token)).thenReturn(username);
        when(userDetailsService.loadUserByUsername(username)).thenReturn(userDetails);
        when(jwtService.isTokenValid(token, userDetails)).thenReturn(true);

        Method method = AuthTokenFilter.class.getDeclaredMethod("doFilterInternal",
                HttpServletRequest.class, HttpServletResponse.class, FilterChain.class);
        method.setAccessible(true);
        method.invoke(authTokenFilter, request, response, filterChain);

        verify(filterChain).doFilter(request, response);

        SecurityContext context = SecurityContextHolder.getContext();
        assertNotNull(context.getAuthentication(), "Authentication should not be null");
        assertInstanceOf(UsernamePasswordAuthenticationToken.class, context.getAuthentication(), "Authentication should be an instance of UsernamePasswordAuthenticationToken");
        assertEquals(username, context.getAuthentication().getName(), "Username should match");
    }


    @Test
    public void test_Do_Filter_Internal_With_Invalid_Token() throws Exception {
        String token = "invalidToken";

        when(request.getHeader("Authorization")).thenReturn("Bearer " + token);
        when(jwtService.getUsernameFromToken(token)).thenReturn(null);

        Method method = AuthTokenFilter.class.getDeclaredMethod("doFilterInternal",
                HttpServletRequest.class, HttpServletResponse.class, FilterChain.class);
        method.setAccessible(true);
        method.invoke(authTokenFilter, request, response, filterChain);

        verify(filterChain).doFilter(request, response);
        SecurityContext context = SecurityContextHolder.getContext();
        assertNull(context.getAuthentication(), "Authentication should be null");
    }

    @Test
    public void test_Do_Filter_Internal_With_No_Token() throws Exception {
        when(request.getHeader("Authorization")).thenReturn(null);

        Method method = AuthTokenFilter.class.getDeclaredMethod("doFilterInternal",
                HttpServletRequest.class, HttpServletResponse.class, FilterChain.class);
        method.setAccessible(true);
        method.invoke(authTokenFilter, request, response, filterChain);

        verify(filterChain).doFilter(request, response);
        SecurityContext context = SecurityContextHolder.getContext();
        assertNull(context.getAuthentication(), "Authentication should be null");
    }
}
