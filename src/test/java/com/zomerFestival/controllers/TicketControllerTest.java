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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class TicketControllerTest {

    @Mock
    private FestivalService festivalService;

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
    private TicketController ticketController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    void testGetTicketInfo() {
        Integer festivalId = 1;
        String username = "testUser";

        User user = new User();
        user.setUsername(username);
        
        List<Ticket> tickets = new ArrayList<>();
        Festival festival = new Festival();
        int aantalBeschikbareTickets = 100;
        int maxAantalTickets = 5;

        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(userDetails);
        when(userDetails.getUsername()).thenReturn(username);
        when(userService.findByUsername(username)).thenReturn(user);
        when(ticketService.getAllTicketsByUser(user)).thenReturn(tickets);
        when(festivalService.getFestivalById(festivalId)).thenReturn(festival);
        when(festivalService.getBeschikbarePlaatsen(festival)).thenReturn(aantalBeschikbareTickets);
        when(ticketService.getMaxAantalTicketsByUserByFestival(festival, user)).thenReturn(maxAantalTickets);

        String viewName = ticketController.getTicketInfo(festivalId, model);

        verify(model).addAttribute("user", user);
        verify(model).addAttribute("ticketsByUser", tickets);
        verify(model).addAttribute("festival", festival);
        verify(model).addAttribute("aantalBeschikbareTickets", aantalBeschikbareTickets);
        verify(model).addAttribute("maxAantalTickets", maxAantalTickets);
        assertEquals("ticket", viewName);
    }

    @Test
    void testTicketKopen() {
        Integer festivalId = 1;
        Integer aantalTickets = 2;

        User user = User.builder().build();
        Festival festival = new Festival();

        when(userService.findByUsername(anyString())).thenReturn(user);
        when(festivalService.getFestivalById(festivalId)).thenReturn(festival);

        String viewName = ticketController.ticketKopen(festivalId, aantalTickets);

        assertEquals("redirect:/home", viewName);
    }
}
