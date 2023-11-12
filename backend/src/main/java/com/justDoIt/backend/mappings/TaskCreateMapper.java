package com.justDoIt.backend.mappings;

import com.justDoIt.backend.entities.Task;
import com.justDoIt.backend.entities.TaskCreateDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TaskCreateMapper {
  @Mapping(target = "category", ignore = true)
  Task toEntity(TaskCreateDto taskCreateDto);

}
