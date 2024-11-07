package com.example.demo;

public class TaskNotFundException extends RuntimeException{
    
TaskNotFundException(Long id) {
        super("Task " + id + " not found");
    }

    
}
