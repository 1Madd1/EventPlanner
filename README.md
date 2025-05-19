# EventPlanner

A Java Spring Boot application for creating, managing, and analyzing events. EventPlanner makes it easier to track attendance, manage guests, and gain insights from your events.

[![View on GitHub](https://img.shields.io/badge/GitHub-View%20Project-blue?logo=github)](https://github.com/1Madd1/EventPlanner)

---

## 🌟 Features

- 📅 Create and manage events with themes
- 👥 Track guest attendance and RSVP statuses
- 📊 Analyze attendance patterns and guest reliability
- 📘 Interactive API documentation via Swagger
- 📈 Ready for advanced analytics (e.g., no-shows, theme loyalty)

---

## 🛠️ Tech Stack

- **Backend:** Spring Boot (Java)
- **Database:** (Pluggable - configure your own, e.g., PostgreSQL or H2)
- **Build Tool:** Maven
- **API Docs:** Springdoc OpenAPI / Swagger UI

---

## 🚀 Getting Started

### ✅ Prerequisites

- Java 17 or higher
- Maven 3.x
- (Optional) PostgreSQL or another RDBMS

### 📦 Installation

```bash
# Clone the repository
git clone https://github.com/1Madd1/EventPlanner.git
cd EventPlanner

# Build the project
mvn clean install

# Run the application
mvn spring-boot:run
```

## 🔌 Configuration
Create an "application.yml" file inside your resources folder with this configuration:
```bash
spring:
  datasource:
    username: postgres
    password: 12345678
    url: jdbc:postgresql://localhost:5433/postgres
  jpa:
    show-sql: false
    database-platform: org.hibernate.dialect.PostgreSQLDialect
    hibernate:
      ddl-auto: validate
    properties:
      hibernate:
        globally_quoted_identifiers: true
  properties:
    hibernate:
      default-schema: "public"
  hibernate:
    generate-ddl: true
  flyway:
    enabled: true
```
### 📘 API Documentation
This project uses Springdoc OpenAPI to provide interactive API documentation.

Once the application is running, access Swagger UI here:

- 🔗 http://localhost:8080/swagger-ui.html

- 🔗 http://localhost:8080/swagger-ui/index.html

You can explore all available endpoints, request formats, and response structures directly in the browser.

### 🧪 Testing & Development
Use the included Swagger UI or tools like Postman to test endpoints. You can add unit and integration tests with JUnit.

### 📂 Project Structure
```bash
EventPlanner/
├── .mvn/                 # Maven Wrapper files
├── src/
│   ├── main/
│   │   ├── java/         # Java source code
│   │   └── resources/    # Configuration files
├── pom.xml               # Maven configuration
├── mvnw, mvnw.cmd        # Maven wrapper scripts
└── README.md             # This file
```
### 🤝 Contributing
Contributions, issues, and feature requests are welcome!
Feel free to fork the repository and submit a pull request.

### 📄 License
This project is licensed under the MIT License.