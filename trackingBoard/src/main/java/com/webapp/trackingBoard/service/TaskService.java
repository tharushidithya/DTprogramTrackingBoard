package com.webapp.trackingBoard.service;

import com.webapp.trackingBoard.model.Task;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.List;

@Configuration
@Service
@ComponentScan
public interface TaskService {

    List<Task> getAllTasks();

    Task getTaskById(Long id);

    void createTask(Task task);

    void updateTask(Task task);

    void deleteTask(Long id);

    List<Task> getSubTasks(Long id);

    void createSubTask(Long id, Task subTask);

    void updateSubTask(Long id, Task subTask);

    void deleteSubTask(Long id);

}

