package com.justDoIt.backend.services;

import com.justDoIt.backend.entities.Category;
import com.justDoIt.backend.entities.Task;
import com.justDoIt.backend.entities.TaskCreateDto;
import com.justDoIt.backend.entities.TaskViewDto;
import com.justDoIt.backend.exceptions.CategoryNotFoundException;
import com.justDoIt.backend.exceptions.ServiceLayerException;
import com.justDoIt.backend.exceptions.TaskNotFoundException;
import com.justDoIt.backend.exceptions.SearchModeNotFoundException;
import com.justDoIt.backend.exceptions.SortModeNotFoundException;
import com.justDoIt.backend.mappings.TaskCreateMapper;
import com.justDoIt.backend.mappings.TaskViewMapper;
import com.justDoIt.backend.repositories.CategoryRepository;
import com.justDoIt.backend.repositories.TaskRepository;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskService {

  private final TaskRepository taskRepository;
  private final CategoryRepository categoryRepository;
  private final TaskViewMapper taskViewMapper;
  private final TaskCreateMapper taskCreateMapper;

  public TaskViewDto create(TaskCreateDto taskCreateDto)
      throws ServiceLayerException { //change to pick category by name not id
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

  public List<TaskViewDto> getByIdOrContainingTextInTitle(String searchBy, String identifier)
      throws ServiceLayerException {
    List<TaskViewDto> taskList = new ArrayList<>();
    if (searchBy.equals("id")) {
      Long id = Long.parseLong(identifier);
      taskList.add(taskViewMapper.toDto(taskRepository.findById(id)
          .orElseThrow(() -> new TaskNotFoundException("Task with given id does not exist"))));
    } else if (searchBy.equals("name")) {
      taskList = taskRepository.getTasksByTitleIsContaining(identifier).stream()
          .map(taskViewMapper::toDto).toList();
    } else {
      throw new SearchModeNotFoundException("Provided task search mode does not exist");
    }

    if (taskList.isEmpty()) {
      throw new CategoryNotFoundException("Task with given name does not exist");
    }

    return taskList;
  }

  public List<TaskViewDto> getByCategoryAndSort(String categoryName, String sortBy)
      throws ServiceLayerException{
    Stream<Task> taskStream;
    if (categoryName!=null) {
      Category category = categoryRepository.getCategoryByName(categoryName).orElseThrow(
          () -> new CategoryNotFoundException("Category with given name does not exist"));
      taskStream = taskRepository.findAllByCategory_Id(category.getId()).stream();
    } else {
      taskStream = taskRepository.findAll().stream();
    }
    taskStream = sortTaskStream(taskStream, sortBy);
    return taskStream.map(taskViewMapper::toDto).toList();
  }

  private Stream<Task> sortTaskStream(Stream<Task> taskStream, String sortBy) throws SortModeNotFoundException {
    taskStream = switch (sortBy) {
      case "disabled" -> taskStream;
      case "priority" -> taskStream.sorted(Comparator.comparing(Task::getPriority));
      case "status" -> taskStream.sorted(Comparator.comparing(Task::getStatus));
      default -> throw new SortModeNotFoundException("Procided sort mode does not exist");
    };
    return taskStream;
  }

  public List<TaskViewDto> getAll() {
    return taskRepository.findAll().stream().map(taskViewMapper::toDto).toList();
  }

  public TaskViewDto update(Long id, TaskCreateDto taskCreateDto) throws ServiceLayerException {
    taskRepository.findById(id)
        .orElseThrow(() -> new TaskNotFoundException("Task with given id does not exist"));
    Task task = taskCreateMapper.toEntity(taskCreateDto);
    task.setId(id);
    Category category = categoryRepository.findById(taskCreateDto.getCategoryId())
        .orElseThrow(
            () -> new CategoryNotFoundException("Category with given id does not exist"));
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
