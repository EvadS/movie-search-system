package ua.se.sample.models.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder(toBuilder = true)
public class MoviePagedResponse {
    private int totalPages;
    private long totalElements;
    private int pageSize;
    //the number of elements currently on this slice
    private int pageElements;
    private List<MovieResponseItem> items;


}
