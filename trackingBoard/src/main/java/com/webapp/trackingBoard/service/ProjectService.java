package com.webapp.trackingBoard.service;

import java.util.List;
import com.webapp.trackingBoard.model.Project;

public interface ProjectService {
    List<Project> getAllProjects();
    Project getProjectById(Long id);
    void createProject(Project project);
    void updateProject(Project project);
    void deleteProject(Long id);
}
