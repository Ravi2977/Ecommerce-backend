# Ravi Computer Backend

Welcome to the **Ravi Computer** backend repository! This project powers the backend for the Ravi Computer e-commerce website, which specializes in computer sales and services, and also provides printer and CCTV camera services.

## Table of Contents
- [Technologies Used](#technologies-used)
- [Features](#features)
- [Installation](#installation)
- [Configuration](#configuration)
- [Usage](#usage)
- [Project Structure](#project-structure)
- [Contributing](#contributing)
- [License](#license)
- [Contact](#contact)

## Technologies Used
- **Spring Boot**: Framework for building Java-based applications.
- **MySQL**: Relational database management system.
- **Dependencies**:
  - `spring-boot-starter-web`: To build web applications, including RESTful applications.
  - `spring-boot-starter-data-jpa`: Spring Data JPA with Hibernate.
  - `spring-boot-starter-security`: Provides security features.
  - `spring-boot-starter-mail`: To send emails.
  - `jjwt`: For JWT token handling.
  - `spring-boot-starter-validation`: For validating input data.
  - `lombok`: To reduce boilerplate code.
  - `spring-boot-starter-thymeleaf`: For email templates.
  - `BCryptPasswordEncoder`: For encoding passwords.

## Features
- User authentication and authorization with JWT.
- Product management.
- Order management.
- User account management.
- Email notifications.
- Secure password handling.

## Installation

1. **Clone the repository**:
    ```bash
    git clone https://github.com/your-username/Ecommerce-backend.git
    ```

2. **Navigate to the project directory**:
    ```bash
    cd Ravi-Computer-backend
    ```

3. **Set up MySQL database**:
    - Create a new MySQL database named `ravi_computer`.
    - Update the database configurations in `application.properties`:
      ```properties
      spring.datasource.url=jdbc:mysql://localhost:3306/ravi_computer
      spring.datasource.username=your-username
      spring.datasource.password=your-password
      ```

4. **Install dependencies and run the application**:
    ```bash
    ./mvnw spring-boot:run
    ```

## Configuration
Update the `application.properties` file with your specific settings:
```properties
# MySQL settings
spring.datasource.url=jdbc:mysql://localhost:3306/ravi_computer
spring.datasource.username=your-username
spring.datasource.password=your-password

# JPA settings
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# Security settings
jwt.secret=your-jwt-secret
jwt.expiration-ms=3600000

# Email settings
spring.mail.host=smtp.example.com
spring.mail.port=587
spring.mail.username=your-email@example.com
spring.mail.password=your-email-password
To start using the application, run:

bash
Copy code
./mvnw spring-boot:run
The application will be available at http://localhost:8080.

Project Structure
Controllers: Handle HTTP requests and responses.
Services: Contain the business logic.
Repositories: Interact with the database.
Models: Define the data structure.
Example structure:

plaintext
Copy code
src/main/java/com/ravi/computer/
├── controller/
├── service/
├── repository/
├── model/
├── config/
└── utils/
Contributing
Contributions are welcome! Please read the CONTRIBUTING.md for guidelines.
Deployment:
Deployed on render.com for free services using Docker

License
This project is licensed under the MIT License. See the LICENSE file for details.

Contact
For any inquiries or feedback, please contact:

Name: Ravi Maurya
Email: mauryaravi599@gmail.com
LinkedIn: https://www.linkedin.com/in/ravi-maurya-1a37331b6/
Thank you for visiting our repository! We hope you find our project useful and appreciate any contributions.
