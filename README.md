📝 README.md

Sewing Industry Management System 👗

A Spring Boot backend system for tracking and managing the workflow in a garment production environment.

🔐 Features

Secure login/registration (JWT)

Email verification with token

OAuth2 login (Google, Facebook, GitHub)

Two-Factor Authentication (2FA)

Role-based access control

Track production stages: Raw Material → Cutting → Sewing → Ironing → Packaging

Record and manage product defects

🧰 Tech Stack

Java 17

Spring Boot

Spring Security, JWT, OAuth2

PostgreSQL / H2

Maven

Swagger / OpenAPI

📁 Project Structure

src/
├── main/
│   ├── java/kg/alatoo/sewing_industry_management/
│   │   ├── auth/ (login, JWT, 2FA)
│   │   ├── controller/ (REST endpoints)
│   │   ├── service/ (business logic)
│   │   ├── model/ (domain entities)
│   │   ├── dto/ (data transfer objects)
│   │   └── config/ (security, Swagger)
│   └── resources/
├── test/ (unit tests)

[▶️ Demo Video](https://youtu.be/WIdt_LPVYLo)

👉 Watch the Final Presentation

🚀 Running the Project

Clone the repo

Configure DB in application.properties

Run with mvn spring-boot:run

Access Swagger UI at: http://localhost:8080/swagger-ui.html

🤝 Contact

Aleksandra Arykova — arykova05@bk.ru

