package com.example.nycmicroservicewrapperwithtests.repositories;

import com.example.nycmicroservicewrapperwithtests.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
