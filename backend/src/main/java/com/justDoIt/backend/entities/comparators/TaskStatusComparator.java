package com.justDoIt.backend.entities.comparators;

import com.justDoIt.backend.entities.Task;
import java.util.Comparator;

public class TaskStatusComparator implements Comparator<Task> {
  @Override
  public int compare(Task task1, Task task2) {
    return task1.getStatus().compareTo(task2.getStatus());
  }
}

