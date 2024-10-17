package com.example.springboot.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TaskService implements TaskServiceInterface {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAllTasks() {

        return taskRepository.findByOrderByCreateDateDesc();
    }

    public Task getTask(Long id) {

        return taskRepository.findById(id).orElse(null);
    }

    public ResponseEntity<String> addTask(Task task) {

        taskRepository.save(task);

        return ResponseEntity.ok("OK");
    }

    public ResponseEntity<String> updateTaskStatus(Long id, String status) {
        Task task = getTask(id);

        if (task != null) {
            task.setStatus(status);
            taskRepository.save(task);

            return ResponseEntity.ok("OK");
        }

        return ResponseEntity.badRequest().body("Task not found");
    }

    public ResponseEntity<String> deleteTask(Long taskId) {

        taskRepository.deleteById(taskId);

        return ResponseEntity.ok("OK");
    }

}
