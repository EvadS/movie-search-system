package ua.se.sample.generated;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ua.se.sample.dao.KeywordEntity;

@Getter
@Setter
@Entity
@Table(name = "movie_keywords")
public class MovieKeyword {
    @EmbeddedId
    private MovieKeywordId id;

    @MapsId("movieId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

    @MapsId("keywordId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "keyword_id", nullable = false)
    private KeywordEntity keyword;

}