# 🎵 ZomerFestival - Enterprise Web Development

> Een volledige enterprise web applicatie voor ticketbeheer op muziekfestivals, gebouwd met **Spring Boot 3.2.5** en moderne Java 21.

![Java](https://img.shields.io/badge/Java-21-orange?style=flat-square)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.5-brightgreen?style=flat-square)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue?style=flat-square)
![Maven](https://img.shields.io/badge/Maven-3-red?style=flat-square)

---

## 📋 Inhoudsopgave

- [Overzicht](#-overzicht)
- [Features](#-features)
- [Technologie Stack](#technologie-stack)
- [Projectstructuur](#-projectstructuur)
- [Installatie](#-installatie)
- [Configuratie](#-configuratie)
- [Gebruik](#-gebruik)
- [API Endpoints](#-api-endpoints)
- [Database](#-database)
- [Security](#-security)
- [Testing](#-testing)

---

## 🎯 Overzicht

**ZomerFestival** is een geavanceerde web applicatie ontworpen voor het beheren van tickets, artiesten en optredens op muziekfestivals. Het systeem biedt gebruikers de mogelijkheid om tickets te boeken, hun bookings te beheren en festivals per regio/genre te filteren.

Dit project is een EWD (Enterprise Web Development) herexamen project uit augustus 2024 en demonstreert best practices voor enterprise-grade Java applicaties.

---

## ✨ Features

### 🎫 Ticketbeheer

- **Tickets boeken**: Gebruikers kunnen eenvoudig tickets reserveren voor festivals
- **Ticketoverzicht**: Beheer alle je geboekte tickets in één plek
- **Beschikbaarheidsbeheer**: Real-time controle op beschikbare plaatsen
- **Maximum ticketlimiet**: Voorkom overbooking met slimme validatie

### 🎭 Festival Management

- **Per regio**: Filtreer festivals op geografische locatie
- **Per genre**: Filtermogelijkheden op muziekgenre en subgenres
- **Dynamisch overzicht**: Real-time updates van beschikbare optredens

### 👤 Gebruikersbeheer

- **Veilige authenticatie**: BCrypt-geïncrypteerde wachtwoorden
- **Rolgebaseerde toegang**: Verschillende rechten per gebruiker
- **Gebruikersprofiel**: Persoonlijk dashboard met ticketgeschiedenis

### 🔒 Security

- **Spring Security 6**: Enterprise-grade beveiligingsframework
- **CSRF Bescherming**: Token-gebaseerde CSRF-preventie
- **JDBC-gebaseerde authenticatie**: Database-gestuurde gebruikersbeheer
- **Access Control**: Granulair beveiligingsbeleid per endpoint

### 🌐 Modern UI

- **Thymeleaf Templates**: Server-side rendering met dynamic content
- **Tailwind CSS**: Modern en responsive design
- \*\*Internationalisatie (i18n): Nederlands en Engels ondersteuning

---

## 🛠️ Technologie Stack

| Component             | Technologie       | Versie |
| --------------------- | ----------------- | ------ |
| **Backend Framework** | Spring Boot       | 3.2.5  |
| **Java**              | JDK               | 21     |
| **Build Tool**        | Maven             | 3.x    |
| **Database**          | MySQL             | 8.0    |
| **ORM**               | Spring Data JPA   | 3.2.5  |
| **Security**          | Spring Security   | 6.x    |
| **Templates**         | Thymeleaf         | 3.1.x  |
| **Styling**           | Tailwind CSS      | Latest |
| **Testing**           | JUnit 5 + Mockito | Latest |
| **Validation**        | Spring Validation | 3.2.5  |

### Dependencies (Kernbibliotheken)

- ✅ Spring Boot Data REST - RESTful APIs
- ✅ Spring Boot Validation - Input validatie
- ✅ Spring Boot Security - Beveiliging
- ✅ Spring Data JPA - Database ORM
- ✅ Thymeleaf + Security extras - Templating
- ✅ MySQL Connector - Database driver
- ✅ Lombok - Code boilerplate reduction
- ✅ DevTools - Hot reload during development

---

## 📁 Projectstructuur

```text
ZomerFestival/
├── src/
│   ├── main/
│   │   ├── java/com/zomerFestival/
│   │   │   ├── ZomerFestivalApp.java              # Entry point
│   │   │   ├── config/
│   │   │   │   ├── SecurityConfig.java            # Spring Security configuratie
│   │   │   │   └── InitDataConfig.java            # Initiële data setup
│   │   │   ├── controllers/                       # MVC Controllers
│   │   │   │   ├── HomeController.java
│   │   │   │   ├── TicketController.java
│   │   │   │   ├── GenreController.java
│   │   │   │   ├── RegioController.java
│   │   │   │   ├── LoginController.java
│   │   │   │   ├── MijnTickettenController.java
│   │   │   │   ├── WijzigPlanningController.java
│   │   │   │   ├── APIController.java
│   │   │   │   └── AccessDeniedController.java
│   │   │   ├── domein/                            # Domain Models (JPA Entities)
│   │   │   │   ├── Festival.java
│   │   │   │   ├── Optreden.java
│   │   │   │   ├── Genre.java
│   │   │   │   ├── SubGenre.java
│   │   │   │   ├── Regio.java
│   │   │   │   ├── Ticket.java
│   │   │   │   ├── User.java
│   │   │   │   └── NieuwOptreden.java
│   │   │   ├── service/                           # Business Logic Layer
│   │   │   │   ├── FestivalService.java
│   │   │   │   ├── FestivalServiceImpl.java
│   │   │   │   ├── TicketService.java
│   │   │   │   ├── TicketServiceImpl.java
│   │   │   │   ├── GenreService.java
│   │   │   │   ├── GenreServiceImpl.java
│   │   │   │   ├── UserService.java
│   │   │   │   ├── UserServiceImpl.java
│   │   │   │   └── ... (meer services)
│   │   │   ├── repository/                        # Data Access Layer
│   │   │   │   ├── FestivalRepository.java
│   │   │   │   ├── TicketRepository.java
│   │   │   │   ├── UserRepository.java
│   │   │   │   └── ... (meer repositories)
│   │   │   ├── validator/                         # Custom Validators
│   │   │   │   └── NieuwOptredenValidation.java
│   │   │   └── perform/
│   │   │       └── PerformRest.java               # REST performer
│   │   ├── resources/
│   │   │   ├── application.properties             # App configuratie
│   │   │   ├── i8n/
│   │   │   │   ├── messages.properties            # Engels
│   │   │   │   └── messages_nl.properties         # Nederlands
│   │   │   ├── templates/                         # Thymeleaf Templates
│   │   │   │   ├── home.html
│   │   │   │   ├── login.html
│   │   │   │   ├── ticket.html
│   │   │   │   ├── mijnticketten.html
│   │   │   │   ├── genre.html
│   │   │   │   ├── regio.html
│   │   │   │   ├── wijzigplanning.html
│   │   │   │   └── access-denied.html
│   │   │   └── static/
│   │   │       └── css/
│   │   │           ├── main.css
│   │   │           └── access-denied.css
│   ├── test/
│   │   └── java/com/zomerFestival/
│   │       ├── controllers/                       # Controller tests
│   │       ├── config/
│   │       │   └── SecurityConfigTest.java
│   │       └── ... (meer tests)
├── pom.xml                                        # Maven configuratie
├── mvnw / mvnw.cmd                                # Maven wrapper scripts
├── tailwind.config.js                             # Tailwind CSS configuratie
└── README.md                                      # Dit bestand
```

### 🏗️ Architectuur

```text
┌─────────────────────────────────────────────────────────┐
│                    Spring Boot Application              │
├─────────────────────────────────────────────────────────┤
│  Controllers (HTTP Requests)                            │
│  ├─ HomeController                                      │
│  ├─ TicketController                                    │
│  ├─ GenreController                                     │
│  └─ ...                                                 │
├─────────────────────────────────────────────────────────┤
│  Service Layer (Business Logic)                         │
│  ├─ FestivalServiceImpl                                  │
│  ├─ TicketServiceImpl                                    │
│  ├─ UserServiceImpl                                      │
│  └─ ...                                                 │
├─────────────────────────────────────────────────────────┤
│  Repository Layer (Data Access - Spring Data JPA)       │
│  ├─ FestivalRepository                                  │
│  ├─ TicketRepository                                    │
│  ├─ UserRepository                                      │
│  └─ ...                                                 │
├─────────────────────────────────────────────────────────┤
│  Domain Models (JPA Entities)                           │
│  ├─ Festival, Optreden, Ticket                         │
│  ├─ User, Genre, Regio                                 │
│  └─ ...                                                 │
├─────────────────────────────────────────────────────────┤
│           MySQL Database (Application DB)               │
└─────────────────────────────────────────────────────────┘
```

---

## 🚀 Installatie

### Vereisten

- **Java Development Kit (JDK)** 21 of hoger
- **MySQL** 8.0 of hoger (draaiend op localhost:3306)
- **Maven** 3.6 of hoger (of gebruik de meegeleverde Maven Wrapper)
- **Git** (optioneel, voor cloning)

### Stappen

#### 1. Clone of download het project

```bash
git clone https://github.com/IgorCasteleyn/Enterprise-web-development.git
cd Enterprise-web-development
```

#### 2. Database setup

```sql
CREATE DATABASE ewdherexamen;
USE ewdherexamen;
```

> ⚠️ **Opmerking**: Schema wordt automatisch gegenereerd via Hibernate (ddl-auto=create-drop in application.properties)

#### 3. Maven build

```bash
# Met Maven Wrapper (aanbevolen)
./mvnw clean install

# Of met systeem Maven
mvn clean install
```

#### 4. Applicatie starten

```bash
# Met Maven
./mvnw spring-boot:run

# Of direct jar uitvoeren
java -jar target/zomerFestival-0.0.1-SNAPSHOT.jar
```

#### 5. Applicatie openen

```bash
http://localhost:8080
```

> De applicatie stuurt je automatisch door naar `/login` op de homepage.

---

## ⚙️ Configuratie

### `application.properties`

```properties
# Server
spring.application.name=zomerFestival

# Thymeleaf (Template Engine)
spring.thymeleaf.cache=false                    # Development: geen caching
spring.thymeleaf.prefix=classpath:/templates/
spring.thymeleaf.suffix=.html

# Internationalisatie
spring.messages.basename=i8n/messages

# MySQL Database
spring.datasource.url=jdbc:mysql://localhost:3306/ewdherexamen
spring.datasource.username=root
spring.datasource.password=Snoeper9758*
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA/Hibernate
spring.jpa.hibernate.ddl-auto=create-drop      # Auto-create schema
spring.web.resources.chain.cache=false          # No caching for static resources
```

### Database User Setup (optioneel)

Indien je een ander database user wilt gebruiken, update deze properties:

```properties
spring.datasource.username=jouw_username
spring.datasource.password=jouw_password
```

### Security Beveiliging (produktie)

Voor produktie, zorg ervoor dat:

- ✅ Wachtwoord **NIET** in git wordt commit (gebruik environment variables)
- ✅ HTTPS ingeschakeld is
- ✅ CORS correct geconfigureerd is
- ✅ Session timeout ingesteld is

---

## 🎮 Gebruik

### Applicatie Flow

1. **Login** (`/login`)

   - Voer username en password in
   - Account moet in database bestaan

2. **Home** (`/home`)

   - Overzicht van geboekte tickets
   - Filteren op genre en regio

3. **Festivals/Tickets** (`/ticket/{festivalId}`)

   - Bekijk festival details
   - Boek tickets (max aantal per festival)

4. **Mijn Tickets** (`/mijnticketten`)

   - Alle geboekte tickets beheren
   - Annuleren optie

5. **Genre/Regio Filteren**
   - Filter festivals per genre/subgenre
   - Filter per geografische regio

### Voorbeeldaccounts

Deze worden automatisch gegenereerd in `InitDataConfig`:

| Username | Password  | Role  |
| -------- | --------- | ----- |
| user1    | password1 | USER  |
| user2    | password2 | USER  |
| admin    | admin123  | ADMIN |

> 💡 Verander deze in production!

---

## 🌐 API Endpoints

### Authentication

| Method | Endpoint          | Beschrijving |
| ------ | ----------------- | ------------ |
| `GET`  | `/login`          | Login pagina |
| `POST` | `/perform-logout` | Uitloggen    |

### Festival Management

| Method | Endpoint               | Beschrijving                      |
| ------ | ---------------------- | --------------------------------- |
| `GET`  | `/home`                | Dashboard                         |
| `GET`  | `/ticket/{festivalId}` | Festival details & tickets boeken |
| `POST` | `/ticket/book`         | Ticket boeken                     |
| `GET`  | `/genre`               | Filteren op genre                 |
| `GET`  | `/regio`               | Filteren op regio                 |

### Ticket Management

| Method | Endpoint         | Beschrijving           |
| ------ | ---------------- | ---------------------- |
| `GET`  | `/mijnticketten` | Mijn tickets overzicht |
| `POST` | `/ticket/cancel` | Ticket annuleren       |

### Planning (Admin)

| Method | Endpoint                 | Beschrijving             |
| ------ | ------------------------ | ------------------------ |
| `GET`  | `/wijzigplanning`        | Planning beheer          |
| `POST` | `/wijzigplanning/update` | Update optreden planning |

### API (REST)

| Method | Endpoint   | Beschrijving                       |
| ------ | ---------- | ---------------------------------- |
| `GET`  | `/api/...` | REST endpoints (zie APIController) |

---

## 💾 Database

### Entity Relationship Diagram (ERD)

```text
User (1) ───────── (M) Ticket
  │                   │
  │                   └──→ Festival (1)
  │                          │
  └──────────────────────────┘

Festival (1) ───────── (M) Optreden
  │
  └──→ Regio (1)

Optreden (M) ←───────── Genre (1)
  │
  └──→ SubGenre (1)

Genre (1) ───────── (M) SubGenre
```

### Kernentiteiten

#### User

```java
- id: Long
- username: String (unique)
- password: String (BCrypt-gecodeerd)
- enabled: Boolean
- roles: String
- tickets: List<Ticket>
```

#### Festival

```java
- id: Integer
- naam: String
- datum: LocalDate
- regio: Regio
- beschikbarePlaatsen: Integer
- optreden: List<Optreden>
```

#### Ticket

```java
- id: Long
- user: User
- festival: Festival
- aankoopDatum: LocalDateTime
- status: Enum (GELDIG, GEANNULEERD, etc)
```

#### Optreden

```java
- id: Integer
- artiest: String
- genre: Genre
- subGenre: SubGenre
- festival: Festival
- startTijd: LocalTime
```

---

## 🔐 Security

### Spring Security Configuratie

**Authentication**: JDBC-gebaseerde authenticatie met BCrypt password encoding

```java
auth.jdbcAuthentication()
    .dataSource(dataSource)
    .passwordEncoder(new BCryptPasswordEncoder());
```

**Authorization**: Role-based access control

```java
.authorizeHttpRequests(authz -> authz
    .requestMatchers("/admin/**").hasRole("ADMIN")
    .requestMatchers("/home/**").hasRole("USER")
    .requestMatchers("/login").permitAll()
    ...
)
```

**CSRF Protection**: HTTP Session based CSRF token

```java
.csrf(csrf -> csrf.csrfTokenRepository(new HttpSessionCsrfTokenRepository()))
```

### Endpoints Access Control

| Endpoint          | Vereiste      | Status             |
| ----------------- | ------------- | ------------------ |
| `/login`          | PUBLIC        | ✅ Iedereen        |
| `/home`           | AUTHENTICATED | 🔒 Ingelogde users |
| `/ticket/*`       | AUTHENTICATED | 🔒 Ingelogde users |
| `/mijnticketten`  | AUTHENTICATED | 🔒 Ingelogde users |
| `/wijzigplanning` | ROLE_ADMIN    | 👑 Admins only     |
| `/admin/**`       | ROLE_ADMIN    | 👑 Admins only     |

---

## 🧪 Testing

### Test Coverage

Het project bevat uitgebreide unit & integration tests:

```text
src/test/java/com/zomerFestival/
├── controllers/
│   ├── HomeControllerTest.java
│   ├── TicketControllerTest.java
│   ├── GenreControllerTest.java
│   ├── LoginControllerTest.java
│   ├── MijnTickettenControllerTest.java
│   ├── RegioControllerTest.java
│   ├── WijzigPlanningControllerTest.java
│   └── APIControllerTest.java
└── config/
    └── SecurityConfigTest.java
```

### Tests Uitvoeren

```bash
# Alle tests
./mvnw test

# Specifieke test class
./mvnw test -Dtest=HomeControllerTest

# Met coverage
./mvnw clean test jacoco:report
```

### Testing Dependencies

- **JUnit 5** - Test framework
- **Mockito** - Mocking library
- **Spring Security Test** - Security testing utilities
- **Spring Boot Test** - Integration testing
