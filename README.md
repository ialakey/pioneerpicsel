# PioneerPicsel — [Test Task](https://docs.google.com/document/d/1Cy0b6v4hlctHrB8u54GomTK3t1cNErQK/edit?usp=sharing&ouid=111687643317443347066&rtpof=true&sd=true)

## 📋 Description

Spring Boot application with support for:
- JWT authentication
- user management and their contact data (email, phone)
- accounts with balance and automatic interest accrual
- transferring funds between users
- caching (Redis)
- OpenAPI documentation (Swagger)
- tests and SQL initialization

---

## 🚀 Run

### 1. Clone the repository

```bash
git clone https://github.com/ialakey/pioneerpicsel.git
cd pioneerpicsel
```

### 2. Run via Docker

```bash
docker-compose up -d
```

### 3. Run Spring Boot

```bash
./mvnw spring-boot:run
```

---

## 📮 Swagger

- URL: http://localhost:8080/swagger-ui.html

---

## 📊 Postman

Import file: [PioneerPicsel.postman_collection.json](https://github.com/ialakey/pioneerpicsel/blob/main/PioneerPicsel.postman_collection.json)

---

## 🛠 Features

- 📈 Automatic interest accrual every 30 seconds (up to 207%)
- 🔁 Money transfers between users (race condition protection)
- 📬 Add and remove email / phone
- ❌ Validation: at least one email and one phone are required
