# ms_order

The Order Service is a core component of a microservice architecture responsible for handling 
product orders. It manages the creation, tracking, and updating of customer orders. Once an order 
is placed, this service communicates with the [Delivery Service](https://github.com/SvetlanaVys/ms_delivery) to coordinate the shipment of products.

### Technologies
* **Java 20** and **Spring Boot 3** for building RESTful APIs.
* **PostgreSQL** Database for data storage (RDB).
* **Kafka** ensuring reliable and asynchronous communication.
* **OpenAPI** specification for well-defined and documented API interactions.
* **Docker** for containerization.
* **Maven** for project management and dependency resolution.

### Microservice Communication
This service sends order details to the [Delivery Service](https://github.com/SvetlanaVys/ms_delivery) via messaging protocol using Kafka after an order is placed.
The service requests delivery information from the Delivery Service through HTTP, utilizing the OpenAPI.

### How to Run the Application

1. **Maven**:
   ```bash
   mvn clean install

2. **Build the Docker Image**:
   Run the following command to build the Docker image:
   ```bash
   docker build -t ms-order-api .

2. **Start the Application**:
   Run the following command to bring up the application along with all its services:
   ```bash
    docker-compose up --build

3. **Access Swagger**:
   Once the application is up, you can access the Swagger UI at: <br />
   http://localhost:8080/ms-order/swagger-ui/index.html