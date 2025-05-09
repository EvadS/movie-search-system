package ua.se.sample.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.se.sample.config.ControllersApiPaths;
import ua.se.sample.errors.exceptions.ResourceNotFoundException;
import ua.se.sample.models.request.DepartmentRequest;
import ua.se.sample.models.response.DepartmentResponse;
import ua.se.sample.service.DepartmentService;

import java.util.List;

@RestController
@RequestMapping(ControllersApiPaths.BASE_PATH + ControllersApiPaths.DEPARTMENT_PATH)
@AllArgsConstructor
@Tag(name = "Department", description = "Department management APIs")
public class DepartmentController {

    private final DepartmentService service;

    @GetMapping(ControllersApiPaths.GET_ITEMS)
    @ResponseStatus(value = HttpStatus.OK)
    public List<DepartmentResponse> getList() {
        return service.list();
    }

    @GetMapping(ControllersApiPaths.GET_ITEM_BY_ID)
    @ResponseStatus(value = HttpStatus.OK)
    public DepartmentResponse getById(@PathVariable(value = "id") Long id) {
        return service.getById(id);
    }

    @PostMapping(ControllersApiPaths.CREATE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<DepartmentResponse> add(@Valid @RequestBody DepartmentRequest departmentRequest) {

        DepartmentResponse response = service.create(departmentRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping(ControllersApiPaths.UPDATE)
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public ResponseEntity<DepartmentResponse> update(
            @PathVariable(value = "id") Long id,
            @Valid @RequestBody DepartmentRequest departmentRequest){

        DepartmentResponse update = service.update(id, departmentRequest);
        return new ResponseEntity<>(update, HttpStatus.ACCEPTED);
    }

    @DeleteMapping(ControllersApiPaths.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void remove(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        service.delete(id);
    }
}
