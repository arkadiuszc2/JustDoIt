package com.justDoIt.backend.mappings;

import com.justDoIt.backend.entities.Category;
import com.justDoIt.backend.entities.CategoryCreateDto;
import org.mapstruct.Mapper;

@Mapper
public interface CategoryCreateMapper {

  Category toEntity(CategoryCreateDto categoryCreateDto);
}
