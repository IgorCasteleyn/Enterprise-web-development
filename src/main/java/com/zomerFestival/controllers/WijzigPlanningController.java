package com.zomerFestival.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import domein.*;
import jakarta.validation.Valid;
import service.*;
import validator.NieuwOptredenValidation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/wijzigplanning")
public class WijzigPlanningController {

  @Autowired
  private FestivalService festivalService;

  @Autowired
  private UserService userService;

  @Autowired
  private TicketService ticketService;

  @Autowired
  private NieuwOptredenValidation nieuwOptredenValidation;

  @GetMapping("/{festivalId}")
  public String getFestivalPlanning(@PathVariable Integer festivalId, Model model) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    String username = userDetails.getUsername();

    User user = userService.findByUsername(username);
    List<Ticket> ticketsByUser = ticketService.getAllTicketsByUser(user);
    Festival festival = festivalService.getFestivalById(festivalId);
    model.addAttribute("nieuwOptreden", new NieuwOptreden());
    model.addAttribute("user", user);
    model.addAttribute("ticketsByUser", ticketsByUser);
    model.addAttribute("festival", festival);
    model.addAttribute("optredens", festival.getOptredens());
    model.addAttribute("subGenres", festivalService.getAllSubGenres(festival.getGenre().getId()));

    return "wijzigplanning";
  }

  @PostMapping("/{festivalId}/deleteOptreden/{optredenId}")
  public String deleteOptreden(@PathVariable Integer festivalId, @PathVariable Integer optredenId,
      RedirectAttributes redirectAttributes) {
    festivalService.deleteOptreden(festivalId, optredenId);
    redirectAttributes.addFlashAttribute("message", "Optreden succesvol verwijderd");
    return "redirect:/wijzigplanning/" + festivalId;
  }

  @PostMapping("/{festivalId}")
  public String addOptreden(@PathVariable Integer festivalId,
      @Valid @ModelAttribute("nieuwOptreden") NieuwOptreden nieuwOptreden, BindingResult bindingResult,
      RedirectAttributes redirectAttributes, @RequestParam(defaultValue = "") List<Integer> subgenreIds, Model model) {
    nieuwOptredenValidation.validate(nieuwOptreden, bindingResult);

    if (bindingResult.hasErrors()) {
      Festival festival = festivalService.getFestivalById(festivalId);
      model.addAttribute("bindingResult", bindingResult);
      model.addAttribute("nieuwOptreden", nieuwOptreden);
      model.addAttribute("subGenres", festivalService.getAllSubGenres(festival.getGenre().getId()));
      model.addAttribute("festival", festival);
      model.addAttribute("optredens", festival.getOptredens());

      System.out.println(bindingResult);
      return "wijzigplanning";
    }

    festivalService.addOptreden(festivalId, nieuwOptreden, subgenreIds);

    redirectAttributes.addFlashAttribute("message", "Optreden succesvol toegevoegd");
    return "redirect:/wijzigplanning/" + festivalId;
  }
}
