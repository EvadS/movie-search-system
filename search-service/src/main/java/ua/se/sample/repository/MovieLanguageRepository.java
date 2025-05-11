package ua.se.sample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.se.sample.dao.MovieLanguage;

@Repository
public interface MovieLanguageRepository extends JpaRepository<MovieLanguage, Long> {
}
