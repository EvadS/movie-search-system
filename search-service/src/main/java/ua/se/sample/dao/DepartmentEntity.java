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
@Table(name = "department")
public class DepartmentEntity {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 200)
    @Column(name = "department_name", length = 200)
    private String departmentName;

}