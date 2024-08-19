package com.zomerFestival.controllers;

import domein.*;
import service.*;

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

public class GenreControllerTest {

    @Mock
    private FestivalService festivalService;

    @Mock
    private UserService userService;

    @Mock
    private TicketService ticketService;

    @Mock
    private GenreService genreService;

    @Mock
    private Model model;

    @Mock
    private Authentication authentication;

    @Mock
    private SecurityContext securityContext;

    @Mock
    private UserDetails userDetails;

    @InjectMocks
    private GenreController genreController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    void testGetGenreController() {
        Integer genreId = 1;
        String username = "testUser";

        User user = new User();
        user.setUsername(username);

        List<Ticket> tickets = new ArrayList<>();
        List<Festival> festivals = new ArrayList<>();
        Genre genre = new Genre();

        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(userDetails);
        when(userDetails.getUsername()).thenReturn(username);
        when(userService.findByUsername(username)).thenReturn(user);
        when(ticketService.getAllTicketsByUser(user)).thenReturn(tickets);
        when(festivalService.getAllFestivalsByGenreId(genreId)).thenReturn(festivals);
        when(genreService.getGenreById(genreId)).thenReturn(genre);
        when(ticketService.magTicketsKopen(user)).thenReturn(true);

        String viewName = genreController.getGenreController(genreId, model);

        verify(model).addAttribute("user", user);
        verify(model).addAttribute("ticketsByUser", tickets);
        verify(model).addAttribute("festivals", festivals);
        verify(model).addAttribute("genre", genre);
        verify(model).addAttribute("festivalService", festivalService);
        verify(model).addAttribute("ticketService", ticketService);
        verify(model).addAttribute("magKopen", true);
        assertEquals("genre", viewName);
    }
}
