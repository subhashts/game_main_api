# Game App SDP (Spring Boot + MongoDB Atlas)

A Spring Boot REST API backed by MongoDB for managing games, members, collections (daily cash collections), admin users, and transactions. Controllers use DTOs for requests/responses and map via a shared `DtoMapper`.

## Tech Stack
- Java 21
- Spring Boot
- MongoDB Atlas
- Maven

## Getting Started

1) Configure MongoDB
- Edit `src/main/resources/application.properties`:
```
spring.data.mongodb.uri=mongodb+srv://<user>:<pass>@<cluster>/<params>
spring.data.mongodb.database=game_app_sdp
```

2) Build
```
./mvnw clean package
```

3) Run
```
./mvnw spring-boot:run
```
The API runs at `http://localhost:8080`.

## Docker (optional)
```
docker build -t game-app-sdp .
docker run --rm -p 8080:8080 -e SPRING_DATA_MONGODB_URI="<your_mongo_uri>" -e SPRING_DATA_MONGODB_DATABASE=game_app_sdp game-app-sdp
```

## API Overview (DTO-based)
All endpoints accept and return DTOs. IDs are Mongo ObjectId strings.

### Games `/games`
- POST `/games`
- GET `/games`
- GET `/games/{id}`
- PUT `/games/{id}`
- DELETE `/games/{id}`

GameDto
```
{
  "id": "",
  "name": "FIFA 25",
  "description": "Football",
  "price": 59.99
}
```

### Members `/members`
- POST `/members`
- GET `/members`
- GET `/members/{id}`
- PUT `/members/{id}`
- DELETE `/members/{id}`

MemberDto
```
{
  "id": "",
  "name": "Alex",
  "balance": 0.0,
  "phone": "+1-555-0100"
}
```

### Collections `/collections`
`date` defaults to now if missing.
- POST `/collections`
- GET `/collections`
- GET `/collections/{id}`
- PUT `/collections/{id}`
- DELETE `/collections/{id}`

CollectionEntryDto
```
{
  "id": "",
  "date": "2025-09-18T10:00:00.000+00:00",
  "amount": 250.0
}
```

### Admin Users `/admin-users`
`username` is unique.
- POST `/admin-users`
- GET `/admin-users`
- GET `/admin-users/{id}`
- PUT `/admin-users/{id}`
- DELETE `/admin-users/{id}`

AdminUserDto
```
{
  "id": "",
  "username": "admin",
  "password": "secret"
}
```

### Transactions `/transactions`
References `members` and `games`. `dateTime` defaults to now if missing.
- POST `/transactions`
- GET `/transactions`
- GET `/transactions/{id}`
- GET `/transactions/member/{memberId}`
- GET `/transactions/game/{gameId}`
- PUT `/transactions/{id}`
- DELETE `/transactions/{id}`

TransactionDto
```
{
  "id": "",
  "memberId": "<memberObjectId>",
  "gameId": "<gameObjectId>",
  "amount": 99.0,
  "dateTime": "2025-09-18T10:30:00.000+00:00"
}
```

## Error Handling
Global handler returns:
- 400 Bad Request: `BusinessException`
- 404 Not Found: `IdNotPresentException`, `CollectionNotFoundException`, `AdminUserNotFoundException`, `TransactionNotFoundException`

## Notes
- DTOs: `com.subash.game_app_sdp.dto`
- Mapper: `DtoMapper` converts entity <-> DTO
- Unique index on `admin_users.username` via `@Indexed(unique = true)`
