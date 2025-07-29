# HPE Employee API

[![CI](https://github.com/richfish85/hpe-employee-api/actions/workflows/ci.yml/badge.svg)](https://github.com/richfish85/hpe-employee-api/actions)

A RESTful Employee Management service built with **Java Spring Boot**, featuring:

- Full CRUD API (GET, POST, PUT, DELETE)
- Swagger UI documentation
- In-memory service with unit tests (JUnit 5)
- Dockerized build with multi-stage optimization
- GitHub Actions CI pipeline

## 🔧 Dev Commands

| Command             | Description                       |
|---------------------|-----------------------------------|
| `make run`          | Run Spring Boot locally           |
| `make test`         | Run unit tests                    |
| `make package`      | Build the app JAR                 |
| `make docker-build` | Build the Docker image            |
| `make docker-run`   | Run the container on port 8080    |
| `make swag`         | Print the Swagger UI URL          |

---

## 📦 Endpoints

```http
GET    /employees
POST   /employees
PUT    /employees/{id}
DELETE /employees/{id}
```

🧪 Tests
See EmployeeControllerTest.java under src/test.

📘 Swagger
📍 http://localhost:8080/swagger-ui/index.html
(when app is running)

🚀 Deployment Ready
You can deploy this to:
- DockerHub or GitHub Container Registry (GHCR)
- Railway / Render / Fly.io with Dockerfile
- Extend with Supabase or Postgres for persistence
