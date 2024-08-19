package com.zomerFestival.controllers;

import service.*;
import domein.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class MijnTickettenControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private TicketService ticketService;

    @Mock
    private Model model;

    @Mock
    private Authentication authentication;

    @Mock
    private SecurityContext securityContext;

    @Mock
    private UserDetails userDetails;

    @InjectMocks
    private MijnTickettenController mijnTickettenController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    void testGetMijnTicketten() {
        String username = "testUser";

        User user = new User();
        user.setUsername(username);

        List<Ticket> tickets = new ArrayList<>();

        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(userDetails);
        when(userDetails.getUsername()).thenReturn(username);
        when(userService.findByUsername(username)).thenReturn(user);
        when(ticketService.getAllTicketsByUser(user)).thenReturn(tickets);

        String viewName = mijnTickettenController.getMijnTicketten(model);

        verify(model).addAttribute("user", user);
        verify(model).addAttribute("ticketsByUser", tickets);
        verify(model).addAttribute("ticketService", ticketService);
        assertEquals("mijnticketten", viewName);
    }
}
