package ua.se.sample.dao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "movie_languages")
public class MovieLanguage {

    @EmbeddedId
    private MovieLanguagePK movieLanguagePK;

}