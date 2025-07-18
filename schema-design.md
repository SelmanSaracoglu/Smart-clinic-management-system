# Schema Design – Smart Clinic Management System

This document outlines the hybrid database design for the Smart Clinic application. 
The system manages both structured and unstructured data using two complementary database technologies:

- MySQL for relational and structured data (e.g., patients, appointments)
- MongoDB for flexible, nested documents (e.g., prescriptions, feedback, logs)

## 🗃️ MySQL – Relational Database Design

The following tables manage core entities of the system with strict relational structure and integrity.

### 1. patients

| Column Name   | Data Type           | Constraints                     |
|---------------|---------------------|----------------------------------|
| patient_id     | BIGINT              | PRIMARY KEY, AUTO_INCREMENT     |
| first_name     | VARCHAR(100)        | NOT NULL                        |
| last_name      | VARCHAR(100)        | NOT NULL                        |
| email          | VARCHAR(150)        | UNIQUE, NOT NULL                |
| phone          | VARCHAR(20)         |                                 |
| gender         | ENUM('Male','Female','Other') | NOT NULL        |
| date_of_birth  | DATE                | NOT NULL                        |
| created_at     | TIMESTAMP           | DEFAULT CURRENT_TIMESTAMP       |

### 2. doctors

| Column Name     | Data Type     | Constraints                     |
|-----------------|---------------|----------------------------------|
| doctor_id        | BIGINT        | PRIMARY KEY, AUTO_INCREMENT     |
| first_name       | VARCHAR(100)  | NOT NULL                        |
| last_name        | VARCHAR(100)  | NOT NULL                        |
| specialization   | VARCHAR(100)  | NOT NULL                        |
| email            | VARCHAR(150)  | UNIQUE, NOT NULL                |
| phone            | VARCHAR(20)   |                                 |
| availability     | TEXT          | Optional (can store JSON string)|

### 3. appointments

| Column Name       | Data Type     | Constraints                             |
|-------------------|---------------|------------------------------------------|
| appointment_id     | BIGINT        | PRIMARY KEY, AUTO_INCREMENT             |
| patient_id         | BIGINT        | FOREIGN KEY REFERENCES patients(patient_id) |
| doctor_id          | BIGINT        | FOREIGN KEY REFERENCES doctors(doctor_id)  |
| appointment_time   | DATETIME      | NOT NULL                                |
| status             | ENUM('Scheduled','Completed','Cancelled') | NOT NULL |
| reason             | TEXT          | Optional                                |

### 4. admins

| Column Name   | Data Type     | Constraints                       |
|---------------|---------------|------------------------------------|
| admin_id       | BIGINT        | PRIMARY KEY, AUTO_INCREMENT       |
| username       | VARCHAR(100)  | UNIQUE, NOT NULL                  |
| email          | VARCHAR(150)  | UNIQUE, NOT NULL                  |
| password       | VARCHAR(255)  | NOT NULL                          |
| role           | VARCHAR(50)   | DEFAULT 'ADMIN'                   |

> All primary keys are `BIGINT` for future scalability. 
> Foreign key relationships ensure data consistency across patients, doctors, and appointments.

## MongoDB – Document Collection Design

Unstructured or semi-structured data such as prescriptions are stored in MongoDB. 
These records vary greatly from one case to another, making document-based storage ideal.

### Collection: `prescriptions`

Each document stores a full prescription, issued by a doctor to a patient, including nested medication arrays and notes.

```json
{
  "_id": "6612c3a5b918f99b5e16a4d1",
  "patientId": 102,
  "doctorId": 14,
  "dateIssued": "2025-07-18T10:30:00Z",
  "medications": [
    {
      "name": "Amoxicillin",
      "dosage": "500mg",
      "frequency": "3 times a day",
      "duration": "7 days"
    },
    {
      "name": "Ibuprofen",
      "dosage": "200mg",
      "frequency": "as needed",
      "duration": "5 days"
    }
  ],
  "notes": "Take antibiotics after meals. Monitor for allergic reaction.",
  "revisitRecommended": true
}

