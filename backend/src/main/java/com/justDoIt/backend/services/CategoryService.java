package com.justDoIt.backend.services;

import com.justDoIt.backend.entities.Category;
import com.justDoIt.backend.repositories.CategoryRepository;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class CategoryService {

  private final CategoryRepository categoryRepository;

  public Category create(Category category) {
    return categoryRepository.save(category);
  }

  public Category getCategory(Long id) {
    return categoryRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
        HttpStatus.NOT_FOUND, "Category with given id does not exist"));
  }

  public Category update(Long id, Category category) {
    if (!Objects.equals(id, category.getId())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
          "Given id and new task id dont match");
    }
    return categoryRepository.save(category);
  }
  public void delete(Long id){
    categoryRepository.deleteById(id);
  }
}
