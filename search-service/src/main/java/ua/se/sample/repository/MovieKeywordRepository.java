package ua.se.sample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.se.sample.dao.MovieKeyword;

@Repository
public interface MovieKeywordRepository extends JpaRepository<MovieKeyword, Long> {
}
