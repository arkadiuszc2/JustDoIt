package com.justDoIt.backend.services;

import com.justDoIt.backend.entities.Category;
import com.justDoIt.backend.entities.CategoryCreateDto;
import com.justDoIt.backend.entities.Task;
import com.justDoIt.backend.exceptions.CategoryNameNotUniqueException;
import com.justDoIt.backend.exceptions.CategoryNotFoundException;
import com.justDoIt.backend.exceptions.ServiceLayerException;
import com.justDoIt.backend.mappings.CategoryCreateMapper;
import com.justDoIt.backend.repositories.CategoryRepository;
import com.justDoIt.backend.repositories.TaskRepository;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class CategoryService {

  private final CategoryRepository categoryRepository;
  private final TaskRepository taskRepository;
  private final CategoryCreateMapper categoryCreateMapper;

  public Category create(CategoryCreateDto categoryCreateDto) throws ServiceLayerException{
    Category category = categoryCreateMapper.toEntity(categoryCreateDto);
    if(categoryRepository.existsByName(category.getName())){
      throw new CategoryNameNotUniqueException("Category name must be unique");
    }
    return categoryRepository.save(category);
  }

  public Category getById(Long id) throws ServiceLayerException {
    return categoryRepository.findById(id)
        .orElseThrow(() -> new CategoryNotFoundException("Category with given id does not exist"));
  }

  public List<Category> getAll() {
    return categoryRepository.findAll();
  }

  public List<Category> getAllWithNameContainingKeyword(String keyword) {
    return categoryRepository.findAll().stream()
        .filter(category -> category.getName().toLowerCase().contains(keyword.toLowerCase()))
        .toList();
  }

  public Category update(Long id, CategoryCreateDto categoryCreateDto) throws ServiceLayerException {
    Category category = categoryRepository.findById(id)
        .orElseThrow(() -> new CategoryNotFoundException("Category with given id does not exist"));
    category.setDescription(categoryCreateDto.getDescription());
    category.setName(categoryCreateDto.getName());
    return categoryRepository.save(category);
  }

  public void delete(Long id) {
    taskRepository.findAll().stream().filter(task -> task.getCategory().getId().equals(id))
        .forEach(task -> task.setCategory(null));
    categoryRepository.deleteById(id);
  }
}
