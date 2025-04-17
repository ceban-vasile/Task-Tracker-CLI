# Task Tracker CLI

A Java-based command-line interface (CLI) application for managing tasks with features like adding, updating, deleting, and filtering tasks.

## Features

- Add new tasks with descriptions and status
- Update existing tasks
- Delete tasks
- List tasks with filtering options
- Command history with undo capability
- JSON-based persistent storage

## Technologies Used

- Java
- Jackson (for JSON handling)
- Design Patterns:
  - Command Pattern
  - Factory Pattern
  - Builder Pattern
  - Strategy Pattern
  - Singleton Pattern

## Installation

1. Clone the repository
2. Ensure you have Java JDK 8 or higher installed
3. Build the Fat JAR using Maven:
```bash
mvn clean package assembly:single
```
The Fat JAR will be created in the `target` directory as `task-tracker-cli-1.0-SNAPSHOT-jar-with-dependencies.jar`

## Usage

First, make sure you're in the directory containing the Fat JAR. You can run the application using:

```bash
java -jar task-tracker-cli-1.0-SNAPSHOT-jar-with-dependencies.jar [command] [arguments]
```

For convenience, you can create an alias:
```bash
# On Unix/Linux/MacOS
alias taskcli='java -jar /path/to/task-tracker-cli-1.0-SNAPSHOT-jar-with-dependencies.jar'

# On Windows (in PowerShell)
Set-Alias -Name taskcli -Value "java -jar path\to\task-tracker-cli-1.0-SNAPSHOT-jar-with-dependencies.jar"
```

### Basic Commands:

```bash
# Add a task
taskcli add "Buy groceries"

# Add a task with specific status
taskcli add "Write report" --status=IN_PROGRESS
```

### Listing Tasks

```bash
# List all tasks
taskcli list

# List tasks by status
taskcli list --status=TODO
taskcli list --status=IN_PROGRESS
taskcli list --status=DONE

# List tasks created after a specific timestamp
taskcli list --after=1634567890000
```

### Updating Tasks

```bash
# Update task description
taskcli update <task_id> --desc="New description"

# Update task status
taskcli update <task_id> --status=DONE

# Update both description and status
taskcli update <task_id> --desc="New description" --status=IN_PROGRESS
```

### Deleting Tasks

```bash
taskcli delete <task_id>
```

## Project Structure

```
src/main/java/org/tracker_task_cli/
├── command/          # Command pattern implementation
├── create_task/      # Factory pattern for task creation
├── file_management/  # JSON file handling
├── listing_tasks/    # Strategy pattern for task filtering
├── model/           # Data models
└── Main.java        # Application entry point
```

## Task Properties

Each task contains:
- Unique ID
- Description
- Status (TODO, IN_PROGRESS, DONE)
- Creation timestamp
- Last update timestamp

## Data Storage

Tasks are stored in a JSON file located at `data/tasks.json`. The file is automatically created when the first task is added.

## Error Handling

The application includes robust error handling for:
- Invalid commands
- Missing parameters
- Invalid task IDs
- File I/O operations
- Invalid status values

## Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## License

This project is licensed under the MIT License.