package com.justDoIt.backend.services;

import com.justDoIt.backend.entities.Category;
import com.justDoIt.backend.entities.Task;
import com.justDoIt.backend.entities.TaskCreateDto;
import com.justDoIt.backend.entities.TaskViewDto;
import com.justDoIt.backend.mappings.TaskCreateMapper;
import com.justDoIt.backend.mappings.TaskViewMapper;
import com.justDoIt.backend.repositories.CategoryRepository;
import com.justDoIt.backend.repositories.TaskRepository;
import java.util.Collection;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class TaskService {

  private final TaskRepository taskRepository;
  private final CategoryRepository categoryRepository;
  private final TaskViewMapper taskViewMapper;
  private final TaskCreateMapper taskCreateMapper;

  public TaskViewDto create(TaskCreateDto taskCreateDto) {
    Task task = taskCreateMapper.toEntity(taskCreateDto);
    Category category = categoryRepository.findById(taskCreateDto.getCategoryId()).orElseThrow();
    task.setCategory(category);
    return taskViewMapper.toDto(taskRepository.save(task));
  }

  public TaskViewDto findById(Long id) {
    Task task = taskRepository.findById(id).orElseThrow();
    return taskViewMapper.toDto(task);
  }

  public Collection<TaskViewDto> findAllWithGivenSubstringInTitle(String text) {
    return taskRepository.findAll().stream()
        .filter(task -> task.getTitle().toLowerCase().indexOf(text.toLowerCase()) != -1).map(
            taskViewMapper::toDto)
        .toList();
  }


  public TaskViewDto update(Long id, TaskViewDto taskViewDto) {
    if (!Objects.equals(id, taskViewDto.getId())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
          "Given id and new task id dont match");
    }
    Task task = taskRepository.save(taskViewMapper.toEntity(taskViewDto));
    return taskViewMapper.toDto(task);
  }

  public void deleteById(Long id) {
    taskRepository.deleteById(id);
  }

}
