package com.zomerFestival.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import domein.Festival;
import service.FestivalService;

@RestController
@RequestMapping("/rest")
public class APIController {

  @Autowired
  private FestivalService festivalService;

  @GetMapping("/artiest/festival/{festivalId}")
  public List<String> getArtiestenPerFestival(@PathVariable int festivalId) {
    return festivalService.getArtiestenPerFestival(festivalService.getFestivalById(festivalId));
  }

  @GetMapping("/festivals/genre/{genreId}")
  public List<Festival> getFestivalsByGenres(@PathVariable int genreId) {
    return festivalService.getFestivalsByGenreId(genreId);
  }
}
