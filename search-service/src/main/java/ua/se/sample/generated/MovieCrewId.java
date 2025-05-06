package ua.se.sample.generated;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@Embeddable
public class MovieCrewId implements java.io.Serializable {
    private static final long serialVersionUID = 3239407776818119559L;
    @NotNull
    @Column(name = "movie_id", nullable = false)
    private Integer movieId;

    @NotNull
    @Column(name = "person_id", nullable = false)
    private Integer personId;

    @NotNull
    @Column(name = "department_id", nullable = false)
    private Integer departmentId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        MovieCrewId entity = (MovieCrewId) o;
        return Objects.equals(this.departmentId, entity.departmentId) &&
                Objects.equals(this.movieId, entity.movieId) &&
                Objects.equals(this.personId, entity.personId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(departmentId, movieId, personId);
    }

}