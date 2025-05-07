package ua.se.sample.dao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "person")
public class PersonEntity {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 500)
    @Column(name = "person_name", length = 500)
    private String personName;

}