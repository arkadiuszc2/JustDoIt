package com.justDoIt.backend.repositories;

import com.justDoIt.backend.entities.Task;
import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
  Collection<Task> findAllByCategory_Id(Long categoryId);
}
