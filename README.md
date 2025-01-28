# ms_order

The Order Service is a core component of a microservice architecture responsible for handling 
product orders. It manages the creation, tracking, and updating of customer orders. Once an order 
is placed, this service communicates with the [Delivery Service](https://github.com/SvetlanaVys/ms_delivery) to coordinate the shipment of products.

### Technologies
* **Java 22** and **Spring Boot 3** for building RESTful APIs.
* **PostgreSQL** Database for data storage (RDB).
* **Kafka** ensuring reliable and asynchronous communication.
* **OpenAPI** specification for well-defined and documented API interactions.
* **Docker** for containerization.
* **Maven** for project management and dependency resolution.

### Microservice Communication
This service sends order details to the [Delivery Service](https://github.com/SvetlanaVys/ms_delivery) via messaging protocol using Kafka after an order is placed.
The service requests delivery information from the Delivery Service through HTTP, utilizing the OpenAPI.

## How to Run the Application

### Running the Application Locally

To run the application in IntelliJ IDEA with the local profile:

1. **Open the project** in IntelliJ IDEA.
2. **Maven**:
   ```bash
   mvn clean install
3. **Select the Run Configuration:**
   - Go to Run > Edit Configurations.
   - Under `VM Options`, add the following:
     ```bash
     -Dspring.profiles.active=local

4. **Start the application**

### Running the Application with Docker

1. **Maven**:
   ```bash
   mvn clean install

2. **Create shared network** if you haven't already created it in delivery service
   ```bash
   docker network create marketplace-network

3. **Build the Docker Image**:
   Run the following command to build the Docker image:
   ```bash
   docker build -t ms-order-api .

4. **Start the Application**:
   Run the following command to bring up the application along with all its services:
   ```bash
    docker-compose up --build

#### Access Swagger:
   Once the application is up, you can access the Swagger UI at: <br />
   http://localhost:8080/ms-order/swagger-ui/index.html