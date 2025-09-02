# Ledger App Migration: Java Swing to Spring Boot + React

This project demonstrates the migration from a Java Swing desktop application to a modern web-based architecture using Spring Boot backend and React frontend.

## Project Structure

```
ledger-app/
├── backend/                  # Spring Boot application
│   ├── src/main/java/com/ledger/
│   │   ├── LedgerApplication.java
│   │   ├── entity/
│   │   │   └── LedgerEntry.java
│   │   ├── repository/
│   │   │   └── LedgerEntryRepository.java
│   │   ├── service/
│   │   │   └── LedgerService.java
│   │   ├── controller/
│   │   │   └── LedgerController.java
│   │   └── dto/
│   │       ├── LedgerEntryDto.java
│   │       └── LedgerSummaryDto.java
│   ├── src/main/resources/
│   │   └── application.properties
│   └── pom.xml
└── frontend/                 # React application
    ├── src/
    │   ├── components/
    │   │   ├── BalanceCard.jsx
    │   │   ├── LedgerForm.jsx
    │   │   ├── LedgerTable.jsx
    │   │   └── FilterBar.jsx
    │   ├── hooks/
    │   │   └── useLedger.js
    │   ├── services/
    │   │   └── api.js
    │   ├── App.jsx
    │   └── main.jsx
    ├── package.json
    └── vite.config.js
```

## Features

- **Backend (Spring Boot)**:
  - JPA entity with validation
  - Repository layer with custom queries
  - Service layer with business logic
  - REST API endpoints
  - H2 in-memory database
  - CORS configuration for frontend

- **Frontend (React)**:
  - Modern, responsive UI with Tailwind CSS
  - Real-time data updates
  - Form validation
  - Filtering (All, Credits, Debits)
  - Running balance calculations
  - Loading and error states

## Prerequisites

- Java 21 or higher
- Node.js 18 or higher
- Maven 3.6 or higher

## Quick Start

### 1. Start the Backend

```bash
cd backend
./mvnw spring-boot:run
```

The Spring Boot application will start on `http://localhost:8080`

**H2 Console**: Access the database console at `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:ledgerdb`
- Username: `sa`
- Password: (leave empty)

### 2. Start the Frontend

```bash
cd frontend
npm install
npm run dev
```

The React application will start on `http://localhost:5173`

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/ledger` | Get all entries with running balances |
| POST | `/api/ledger` | Add new entry |
| DELETE | `/api/ledger/{id}` | Delete entry by ID |
| GET | `/api/ledger/credits` | Get credits only |
| GET | `/api/ledger/debits` | Get debits only |
| GET | `/api/ledger/balance` | Get current balance |

## Key Migration Benefits

### What Changed:
- **File I/O → Database**: Replaced file processing with Spring Data JPA
- **Swing GUI → React Web UI**: Modern web interface instead of desktop GUI
- **In-memory arrays → Persistent storage**: Data persists between restarts
- **Direct object manipulation → REST API**: Clean separation between frontend and backend

### What Stayed the Same:
- **Core business logic**: Date validation, entry sorting, balance calculation
- **Data validation**: Same rules for dates, descriptions, and amounts
- **Sorting behavior**: Entries still sorted by date, then description, then amount

## Development

### Backend Development
- The application uses H2 in-memory database for development
- JPA entities are automatically created/dropped on startup
- All SQL queries are logged for debugging

### Frontend Development
- Built with Vite for fast development
- Uses Tailwind CSS for styling
- Components are modular and reusable
- Custom hook (`useLedger`) manages API communication

## Production Considerations

- **Database**: Switch from H2 to PostgreSQL/MySQL
- **Security**: Add Spring Security for authentication
- **Error Handling**: Add global exception handlers
- **Logging**: Add proper logging with SLF4J
- **Testing**: Add unit and integration tests
- **Deployment**: Containerize with Docker

## Troubleshooting

### Common Issues:

1. **Port conflicts**: Ensure ports 8080 (backend) and 5173 (frontend) are available
2. **CORS errors**: Verify the backend CORS configuration matches your frontend URL
3. **Database connection**: Check H2 console is accessible at `/h2-console`

### Backend Issues:
- Check Maven dependencies are resolved
- Verify Java version is 21+
- Check application logs for detailed error messages

### Frontend Issues:
- Ensure all npm dependencies are installed
- Check browser console for JavaScript errors
- Verify API calls are reaching the backend

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests if applicable
5. Submit a pull request

## License

This project is open source and available under the MIT License.
