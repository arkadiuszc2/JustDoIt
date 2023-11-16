package com.justDoIt.backend.controllers;

import com.justDoIt.backend.entities.Task;
import com.justDoIt.backend.entities.TaskCreateDto;
import com.justDoIt.backend.entities.TaskViewDto;
import com.justDoIt.backend.exceptions.ServiceNotFoundException;
import com.justDoIt.backend.exceptions.WrongIdFormatException;
import com.justDoIt.backend.services.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "tasks")
public class TaskController {

  private final TaskService taskService;

  @Operation(
      summary = "Create task",
      description = "Create new task."
  )

  @PostMapping
  public ResponseEntity<TaskViewDto> create(@RequestBody @Valid TaskCreateDto taskCreateDto)
      throws ServiceNotFoundException, WrongIdFormatException {
    return ResponseEntity.status(HttpStatus.CREATED).body(taskService.create(taskCreateDto));
  }

  @Operation(
      summary = "Find task by id",
      description = "Find a task by providing its id."
  )
  @ApiResponse(
      responseCode = "200",
      content = {
          @Content(schema = @Schema(implementation = Task.class), mediaType = "application/json")})
  @ApiResponse(
      responseCode = "404",
      content = {
          @Content(schema = @Schema)}
  )
  @GetMapping("/{identifier}")
  public ResponseEntity<List<TaskViewDto>> getByIdOrContainingNameInTitle(
      @RequestParam("searchBy") @Pattern(regexp = "id|name", message = "must be 'id' or 'name'") String searchBy,
      @PathVariable("identifier") String identifier)
      throws ServiceNotFoundException, WrongIdFormatException {
    return ResponseEntity.status(HttpStatus.OK)
        .body(taskService.getByIdOrContainingTextInTitle(searchBy, identifier));
  }

  @GetMapping("/sort-and-filter")
  public ResponseEntity<List<TaskViewDto>> getByCategoryAndSort(@RequestParam(required = false) String categoryName,
      @RequestParam("sortBy") @Pattern(regexp = "priority|status|disabled", message = "must be 'priority', 'status' or 'disabled'") String sortBy)
      throws ServiceNotFoundException {
    return ResponseEntity.ok(taskService.getByCategoryAndSort(categoryName, sortBy));
  }

  @GetMapping
  public List<TaskViewDto> getAll() {
    return taskService.getAll();
  }

  @Operation(
      summary = "Update task",
      description = "Update already existing task."
  )
  @PutMapping(value = "{id}")
  public ResponseEntity<TaskViewDto> update(@PathVariable Long id,
      @RequestBody TaskCreateDto taskCreateDto) throws ServiceNotFoundException, WrongIdFormatException {
    return ResponseEntity.ok(taskService.update(id, taskCreateDto));
  }

  @Operation(
      summary = "Delete task",
      description = "Delete existing task."
  )
  @DeleteMapping("/{id}")
  public void deleteTask(@PathVariable Long id) {
    taskService.deleteById(id);
  }
}
