package ua.se.sample.dao;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "movie_languages")
public class MovieLanguage {

    @EmbeddedId
    private MovieLanguagePK movieLanguagePK;

}