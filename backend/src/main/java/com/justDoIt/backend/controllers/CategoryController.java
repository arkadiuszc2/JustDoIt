package com.justDoIt.backend.controllers;

import com.justDoIt.backend.entities.Category;
import com.justDoIt.backend.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping(value="categories")
public class CategoryController {
  private final CategoryService categoryService;

  @PostMapping
  public Category create(@RequestBody Category category){
    return categoryService.create(category);
  }
  @GetMapping("/{id}")
  public Category get(@PathVariable Long id){
    return categoryService.getCategory(id);
  }
  @PutMapping("/{id}")
  public Category update(@PathVariable Long id, @RequestBody Category category){
    return categoryService.update(id,category);
  }
  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id){
    categoryService.delete(id);
  }
}
