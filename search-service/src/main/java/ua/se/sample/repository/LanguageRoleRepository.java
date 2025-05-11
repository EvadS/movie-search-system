package ua.se.sample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.se.sample.dao.LanguageRole;

@Repository
public interface LanguageRoleRepository extends JpaRepository<LanguageRole, Long> {
}

//LanguageRole