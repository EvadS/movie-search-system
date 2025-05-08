package ua.se.sample.models.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DepartmentRequest {

    @NotBlank(message = "{department.name.size}")
    @Size(max = 200, message = "{department.name.not.blank}")
    private String departmentName;
}
