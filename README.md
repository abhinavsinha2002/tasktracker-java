Task tracker is a project used to track and manage your tasks. In this task, you will build a simple command line interface (CLI) to track what you need to do, what you have done, and what you are currently working on. This project will help you practice your programming skills, including working with the filesystem, handling user inputs, and building a simple CLI application.

Maven build tool is used for this project. The language is java.

# How to run

You can directly run the file from the terminal(CMD), but I would recommend running the files from launch.json in VSCode as it is much easier to input the arguments there.
So, in the launch.json enter the arguemnts as  "args": ["list","done"] and then run the file Main.java
The required functionality is obtained.

# Supported Commands

The list of commands and their usage is given below:

# Adding a new task
task-cli add "Buy groceries" <br>
Output: Task added successfully (ID: 1)

# Updating and deleting tasks
task-cli update 1 "Buy groceries and cook dinner"<br>
task-cli delete 1

# Marking a task as in progress or done
task-cli mark-in-progress 1<br>
task-cli mark-done 1

# Listing all tasks
task-cli list

# Listing tasks by status
task-cli list done<br>
task-cli list todo<br>
task-cli list in-progress<br>
