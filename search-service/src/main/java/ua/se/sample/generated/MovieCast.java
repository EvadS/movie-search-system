package ua.se.sample.generated;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "movie_cast")
public class MovieCast {
    @EmbeddedId
    private MovieCastPK movieCastPK;

    @Size(max = 400)
    @Column(name = "character_name", length = 400)
    private String characterName;


    @Column(name = "cast_order")
    private Integer castOrder;
}