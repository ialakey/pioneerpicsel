# PioneerPicsel — [Тестовое задание](https://docs.google.com/document/d/1Cy0b6v4hlctHrB8u54GomTK3t1cNErQK/edit?usp=sharing&ouid=111687643317443347066&rtpof=true&sd=true)

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

### 2. Запуск через Docker

```bash
docker-compose up -d
```

### 3. Запуск Spring Boot

```bash
./mvnw spring-boot:run
```

---

## 📮 Swagger

- URL: http://localhost:8080/swagger-ui.html

---

## 📊 Postman

Импортируй файл: [PioneerPicsel.postman_collection.json](https://github.com/ialakey/pioneerpicsel/blob/main/PioneerPicsel.postman_collection.json)

---

## 🛠 Возможности

- 📈 Автоматическое начисление процентов раз в 30 сек (до 207%)
- 🔁 Перевод денег между пользователями (с защитой от гонок)
- 📬 Удаление и добавление email / телефона
- ❌ Валидация: хотя бы один email и телефон обязателен

---
