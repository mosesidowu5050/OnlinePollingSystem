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
