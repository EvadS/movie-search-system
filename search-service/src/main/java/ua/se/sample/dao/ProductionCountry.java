package ua.se.sample.dao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "production_country")
public class ProductionCountry {

    @EmbeddedId
    private ProductionCountryPK productionCountryPK;

}