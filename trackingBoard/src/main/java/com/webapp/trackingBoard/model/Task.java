package com.webapp.trackingBoard.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "task")
public class Task {

    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer kpi;
    private Integer achieved_kpi;
    private Integer weight;
    private Integer cost_task;

    private String taskName;
    private String description;
    private Date dueDate;
    private boolean completed;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SubTask> subTasks = new ArrayList<>();

    public Task() {
    }


//    public Task(String taskName, String description, Date dueDate,Integer kpi,Integer achieved_kpi,Integer cost_task, Integer weight,Project project) {
//        this.taskName = taskName;
//        this.description = description;
//        this.dueDate = dueDate;
//        this.kpi=kpi;
//        this.achieved_kpi=achieved_kpi;
//        this.weight=weight;
//        this.cost_task=cost_task;
//        this.project = project;
//    }

    public void addSubTask(SubTask subTask) {
        subTasks.add(subTask);
        subTask.setTask(this);
    }

    public void removeSubTask(SubTask subTask) {
        subTasks.remove(subTask);
        subTask.setTask(null);
    }



    public boolean isCompleted(boolean completed) {
        return true;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }


}


