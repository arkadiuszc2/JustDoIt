package com.justDoIt.backend.entities;

import com.justDoIt.backend.entities.enums.Priority;
import com.justDoIt.backend.entities.enums.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Builder
public class Task {

  @Id
  @GeneratedValue
  private Long id;
  private String title;
  private String description;
  @Enumerated(EnumType.STRING)
  private Priority priority;
  @Enumerated(EnumType.STRING)
  private Status status;
  @ManyToOne
  @JoinColumn(name = "category_id")
  private Category category;

}
