package repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domein.Ticket;
import domein.User;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {
  List<Ticket> findByUser(User user);

  List<Ticket> findAllByFestivalId(Integer festivalId);

  int countByUserAndFestivalId(User user, int festivalId);

}
