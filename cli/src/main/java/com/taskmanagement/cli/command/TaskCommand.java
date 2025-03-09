package com.taskmanagement.cli.command;

import com.taskmanagement.cli.service.APIService;
import org.springframework.stereotype.Component;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

@Component
@Command(name = "task", description = "Manage tasks")
public class TaskCommand {

    @Autowired
    private APIService apiService;

    @Command(name = "list", description = "List all tasks")
    public Integer list() {
        try {
            Object[] tasks = apiService.get("/tasks", Object[].class);
            if (tasks.length == 0) {
                System.out.println("No tasks found");
            } else {
                System.out.println("Tasks:");
                for (Object taskObj : tasks) {
                    // Convert task object to a map for easier handling
                    @SuppressWarnings("unchecked")
                    Map<String, Object> task = (Map<String, Object>) taskObj;

                    System.out.println("- ID: " + task.get("id"));
                    System.out.println("  Title: " + task.get("title"));
                    System.out.println("  Assigned to: " + task.get("assignedToName"));
                    System.out.println("  Status: " + task.get("statusName"));
                    System.out.println("  Priority: " + task.get("priorityName"));
                    System.out.println();
                }
            }
            return 0;
        } catch (Exception e) {
            System.err.println("Error fetching tasks: " + e.getMessage());
            return 1;
        }
    }

    @Command(name = "create", description = "Create a new task")
    public Integer create(
            @Option(names = {"-t", "--title"}, required = true) String title,
            @Option(names = {"-d", "--desc"}, required = true) String description,
            @Option(names = {"-a", "--assignee"}, required = true) String assigneeId,
            @Option(names = {"-s", "--status"}, required = true) String statusId,
            @Option(names = {"-p", "--priority"}, required = true) String priorityId,
            @Option(names = {"-due", "--due-date"}, required = true) String dueDate
    ) {
        try {
            // Create task request object
            Map<String, Object> task = new HashMap<>();
            task.put("title", title);
            task.put("description", description);
            task.put("assignedToId", assigneeId);
            task.put("statusId", statusId);
            task.put("priorityId", priorityId);
            task.put("dueDate", dueDate);
            task.put("storyPoints", 0);
            task.put("estimatedHours", 0);

            Object createdTask = apiService.post("/tasks", task, Object.class);
            System.out.println("Task created successfully!");
            return 0;
        } catch (Exception e) {
            System.err.println("Error creating task: " + e.getMessage());
            return 1;
        }
    }

    @Command(name = "get", description = "Get task details")
    public Integer get(@CommandLine.Parameters(index = "0", description = "Task ID") String taskId) {
        try {
            Object taskObj = apiService.get("/tasks/" + taskId, Object.class);
            @SuppressWarnings("unchecked")
            Map<String, Object> task = (Map<String, Object>) taskObj;

            System.out.println("Task Details:");
            System.out.println("ID: " + task.get("id"));
            System.out.println("Title: " + task.get("title"));
            System.out.println("Description: " + task.get("description"));
            System.out.println("Assigned to: " + task.get("assignedToName"));
            System.out.println("Status: " + task.get("statusName"));
            System.out.println("Priority: " + task.get("priorityName"));
            System.out.println("Story Points: " + task.get("storyPoints"));
            System.out.println("Estimated Hours: " + task.get("estimatedHours"));
            System.out.println("Due Date: " + task.get("dueDate"));

            return 0;
        } catch (Exception e) {
            System.err.println("Error fetching task: " + e.getMessage());
            return 1;
        }
    }

    // Other task operations like update, delete, etc.
}