package com.justDoIt.backend.controllers;

import com.justDoIt.backend.entities.Task;
import com.justDoIt.backend.services.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.Collection;
import lombok.RequiredArgsConstructor;
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
  public Task create(@RequestBody Task task) {
    return taskService.create(task);
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
  public Task findById(@PathVariable Long id) {
    return taskService.findById(id);
  }

  @Operation(
      summary = "Find task by title content",
      description = "Find task by specifying words from its title."
  )
  @GetMapping(path = "contains/{text}")
  public Collection<Task> findAllWithGivenSubstringInTitle(@PathVariable String text) {
    return taskService.findAllWithGivenSubstringInTitle(text);
  }

  @Operation(
      summary = "Update task",
      description = "Update already existing task."
  )
  @PutMapping(value = "{id}")
  public Task update(@PathVariable Long id, @RequestBody Task task) {
    return taskService.update(id, task);
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
