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
import org.springframework.ui.Model;

@Controller
@RequestMapping("/regio")
public class RegioController {

  @Autowired
  private FestivalService festivalService;

  @Autowired
  private UserService userService;

  @Autowired
  private TicketService ticketService;

  @Autowired
  private RegioService regioService;

  @GetMapping("/{regioId}")
  public String getMethodName(@PathVariable Integer regioId, Model model) {

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    String username = userDetails.getUsername();

    User user = userService.findByUsername(username);
    List<Ticket> ticketsByUser = ticketService.getAllTicketsByUser(user);

    model.addAttribute("user", user);
    model.addAttribute("ticketsByUser", ticketsByUser);
    model.addAttribute("festivals", festivalService.getAllFestivalsByRegioId(regioId));
    model.addAttribute("regio", regioService.getRegioById(regioId));
    model.addAttribute("festivalService", festivalService);
    model.addAttribute("ticketService", ticketService);
    model.addAttribute("magKopen", ticketService.magTicketsKopen(user));
    
 
    return "regio";
  }

}