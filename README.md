# Company employee web service

This Spring Boot application stores employee records (`id`, `name`, and `salary`) in a database and provides a small browser interface for testing the service.

## Run

Run `.\mvnw.cmd spring-boot:run` from the project directory, then open [http://localhost:8080](http://localhost:8080). The default H2 database is stored in `data/company_webservice`; no separate database setup is required.

To use another JDBC database, set `DATABASE_URL`, `DATABASE_USERNAME`, and `DATABASE_PASSWORD` before starting the application. The matching JDBC driver must be available.

## Web service

| Method | Endpoint | Purpose |
| --- | --- | --- |
| GET | `/api/employees` | Retrieve all employees |
| POST | `/api/employees` | Add an employee |
| PUT | `/api/employees/{id}` | Update an employee |

Send JSON such as `{"name":"An Nguyen","salary":2500.50}` when adding or updating an employee. The page at `/` calls these endpoints.

Run the integration tests with `.\mvnw.cmd test`.
