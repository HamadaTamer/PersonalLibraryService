# Personal Library Service

This Spring Boot application demonstrates the use of Spring profiles and configuration properties.

## Configuration

The application supports three profiles:

- **default**: Uses `application.yml`
- **dev**: Uses `application-dev.yml`
- **prod**: Uses `application-prod.yml`

## Running the Application

### Default Profile

```bash
./mvnw spring-boot:run
```

### Development Profile

```bash
SPRING_PROFILES_ACTIVE=dev ./mvnw spring-boot:run
```

### Production Profile

```bash
SPRING_PROFILES_ACTIVE=prod ./mvnw spring-boot:run
```

## Testing the Configuration

### API Endpoints

1. **Get Library Info**: `GET /api/library/info`

   - Returns library name and mode
   - Logs configuration details

2. **Get Configuration**: `GET /api/library/config`

   - Logs detailed configuration information
   - Returns confirmation message

3. **Test Greeting**: `GET /greet`

   - Returns greeting with library information

4. **Simple Greeting**: `GET /greet/simple`

   - Returns simple welcome message

### Running Tests

```bash
# Run all tests
./mvnw test

# Run specific profile tests
./mvnw test -Dtest=LibraryConfigDevTest
./mvnw test -Dtest=LibraryConfigProdTest
```

## Configuration Properties

The application uses `@ConfigurationProperties` to bind the following properties:

- `library.name`: The name of the library
- `library.mode`: The mode of operation

### Profile-Specific Values

| Profile | Library Name        | Mode        |
| ------- | ------------------- | ----------- |
| default | My Personal Library | default     |
| dev     | Development Library | development |
| prod    | Production Library  | production  |

## Logging Behavior

Each profile has different logging levels:

- **default**: Standard Spring Boot logging
- **dev**: DEBUG level for application and Spring Boot
- **prod**: INFO level for application, WARN for Spring Boot

## Quick Test Commands

### Test All Profiles

```bash
# 1. Test with unit tests
./mvnw test

# 2. Test default profile
./mvnw spring-boot:run
# In another terminal: curl http://localhost:8080/api/library/info

# 3. Test dev profile
SPRING_PROFILES_ACTIVE=dev ./mvnw spring-boot:run
# In another terminal: curl http://localhost:8080/api/library/info

# 4. Test prod profile
SPRING_PROFILES_ACTIVE=prod ./mvnw spring-boot:run
# In another terminal: curl http://localhost:8080/api/library/info
```

### Expected API Responses

- **Default Profile**: `Library: My Personal Library (Mode: default)`
- **Dev Profile**: `Library: Development Library (Mode: development)`
- **Prod Profile**: `Library: Production Library (Mode: production)`

## Files Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/sprints/PersonalLibraryService/
│   │       ├── config/
│   │       │   └── LibraryConfig.java          # Configuration properties binding
│   │       ├── controller/
│   │       │   └── LibraryController.java      # REST endpoints
│   │       ├── service/
│   │       │   └── LibraryService.java         # Business logic with logging
│   │       └── PersonalLibraryServiceApplication.java
│   └── resources/
│       ├── application.properties              # Basic configuration
│       ├── application.yml                     # Default profile configuration
│       ├── application-dev.yml                 # Development profile
│       └── application-prod.yml                # Production profile
└── test/
    └── java/
        └── com/sprints/PersonalLibraryService/
            └── LibraryConfigTest.java          # Profile testing
```

## Troubleshooting

### Profile Not Loading

If you see "No active profile set" in the logs, make sure to use the environment variable syntax:

```bash
# ✅ Correct way
SPRING_PROFILES_ACTIVE=prod ./mvnw spring-boot:run

# ❌ This might not work with Maven wrapper
./mvnw spring-boot:run -Dspring.profiles.active=prod
```

### Alternative Commands

If the environment variable doesn't work, try:

```bash
# Option 1: Maven system property
./mvnw spring-boot:run -Dspring-boot.run.profiles=prod

# Option 2: Maven properties
./mvnw -Dspring.profiles.active=prod spring-boot:run
```
