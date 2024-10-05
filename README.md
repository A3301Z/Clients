# Клиентский Микросервис для Тренажерного Зала

## Описание

Этот микросервис управляет информацией о клиентах и их целях для тренажерного зала.
Он предоставляет REST API для добавления, обновления, получения и удаления клиентов и их целей.

## Основные возможности

- **Управление клиентами:**
  - Добавление нового клиента
  - Получение списка клиентов
  - Получение полной информации о клиенте
  - Обновление информации о клиенте
  - Блокировка клиента
  - Удаление клиента
  - Загрузка и получение фото клиента

- **Управление целями клиентов:**
  - Добавление новой цели
  - Обновление информации о цели
  - Получение списка целей клиента
  - Получение полной информации о цели
  - Завершение и отмена завершения цели
  - Удаление цели

## Используемые технологии

- **Spring Boot:** для создания RESTful сервиса
- **Spring Data JDBC:** для работы с базой данных
- **PostgreSQL:** в качестве базы данных
- **Springdoc OpenAPI:** для автоматической генерации документации API
- **Logback:** для логирования
- **Spring Data Redis:** для кеширования
- **JUnit:** для тестирования

## API Эндпоинты

### Клиенты

#### Добавить клиента
- **URL:** `/client`
- **Метод:** `POST`
- **Описание:** Добавляет нового клиента в систему.
- **Тело запроса:** `ClientDTO`

#### Получить список клиентов
- **URL:** `/clients`
- **Метод:** `GET`
- **Описание:** Возвращает список всех клиентов.
- **Ответ:** Список `ClientMinimalDTO`

#### Получить полную информацию о клиенте
- **URL:** `/client/{id}`
- **Метод:** `GET`
- **Описание:** Возвращает полную информацию о клиенте по его ID.
- **Ответ:** `Client`

#### Обновить информацию о клиенте
- **URL:** `/client`
- **Метод:** `PUT`
- **Описание:** Обновляет информацию о клиенте.
- **Тело запроса:** `ClientDTO`

#### Заблокировать клиента
- **URL:** `/client/{id}/block`
- **Метод:** `DELETE`
- **Описание:** Блокирует клиента с указанием причины блокировки.
- **Параметры запроса:** `reasonOfBlock`

#### Удалить клиента
- **URL:** `/client/{id}/delete`
- **Метод:** `DELETE`
- **Описание:** Удаляет клиента из системы.

#### Добавить фото клиента
- **URL:** `/client/{id}/photo`
- **Метод:** `POST`
- **Описание:** Добавляет фото клиента.
- **Параметры запроса:** `MultipartFile file`

#### Получить фото клиента
- **URL:** `/client/{id}/photo`
- **Метод:** `GET`
- **Описание:** Возвращает фото клиента.
- **Ответ:** `byte[]`

### Цели

#### Добавить цель клиента
- **URL:** `/client/{clientId}/newGoal`
- **Метод:** `POST`
- **Описание:** Добавляет новую цель для клиента.
- **Тело запроса:** `GoalMinimalDTO`

#### Обновить цель
- **URL:** `/client/goal`
- **Метод:** `PUT`
- **Описание:** Обновляет информацию о цели.
- **Тело запроса:** `GoalMinimalDTO`

#### Получить список целей клиента
- **URL:** `/client/{clientId}/goals`
- **Метод:** `GET`
- **Описание:** Возвращает список всех целей клиента.
- **Ответ:** Список `GoalDTO`

#### Получить полную информацию о цели
- **URL:** `/client/goal/{goalId}/fullInfo`
- **Метод:** `GET`
- **Описание:** Возвращает полную информацию о цели по её ID.
- **Ответ:** `Goal`

#### Завершить цель
- **URL:** `/client/goal/{goalId}/complete`
- **Метод:** `PUT`
- **Описание:** Помечает цель как завершенную.

#### Сделать цель незавершенной
- **URL:** `/client/goal/{goalId}/doNotComplete`
- **Метод:** `PUT`
- **Описание:** Снимает отметку о завершении цели.

#### Удалить цель
- **URL:** `/client/goal/{goalId}/delete`
- **Метод:** `DELETE`
- **Описание:** Удаляет цель из системы.
