package com.justDoIt.backend.entities;

import com.justDoIt.backend.entities.enums.Priority;
import com.justDoIt.backend.entities.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TaskViewDto {

  private Long id;
  private String title;
  private String description;
  private Priority priority;
  private Status status;
  private Long categoryId;
}
