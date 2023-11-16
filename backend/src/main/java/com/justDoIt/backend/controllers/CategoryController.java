package com.justDoIt.backend.controllers;

import com.justDoIt.backend.entities.Category;
import com.justDoIt.backend.entities.CategoryCreateDto;
import com.justDoIt.backend.exceptions.ServiceNotFoundException;
import com.justDoIt.backend.services.CategoryService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.executable.ValidateOnExecution;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "categories")
@ValidateOnExecution
public class CategoryController {

  private final CategoryService categoryService;

  @PostMapping
  public Category create(@RequestBody @Valid CategoryCreateDto categoryCreateDto)
      throws ServiceNotFoundException {
    return categoryService.create(categoryCreateDto);
  }

  @GetMapping("/{identifier}")
  public ResponseEntity<List<Category>> getByIdOrContainingTextInName(
      @RequestParam("searchBy") @Pattern(regexp = "id|name", message = "must be 'id' or 'name'") String searchBy,
      @PathVariable("identifier") @Pattern(regexp = "^[1-9][0-9]*$") String identifier)
      throws ServiceNotFoundException {
    return ResponseEntity.status(HttpStatus.OK)
        .body(categoryService.getByIdOrContainingTextInName(searchBy, identifier));
  }

  @GetMapping
  public List<Category> getAll() {
    return categoryService.getAll();
  }

  @PutMapping("/{id}")
  public ResponseEntity<Category> update(@PathVariable Long id,
      @RequestBody CategoryCreateDto categoryCreateDto) throws ServiceNotFoundException {
    return ResponseEntity.ok(categoryService.update(id, categoryCreateDto));
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) throws ServiceNotFoundException {
    categoryService.delete(id);
  }
}
