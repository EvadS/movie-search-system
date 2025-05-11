package ua.se.sample.generated;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "movie_genres")
public class MovieGenre {
    @EmbeddedId
    private MovieGenrePK orderPK;

}