

# Smart Clinic Management System – Architecture Design

## Section 1: Architecture Summary

The Smart Clinic Management System is built using a three-tier architecture that separates the application into Presentation, Application, and Data layers. 
The frontend is created with HTML, CSS, and JavaScript, which communicates with backend microservices via RESTful APIs developed in Java with Spring Boot. 
These APIs are managed using the MVC (Model-View-Controller) pattern. 
Data is stored in both relational (MySQL) and NoSQL (MongoDB) databases. 
Spring Data JPA is used to interact with MySQL for structured entities like doctors and patients, while Spring Data MongoDB handles flexible documents such as prescriptions.

## Section 2: Request/Response Flow

1. User Interaction (Frontend)
   The user (doctor, patient, or admin) interacts with the UI built with HTML, CSS, and JavaScript.

2. HTTP Request Sent to REST API
   The frontend sends an HTTP request (e.g., POST /appointments) to the backend REST API.

3. Controller Layer (Spring MVC)
   The request is received by a Spring Boot REST controller, which acts as the entry point of the application layer.

4. Service Layer (Business Logic)
   The controller delegates the request to a service class that handles business logic and validations.

5. Repository Layer (Persistence)
   The service interacts with a repository interface to perform CRUD operations:
    - MySQL: Uses Spring Data JPA repositories for entities like User, Patient, Doctor, Appointment.
    - MongoDB: Uses Spring Data MongoDB repositories for flexible documents like Prescriptions.

6. Database Access
   The appropriate database is queried or updated (MySQL or MongoDB) and a result is returned to the service layer.

7. Response Handling  
   The service returns the response to the controller, which wraps it in an HTTP response (e.g., 200 OK, JSON body).

8. Frontend Display
   The frontend receives the response and updates the UI accordingly.

