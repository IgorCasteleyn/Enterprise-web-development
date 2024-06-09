package com.zomerFestival.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import domein.*;
import service.*;

@Controller
@RequestMapping("/home")
public class HomeController {

  @Autowired
  private RegioService regioService;

  @Autowired
  private GenreService genreService;

  @GetMapping()
  public String home(Model model) {
    List<Regio> regios = regioService.getAllRegios();
    model.addAttribute("regios", regios);
    System.out.println("regios: " + regios);
    List<Genre> genres = genreService.getAllGenres();
    model.addAttribute("genres", genres);
    System.out.println("genres: " + genres);

    return "home";
  }

}
