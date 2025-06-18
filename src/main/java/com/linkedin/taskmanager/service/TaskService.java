package com.linkedin.taskmanager.service;

import com.linkedin.taskmanager.model.Task;
import com.linkedin.taskmanager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class TaskService {

    @Autowired
    TaskRepository taskRepository;

    public Task updateTaskStatus(Long taskId, String status) {
        Optional<Task> task = taskRepository.findById(taskId);
        if(task.isPresent()){
            Task existingTask = task.get();
            existingTask.setStatus(status);
            return taskRepository.save(existingTask);
        } else {
            throw new RuntimeException("Task not found with id: " + taskId);

        }
    }
}
