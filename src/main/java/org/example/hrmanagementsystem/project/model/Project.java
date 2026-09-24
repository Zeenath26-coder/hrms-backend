package org.example.hrmanagementsystem.project.model;

import jakarta.persistence.*;
import lombok.*;
import org.example.hrmanagementsystem.auth.entity.User;
import org.example.hrmanagementsystem.employees.model.Employee;
import org.example.hrmanagementsystem.enums.ProjectStatus;
import org.example.hrmanagementsystem.task.entity.Task;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table (name ="project")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SQLRestriction("deleted = false")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectId;

    @Column(nullable = false)
    private String projectName ;

    @Column(nullable = false)
    private boolean deleted = false;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDate createdDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProjectStatus status = ProjectStatus.ACTIVE;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "managerId" , nullable = false)
    private User manager ;


    @ManyToMany
    @JoinTable(
            name ="project_employee",
            joinColumns = @JoinColumn(name= "project_id" ),
            inverseJoinColumns= @JoinColumn(name="employee_id")
    )
    private List<Employee> employees = new ArrayList<>();


    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
    private List<Task> tasks  = new ArrayList<>();
}
