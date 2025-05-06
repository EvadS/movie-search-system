package ua.se.sample.generated;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ua.se.sample.dao.CountryEntity;

@Getter
@Setter
public class ProductionCountryPK {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    private CountryEntity country;

}