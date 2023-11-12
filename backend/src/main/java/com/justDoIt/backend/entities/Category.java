package com.justDoIt.backend.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Category {
  @Id
  private Long id;
  private String name;
  private String description;
  @OneToMany(mappedBy = "category")
  private List<Task> tasks;
}
