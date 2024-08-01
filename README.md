# Spring Boot Task Manager Application

## Overview
This is a Task Manager application built with Spring Boot. It allows users to manage tasks and user profiles. The application uses a relational database for data storage and provides RESTful APIs for interaction.

## Features
- User authentication and authorization.
- CRUD operations for tasks.
- CRUD operations  for users.
- User profile management.
- Relationship between users and tasks.

## Technologies Used
- **Backend**: Spring Boot, Spring Security, JPA (Hibernate)
- **Database**: MySQL/PostgreSQL (for production)
- **Frontend**: React (optional for frontend development)
- **Build Tool**: Maven
- **Other**: Lombok, JUnit, Mockito

## Getting Started

### Prerequisites
- Java 21 or higher
- Maven 3.6+
- IDE (IntelliJ IDEA, Eclipse, VSCode)

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/LIBERTY-D/Task-Manager-Back-End.git
   cd task-manager
2. **Setup Database**
    Update the application.yml file with your database configurations:
    ```
    spring:
      datasource:
        url: jdbc:mysql://localhost:3306/taskmanager
        username: root
        password: yourpassword
      jpa:
        hibernate:
          ddl-auto: update
3. **Build the project**
   ```bash
   mvn clean install
4. **Run the application**
   mvn spring-boot:run
