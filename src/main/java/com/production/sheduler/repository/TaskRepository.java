package com.production.sheduler.repository;

import com.production.sheduler.domain.Task;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface TaskRepository extends ReactiveCrudRepository<Task, String> {

}
