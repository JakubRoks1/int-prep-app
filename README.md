# int-prep-app.

# IntPrep - Job Interview Preparation Application

IntPrep is a web application built using Java, Spring Boot, Lombok, and PostgreSQL technologies, designed to assist users in preparing for job interviews.


## Idea for a Project

This project is being developed as part of the "100 Commits" initiative. "100 Commits" is a nationwide, open, and entirely free competition aimed at all programmers, regardless of their level of experience, organized by DevMentors. The competition involves the development of a personal, public open-source project in any programming language, on a chosen topic (e.g., web application, mobile app, game, framework, library, etc.).

## Features

- User registration and management.
- Addition, editing, and deletion of questions and answers related to various knowledge domains (e.g., programming, algorithms, software architecture, etc.).
- Browsing available questions and answers.
- Ability to mark questions and answers as favorites or completed.
- Generating random sets of questions for practice.
- Progress statistics tracking.
- e-mail notifications/reminders
- generate set of questions

## Technologies Used

- Java
- Spring Boot
- Lombok
- PostgreSQL
- HTML/CSS (for the front-end layer)
- Junit 5
- Mockito
- AssertJ
- Maven
- Postman
- Angular (?)
- Feign

## Functionalities

### Project Setup and Configuration:

Set up a new Spring Boot project with Maven or Gradle. [x]

Configure project dependencies including Spring Boot, Lombok, and PostgreSQL. [x]

Configure database connection properties in application.properties file. [x]

Set up Lombok for automatic boilerplate code generation. [x]

### User Management:

Implement user registration functionality. [x]

Set up user authentication and authorization using Spring Security.

Create user profile management features (update profile, change password, etc.).

Implement user roles (e.g., admin, regular user) if necessary.

### Question and Answer Management:

Design database schema for storing questions and answers. [x]

Implement CRUD operations for questions and answers. [x]

Create RESTful APIs for adding, editing, and deleting questions and answers. [x]

Validate input data for questions and answers.

Implement pagination and sorting for browsing questions and answers.

### Favorite and Completed Lists:

Implement functionality for users to mark questions and answers as favorites or completed.

Create endpoints for managing favorite and completed lists.

Allow users to view and manage their lists through the UI.

### Random Question Sets:

Develop an algorithm to generate random sets of questions based on user preferences.

Implement endpoints for generating and retrieving random question sets.

Ensure that the same question is not repeated in a single set.

### Progress Tracking:

Design database schema for storing user progress statistics.

Implement functionality to track user progress (e.g., number of questions answered, accuracy, etc.).

Create APIs to retrieve and update user progress data.

Display progress statistics on the user dashboard.

### Email Notifications/Reminders:

Integrate email sending functionality using Spring Mail.

Implement scheduled tasks for sending email notifications/reminders (e.g., daily practice reminders, weekly progress reports).

Customize email templates for different types of notifications.

### Additional Features:

Implement search functionality to allow users to search for specific questions or topics.

Add filtering options for browsing questions by categories or difficulty levels.

Integrate a rich text editor for creating and editing question descriptions.

Implement user feedback and rating system for questions and answers.

### Testing:

Write unit tests for critical components using JUnit and Mockito.

Perform integration testing to ensure proper interaction between different modules.

Conduct user acceptance testing to gather feedback and ensure usability.

### Deployment:

Deploy the application to a cloud platform (e.g., AWS, Azure, Heroku).

Set up continuous integration and continuous deployment (CI/CD) pipelines.

Configure monitoring and logging for monitoring application performance and identifying issues.

### Documentation:

Document the application architecture, APIs, and deployment process.

Write user documentation and guides for using the application.

Create developer documentation for future maintenance and contributions.

### Maintenance and Updates:

Plan for regular maintenance tasks such as database backups, security patches, etc.

Monitor user feedback and update the application based on user suggestions and bug reports.

Stay updated with the latest versions of frameworks and libraries used in the project.

## Screenshots

## Demo

## Prerequisites

To run the application locally, you need to have installed:

- Java Development Kit (JDK)
- PostgreSQL
- Maven (optionally, if you are using a project build system)

## Running the Application

1. Clone the repository:

git clone https://github.com//intprep.git


2. Navigate to the project directory:

cd intprep


3. Configure the PostgreSQL database according to the settings in the `application.properties` file.

4. Run the application using Maven:

mvn spring-boot:run

After completing these steps, the application should be accessible at `http://localhost:8080`.

.
