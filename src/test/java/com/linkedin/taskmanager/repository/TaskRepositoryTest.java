package com.linkedin.taskmanager.repository;

import com.linkedin.taskmanager.model.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class TaskRepositoryTest {

    @Autowired
    TaskRepository taskRepository;

    @Test
    public void testSaveTask(){
        Task task = new Task("Test Task", "To Do");

        //TaskRepository taskRepository = new TaskRepository();
        Task savedTask = taskRepository.save(task);

        assertNotNull(savedTask);
        assertEquals("Test Task", savedTask.getTitle());
        //assertNull(savedTask);
    }

    @Test
    public void testDeleteTask(){
        //arrange
        Task task = new Task("Task To be Deleted", "Completed");
//        task.setTitle("Task To be Deleted");
//        task.setStatus("Completed");
        taskRepository.save(task);

        //act
        taskRepository.delete(task);
        Optional<Task> deletedTask = taskRepository.findById(task.getId());

        //assert
        assertFalse(deletedTask.isPresent());
    }

    @Test
    public void testFindAllTasks() {
        // Arrange
        Task task1 = new Task("Task 1", "To Do");
        Task task2 = new Task("Task 2", "Done");
        taskRepository.save(task1);
        taskRepository.save(task2);

        // Act
        List<Task>  tasks = taskRepository.findAll();

        // Assert
        assertEquals(2, tasks.size());
    }

    @Test
    public void testFindTaskById() {
        // Arrange
        Task task = new Task("Task 1", "To Do");
        Task savedTask = taskRepository.save(task);

        // Act
        Optional<Task> foundTask = taskRepository.findById(savedTask.getId());

        // Assert
        assertTrue(foundTask.isPresent());
        assertEquals("Task 1", foundTask.get().getTitle());
    }

    @Test
    public void testUpdateTask() {
        // Arrange
        Task task = new Task("Old Task", "To Do");
        Task savedTask = taskRepository.save(task);

        // Act
        savedTask.setTitle("Updated Task");
        savedTask.setStatus("In Progress");
        Task updatedTask = taskRepository.save(savedTask);

        // Assert
        assertEquals("Updated Task", updatedTask.getTitle());
        assertEquals("In Progress", updatedTask.getStatus());
    }
}
