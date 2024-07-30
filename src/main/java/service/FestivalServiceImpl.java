package service;

import java.util.Comparator;
import java.util.List;

import domein.Festival;
import domein.Ticket;
import repository.FestivalRepository;
import repository.TicketRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("festivalService")
public class FestivalServiceImpl implements FestivalService {

  @Autowired
  private FestivalRepository festivalRepository;

  @Autowired
  private TicketRepository ticketRepository;

  @Override
  public List<Festival> getAllFestivalsByGenre(Integer genreId) {
    List<Festival> festivals = festivalRepository.findAllByGenreId(genreId);
    festivals.sort(Comparator.comparing((Festival f) -> f.getRegio().getNaam())
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
}
