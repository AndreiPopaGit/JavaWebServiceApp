# Food Service Application

### Overview
The **Food Service Application** is a Spring Boot-based RESTful API designed to manage food items, calculate nutritional values, and log daily food consumption. This application serves as a backend service for a calorie tracking and nutrition management system.

---

## Features
- **CRUD Operations for Food Items**:
  - Add, retrieve, update, and delete food items.
- **Macro Calculations**:
  - Calculate total calories, protein, carbs, and fats for a list of food items.
- **Daily Log Management**:
  - Log food consumption with specific quantities and view daily logs by date.
- **Pagination and Search**:
  - Retrieve food items with pagination and search by name.

---

## Technologies Used
- **Backend**:
  - Java 17
  - Spring Boot
  - JPA/Hibernate
- **Database**:
  - H2 (for testing)
  - Configurable for MySQL, PostgreSQL, etc.
- **Testing**:
  - JUnit 5
  - Mockito
  - MockMvc for integration tests

---

## API Endpoints

### 1. **Food Management**
| HTTP Method | Endpoint         | Description                       |
|-------------|------------------|-----------------------------------|
| `GET`       | `/api/foods`     | Get all food items (paginated).   |
| `GET`       | `/api/foods/{id}`| Get food by ID.                   |
| `POST`      | `/api/foods`     | Add a new food item.              |
| `DELETE`    | `/api/foods/{id}`| Delete food by ID.                |

### 2. **Macro Calculations**
| HTTP Method | Endpoint                | Description                       |
|-------------|-------------------------|-----------------------------------|
| `POST`      | `/api/foods/totalMacro` | Calculate macros for food IDs.    |

### 3. **Daily Log**
| HTTP Method | Endpoint               | Description                                |
|-------------|------------------------|--------------------------------------------|
| `POST`      | `/api/foods/dailyLog`  | Log food with quantity for the current day.|
| `GET`       | `/api/foods/dailyLog`  | Retrieve daily logs for a specific date.   |
