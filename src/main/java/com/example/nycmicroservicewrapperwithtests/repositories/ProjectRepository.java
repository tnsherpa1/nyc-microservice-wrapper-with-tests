package com.example.nycmicroservicewrapperwithtests.repositories;

import com.example.nycmicroservicewrapperwithtests.models.Project;
import org.springframework.data.repository.CrudRepository;

public interface ProjectRepository extends CrudRepository<Project, Long>{
}
