package service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import domein.*;
import repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("festivalService")
public class FestivalServiceImpl implements FestivalService {

  @Autowired
  private FestivalRepository festivalRepository;

  @Autowired
  private TicketRepository ticketRepository;

  @Autowired
  private SubGenreRepository subGenreRepository;

  @Autowired
  private SubGenreService subGenreService;

  @Autowired
  private OptredenRepository optredenRepository;

  @Override
  public Festival getFestivalById(Integer festivalId) {
    return festivalRepository.findById(festivalId).get();
  }

  @Override
  public List<Festival> getAllFestivalsByGenreId(Integer genreId) {
    List<Festival> festivals = festivalRepository.findAllByGenreId(genreId);
    festivals.sort(Comparator.comparing((Festival f) -> f.getRegio().getNaam())
        .thenComparing(Festival::getDatum)
        .thenComparing(Festival::getAanvangsuur));
    return festivals;
  }

  @Override
  public List<Festival> getAllFestivalsByRegioId(Integer regioId) {
    List<Festival> festivals = festivalRepository.findAllByRegioId(regioId);
    festivals.sort(Comparator.comparing((Festival f) -> f.getGenre().getNaam())
        .thenComparing(Festival::getDatum)
        .thenComparing(Festival::getAanvangsuur));
    return festivals;
  }

  @Override
  public int getBeschikbarePlaatsen(Festival festival) {
    int verkochteTickets = 0;
    for (Ticket t : ticketRepository.findAllByFestivalId(festival.getId())) {
      verkochteTickets += t.getAantal();
    }
    return festival.getCapaciteit() - verkochteTickets;
  }

  @Override
  public void deleteOptreden(Integer festivalId, Integer optredenId) {
    Festival festival = this.getFestivalById(festivalId);
    if (festival != null) {
      List<Optreden> optredens = festival.getOptredens();
      Optreden optredenToRemove = null;
      for (Optreden optreden : optredens) {
        if (optreden.getId() == optredenId) {
          optredenToRemove = optreden;
        }
      }
      if (optredenToRemove != null) {
        optredens.remove(optredenToRemove);
        festival.setOptredens(optredens);
        festivalRepository.save(festival);
      }
    }
  }

  @Override
  public void addOptreden(Integer festivalId, NieuwOptreden nieuwOptreden, List<Integer> subgenreIds) {
    Festival festival = this.getFestivalById(festivalId);
    List<SubGenre> subGenreList = subgenreIds.stream()
        .map(id -> subGenreService.getSubGenreById(id))
        .collect(Collectors.toList());
    if (festival != null) {
      Optreden optreden = Optreden.builder()
          .naam(nieuwOptreden.getNaam())
          .startuur(nieuwOptreden.getStartuur())
          .subgenres(subGenreList)
          .build();
      optredenRepository.save(optreden);
      List<Optreden> optredens = festival.getOptredens();
      optredens.add(optreden);
      festival.setOptredens(optredens);
      festivalRepository.save(festival);
    }
  }

  @Override
  public List<SubGenre> getAllSubGenres(Integer genreId) {
    return subGenreRepository.findAllByGenreId(genreId);
  }

}
