# Log Routing Service

This is a simple log routing service that accepts log data in JSON format over HTTP and routes it to a SQL database table. The service provides an HTTP endpoint `/log` where clients can POST log data in the following JSON format:

```json
{
  "id": 1234,
  "unix_ts": 1684129671,
  "user_id": 123456,
  "event_name": "login"
}
```

The log represents application or user behavior logs. For example, a log can be generated when a user logs in or logs out of an application.

The main components of the Log Routing Service are:

- **Log Routing Server**: A HTTP server implemented in your language of choice. It exposes the `POST /log` endpoint to receive log data from clients. Upon receiving a log, the server writes it to a database table.
- **Database**: A SQL database, such as PostgreSQL, is used to store the log data. The log data received by the server is eventually written to the database table.
- **Testing Script**: A script (e.g., written in Bash or any language of your choice) is provided to fire 100,000 requests to the log server until stopped. This script helps validate the server's performance under high load.
- **Dockerization**: The Log Routing Service, database, and testing script are containerized using Docker for easy deployment and reproducibility.

## Prerequisites

Before running the Log Routing Service, make sure you have the following software and dependencies installed on your machine:

- Java Development Kit (JDK): Install the latest version of JDK. You can download it from the official Oracle website: Java SE Downloads

- Maven: Install Maven to build and manage the project dependencies. You can download it from the official Apache Maven website: Apache Maven

- Git: Install Git for version control. You can download it from the official Git website: Git Downloads

- MySQL ecosystem: Make sure you have MySQL Server and MySQL Workbench installed on your machine. You can download them from the official MySQL website: MySQL Downloads


## Getting Started

To run the Log Routing Service and database, follow these steps:

1. Clone this repository.
2. Make sure you have Docker installed on your machine.
3. Open a terminal and navigate to the project's root directory.
4. Run the following command to start the Log Routing Service and database:

   ```bash
   docker-compose up
   ```

   This command will bring up the Log Routing Service server and the associated database.

5. To run the testing script, open a new terminal and navigate to the project's root directory.
6. Run the following command to execute the testing script:

   ```bash
   docker-compose run test
   ```

   This command will start the testing script and fire 100,000 requests to the Log Routing Service. The script will run until stopped.

7. Monitor the Log Routing Service's performance and observe the logs being written to the database.

## Customization and Configuration

The Log Routing Service and database can be customized and configured as per your requirements. Here are some areas you can explore:

- **Database Configuration**: If you prefer a different SQL database, you can modify the `docker-compose.yml` file to use the desired database image and configure its environment variables accordingly.
- **Server Implementation**: The Log Routing Service server is implemented in your language of choice. You can modify and extend the server code to add additional functionality or adapt it to your specific use case.
- **Scaling**: If you need to scale the Log Routing Service to handle higher loads, you can explore container orchestration solutions like Kubernetes or use load balancers to distribute the incoming requests across multiple instances of the server.

## Contributing

Contributions to the Log Routing Service project are welcome! If you have any suggestions, improvements, or bug fixes, please open an issue or submit a pull request on the repository.

## License

This project is unlicensed.

---

We hope you find the Log Routing Service useful for your log management needs. If you have any questions or need further assistance, please don't hesitate to contact us at *akash@sharefable.com* with the subject line *Assignment Backend A1 - Log Routing Service*.

Thank you for your interest and happy logging!

**
