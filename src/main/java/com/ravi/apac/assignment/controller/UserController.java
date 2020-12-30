package com.ravi.apac.assignment.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.Positive;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ravi.apac.assignment.model.User;
import com.ravi.apac.assignment.repository.UserRepository;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

 
@RestController
@RequestMapping("users")
@Validated
public class UserController {
 
    private final UserRepository userRepository;
 
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
     
    
    @GetMapping("{id}")
    @Valid
    public Optional<User> getUser(@PathVariable @Positive Long id) {
        return userRepository.findById(id);
    } 
    
    @PostMapping
    public ResponseEntity<String> addUser(@RequestBody @Valid User user) {
        userRepository.save(user);
        return ResponseEntity.ok("User data is valid");
    }
    
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
     
        ex.getBindingResult().getFieldErrors().forEach(error -> 
            errors.put(error.getField(), error.getDefaultMessage()));
         
        return errors;
    }
    
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ConstraintViolationException.class)
    public Map<String, String> handleConstraintViolation(ConstraintViolationException ex) {
        Map<String, String> errors = new HashMap<>();
         
        ex.getConstraintViolations().forEach(cv -> {
            errors.put("message", cv.getMessage());
            errors.put("path", (cv.getPropertyPath()).toString());
        }); 
     
        return errors;
    }
    
}