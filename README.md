# PioneerPicsel — Тестовое задание

## 📋 Описание

Spring Boot приложение с поддержкой:
- JWT авторизации
- управления пользователями и их контактными данными (email, телефон)
- аккаунтов с балансом и автоначислением процентов
- перевода средств между пользователями
- кэширования (Redis)
- OpenAPI документации (Swagger)
- тестов и SQL-инициализации

---

## 🚀 Запуск

### 1. Клонируй репозиторий

```bash
git clone https://github.com/ialakey/pioneerpicsel.git
cd pioneerpicsel
```

### 2. Запуск PostgreSQL и Redis (Docker)

```bash
docker-compose up -d
```

### 3. Запуск Spring Boot

```bash
./mvnw spring-boot:run
```

---

## 🔐 Авторизация

**POST** `/api/auth/login`  
Тело запроса:

```json
{
  "emailOrPhone": "alice@example.com",
  "password": "123456"
}
```

Ответ:

```json
{
  "token": "JWT_TOKEN"
}
```

Добавляй его в заголовок:
```
Authorization: Bearer JWT_TOKEN
```

---

## 📮 Swagger

- URL: http://localhost:8080/swagger-ui.html

---

## 📊 Postman

Импортируй файл: `PioneerPicsel.postman_collection.json`

---

## 🧪 Тестовые данные (в `data.sql`)

```sql
INSERT INTO user_info (id, name, date_of_birth, password) VALUES
(1, 'Alice', '1990-01-01', '$2a$10$...'), 
(2, 'Bob', '1985-05-05', '$2a$10$...');

INSERT INTO email_data (email, user_info_id) VALUES ('alice@example.com', 1);
INSERT INTO phone_data (phone, user_info_id) VALUES ('+1234567890', 1);

INSERT INTO account (user_info_id, balance, initial_balance) VALUES (1, 1000.00, 1000.00);
```

Пароль: `123456`

---

## 🛠 Возможности

- 📈 Автоматическое начисление процентов раз в 30 сек (до 207%)
- 🔁 Перевод денег между пользователями (с защитой от гонок)
- 📬 Удаление и добавление email / телефона
- ❌ Валидация: хотя бы один email и телефон обязателен

---

## 📦 Автор

[I_Alakey](https://github.com/ialakey)