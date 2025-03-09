# Project Structure

```
task-management/
├── server/                      # Backend server application
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/taskmanagement/
│   │   │   │   ├── config/      # Configuration classes
│   │   │   │   │   ├── SecurityConfig.java
│   │   │   │   │   └── WebConfig.java
│   │   │   │   ├── controller/  # REST controllers
│   │   │   │   │   ├── AuthController.java
│   │   │   │   │   ├── EpicController.java
│   │   │   │   │   ├── SprintController.java
│   │   │   │   │   ├── TaskController.java
│   │   │   │   │   └── UserController.java
│   │   │   │   ├── dto/         # Data Transfer Objects
│   │   │   │   │   ├── EpicDTO.java
│   │   │   │   │   ├── SprintDTO.java
│   │   │   │   │   ├── TaskDTO.java
│   │   │   │   │   └── ...
│   │   │   │   ├── exception/   # Custom exceptions
│   │   │   │   │   ├── ResourceNotFoundException.java
│   │   │   │   │   └── ...
│   │   │   │   ├── model/       # Domain model entities
│   │   │   │   │   ├── Epic.java
│   │   │   │   │   ├── Role.java
│   │   │   │   │   ├── Sprint.java
│   │   │   │   │   ├── Task.java
│   │   │   │   │   ├── TaskComment.java
│   │   │   │   │   ├── TaskPriority.java
│   │   │   │   │   ├── TaskStatus.java
│   │   │   │   │   └── User.java
│   │   │   │   ├── repository/  # Spring Data repositories
│   │   │   │   │   ├── EpicRepository.java
│   │   │   │   │   ├── RoleRepository.java
│   │   │   │   │   ├── SprintRepository.java
│   │   │   │   │   ├── TaskCommentRepository.java
│   │   │   │   │   ├── TaskPriorityRepository.java
│   │   │   │   │   ├── TaskRepository.java
│   │   │   │   │   ├── TaskStatusRepository.java
│   │   │   │   │   └── UserRepository.java
│   │   │   │   ├── security/    # Security components
│   │   │   │   │   ├── GoogleTokenVerifier.java
│   │   │   │   │   ├── JwtTokenProvider.java
│   │   │   │   │   └── ...
│   │   │   │   ├── service/     # Business logic services
│   │   │   │   │   ├── AuthService.java
│   │   │   │   │   ├── EpicService.java
│   │   │   │   │   ├── SprintService.java
│   │   │   │   │   ├── TaskService.java
│   │   │   │   │   └── UserService.java
│   │   │   │   └── TaskManagementApplication.java  # Main class
│   │   │   ├── resources/
│   │   │   │   ├── application.yml  # Application configuration
│   │   │   │   ├── application-dev.yml  # Dev profile configuration
│   │   │   │   ├── application-prod.yml  # Prod profile configuration
│   │   │   │   └── db/migration/    # Flyway migration scripts
│   │   │   │       ├── V1__create_initial_schema.sql
│   │   │   │       ├── V2__insert_default_data.sql
│   │   │   │       └── ...
│   │   ├── test/
│   │   │   ├── java/com/taskmanagement/
│   │   │   │   ├── controller/   # Controller tests
│   │   │   │   ├── repository/   # Repository tests
│   │   │   │   ├── service/      # Service tests
│   │   │   │   └── ...
│   │   │   └── resources/
│   │   │       └── application-test.yml  # Test configuration
│   ├── build.gradle              # Gradle build configuration for server
│   └── Dockerfile                # Docker container definition
├── cli/                          # Command line client
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/taskmanagement/cli/
│   │   │   │   ├── command/      # CLI commands
│   │   │   │   ├── config/       # Configuration
│   │   │   │   ├── model/        # Data models
│   │   │   │   ├── service/      # Services for API communication
│   │   │   │   ├── util/         # Utilities
│   │   │   │   └── TaskManagementCli.java  # Main CLI class
│   │   │   └── resources/
│   │   │       └── application.yml
│   │   └── test/
│   │       └── java/com/taskmanagement/cli/
│   ├── build.gradle              # Gradle build configuration for CLI
│   └── Dockerfile                # Docker container for CLI build
├── infrastructure/               # Infrastructure as code
│   ├── terraform/                # Terraform configuration
│   │   ├── main.tf               # Main Terraform configuration
│   │   ├── variables.tf          # Input variables
│   │   ├── outputs.tf            # Output values
│   │   ├── rds.tf                # RDS database resources
│   │   ├── ecs.tf                # ECS container resources
│   │   └── ...
│   └── scripts/                  # Infrastructure scripts
│       ├── deploy.sh             # Deployment script
│       └── ...
├── .github/                      # GitHub configuration
│   └── workflows/                # GitHub Actions
│       ├── build.yml             # Build workflow
│       ├── test.yml              # Test workflow
│       └── deploy.yml            # Deployment workflow
├── build.gradle                  # Root Gradle build file
├── settings.gradle               # Gradle settings file
└── README.md                     # Project documentation
```
