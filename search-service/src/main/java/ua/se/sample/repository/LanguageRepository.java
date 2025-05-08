package ua.se.sample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.se.sample.dao.LanguageEntity;

@Repository
public interface LanguageRepository extends JpaRepository<LanguageEntity, Long> {
}
