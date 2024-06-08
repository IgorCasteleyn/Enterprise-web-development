package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domein.Festival;

@Repository
public interface FestivalRepository extends JpaRepository<Festival, Integer> {
}
