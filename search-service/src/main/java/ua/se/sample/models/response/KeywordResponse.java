package ua.se.sample.models.response;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KeywordResponse {

    private Long id;

    private String name;

}