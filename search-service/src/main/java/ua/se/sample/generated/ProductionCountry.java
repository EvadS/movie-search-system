package ua.se.sample.generated;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ua.se.sample.dao.CountryEntity;

@Getter
@Setter
@Entity
@Table(name = "production_country")
public class ProductionCountry {

    @EmbeddedId
    private ProductionCountryPK productionCountryPK;

}