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
