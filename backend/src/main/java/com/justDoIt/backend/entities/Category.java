package com.justDoIt.backend.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
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
  @GeneratedValue
  private Long id;
  private String name;
  private String description;
  //@OneToMany(mappedBy = "category")
  //private List<Task> tasks; //for now bidirectional relation but one-directional is enough
  // not needed - tasks for category will be shown from
  //endpoints /tasks/{category}
}
