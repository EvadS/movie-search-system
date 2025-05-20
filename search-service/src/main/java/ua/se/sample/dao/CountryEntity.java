package ua.se.sample.dao;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import jakarta.persistence.*;


/**
 * Simple business object representing a country.
 *
 * @author Yevheniy Skyba
 */
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

    @NonNull
    @Column(name = "country_iso_code", unique = true, nullable = false, length = 10)
    public String countryIsoCode;

    @NonNull
    @Column(name = "country_name", unique = true, nullable = false, length = 100)
    public String countryName;


    @Column(name = "text", length = 1000)
    public String text;

    /*
     @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
     private LocalDate dob;
     */
}
