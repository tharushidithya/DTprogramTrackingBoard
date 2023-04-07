package com.webapp.trackingBoard.controller;


import com.webapp.trackingBoard.model.SubTask;
import com.webapp.trackingBoard.model.Task;
import com.webapp.trackingBoard.model.TaskStatus;
import com.webapp.trackingBoard.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable("id") Long id) {
        Task task = taskService.getTaskById(id);
        if (task == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Task> createTask() {
        Task task = new Task();
        task.setTaskName(task.getTaskName());
        task.setDescription(task.getDescription());
        task.setCost_task(task.getCost_task());
        task.setKpi(task.getKpi());
        task.setAchieved_kpi(task.getAchieved_kpi());
        task.setWeight(task.getWeight());
        task.isCompleted(task.isCompleted(task.isCompleted()));
        task.setDueDate(task.getDueDate());
        taskService.createTask(task);
        return new ResponseEntity<>(task, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable("id") Long id, @RequestBody Task task) {
        Task existingTask = taskService.getTaskById(id);
        if (existingTask == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        existingTask.setTaskName(task.getTaskName());
        existingTask.setDescription(task.getDescription());
        task.setCost_task(task.getCost_task());
        task.setKpi(task.getKpi());
        task.setAchieved_kpi(task.getAchieved_kpi());
        task.setWeight(task.getWeight());
        existingTask.setCompleted(task.isCompleted(task.isCompleted()));
        existingTask.setDueDate(task.getDueDate());
        taskService.updateTask(existingTask);
        return new ResponseEntity<>(existingTask, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteTask(@PathVariable("id") Long id) {
        taskService.deleteTask(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{id}/sub-tasks")
    public ResponseEntity<SubTask> createSubTask(@PathVariable("id") Long id) {
        Task task = taskService.getTaskById(id);
        if (task == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        SubTask subTask = new SubTask();
        task.setSubTaskName(task.getSubTaskName());
        task.addSubTask(subTask);
        taskService.updateTask(task);
        taskService.deleteSubTask(task.getId());
        return new ResponseEntity<>(subTask, HttpStatus.CREATED);
    }

//progress bar status
    @GetMapping("/tasks/progress")
    public ResponseEntity<Map<String, Double>> getTaskProgress() {
        double totalTasks = taskService.getTotalTasksCount();
        double notStartedCount = taskService.getTasksCountByStatus(TaskStatus.NOT_STARTED);
        double inProgressCount = taskService.getTasksCountByStatus(TaskStatus.IN_PROGRESS);
        double completedCount = taskService.getTasksCountByStatus(TaskStatus.COMPLETED);

        Map<String, Double> progressMap = new HashMap<>();
        progressMap.put("notStartedPercentage", notStartedCount / totalTasks * 100);
        progressMap.put("inProgressPercentage", inProgressCount / totalTasks * 100);
        progressMap.put("completedPercentage", completedCount / totalTasks * 100);

        return ResponseEntity.ok(progressMap);
    }





}

