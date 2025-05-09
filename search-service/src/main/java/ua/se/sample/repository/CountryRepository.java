package ua.se.sample.repository;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.se.sample.dao.CountryEntity;

import java.util.Optional;

@Repository
public interface CountryRepository  extends JpaRepository<CountryEntity, Long> {

    /**
     * Retrieve an {@link CountryEntity} from the data store by name.
     * <p>
     * This method returns an {@link Optional} containing the {@link CountryEntity} if found. If
     * no {@link CountryEntity} is found with the provided id, it will return an empty
     * {@link Optional}.
     * </p>
     * @param name the name to search for
     * @return an {@link Optional} containing the {@link CountryEntity} if found, or an empty
     * {@link Optional} if not found.
     * @throws IllegalArgumentException if the id is null (assuming null is not a valid
     * input for name)
     */
    Optional<CountryEntity> findByCountryName(@NotNull  String name);
}