package com.justDoIt.backend.entities;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryCreateDto {

  @NotBlank(message = "must not be blank")
  private String name;
  @NotNull(message = "must not be null")
  private String description;
}
