# Task Management API

## Authentication Endpoints

### OAuth 2.0 with Google

- **POST** `/api/auth/login`
  - Processes Google OAuth token
  - Returns JWT for subsequent API calls
  - Required in headers: `Authorization: Bearer {google_token}`
  - Response includes: User details and JWT token

- **POST** `/api/auth/refresh`
  - Refreshes an existing JWT token
  - Required in headers: `Authorization: Bearer {jwt_token}`
  - Response includes: New JWT token

## User Endpoints

- **GET** `/api/users/me`
  - Returns the current user's profile
  - Required in headers: `Authorization: Bearer {jwt_token}`
  
- **GET** `/api/users`
  - Returns list of users (for assignment)
  - Parameters: 
    - `role` (optional): Filter by role
  - Required in headers: `Authorization: Bearer {jwt_token}`

## Task Endpoints

- **GET** `/api/tasks`
  - Returns list of tasks based on filters
  - Parameters:
    - `assigned_to_me`: Boolean
    - `created_by_me`: Boolean
    - `epic_id`: UUID
    - `sprint_id`: UUID
    - `status_id`: UUID
    - `priority_id`: UUID
  - Required in headers: `Authorization: Bearer {jwt_token}`

- **GET** `/api/tasks/{id}`
  - Returns single task details
  - Required in headers: `Authorization: Bearer {jwt_token}`

- **POST** `/api/tasks`
  - Creates a new task
  - Required in headers: `Authorization: Bearer {jwt_token}`
  - Request body: Task details

- **PUT** `/api/tasks/{id}`
  - Updates an existing task
  - Required in headers: `Authorization: Bearer {jwt_token}`
  - Request body: Updated task details

- **DELETE** `/api/tasks/{id}`
  - Deletes a task
  - Required in headers: `Authorization: Bearer {jwt_token}`

- **POST** `/api/tasks/{id}/comments`
  - Adds a comment to a task
  - Required in headers: `Authorization: Bearer {jwt_token}`
  - Request body: Comment content

## Epic Endpoints

- **GET** `/api/epics`
  - Returns list of epics
  - Parameters:
    - `owned_by_me`: Boolean
  - Required in headers: `Authorization: Bearer {jwt_token}`

- **GET** `/api/epics/{id}`
  - Returns single epic details with associated tasks
  - Required in headers: `Authorization: Bearer {jwt_token}`

- **POST** `/api/epics`
  - Creates a new epic
  - Required in headers: `Authorization: Bearer {jwt_token}`
  - Request body: Epic details

- **PUT** `/api/epics/{id}`
  - Updates an existing epic
  - Required in headers: `Authorization: Bearer {jwt_token}`
  - Request body: Updated epic details

- **DELETE** `/api/epics/{id}`
  - Deletes an epic and potentially its tasks
  - Required in headers: `Authorization: Bearer {jwt_token}`
  - Parameters: 
    - `delete_tasks`: Boolean (default: false)

## Sprint Endpoints

- **GET** `/api/sprints`
  - Returns list of sprints
  - Parameters:
    - `active_only`: Boolean
  - Required in headers: `Authorization: Bearer {jwt_token}`

- **GET** `/api/sprints/{id}`
  - Returns single sprint details with associated tasks
  - Required in headers: `Authorization: Bearer {jwt_token}`

- **POST** `/api/sprints`
  - Creates a new sprint
  - Required in headers: `Authorization: Bearer {jwt_token}`
  - Request body: Sprint details

- **PUT** `/api/sprints/{id}`
  - Updates an existing sprint
  - Required in headers: `Authorization: Bearer {jwt_token}`
  - Request body: Updated sprint details

- **DELETE** `/api/sprints/{id}`
  - Deletes a sprint (but not its tasks)
  - Required in headers: `Authorization: Bearer {jwt_token}`

## Lookup Endpoints

- **GET** `/api/statuses`
  - Returns list of available task statuses
  - Required in headers: `Authorization: Bearer {jwt_token}`

- **GET** `/api/priorities`
  - Returns list of available task priorities
  - Required in headers: `Authorization: Bearer {jwt_token}`

- **GET** `/api/roles`
  - Returns list of available user roles
  - Required in headers: `Authorization: Bearer {jwt_token}`
