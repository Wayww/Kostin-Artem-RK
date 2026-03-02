# Kostin Artem: Base CRUD operation

Проект под задание: `Base CRUD operation. One to many one to one`.
- 3 сущности: `Student`, `University`, `Subject`
- 2 связи:
  - `OneToMany`: `University -> Student`
  - `OneToOne`: `Student -> Subject`
- стек: `Spring Web`, `Spring Data JPA`, `Validation`, `Lombok`, `PostgreSQL Driver`

## Валидация (2 типа)
- `@NotBlank` для строк (`name`, `title`)
- `@NotNull` для обязательных ID (`universityId`, `subjectId`)

## Настройка PostgreSQL
Создайте БД `rk_db` и при необходимости задайте переменные окружения:
- `DB_URL` (по умолчанию `jdbc:postgresql://localhost:5432/rk_db`)
- `DB_USERNAME` (по умолчанию `postgres`)
- `DB_PASSWORD` (по умолчанию `postgres`)

## Запуск
```bash
mvn spring-boot:run
```

## CRUD endpoints
- `/universities`
- `/subjects`
- `/students`

Поддерживаются методы: `GET`, `GET /{id}`, `POST`, `PUT /{id}`, `DELETE /{id}`.

## Порядок создания данных
1. `POST /universities`
2. `POST /subjects`
3. `POST /students` (передать `universityId` и `subjectId`)

## Postman
Готовая коллекция: `postman/rk-crud.postman_collection.json`

Импортируйте её в Postman и запустите запросы по порядку.
# Kostin-Artem-RK
