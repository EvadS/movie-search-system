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
@Table(name = "gender")
public class GenderEntity {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 20)
    @Column(name = "gender", length = 20)
    private String gender;
}