package com.justDoIt.backend.mappings;

import com.justDoIt.backend.entities.Task;
import com.justDoIt.backend.entities.TaskViewDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TaskViewMapper {
  TaskViewMapper INSTANCE = Mappers.getMapper(TaskViewMapper.class);
  @Mapping(source = "category.id", target = "categoryId")
  TaskViewDto toDto(Task task);
  Task toEntity(TaskViewDto taskViewDto);
}
