# Visitor Management System (VMS) Service

Welcome to the Visitor Management System (VMS) Service repository. This project is designed to manage visitor information for commercial office spaces, hotel lounges, or similar environments. It provides a RESTful API for performing CRUD (Create, Read, Update, Delete) operations on visitor data.

## Table of Contents

- [Features](#features)
- [Architecture](#architecture)
- [Technologies Used](#technologies-used)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
  - [Configuration](#configuration)
  - [Running the Application](#running-the-application)
- [API Endpoints](#api-endpoints)
- [Deployment](#deployment)
- [Contributing](#contributing)
- [License](#license)

## Features

- Manage visitor information with full CRUD functionality.
- Store visitor details such as name, contact information, company, purpose of visit, and more.
- Integration with MySQL database for persistent storage.
- RESTful API design for easy integration with frontend applications.

## Architecture

The application follows a layered architecture:

- **Controller Layer**: Handles HTTP requests and responses.
- **Service Layer**: Contains business logic.
- **Repository Layer**: Manages data persistence using Spring Data JPA.

This separation of concerns ensures modularity and ease of maintenance.

## Technologies Used

- **Backend**: Java Spring Boot
- **Database**: MySQL
- **Build Tool**: Maven
- **Deployment Platforms**:
  - Backend: AWS Elastic Beanstalk
  - Frontend: Netlify

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 11 or higher
- Maven 3.6.0 or higher
- MySQL Server
- Git

### Installation

1. **Clone the Repository**:

   ```bash
   git clone https://github.com/diipanshuu/vms-service.git
   cd vms-service
   ```

2. **Set Up the Database**:

   - Create a MySQL database named `vms_db`.
   - Update the database configuration in `src/main/resources/application.properties`:

     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/vms_db
     spring.datasource.username=your_username
     spring.datasource.password=your_password
     ```

3. **Build the Application**:

   ```bash
   mvn clean install
   ```

### Configuration

- **Application Properties**: Located in `src/main/resources/application.properties`. Configure database connection, server port, and other settings as needed.

### Running the Application

1. **Start the Application**:

   ```bash
   mvn spring-boot:run
   ```

2. **Access the Application**:

   - The API will be accessible at `http://localhost:8080`.

## API Endpoints

The application exposes the following RESTful endpoints:

- **GET /visitors**: Retrieve all visitors.
- **GET /visitors/{id}**: Retrieve a specific visitor by ID.
- **POST /visitors**: Create a new visitor.
- **PUT /visitors/{id}**: Update an existing visitor by ID.
- **DELETE /visitors/{id}**: Delete a visitor by ID.

For detailed API documentation and request/response formats, please refer to the [API Documentation](API_DOCUMENTATION.md).

## Deployment

The backend is deployed on AWS Elastic Beanstalk, and the frontend is hosted on Netlify. For deployment instructions, refer to the [Deployment Guide](DEPLOYMENT_GUIDE.md).

## Contributing

Contributions are welcome! Please read the [Contributing Guidelines](CONTRIBUTING.md) for more information.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

---

Feel free to explore, use, and contribute to the VMS Service project. If you encounter any issues or have suggestions, please open an issue or submit a pull request.
