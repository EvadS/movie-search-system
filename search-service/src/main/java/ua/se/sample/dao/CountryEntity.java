package ua.se.sample.dao;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import jakarta.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@RequiredArgsConstructor(staticName = "of")

@Table(name = "country",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "country_iso_code")
        })
public class CountryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Long id;

    @NotBlank
    @Size(max = 1000)
    private String text;

    @NonNull
    @NotEmpty(message = "county iso code cannot be null or empty")
    @Column(name = "country_iso_code", unique = true, nullable = false, length = 10)
    public String countryIsoCode;

    @NonNull
    @NotEmpty(message = "country cannot be null or empty")
    @Column(name = "country_name", unique = true, nullable = false, length = 100)
    public String countryName;

    /*
     @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
     private LocalDate dob;
     */
}
