# Personal Library Service

This Spring Boot application demonstrates the use of Spring profiles, configuration properties, dependency injection patterns, and component scanning.

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

#### Health Check
1. **Health Check**: `GET /health`
   - Returns "OK" status

#### Library Information
2. **Get Library Info**: `GET /api/library/info`
   - Returns library name and mode
   - Logs configuration details

3. **Get Configuration**: `GET /api/library/config`
   - Logs detailed configuration information
   - Returns confirmation message

#### Greeting Services (Dependency Injection Demo)
4. **Simple Greeting**: `GET /greet`
   - Returns greeting with library information

5. **Basic Greeting**: `GET /greet/simple`
   - Returns simple welcome message

6. **Constructor Injection Demo**: `GET /greet/evening`
   - Uses constructor-injected EveningGreetingService

7. **Field Injection Demo**: `GET /greet/morning`
   - Uses field-injected MorningGreetingService

8. **Setter Injection Demo**: `GET /greet/default`
   - Uses setter-injected MorningGreetingService

#### Notification Services (Advanced DI Demo)
9. **Email Notification**: `GET /notify/email?message=Hello`
   - Uses @Primary EmailNotificationService
   - Default message: "Hello from email service"

10. **SMS Notification**: `GET /notify/sms?message=Hello`
    - Uses @Qualifier("sms") SmsNotificationService
    - Default message: "Hello from SMS service"

#### Book CRUD Operations
11. **Create Book**: `POST /books`
    - Body: JSON with title, genre, author, year, price

12. **Get All Books**: `GET /books`
    - Returns list of all books

13. **Get Book by ID**: `GET /books/{id}`
    - Returns specific book

14. **Update Book**: `PUT /books/{id}`
    - Body: JSON with updated book data

15. **Delete Book**: `DELETE /books/{id}`
    - Deletes book by ID

### Running Tests

```bash
# Run all tests
./mvnw test

# Run specific profile tests
./mvnw test -Dtest=LibraryConfigDevTest
./mvnw test -Dtest=LibraryConfigProdTest

# Run component scan tests
./mvnw test -Dtest=ComponentScanTest
```

## Configuration Properties

The application uses `@ConfigurationProperties` to bind the following properties:

- `library.name`: The name of the library
- `library.mode`: The mode of operation
- `app.email`: Email address for notifications
- `app.phone`: Phone number for SMS notifications

### Profile-Specific Values

| Profile | Library Name        | Mode        | Email                    | Phone        |
| ------- | ------------------- | ----------- | ------------------------ | ------------ |
| default | My Personal Library | default     | library@example.com      | +1-555-0123 |
| dev     | Development Library | development | dev-library@example.com  | +1-555-0124 |
| prod    | Production Library  | production  | prod-library@example.com | +1-555-0125 |

## Dependency Injection Patterns

The application demonstrates three types of dependency injection:

### 1. Constructor Injection
- Used in `GreetingController` for `EveningGreetingService`
- Used in `NotificationController` for both notification services

### 2. Setter Injection
- Used in `GreetingController` for `MorningGreetingService` (default)

### 3. Field Injection
- Used in `GreetingController` for `MorningGreetingService`

## Advanced Dependency Injection

### @Primary and @Qualifier
- `EmailNotificationService` is marked with `@Primary`
- `SmsNotificationService` is marked with `@Qualifier("sms")`
- Both are properly injected in `NotificationController`

## Component Scanning

The application uses `@ComponentScan` with:
- **Include Filters**: Only loads beans matching pattern `.*Service|.*Controller|.*Config`
- **Exclude Filters**: Excludes beans matching pattern `.*Excluded.*`

### Testing Component Scanning
- `ComponentScanTest` verifies that excluded beans are not loaded
- Tests that included services, controllers, and configs are properly loaded

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

### Test Dependency Injection

```bash
# Test constructor injection
curl http://localhost:8080/greet/evening

# Test field injection
curl http://localhost:8080/greet/morning

# Test setter injection
curl http://localhost:8080/greet/default
```

### Test Notification Services

```bash
# Test primary service (email)
curl http://localhost:8080/notify/email

# Test qualified service (SMS)
curl http://localhost:8080/notify/sms

# Test with custom messages
curl "http://localhost:8080/notify/email?message=Custom%20message"
curl "http://localhost:8080/notify/sms?message=Custom%20SMS"
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
│   │       │   ├── GreetingController.java     # DI demonstration (constructor, setter, field)
│   │       │   ├── HealthController.java       # Health endpoint
│   │       │   ├── LibraryController.java      # Library REST endpoints
│   │       │   └── NotificationController.java # Advanced DI with @Primary/@Qualifier
│   │       ├── service/
│   │       │   ├── EveningGreetingService.java # Evening greeting implementation
│   │       │   ├── ExcludedService.java        # Service excluded by component scan
│   │       │   ├── EmailNotificationService.java # @Primary notification service
│   │       │   ├── GreetingService.java        # Greeting service interface
│   │       │   ├── LibraryService.java         # Business logic with logging
│   │       │   ├── MorningGreetingService.java # Morning greeting implementation
│   │       │   ├── NotificationService.java    # Notification service interface
│   │       │   └── SmsNotificationService.java # @Qualifier notification service
│   │       └── PersonalLibraryServiceApplication.java # Main app with @ComponentScan
│   └── resources/
│       ├── application.properties              # Basic configuration
│       ├── application.yml                     # Default profile configuration
│       ├── application-dev.yml                 # Development profile
│       └── application-prod.yml                # Production profile
└── test/
    └── java/
        └── com/sprints/PersonalLibraryService/
            ├── ComponentScanTest.java          # Component scanning tests
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

### Component Scan Issues

If you see issues with component scanning:

1. Check that the `@ComponentScan` configuration is correct
2. Verify that excluded services are not being loaded
3. Run the `ComponentScanTest` to verify behavior
