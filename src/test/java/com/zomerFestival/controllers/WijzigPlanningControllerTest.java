package com.zomerFestival.controllers;

import service.*;
import validator.NieuwOptredenValidation;
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
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;

public class WijzigPlanningControllerTest {

    @Mock
    private FestivalService festivalService;

    @Mock
    private UserService userService;

    @Mock
    private TicketService ticketService;

    @Mock
    private NieuwOptredenValidation nieuwOptredenValidation;

    @Mock
    private Model model;

    @Mock
    private Authentication authentication;

    @Mock
    private SecurityContext securityContext;

    @Mock
    private UserDetails userDetails;

    @Mock
    private BindingResult bindingResult;

    @Mock
    private RedirectAttributes redirectAttributes;

    @InjectMocks
    private WijzigPlanningController wijzigPlanningController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    void testGetFestivalPlanning() {
        Integer festivalId = 1;

        Festival festival = mock(Festival.class);
        Genre genre = mock(Genre.class);
        List<Optreden> optredens = new ArrayList<>();
        List<SubGenre> subGenres = new ArrayList<>();

        when(genre.getId()).thenReturn(10);
        when(festival.getGenre()).thenReturn(genre);
        when(festival.getOptredens()).thenReturn(optredens);
        when(festivalService.getFestivalById(festivalId)).thenReturn(festival);
        when(festivalService.getAllSubGenres(10)).thenReturn(subGenres);

        User user = mock(User.class);
        List<Ticket> ticketsByUser = new ArrayList<>();
        when(ticketService.getAllTicketsByUser(user)).thenReturn(ticketsByUser);
        when(userService.findByUsername(anyString())).thenReturn(user);

        SecurityContext securityContext = mock(SecurityContext.class);
        Authentication authentication = mock(Authentication.class);
        UserDetails userDetails = mock(UserDetails.class);

        when(authentication.getPrincipal()).thenReturn(userDetails);
        when(userDetails.getUsername()).thenReturn("testUser");
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        Model model = mock(Model.class);

        String viewName = wijzigPlanningController.getFestivalPlanning(festivalId, model);

        verify(model).addAttribute("user", user);
        verify(model).addAttribute("ticketsByUser", ticketsByUser);
        verify(model).addAttribute("festival", festival);
        verify(model).addAttribute("optredens", optredens);
        verify(model).addAttribute("subGenres", subGenres);
        assertEquals("wijzigplanning", viewName);
    }

    @Test
    void testDeleteOptreden() {
        Integer festivalId = 1;
        Integer optredenId = 2;

        String viewName = wijzigPlanningController.deleteOptreden(festivalId, optredenId, redirectAttributes);

        verify(festivalService).deleteOptreden(festivalId, optredenId);
        verify(redirectAttributes).addFlashAttribute("message", "Optreden succesvol verwijderd");
        assertEquals("redirect:/wijzigplanning/" + festivalId, viewName);
    }

    @Test
    void testAddOptreden_WithErrors() {
        Integer festivalId = 1;
        NieuwOptreden nieuwOptreden = new NieuwOptreden();
        List<Integer> subgenreIds = new ArrayList<>();

        Festival festival = new Festival();

        Genre genre = mock(Genre.class);
        when(genre.getId()).thenReturn(10);

        festival.setGenre(genre);

        List<Optreden> optredens = new ArrayList<>();
        List<SubGenre> subGenres = new ArrayList<>();

        Festival festivalSpy = spy(festival);

        when(bindingResult.hasErrors()).thenReturn(true);
        when(festivalService.getFestivalById(festivalId)).thenReturn(festivalSpy);
        when(festivalSpy.getOptredens()).thenReturn(optredens);
        when(festivalService.getAllSubGenres(genre.getId())).thenReturn(subGenres);

        String viewName = wijzigPlanningController.addOptreden(festivalId, nieuwOptreden, bindingResult,
                redirectAttributes, subgenreIds, model);

        verify(nieuwOptredenValidation).validate(nieuwOptreden, bindingResult);
        verify(model).addAttribute("bindingResult", bindingResult);
        verify(model).addAttribute("nieuwOptreden", nieuwOptreden);
        verify(model).addAttribute("subGenres", subGenres);
        verify(model).addAttribute("festival", festivalSpy);
        verify(model).addAttribute("optredens", optredens);
        assertEquals("wijzigplanning", viewName);
    }

    @Test
    void testAddOptreden_NoErrors() {
        Integer festivalId = 1;
        NieuwOptreden nieuwOptreden = new NieuwOptreden();
        List<Integer> subgenreIds = new ArrayList<>();

        when(bindingResult.hasErrors()).thenReturn(false);

        String viewName = wijzigPlanningController.addOptreden(festivalId, nieuwOptreden, bindingResult,
                redirectAttributes, subgenreIds, model);

        verify(festivalService).addOptreden(festivalId, nieuwOptreden, subgenreIds);
        verify(redirectAttributes).addFlashAttribute("message", "Optreden succesvol toegevoegd");
        assertEquals("redirect:/wijzigplanning/" + festivalId, viewName);
    }
}
