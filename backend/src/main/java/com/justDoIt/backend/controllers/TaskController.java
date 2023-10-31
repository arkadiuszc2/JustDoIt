package com.justDoIt.backend.controllers;

import com.justDoIt.backend.entities.Task;
import com.justDoIt.backend.services.TaskService;
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

  @PostMapping
  public Task create(@RequestBody Task task) {
    return taskService.create(task);
  }

  @GetMapping("/{id}")
  public Task findById(@PathVariable Long id) {
    return taskService.findById(id);
  }

  @PutMapping(value = "{id}")
  public Task update(@PathVariable Long id, @RequestBody Task task) {
    return taskService.update(id, task);
  }

  @DeleteMapping("/{id}")
  public void deleteTask(@PathVariable Long id) {
    taskService.deleteById(id);
  }
}
