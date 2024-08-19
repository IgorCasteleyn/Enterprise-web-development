package com.zomerFestival.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.core.Authentication;

import domein.*;
import service.*;

@Controller
@RequestMapping("/home")
public class HomeController {

  @Autowired
  private RegioService regioService;

  @Autowired
  private UserService userService;

  @Autowired
  private TicketService ticketService;

  @Autowired
  private GenreService genreService;

  @GetMapping()
  public String home(Model model,@RequestParam(value = "message", required = false) String message) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    String username = userDetails.getUsername();

    User user = userService.findByUsername(username);
    List<Ticket> ticketsByUser = ticketService.getAllTicketsByUser(user);
    model.addAttribute("ticketsByUser", ticketsByUser);

    List<Regio> regios = regioService.getAllRegios();
    model.addAttribute("regios", regios);

    List<Genre> genres = genreService.getAllGenres();
    model.addAttribute("genres", genres);

    
    return "home";
  }

}
