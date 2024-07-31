package repository;

import java.time.LocalTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domein.Optreden;

@Repository
public interface OptredenRepository extends JpaRepository<Optreden, Integer> {

  Object findByStartuur(LocalTime startuur);
}
