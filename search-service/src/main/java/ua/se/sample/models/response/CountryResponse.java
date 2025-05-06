package ua.se.sample.models.response;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CountryResponse {

    private Long id;

    private String text;

    public String isoCode;

    public String name;
}
