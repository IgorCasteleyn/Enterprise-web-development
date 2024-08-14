package service;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domein.*;
import repository.*;

@Service("ticketService")
public class TicketServiceImpl implements TicketService {

  @Autowired
  private TicketRepository ticketRepository;

  @Override
  public List<Ticket> getAllTicketsByUser(User user) {
    List<Ticket> tickets = ticketRepository.findByUser(user);

    tickets.sort(Comparator.comparing((Ticket t) -> t.getFestival().getDatum())
        .thenComparing(t -> t.getFestival().getRegio().getNaam())
        .thenComparing(t -> t.getFestival().getGenre().getNaam()));

    return tickets;
  }

  @Override
  public int getNumberOfTicketsPerUserPerFestivalId(User user, int festivalId) {
    return ticketRepository.countByUserAndFestivalId(user, festivalId);
  }

  @Override
  public int getMaxAantalTicketsByUserByFestival(Festival festival, User user) {
    int aantal = getNumberOfTicketsPerUserPerFestivalId(user, festival.getId());
    int max = 15 - aantal;
    return max;
  }

  @Override
  public boolean magTicketsKopen(User user) {
    int aantal = 0;
    for (Ticket ticket : getAllTicketsByUser(user)) {
      aantal += ticket.getAantal();
    }
    return aantal < 50;
  }

  @Override
  public boolean magTicketsKopenSpecifiek(User user, int festivalId) {
    boolean output = false;
    if (getNumberOfTicketsPerUserPerFestivalId(user, festivalId) < 15) {
      output = true;
    }
    return output;
  }

}
