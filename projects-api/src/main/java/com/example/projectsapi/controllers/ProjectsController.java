package com.example.projectsapi.controllers;

import com.example.projectsapi.models.Project;
import com.example.projectsapi.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

public class ProjectsController {
    @Autowired
    private ProjectRepository projectRepository;

    @RequestMapping("/")
    public Iterable<Project> findAllProjects() {
        return projectRepository.findAll();
    }
}
