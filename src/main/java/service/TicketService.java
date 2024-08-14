package service;

import org.springframework.stereotype.Service;
import java.util.List;

import domein.*;

@Service
public interface TicketService {

  public List<Ticket> getAllTicketsByUser(User user);

  public int getNumberOfTicketsPerUserPerFestivalId(User user, int festivalId);

  public int getMaxAantalTicketsByUserByFestival(Festival festival, User user);

  public boolean magTicketsKopen(User user);

  public boolean magTicketsKopenSpecifiek(User user, int festivalId);

}
