package service;

import org.springframework.stereotype.Service;
import java.util.List;
import domein.Ticket;
import domein.User;

@Service
public interface TicketService {

  public List<Ticket> getAllTicketsByUser(User user);

  public int getNumberOfTicketsPerUserPerFestival(User user, int festivalId);

}
