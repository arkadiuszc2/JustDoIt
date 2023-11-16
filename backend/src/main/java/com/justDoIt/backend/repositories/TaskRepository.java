package com.justDoIt.backend.repositories;

import com.justDoIt.backend.entities.Task;
import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

  Collection<Task> findAllByCategory_Id(Long categoryId);

  Collection<Task> getTasksByTitleIsContaining(String word);
}
