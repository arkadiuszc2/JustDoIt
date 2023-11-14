package com.justDoIt.backend.services;

import com.justDoIt.backend.entities.Category;
import com.justDoIt.backend.entities.Task;
import com.justDoIt.backend.entities.TaskCreateDto;
import com.justDoIt.backend.entities.TaskViewDto;
import com.justDoIt.backend.entities.comparators.TaskPriorityComparator;
import com.justDoIt.backend.entities.comparators.TaskStatusComparator;
import com.justDoIt.backend.mappings.TaskCreateMapper;
import com.justDoIt.backend.mappings.TaskViewMapper;
import com.justDoIt.backend.repositories.CategoryRepository;
import com.justDoIt.backend.repositories.TaskRepository;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
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
  private final TaskStatusComparator taskStatusComparator = new TaskStatusComparator();
  private final TaskPriorityComparator taskPriorityComparator = new TaskPriorityComparator();

  public TaskViewDto create(TaskCreateDto taskCreateDto) { //change to pick category by name not id
    Task task = taskCreateMapper.toEntity(taskCreateDto);
    boolean categoryExists = categoryRepository.existsById(taskCreateDto.getCategoryId());
    Category category;
    if (categoryExists) {
      category = categoryRepository.findById(taskCreateDto.getCategoryId())
          .orElseThrow(() -> new NoSuchElementException("Category with given id does not exist"));
    } else {
      category = null;
    }
    task.setCategory(category);
    return taskViewMapper.toDto(taskRepository.save(task));
  }

  public TaskViewDto findById(Long id) {
    Task task = taskRepository.findById(id)
        .orElseThrow(() -> new NoSuchElementException("Task with given id does not exist"));
    return taskViewMapper.toDto(task);
  }

  public Collection<TaskViewDto> findAllWithGivenSubstringInTitle(String text) {
    return taskRepository.findAll().stream()
        .filter(task -> task.getTitle().toLowerCase().indexOf(text.toLowerCase()) != -1).map(
            taskViewMapper::toDto)
        .toList();
  }

  public List<TaskViewDto> getAll() {
    return taskRepository.findAll().stream().map(taskViewMapper::toDto).toList();
  }

  public List<TaskViewDto> getByCategory(String categoryName) {
    return getTaskEntityByCategoryName(categoryName).stream().map(taskViewMapper::toDto).toList();
  }

  public List<TaskViewDto> getByCategorySortByStatus(String categoryName) {
    return getTaskEntityByCategoryName(categoryName).stream().sorted(taskStatusComparator)
        .map(taskViewMapper::toDto).toList();
  }

  public List<TaskViewDto> getByCategorySortByPriority(String categoryName) {
    return getTaskEntityByCategoryName(categoryName).stream().sorted(taskPriorityComparator)
        .map(taskViewMapper::toDto).toList();
  }


  public TaskViewDto update(Long id, TaskCreateDto taskCreateDto) {
    taskRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Task with given id does not exist"));
    Task task = taskCreateMapper.toEntity(taskCreateDto);
    task.setId(id);
    boolean categoryExists = categoryRepository.existsById(taskCreateDto.getCategoryId());
    Category category;
    if (categoryExists) {
      category = categoryRepository.findById(taskCreateDto.getCategoryId())
          .orElseThrow(() -> new NoSuchElementException("Category with given id does not exist"));
    } else {
      category = null;
    }
    task.setCategory(category);
    taskRepository.save(task);
    return taskViewMapper.toDto(task);
  }

  public void deleteById(Long id) {
    taskRepository.deleteById(id);
  }


  private List<Task> getTaskEntityByCategoryName(String categoryName) {
    return taskRepository.findAll().stream().filter(task -> task.getCategory().getName()
        .equals(categoryName)).toList();
  }
}
