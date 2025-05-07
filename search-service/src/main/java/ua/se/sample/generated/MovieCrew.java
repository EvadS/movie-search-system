package ua.se.sample.generated;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import ua.se.sample.dao.DepartmentEntity;
import ua.se.sample.dao.PersonEntity;

@Getter
@Setter
@Entity
@Table(name = "movie_crew")
public class MovieCrew {
    @EmbeddedId
    private MovieCrewId id;

    @MapsId("movieId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

    @MapsId("personId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "person_id", nullable = false)
    private PersonEntity person;

    @MapsId("departmentId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "department_id", nullable = false)
    private DepartmentEntity department;

    @Size(max = 200)
    @Column(name = "job", length = 200)
    private String job;

}