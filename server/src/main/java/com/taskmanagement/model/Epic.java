package com.taskmanagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name="epics")
public class Epic {
   @Id
   private UUID id;
}
