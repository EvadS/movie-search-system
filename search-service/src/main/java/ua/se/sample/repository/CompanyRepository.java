package ua.se.sample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.se.sample.dao.ProductionCompanyEntity;

@Repository
public interface CompanyRepository extends JpaRepository<ProductionCompanyEntity, Long> {
}
