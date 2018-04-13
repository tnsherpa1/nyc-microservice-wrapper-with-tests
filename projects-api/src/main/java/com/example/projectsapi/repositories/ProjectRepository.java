package com.example.projectsapi.repositories;

import com.example.projectsapi.models.Project;
import org.springframework.data.repository.CrudRepository;

public interface ProjectRepository extends CrudRepository<Project, Long>{
}
