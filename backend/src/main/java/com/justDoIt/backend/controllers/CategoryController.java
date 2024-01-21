package com.justDoIt.backend.controllers;

import com.justDoIt.backend.entities.Category;
import com.justDoIt.backend.entities.CategoryCreateDto;
import com.justDoIt.backend.exceptions.ServiceNotFoundException;
import com.justDoIt.backend.services.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
@SecurityRequirement(name = "bearer-key")
@RequestMapping(value = "categories")
@ValidateOnExecution
public class CategoryController {

  private final CategoryService categoryService;
  @Operation(
      summary = "Create new category",
      description = "Create new category with unique name"
  )
  @PostMapping
  public Category create(@RequestBody @Valid CategoryCreateDto categoryCreateDto)
      throws ServiceNotFoundException {
    return categoryService.create(categoryCreateDto);
  }
  @Operation(
      summary = "Find categories by id or name",
      description = "Find category by providing its id (to search for one specific category) "
          + "or provide task name (all categories with title containing provided keyword will be shown)"
  )
  @GetMapping("/{identifier}")
  public ResponseEntity<List<Category>> getByIdOrContainingTextInName(
      @RequestParam("searchBy") @Pattern(regexp = "id|name", message = "must be 'id' or 'name'") String searchBy,
      @PathVariable("identifier") @Pattern(regexp = "^[1-9][0-9]*$") String identifier)
      throws ServiceNotFoundException {
    return ResponseEntity.status(HttpStatus.OK)
        .body(categoryService.getByIdOrContainingTextInName(searchBy, identifier));
  }
  @Operation(
      summary = "Get all categories",
      description = "Get all exisiting categories"
  )
  @GetMapping
  public List<Category> getAll() {
    return categoryService.getAll();
  }

  @Operation(
      summary = "Update category",
      description = "Update existing category"
  )
  @PutMapping("/{id}")
  public ResponseEntity<Category> update(@PathVariable Long id,
      @RequestBody CategoryCreateDto categoryCreateDto) throws ServiceNotFoundException {
    return ResponseEntity.ok(categoryService.update(id, categoryCreateDto));
  }
  @Operation(
      summary = "Delete category",
      description = "Delete category and all tasks which belong to this category"
  )
  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) throws ServiceNotFoundException {
    categoryService.delete(id);
  }
}
