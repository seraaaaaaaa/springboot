package com.example.springboot.task;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface TaskServiceInterface {

    List<Task> getAllTasks();

    Task getTask(Long id);

    ResponseEntity<String> addTask(Task task);

    ResponseEntity<String> updateTaskStatus(Long id, String status);

    ResponseEntity<String> deleteTask(Long taskId);
}
