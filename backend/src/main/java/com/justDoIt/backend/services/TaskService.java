package com.justDoIt.backend.services;

import com.justDoIt.backend.entities.Category;
import com.justDoIt.backend.entities.Task;
import com.justDoIt.backend.entities.TaskCreateDto;
import com.justDoIt.backend.entities.TaskViewDto;
import com.justDoIt.backend.exceptions.CategoryNotFoundException;
import com.justDoIt.backend.exceptions.ServiceLayerException;
import com.justDoIt.backend.exceptions.TaskNotFoundException;
import com.justDoIt.backend.mappings.TaskCreateMapper;
import com.justDoIt.backend.mappings.TaskViewMapper;
import com.justDoIt.backend.repositories.CategoryRepository;
import com.justDoIt.backend.repositories.TaskRepository;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskService {

  private final TaskRepository taskRepository;
  private final CategoryRepository categoryRepository;
  private final TaskViewMapper taskViewMapper;
  private final TaskCreateMapper taskCreateMapper;

  public TaskViewDto create(TaskCreateDto taskCreateDto) throws ServiceLayerException { //change to pick category by name not id
    Task task = taskCreateMapper.toEntity(taskCreateDto);
    Long categoryId = taskCreateDto.getCategoryId();
    if (categoryId != null) {
      Category category = categoryRepository.findById(categoryId)
          .orElseThrow(
              () -> new CategoryNotFoundException("Category with given id does not exist"));
      task.setCategory(category);
    } else {
      task.setCategory(null);
    }
    return taskViewMapper.toDto(taskRepository.save(task));
  }

  public TaskViewDto findById(Long id) throws ServiceLayerException {
    Task task = taskRepository.findById(id)
        .orElseThrow(() -> new TaskNotFoundException("Task with given id does not exist"));
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
    return getTaskEntityByCategoryName(categoryName).stream()
        .sorted(Comparator.comparing(Task::getStatus))
        .map(taskViewMapper::toDto)
        .toList();
  }

  public List<TaskViewDto> getByCategorySortByPriority(String categoryName) {
    return getTaskEntityByCategoryName(categoryName).stream()
        .sorted(Comparator.comparing(Task::getPriority))
        .map(taskViewMapper::toDto).toList();
  }


  public TaskViewDto update(Long id, TaskCreateDto taskCreateDto) throws ServiceLayerException {
    taskRepository.findById(id)
        .orElseThrow(() -> new TaskNotFoundException("Task with given id does not exist"));
    Task task = taskCreateMapper.toEntity(taskCreateDto);
    task.setId(id);
    Category category = categoryRepository.findById(taskCreateDto.getCategoryId())
        .orElseThrow(() -> new CategoryNotFoundException("Category with given id does not exist"));
    task.setCategory(category);
    taskRepository.save(task);
    return taskViewMapper.toDto(task);
  }

  public void deleteById(Long id) {
    taskRepository.deleteById(id);
  }


  private List<Task> getTaskEntityByCategoryName(String categoryName) {
    List<Task> taskList = taskRepository.findAll().stream().toList();
    List<Task> filteredList = new ArrayList<Task>();
    for (Task task : taskList) {
      if (task.getCategory() != null) {
        if (task.getCategory().getName().equals(categoryName)) {
          filteredList.add(task);
        }
      }
    }
    return filteredList;
  }
}
