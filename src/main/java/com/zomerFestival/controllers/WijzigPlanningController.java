package com.zomerFestival.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import domein.*;
import service.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
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

  @GetMapping("/{festivalId}")
  public String getFestivalPlanning(@PathVariable Integer festivalId, Model model) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    String username = userDetails.getUsername();

    User user = userService.findByUsername(username);
    List<Ticket> ticketsByUser = ticketService.getAllTicketsByUser(user);
    Festival festival = festivalService.getFestivalById(festivalId);

    model.addAttribute("user", user);
    model.addAttribute("ticketsByUser", ticketsByUser);
    model.addAttribute("festival", festival);
    model.addAttribute("optredens", festival.getOptredens());

    return "wijzigplanning";
  }

  @PostMapping("/{festivalId}/deleteOptreden/{optredenId}")
  public String deleteOptreden(@PathVariable Integer festivalId, @PathVariable Integer optredenId,
      RedirectAttributes redirectAttributes) {
    festivalService.deleteOptreden(festivalId, optredenId);
    redirectAttributes.addFlashAttribute("message", "Optreden succesvol verwijderd");
    return "redirect:/wijzigplanning/" + festivalId;
  }

  // @PostMapping("/{festivalId}/addOptreden")
  // public String addOptreden(@PathVariable Integer festivalId, @RequestParam
  // String naam, @RequestParam String startuur, RedirectAttributes
  // redirectAttributes) {
  // festivalService.addOptreden(festivalId, naam, startuur);
  // redirectAttributes.addFlashAttribute("message", "Optreden succesvol
  // toegevoegd");
  // return "redirect:/wijzigplanning/" + festivalId;
  // }
}
