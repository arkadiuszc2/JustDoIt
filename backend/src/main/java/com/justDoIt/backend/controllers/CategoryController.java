package com.justDoIt.backend.controllers;

import com.justDoIt.backend.entities.Category;
import com.justDoIt.backend.entities.CategoryCreateDto;
import com.justDoIt.backend.services.CategoryService;
import jakarta.validation.Valid;
import jakarta.validation.executable.ValidateOnExecution;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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
@RequestMapping(value = "categories")
@ValidateOnExecution
public class CategoryController {

  private final CategoryService categoryService;

  @PostMapping
  public Category create(@RequestBody @Valid CategoryCreateDto categoryCreateDto) {
    return categoryService.create(categoryCreateDto);
  }

  @GetMapping("/{id}")
  public Category get(@PathVariable Long id) {
    return categoryService.getById(id);
  }

  @GetMapping
  public List<Category> getAll() {
    return categoryService.getAll();
  }

  @GetMapping("/contains/{keyword}")
  public List<Category> getAllWithNameContainingKeyword(@PathVariable String keyword){
    return categoryService.getAllWithNameContainingKeyword(keyword);
  }

  @PutMapping("/{id}")
  public Category update(@PathVariable Long id, @RequestBody CategoryCreateDto categoryCreateDto) {
    return categoryService.update(id, categoryCreateDto);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) {
    categoryService.delete(id);
  }
}
