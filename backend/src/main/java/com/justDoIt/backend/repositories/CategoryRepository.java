package com.justDoIt.backend.repositories;

import com.justDoIt.backend.entities.Category;
import java.util.Collection;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

  boolean existsByName(String categoryName);

  Optional<Category> getCategoryByName(String categoryName);

  Collection<Category> getCategoriesByNameIsContaining(String word);
}
