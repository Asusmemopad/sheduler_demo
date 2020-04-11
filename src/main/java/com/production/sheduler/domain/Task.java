package com.production.sheduler.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    @Id
    private String taskId;
    private String taskName;
    private Boolean completed;
    private Boolean root;
    private StateTask state;
    private String description;
    private Integer completion;
    private List<Task> subTask;
}
