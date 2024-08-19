package com.zomerFestival.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verifyNoInteractions;

public class LoginControllerTest {

    @Mock
    private Model model;

    @InjectMocks
    private LoginController loginController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLogin() {
        String error = null;
        String logout = null;

        String viewName = loginController.login(error, logout, model);

        verifyNoInteractions(model);
        assertEquals("login", viewName);
    }
}
