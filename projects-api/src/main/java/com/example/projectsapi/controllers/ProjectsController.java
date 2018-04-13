package com.example.projectsapi.controllers;

import com.example.projectsapi.models.Project;
import com.example.projectsapi.repositories.ProjectRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class ProjectsController {
    @Autowired
    private ProjectRepository projectRepository;

    @GetMapping("/")
    public Iterable<Project> findAllProjects() {
        return projectRepository.findAll();
    }

    @GetMapping("/{projectId}")
    public Project findProjectById(@PathVariable Long projectId) throws NotFoundException {
        Project foundProject = projectRepository.findOne(projectId);

        if(foundProject == null) {
            throw new NotFoundException("Project with ID of " + projectId + " was not found!");
        }
        return foundProject;
    }

    @DeleteMapping("/{projectId}")
    public HttpStatus deleteProjectById(@PathVariable Long projectId) {
        projectRepository.delete(projectId);
        return HttpStatus.OK;
    }

    @PostMapping("/")
    public Project createNewProject(@RequestBody Project newProject) {
        return projectRepository.save(newProject);
    }

    @PatchMapping("/{projectId}")
    public Project updateProjectById(@PathVariable Long projectId, @RequestBody Project projectRequest) {
        Project projectFromDb = projectRepository.findOne(projectId);

        projectFromDb.setAgencyName(projectRequest.getAgencyName());
        projectFromDb.setContactName(projectRequest.getContactName());
        projectFromDb.setEmail(projectRequest.getEmail());

        return projectRepository.save(projectFromDb);
    }

    @ExceptionHandler
    void handleProjectNotFound(
            NotFoundException exception,
            HttpServletResponse response) throws IOException {

        response.sendError(HttpStatus.NOT_FOUND.value(), exception.getMessage());
    }


}
