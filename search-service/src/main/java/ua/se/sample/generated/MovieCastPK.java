package ua.se.sample.generated;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ua.se.sample.dao.GenderEntity;
import ua.se.sample.dao.PersonEntity;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@Getter
public class MovieCastPK implements Serializable {

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "person_id", nullable = false)
    private PersonEntity person;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "gender_id", nullable = false)
    private GenderEntity gender;
}
