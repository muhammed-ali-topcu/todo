package com.example.demo;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {

    private final TaskRepository repository;

    TaskController(TaskRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/tasks")
    List<Task> index() {
        return repository.findAll();
    }

    @GetMapping("/tasks/search/{keyword}")
    List<Task> search(@PathVariable("keyword") String keyword) {
        return repository.search(keyword.toLowerCase());
    }

    @GetMapping("/tasks/{id}")
    Task show2(@PathVariable("id") Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new TaskNotFundException(id));
    }

    @PostMapping("/tasks")
    Task store(@RequestBody Task newTask) {
        return repository.save(newTask);
    }

    @PutMapping("/tasks/{id}")
    Task update(@RequestBody Task _task, @PathVariable("id") Long id) {

        return repository.findById(id)
                .map(task -> {
                    task.setTitle(_task.getTitle());
                    task.setDescription(_task.getDescription());
                    task.setCompleted(_task.isCompleted());
                    return repository.save(task);
                })
                .orElseThrow(() -> new TaskNotFundException(id));
    }

    @PutMapping("/tasks/{id}/complete")
    Task complete(@PathVariable("id") Long id) {
        return repository.findById(id)
                .map(task -> {
                    task.setCompleted(true);
                    return repository.save(task);
                })
                .orElseThrow(() -> new TaskNotFundException(id));
    }

    @DeleteMapping("/tasks/{id}")
    void delete(@PathVariable("id") Long id) {
        repository.deleteById(id);
    }

}
