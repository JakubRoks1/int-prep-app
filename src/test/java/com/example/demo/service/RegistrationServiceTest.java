package com.example.demo.service;

import demo.dao.UserDAO;
import demo.model.User;
import demo.service.RegistrationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RegistrationServiceTest {

    @Mock
    private UserDAO userDAO;

    @InjectMocks
    private RegistrationService registrationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void registerNewUser_UsernameAlreadyExists_ThrowsException() {
        User user = new User();
        user.setUsername("existingUsername");

        when(userDAO.findByUsername(user.getUsername())).thenReturn(Optional.of(user));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            registrationService.registerNewUser(user);
        });

        assertEquals("Username already exists", exception.getMessage());
    }

    @Test
    void registerNewUser_EmailAlreadyExists_ThrowsException() {
        User user = new User();
        user.setEmail("existingEmail@example.com");

        when(userDAO.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            registrationService.registerNewUser(user);
        });

        assertEquals("Email already exists", exception.getMessage());
    }

    @Test
    void registerNewUser_PasswordContainsUsername_ThrowsException() {
        User user = new User();
        user.setUsername("username");
        user.setPassword("passwordWithUsername");

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            registrationService.registerNewUser(user);
        });

        assertEquals("Password cannot contain username or first name", exception.getMessage());
    }

    @Test
    void registerNewUser_PasswordDoesNotMeetRequirements_ThrowsException() {
        User user = new User();
        user.setPassword("weakPassword");

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            registrationService.registerNewUser(user);
        });

        assertEquals("Password must contain uppercase letters, lowercase letters, non-alphanumeric characters, and numeric characters", exception.getMessage());
    }
}
