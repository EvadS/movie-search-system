package ua.se.sample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.se.sample.dao.KeywordEntity;

@Repository
public interface KeywordRepository extends JpaRepository<KeywordEntity, Long> {
}
