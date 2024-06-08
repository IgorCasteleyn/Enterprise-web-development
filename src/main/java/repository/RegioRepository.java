package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domein.Regio;

@Repository
public interface RegioRepository extends JpaRepository<Regio, Integer> {
}
