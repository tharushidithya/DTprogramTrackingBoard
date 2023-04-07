package com.webapp.trackingBoard.service;

import com.webapp.trackingBoard.model.Project;

import java.util.List;

public interface ProjectService {
    List<Project> getAllProjects();
    Project getProjectById(Long id);
    void createProject(Project project);
    void updateProject(Project project);
    void deleteProject(Long id);

    void addUserProject(Project project);
}
