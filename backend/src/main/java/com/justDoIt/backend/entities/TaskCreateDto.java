package com.justDoIt.backend.entities;

import com.justDoIt.backend.entities.enums.Priority;
import com.justDoIt.backend.entities.enums.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TaskCreateDto {

  @NotBlank(message = "must not be blank")
  private String title;
  @NotNull(message = "must not be null")
  private String description;
  @NotNull(message = "must not be null")
  @Pattern(regexp = "(?i)^(high|medium|low)$", message = "must be high, medium or low")
  private String priority;
  @NotNull(message = "must not be null")
  @Pattern(regexp = "(?i)^(todo|done)$", message = "must be TODO or DONE")
  private String status;
  @NotNull
  @Min(value = 0L, message = "must be greater than or equal to 0")
  private Long categoryId;

}
