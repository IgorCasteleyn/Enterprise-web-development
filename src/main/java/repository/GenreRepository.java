package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domein.Genre;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer> {
}
