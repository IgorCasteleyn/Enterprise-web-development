package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domein.SubGenre;

@Repository
public interface SubGenreRepository extends JpaRepository<SubGenre, Integer> {
}
