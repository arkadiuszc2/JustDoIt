package com.justDoIt.backend.services;

import com.justDoIt.backend.entities.Task;
import com.justDoIt.backend.repositories.TaskRepository;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class TaskService {

  private final TaskRepository taskRepository;

  public Task create(Task task) {
    return taskRepository.save(task);
  }

  public Task findById(Long id) {
    return taskRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found"));
  }

  public Task update(Long id, Task task) {
    if (!Objects.equals(id, task.getId())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
          "Given id and new task id dont match");
    }
    return taskRepository.save(task);
  }

  public void deleteById(Long id) {
    taskRepository.deleteById(id);
  }
}
