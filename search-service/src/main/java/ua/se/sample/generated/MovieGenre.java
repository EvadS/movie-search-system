package ua.se.sample.generated;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "movie_genres")
public class MovieGenre {
    @EmbeddedId
    private MovieGenrePK orderPK;

}