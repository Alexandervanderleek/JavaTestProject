package com.taskmanagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name="tasks")
public class Task {
    @Id
    private UUID id;
}
