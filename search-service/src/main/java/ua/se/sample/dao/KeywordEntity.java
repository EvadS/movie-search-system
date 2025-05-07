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
@Table(name = "keyword")
public class KeywordEntity {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 100)
    @Column(name = "keyword_name", length = 100)
    private String keywordName;

}