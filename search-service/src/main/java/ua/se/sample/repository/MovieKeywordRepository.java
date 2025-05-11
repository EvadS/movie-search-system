package ua.se.sample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.se.sample.dao.LanguageEntity;
import ua.se.sample.generated.MovieKeyword;

public interface MovieKeywordRepository extends JpaRepository<MovieKeyword, Long> {
}
