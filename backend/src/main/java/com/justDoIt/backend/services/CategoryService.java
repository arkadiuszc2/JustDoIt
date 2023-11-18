package com.justDoIt.backend.services;

import com.justDoIt.backend.entities.Category;
import com.justDoIt.backend.entities.CategoryCreateDto;
import com.justDoIt.backend.exceptions.CategoryNameNotUniqueException;
import com.justDoIt.backend.exceptions.CategoryNotFoundException;
import com.justDoIt.backend.exceptions.ServiceNotFoundException;
import com.justDoIt.backend.exceptions.SearchModeNotFoundException;
import com.justDoIt.backend.mappings.CategoryCreateMapper;
import com.justDoIt.backend.repositories.CategoryRepository;
import com.justDoIt.backend.repositories.TaskRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

  private final CategoryRepository categoryRepository;
  private final TaskRepository taskRepository;
  private final CategoryCreateMapper categoryCreateMapper;

  public Category create(CategoryCreateDto categoryCreateDto) throws ServiceNotFoundException {
    Category category = categoryCreateMapper.toEntity(categoryCreateDto);
    if (categoryRepository.existsByName(category.getName())) {
      throw new CategoryNameNotUniqueException("Category name must be unique");
    }
    return categoryRepository.save(category);
  }

  public List<Category> getByIdOrContainingTextInName(String searchBy, String identifier)
      throws ServiceNotFoundException {
    List<Category> categoryList = new ArrayList<>();
    if (searchBy.equals("id")) {
      Long id = Long.parseLong(identifier);
      categoryList.add(categoryRepository.findById(id).orElseThrow(
          () -> new CategoryNotFoundException("Category with given id does not exist")));
    } else if (searchBy.equals("name")) {
      categoryList = categoryRepository.getCategoriesByNameIsContaining(identifier).stream()
          .toList();
    } else {
      throw new SearchModeNotFoundException("Provided category search mode does not exist");
    }

    if (categoryList.isEmpty()) {
      throw new CategoryNotFoundException("Category with given name does not exist");
    }

    return categoryList;
  }

  public List<Category> getAll() {
    return categoryRepository.findAll();
  }

  public Category update(Long id, CategoryCreateDto categoryCreateDto)
      throws ServiceNotFoundException {
    Category category = categoryRepository.findById(id)
        .orElseThrow(() -> new CategoryNotFoundException("Category with given id does not exist"));
    category.setDescription(categoryCreateDto.getDescription());
    category.setName(categoryCreateDto.getName());
    return categoryRepository.save(category);
  }

  public void delete(Long id) throws ServiceNotFoundException {
    if (!categoryRepository.existsById(id)) {
      throw new CategoryNotFoundException("Category with given id does not exist");
    }
    taskRepository.findAllByCategory_Id(id)
        .forEach(task -> taskRepository.deleteById(task.getId()));
    categoryRepository.deleteById(id);
  }
}
