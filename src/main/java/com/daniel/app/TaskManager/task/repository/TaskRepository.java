package com.daniel.app.TaskManager.task.repository;

import com.daniel.app.TaskManager.task.model.TaskModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository()
public interface TaskRepository extends JpaRepository<TaskModel,Integer> {
}
