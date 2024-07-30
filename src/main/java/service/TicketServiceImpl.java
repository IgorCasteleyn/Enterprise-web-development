package service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domein.Ticket;
import domein.User;
import repository.TicketRepository;

@Service("ticketService")
public class TicketServiceImpl implements TicketService {

  @Autowired
  private TicketRepository ticketRepository;

  @Override
  public List<Ticket> getAllTicketsByUser(User user) {
    List<Ticket> tickets = new ArrayList<Ticket>();
    tickets = ticketRepository.findByUser(user);

    return tickets;
  }

  @Override
  public int getNumberOfTicketsPerUserPerFestival(User user, int festivalId) {
    return ticketRepository.countByUserAndFestivalId(user, festivalId);
  }

}
