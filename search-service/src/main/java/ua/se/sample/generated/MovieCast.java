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
//    @Id
//    @NotNull
//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "movie_id", nullable = false)
//    private Movie movie;
//
//    @Id
//    @NotNull
//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "person_id", nullable = false)
//    private Person person;
//
//
//    @Id
//    @NotNull
//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "gender_id", nullable = false)
//    private Gender gender;

    @EmbeddedId
    private MovieCastPK movieCastPK;

    @Size(max = 400)
    @Column(name = "character_name", length = 400)
    private String characterName;


    @Column(name = "cast_order")
    private Integer castOrder;

}