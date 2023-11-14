package com.justDoIt.backend.controllers;

import com.justDoIt.backend.entities.Category;
import com.justDoIt.backend.entities.Task;
import com.justDoIt.backend.entities.TaskCreateDto;
import com.justDoIt.backend.entities.TaskViewDto;
import com.justDoIt.backend.services.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
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
  public ResponseEntity<?> create(@RequestBody @Valid TaskCreateDto taskCreateDto) {
    try {
      return ResponseEntity.ok(taskService.create(taskCreateDto));
    } catch (NoSuchElementException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body("An error occured: " + e.getMessage());
    } catch (RuntimeException e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
    }

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
  @GetMapping("/{id}")
  public ResponseEntity<?> findById(@PathVariable Long id) {
    try {
      return ResponseEntity.ok(taskService.findById(id));
    } catch (NoSuchElementException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body("An error occured: " + e.getMessage());
    } catch (RuntimeException e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
    }
  }

  @Operation(
      summary = "Find task by title content",
      description = "Find task by specifying words from its title."
  )
  @GetMapping(path = "contains/{text}")
  public Collection<TaskViewDto> findAllWithGivenSubstringInTitle(@PathVariable String text) {
    return taskService.findAllWithGivenSubstringInTitle(text);
  }

  @GetMapping
  public List<TaskViewDto> getAll() {
    return taskService.getAll();
  }

  @GetMapping("/categories/{categoryName}")
  public List<TaskViewDto> getByCategory(@PathVariable String categoryName) {
    return taskService.getByCategory(categoryName);
  }

  @GetMapping("/categories/{categoryName}/sortStatus")
  public List<TaskViewDto> getByCategorySortByStatus(@PathVariable String categoryName) {
    return taskService.getByCategorySortByStatus(categoryName);
  }

  @GetMapping("/categories/{categoryName}/sortPriority")
  public List<TaskViewDto> getByCategorySortByPriority(@PathVariable String categoryName) {
    return taskService.getByCategorySortByPriority(categoryName);
  }


  @Operation(
      summary = "Update task",
      description = "Update already existing task."
  )
  @PutMapping(value = "{id}")
  public ResponseEntity<?> update(@PathVariable Long id, @RequestBody TaskCreateDto taskCreateDto) {
    try {
      return ResponseEntity.ok(taskService.update(id, taskCreateDto));
    } catch (IllegalArgumentException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body("An error occured" + e.getMessage());
    } catch (NoSuchElementException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body("An error occured: " + e.getMessage());
    } catch (RuntimeException e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
    }
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
