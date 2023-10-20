package com.sant.taskmaster.controller;

import com.sant.taskmaster.entity.Task;
import com.sant.taskmaster.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("all")
    public ResponseEntity<?> searchAllTasks(){
        try {
            List<Task> list = taskService.searchAllTasks();
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>("Erro na requisição", HttpStatus.BAD_GATEWAY);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> searchOneTask(@PathVariable("id") Long id){
        try {
            Task task = taskService.searchOneTask(id);
            return new ResponseEntity(task, HttpStatus.OK);
        } catch (Exception exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("add")
    public ResponseEntity<?> createTask(@RequestBody Task task){
        try {
            task = taskService.createTask(task);
            return new ResponseEntity<>(task, HttpStatus.CREATED);
        } catch (Exception exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("edit/{id}")
    public ResponseEntity<?> editTask(@RequestBody Task task, @PathVariable("id") Long id){
        try {
            task = taskService.editTask(id, task);
            return new ResponseEntity<>(task, HttpStatus.OK);
        } catch (Exception exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("delet/{id}")
    public ResponseEntity<?> deletTask(@PathVariable("id") Long id){
        try {
            taskService.deleteTask(id);
            return new ResponseEntity<>("Tarefa removida com sucesso!", HttpStatus.OK);
        } catch (Exception exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
