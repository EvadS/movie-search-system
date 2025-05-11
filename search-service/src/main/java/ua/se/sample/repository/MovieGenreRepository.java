package ua.se.sample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.se.sample.generated.MovieGenre;

@Repository
public interface MovieGenreRepository  extends JpaRepository<MovieGenre, Long> {
}
