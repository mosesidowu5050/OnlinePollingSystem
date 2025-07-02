Simple Online Polling System
Introduction
The Simple Online Polling System is a web application for creating and participating in polls. It demonstrates modern Java backend development using OOP, layered architecture, and secure Google OAuth 2.0 authentication with a PostgreSQL database. A React frontend will consume its APIs.

Features
User Authentication: Secure login via Google OAuth 2.0.

Roles: Voter (vote on polls) and Admin (create/manage polls).

Poll Management (Admin): Create, view, edit, delete, and close polls. View results.

Voting (Voter): View active polls, cast a single vote per poll, and view results.

Technology Stack
Backend: Java 17+, Spring Boot, Spring Security, Spring Data JPA, Maven, Lombok.

Database: PostgreSQL.

Frontend: React (coming soon).

Authentication: Google OAuth 2.0, JWTs for API authentication.

Architecture
Uses a layered architecture (Presentation, Service, Data Access, Domain) for clear separation of concerns, applying OOP principles like encapsulation, abstraction, inheritance, and polymorphism.

Getting Started (Backend)
Clone: git clone https://github.com/your-username/simple-polling-system.git

Database: Set up PostgreSQL (polling_db), user, and configure src/main/resources/application.properties.

Google OAuth: Register a "Web application" Client ID in Google Cloud Console. Add client-id and client-secret to application.properties. Set Authorized redirect URIs to http://localhost:8080/login/oauth2/code/google.

Run: cd simple-polling-system/backend then mvn clean install and mvn spring-boot:run.

API Endpoints (Key Examples)
GET /oauth2/authorization/google: Initiate Google OAuth.

GET /api/users/me: Get current user profile.

POST /api/auth/logout: Log out.

POST /api/polls: Create poll (Admin).

GET /api/polls: Get active polls.

POST /api/polls/{pollId}/vote: Cast vote (Voter).

Future Enhancements
More OAuth providers.

Admin dashboard UI.

Advanced poll types.

Real-time results (WebSockets).
🗳️ Simple Online Polling System

🧩 Introduction

The Simple Online Polling System is a full-stack web application that enables users to create, manage, and participate in polls. It showcases modern Java backend development using OOP principles, layered architecture, and secure Google OAuth 2.0 authentication. The backend is built with Spring Boot and PostgreSQL, while a React frontend is planned for API consumption.

🚀 Features

👥 User Authentication
Secure login using Google OAuth 2.0

JWT-based session handling for API access

🧑‍💼 User Roles

Admin: Create, edit, delete, close polls; view poll results

Voter: Vote on active polls and view results

📊 Poll Management (Admin)

Create, edit, delete, and close polls

View real-time poll statistics (planned)

#🗳️ Voting (Voter)
View active polls

Vote once per poll

View poll results

🛠️ Technology Stack
Backend: Java 21, Spring Boot, Spring Security, Spring Data JPA, Maven, Lombok

Database: PostgreSQL

Authentication: Google OAuth 2.0, JWT

Frontend: React (coming soon)

🧱 Architecture
This project follows a Layered Architecture for better separation of concerns:

Presentation Layer – Controllers & API handling

Service Layer – Business logic

Data Access Layer – Repositories & persistence

Domain Layer – Core entities & models

Applies core OOP principles:

Encapsulation

Abstraction

Inheritance

Polymorphism


📡 Sample API Endpoints

Method	Endpoint	Description
GET	/oauth2/authorization/google	Initiate Google OAuth login
GET	/api/users/me	Get current user profile
POST	/api/auth/logout	Log out user
POST	/api/polls	Create a new poll (Admin)
GET	/api/polls	Get all active polls
POST	/api/polls/{pollId}/vote	Cast vote on a poll (Voter)


🔮 Future Enhancements

Support for more OAuth providers (GitHub, Facebook, etc.)

Admin dashboard UI

Multiple poll types (checkbox, ranking, etc.)

Real-time vote updates using WebSockets
