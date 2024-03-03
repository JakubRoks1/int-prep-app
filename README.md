# int-prep-app

# IntPrep - Job Interview Preparation Application

IntPrep is a web application built using Java, Spring Boot, Lombok, and PostgreSQL technologies, designed to assist users in preparing for job interviews.

## Features

- User registration and management.
- Addition, editing, and deletion of questions and answers related to various knowledge domains (e.g., programming, algorithms, software architecture, etc.).
- Browsing available questions and answers.
- Ability to mark questions and answers as favorites or completed.
- Generating random sets of questions for practice.
- Progress statistics tracking.

## Technologies Used

- Java
- Spring Boot
- Lombok
- PostgreSQL
- HTML/CSS (for the front-end layer)

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
