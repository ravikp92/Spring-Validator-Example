package com.ravi.apac.assignment.repository;

import org.springframework.data.repository.CrudRepository;
import com.ravi.apac.assignment.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
}