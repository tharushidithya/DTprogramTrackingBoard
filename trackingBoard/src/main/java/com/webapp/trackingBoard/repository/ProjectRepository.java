package com.webapp.trackingBoard.repository;

import com.webapp.trackingBoard.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProjectRepository extends JpaRepository<Project, Long> {

}

