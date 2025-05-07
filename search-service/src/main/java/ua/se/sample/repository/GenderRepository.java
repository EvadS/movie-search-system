package ua.se.sample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.se.sample.dao.GenderEntity;

@Repository
public interface GenderRepository extends JpaRepository<GenderEntity, Long> {
}
