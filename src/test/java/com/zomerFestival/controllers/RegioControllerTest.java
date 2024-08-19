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

public class RegioControllerTest {

    @Mock
    private FestivalService festivalService;

    @Mock
    private UserService userService;

    @Mock
    private TicketService ticketService;

    @Mock
    private RegioService regioService;

    @Mock
    private Model model;

    @Mock
    private Authentication authentication;

    @Mock
    private SecurityContext securityContext;

    @Mock
    private UserDetails userDetails;

    @InjectMocks
    private RegioController regioController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    void testRegio() {
        Integer regioId = 1;
        String username = "testUser";

        User user = new User();
        user.setUsername(username);

        List<Ticket> tickets = new ArrayList<>();
        List<Festival> festivals = new ArrayList<>();
        Regio regio = new Regio();

        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(userDetails);
        when(userDetails.getUsername()).thenReturn(username);
        when(userService.findByUsername(username)).thenReturn(user);
        when(ticketService.getAllTicketsByUser(user)).thenReturn(tickets);
        when(festivalService.getAllFestivalsByRegioId(regioId)).thenReturn(festivals);
        when(regioService.getRegioById(regioId)).thenReturn(regio);
        when(ticketService.magTicketsKopen(user)).thenReturn(true);

        String viewName = regioController.regio(regioId, model);

        verify(model).addAttribute("user", user);
        verify(model).addAttribute("ticketsByUser", tickets);
        verify(model).addAttribute("festivals", festivals);
        verify(model).addAttribute("regio", regio);
        verify(model).addAttribute("festivalService", festivalService);
        verify(model).addAttribute("ticketService", ticketService);
        verify(model).addAttribute("magKopen", true);
        assertEquals("regio", viewName);
    }
}
