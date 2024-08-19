package com.zomerFestival.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import domein.*;
import service.FestivalService;
import service.TicketService;
import service.UserService;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/ticket")
public class TicketController {
  @Autowired
  private FestivalService festivalService;

  @Autowired
  private UserService userService;

  @Autowired
  private TicketService ticketService;

  private Festival festival;
  private User user;
  private List<Ticket> ticketsByUser;
  private int aantalBeschikbareTickets;
  private int maxAantalTickets;

  @GetMapping("/{festivalId}")
  public String getTicketInfo(@PathVariable Integer festivalId, Model model) {

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    String username = userDetails.getUsername();

    user = userService.findByUsername(username);
    ticketsByUser = ticketService.getAllTicketsByUser(user);
    festival = festivalService.getFestivalById(festivalId);
    aantalBeschikbareTickets = festivalService.getBeschikbarePlaatsen(festival);
    maxAantalTickets = ticketService.getMaxAantalTicketsByUserByFestival(festival, user);

    model.addAttribute("user", user);
    model.addAttribute("ticketsByUser", ticketsByUser);
    model.addAttribute("festival", festival);
    model.addAttribute("aantalBeschikbareTickets", aantalBeschikbareTickets);
    model.addAttribute("maxAantalTickets", maxAantalTickets);
    return "ticket";
  }

  @PostMapping("/kopen")
  public String ticketKopen(@RequestParam Integer festivalId, @RequestParam Integer aantalTickets) {
    ticketService.saveTicket(user, festivalService.getFestivalById(festivalId), aantalTickets);

    return "redirect:/home";
  }

}
