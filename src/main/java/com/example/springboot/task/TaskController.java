package com.example.springboot.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TaskController {

    @Autowired
    private TaskServiceInterface taskServiceInterface;

    @GetMapping("/")
    public String showTaskist(Model model) {

        model.addAttribute("tasks", taskServiceInterface.getAllTasks());

        return "index";
    }

    @GetMapping("/task/refresh")
    public String refreshTask(Model model) {

        model.addAttribute("tasks", taskServiceInterface.getAllTasks());

        return "index :: task_frag";
    }

    @PostMapping("/task/add")
    public ResponseEntity<String> addTask(@RequestBody Task task) {

        return taskServiceInterface.addTask(task);
    }

    @PostMapping("/task/updateStatus/{id}")
    public ResponseEntity<String> updateTaskStatus(@PathVariable long id, @RequestParam String status) {

        return taskServiceInterface.updateTaskStatus(id, status);
    }

    @DeleteMapping("/task/delete/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable("id") long id) {

        return taskServiceInterface.deleteTask(id);
    }

}
