# VyapaarAI — AI-Powered Business Advisory & Financial Structuring Assistant

**VyapaarAI** is an AI-powered hyper-local business advisory and financial structuring assistant designed to empower rural micro-entrepreneurs. It delivers structured business planning, credit-readiness assessments, loan eligibility structuring, and hyper-local market intelligence.

---

## 🚀 Technology Stack

### Frontend
- **Framework:** React.js (v18+)
- **Build Tool:** Vite
- **Language:** JavaScript (ES6+)
- **Styling:** Custom CSS3 (Design Tokens, Glassmorphism, Responsive Grid)

### Backend
- **Language:** Java 17 / 21
- **Framework:** Spring Boot 3.2.5
- **Build Tool:** Maven
- **Modules:** Spring Web, Spring Data JPA, Spring Boot Validation, MySQL Connector

---

## 📁 Project Folder Structure

```text
VyapaarAI/
├── frontend/
│   ├── src/
│   │   ├── assets/          # Static assets and icons
│   │   ├── components/      # Modular React components (Navbar, Hero, HealthCard, Features, Footer)
│   │   ├── pages/           # Page views (LandingPage)
│   │   ├── services/        # API service integrations (api.js)
│   │   ├── App.css          # Component and layout styling
│   │   ├── App.jsx          # Root application component
│   │   ├── index.css        # Global CSS design tokens & reset
│   │   └── main.jsx         # Vite entry point
│   ├── index.html           # HTML template
│   ├── package.json         # Node dependencies & scripts
│   └── vite.config.js       # Vite configuration
│
├── backend/
│   ├── src/main/java/com/vyapaarai/
│   │   ├── config/          # Configuration classes (CORS / WebConfig)
│   │   ├── controller/      # REST API Controllers (HealthController)
│   │   ├── dto/             # Data Transfer Objects (HealthResponseDTO)
│   │   ├── model/           # Domain entities (reserved for database phase)
│   │   ├── repository/      # JPA Data repositories (reserved for database phase)
│   │   ├── service/         # Business logic services (reserved for database phase)
│   │   └── VyapaarAiApplication.java # Spring Boot Main Class
│   ├── src/main/resources/
│   │   └── application.properties # Application configurations
│   └── pom.xml              # Maven dependencies & build configuration
│
└── README.md                # Project documentation
```

---

## 🛠️ Prerequisites

Ensure you have the following installed on your machine:
- **Node.js**: v18.0.0 or higher (npm v9+)
- **Java Development Kit (JDK)**: JDK 17 or JDK 21
- **Apache Maven**: v3.8+ (or use `./mvnw` if configured)

---

## 🏃 Running the Application

### 1. Backend (Spring Boot)

Navigate to the `backend` directory and start the server using Maven:

```bash
cd backend
mvn spring-boot:run
```

The Spring Boot backend will start on **`http://localhost:8080`**.

#### 🔍 Health Check API Endpoint
- **URL:** `http://localhost:8080/api/health`
- **Method:** `GET`
- **Sample JSON Response:**
```json
{
  "status": "success",
  "message": "VyapaarAI backend is running"
}
```

---

### 2. Frontend (React + Vite)

Open a new terminal, navigate to the `frontend` directory, install dependencies (if not installed), and launch the development server:

```bash
cd frontend
npm install
npm run dev
```

The Vite React application will start at **`http://localhost:5173`**.

---

## 🌐 CORS Configuration

The backend is pre-configured to allow CORS requests originating from `http://localhost:5173`, enabling real-time communication between the React landing page and the REST API.
