package com.sant.taskmaster.service;

import com.sant.taskmaster.entity.Task;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TaskService {

    private List<Task> taskList;

    public TaskService(){
        taskList = new ArrayList<>();
    }

    public Task createTask(Task task){
        taskList.add(task);
        return task;
    }

    public List<Task> searchAllTasks(){
        return taskList;
    }

    public Task searchOneTask(Long id) throws Exception {
        Optional<Task> task = taskList.stream().filter(p -> p.getId() == id).findFirst();

        if(task.isPresent()){
            return task.get();
        } else {
            throw new Exception("Tarefa não encontrada.");
        }
    }

    public Task editTask(Long id, Task updateTask) throws Exception{

        ListIterator<Task> iterator = taskList.listIterator();

        while (iterator.hasNext()){
            Task task = iterator.next();
            if(task.getId().equals(id)){
                task.setName(updateTask.getName());
                task.setDescription(updateTask.getDescription());
                task.setStatus(updateTask.getStatus());
                return task;
            }
        }

        throw new Exception("ID da tarefa não encontrado.");
    }

    public void deleteTask(Long id) throws Exception{

        Iterator<Task> iterator = taskList.iterator();

        while (iterator.hasNext()){
            Task task = iterator.next();
            if(task.getId().equals(id)){
                iterator.remove();
                return;
            }
        }

        throw new Exception("ID da tarefa não encontrado.");
    }

}
