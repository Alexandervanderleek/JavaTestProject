package com.taskmanagement.dto;

import java.time.ZonedDateTime;
import java.util.UUID;

public class TaskDTO {
    private UUID id;
    private UUID epicId;
    private UUID sprintId;
    private UUID assignedToId;
    private UUID priorityId;
    private String title;
    private String description;
    private UUID statusId;
    private int storyPoints;
    private int estimatedHours;
    private ZonedDateTime dueDate;
}
