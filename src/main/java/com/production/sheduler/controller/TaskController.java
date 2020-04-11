package com.production.sheduler.controller;

import com.production.sheduler.domain.Task;
import com.production.sheduler.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tasks")
public class TaskController {

    private final TaskRepository taskRepository;

    @GetMapping
    public Flux<Task> listTasks(){
        return this.taskRepository.findAll();
    }

    @PostMapping
    public Mono<ResponseEntity<Task>> createTask(@RequestBody Task task){
        return taskRepository.save(task)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/{taskId}")
    public Mono<ResponseEntity<Task>> getTaskById(@PathVariable String taskId,
                                                  @RequestBody Task task){
        return taskRepository.findById(taskId)
                .flatMap(dbTask -> {
                    dbTask.setTaskName(task.getTaskName());
                    dbTask.setCompleted(task.getCompleted());
                    dbTask.setRoot(task.getRoot());
                    return taskRepository.save(dbTask);
                })
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{taskId}")
    public Mono<ResponseEntity<Void>> deleteTaskById(@PathVariable String taskId){
        return taskRepository.findById(taskId)
                .flatMap(existingTask ->
                        taskRepository.delete(existingTask)
                                .then(Mono.just(ResponseEntity.ok().<Void>build()))
                )
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
