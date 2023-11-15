package com.justDoIt.backend.mappings;

import com.justDoIt.backend.entities.Task;
import com.justDoIt.backend.entities.TaskCreateDto;
import com.justDoIt.backend.entities.enums.Priority;
import com.justDoIt.backend.entities.enums.Status;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TaskCreateMapper {

  @Mapping(target = "category", ignore = true)
  Task toEntity(TaskCreateDto taskCreateDto);

  default Priority mapPriority(String priority) {
    if (priority == null) {
      return null;
    }
    return Priority.valueOf(priority.toUpperCase());
  }

  default Status mapStatus(String status) {
    if (status == null) {
      return null;
    }
    return Status.valueOf(status.toUpperCase());
  }
}
