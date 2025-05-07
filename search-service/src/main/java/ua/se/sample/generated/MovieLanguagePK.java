package ua.se.sample.generated;

import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import ua.se.sample.dao.LanguageEntity;

public class MovieLanguagePK {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "language_id")
    private LanguageEntity language;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "language_role_id")
    private LanguageRole languageRole;
}
