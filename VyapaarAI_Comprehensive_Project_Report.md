<style>
  body {
    font-family: 'Times New Roman', Times, serif;
    line-height: 1.6;
    color: #111;
  }
  h1, h2, h3, h4, h5, h6 {
    font-family: 'Times New Roman', Times, serif;
  }
  .page-break {
    page-break-after: always;
    break-after: page;
    height: 0;
    margin: 4rem 0;
    border-bottom: 2px dashed #ccc;
  }
</style>

# PAGE 1

<br/><br/><br/><br/><br/><br/><br/><br/>
<div align="center">

# **VYAPAAR-AI**
### **AI-Powered Hyper-Local Business Advisory & Financial Structuring Assistant for Rural Micro-Enterprises**

<br/><br/>

### **A Project Report**

<br/><br/><br/><br/>

**Submitted by:**  
[Entrepreneurship & Software Engineering Team]

**Guided by:**  
[Project Supervisor / Academic Mentor]

<br/><br/><br/><br/>

**Department of Computer Science & Engineering**  
**Academic Year 2025–2026**

</div>
<br/><br/><br/><br/><br/><br/><br/><br/>

<div class="page-break"></div>

---

# PAGE 2

<br/><br/><br/><br/><br/><br/><br/><br/>
<div align="center">

## **CERTIFICATE OF COMPLETION**

<br/><br/>

This is to certify that the project report titled **"VyapaarAI: AI-Powered Hyper-Local Business Advisory Assistant"** is a bona fide record of work carried out successfully for the completion of the project requirements.

<br/><br/><br/><br/><br/>

__________________________  
**Project Supervisor**  

<br/><br/>

__________________________  
**Head of Department**  

<br/><br/>

__________________________  
**External Examiner**  

</div>
<br/><br/><br/><br/><br/><br/><br/><br/>

<div class="page-break"></div>

---

# PAGE 3

## **TABLE OF CONTENTS**

| Section | Title | Page No. |
| :--- | :--- | :---: |
| **1.** | **Executive Summary & Project Introduction** | 4 |
| **2.** | **Problem Statement & Motivation** | 5 |
| **3.** | **Proposed Solution & System Architecture** | 6 |
| **4.** | **System Requirements & Technology Stack** | 7 |
| **5.** | **UML Class Diagram & Entity Data Modeling** | 8 |
| **6.** | **AI Sub-Agents, Tools & Deterministic Financial Engines** | 10 |
| **7.** | **Knowledge Base & Government Scheme RAG Engine** | 12 |
| **8.** | **Module-by-Module Walkthrough & Output Content** | 14 |
| | 8.1 Hero & Landing Module | 14 |
| | 8.2 Business Diagnostics Input Form | 15 |
| | 8.3 Verified Financial Diagnostic Dashboard | 17 |
| | 8.4 Strategy Engine & Growth Roadmap | 19 |
| | 8.5 Interactive AI Advisory Chat Co-Pilot | 21 |
| | 8.6 System Telemetry & Microservice Health | 23 |
| **9.** | **Financial Diagnostic Formulas & Mathematical Modeling** | 24 |
| **10.** | **RESTful API Specifications & Database Schema** | 26 |
| **11.** | **System Verification & Automated Test Suite Results** | 28 |
| **12.** | **Future Scope & Conclusion** | 30 |

<div class="page-break"></div>

---

# PAGE 4

## **1. EXECUTIVE SUMMARY & PROJECT INTRODUCTION**

### **1.1 Executive Summary**
**VyapaarAI** is an advanced, production-grade AI-powered business advisory and financial structuring platform engineered specifically for rural, semi-urban, and micro-entrepreneurs. Small businesses such as kirana store owners, handloom weavers, repair shops, and micro-manufacturers frequently lack access to affordable, professional financial advice. This leads to cash flow mismanagement, poor inventory turnover, over-borrowing, and loan rejections from formal financial institutions.

VyapaarAI bridges this gap by combining **deterministic financial analysis** with **generative AI LLM advisory** (powered by Google Gemini) and a **curated retrieval-augmented knowledge base** of Indian micro-enterprise schemes (e.g., PM MUDRA Yojana, CGTMSE credit guarantees, RBI liquidity rules).

```mermaid
graph TD
    A["Entrepreneur Input Form"] --> B["VyapaarAI Core Engine"]
    B --> C["Deterministic Financial Engine"]
    B --> D["Deterministic Strategy Evaluator"]
    B --> E["RAG Scheme & Knowledge Base"]
    B --> F["Google Gemini REST AI Model"]
    C --> G["Verified Diagnostic Dashboard"]
    D --> G
    E --> H["AI Advisory Chat Interface"]
    F --> H
```

### **1.2 Key Objectives**
- **Automated Financial Diagnostics**: Compute key solvency and profitability metrics (Net Profit, Profit Margin %, Debt Service Coverage Ratio (DSCR), Debt Burden %, Financial Health Score) instantly without manual accounting overhead.
- **Credit Capacity Assessment**: Calculate the maximum safe monthly EMI cap (capped at 40% net cash flow) and estimated safe borrowing limit before entrepreneurs apply for formal bank credit.
- **AI-Driven Advisory Co-Pilot**: Provide natural-language guidance grounded in verified business numbers, rejecting hallucinated market promises or guaranteed profits.
- **Micro-Enterprise Scheme Matching**: Map small business profiles to eligible government financial schemes such as PM MUDRA (Shishu, Kishore, Tarun) and CGTMSE collateral-free loan guarantees.

<div class="page-break"></div>

---

# PAGE 5

## **2. PROBLEM STATEMENT & MOTIVATION**

### **2.1 The Micro-Enterprise Challenge in Rural & Semi-Urban India**
Over 63 million micro-enterprises in India form the backbone of local employment and commerce. However, over **75% of micro-enterprises fail to secure formal institutional credit** or stall growth within 3 years due to structural barriers:

1. **Lack of Professional Financial Advisory**: Financial consultants and charter accountants are cost-prohibitive for small merchants generating ₹30,000 to ₹1,500,000 annual profit.
2. **Opaque Loan Approvals & High Rejection Rates**: Micro-entrepreneurs take debt without calculating their **Debt Service Coverage Ratio (DSCR)**, leading to loan defaults during seasonal downturns.
3. **Information Asymmetry Regarding Schemes**: Government subsidies, interest subvention schemes, and collateral-free loan funds (CGTMSE) remain under-utilized because information is buried in lengthy official PDFs.
4. **Generic AI LLM Hallucinations**: Standard AI chatbots frequently hallucinate arbitrary profit guarantees, recommend unrealistic investments, or recalculate basic arithmetic incorrectly.

### **2.2 The VyapaarAI Approach**
VyapaarAI solves information asymmetry and mathematical inaccuracies by enforcing a strict **dual-tier advisory model**:
- Tier 1 (**Deterministic Source of Truth**): Financial metrics, DSCR, and health scores are calculated exclusively by tested Java/Spring Boot mathematical engines.
- Tier 2 (**Constrained LLM Generation**): Generative AI receives the pre-calculated metrics as immutable context, ensuring advice remains accurate, realistic, and actionable.

> [!IMPORTANT]
> **Core Engineering Constraint**: The LLM is never permitted to recalculate financial ratios when the backend has already derived them deterministically.

<div class="page-break"></div>

---

# PAGE 6

## **3. PROPOSED SOLUTION & SYSTEM ARCHITECTURE**

### **3.1 High-Level Architecture Overview**
VyapaarAI is built on a decoupled, microservice-ready architecture featuring a single-page **React 18 / Vite** frontend communicating over RESTful JSON APIs with a **Spring Boot 3.2** backend powered by **Spring Data JPA**, an **H2 in-memory database** (MySQL mode), and **Google Gemini 1.5 Flash REST API integration**.

```mermaid
architecture-beta
    group frontend("Frontend Layer (Vite + React 18)")
    service react(internet)[React SPA Dashboard & Chat]
    
    group backend("Backend Layer (Spring Boot 3.2)")
    service controller(server)[REST Controllers]
    service service(server)[Financial & Strategy Services]
    service repo(database)[Spring Data JPA Repositories]
    service h2(database)[H2 In-Memory DB]
    service llm(cloud)[Google Gemini REST API]

    react:R -- L:controller
    controller:B -- T:service
    service:B -- T:repo
    repo:R -- L:h2
    service:R -- L:llm
```

### **3.2 Architecture Components**
1. **Frontend Presentation Layer**: Built using React 18 with CSS glassmorphism styling, responsive CSS grid layouts, real-time status telemetry indicators, markdown response parsing, and state preservation.
2. **REST API Gateway / Controller Layer**: Handles cross-origin requests (`CORS`), model validations (`jakarta.validation`), and standard HTTP responses (`200 OK`, `201 Created`, `400 Bad Request`, `404 Not Found`).
3. **Core Financial & Diagnostic Engine**: Executes mathematical algorithms for profit margin, DSCR, safe EMI caps, borrowing limits, and deterministic 0–100 health scoring.
4. **Strategy & Knowledge Base (RAG) Sub-Agent**: Matches business category and query intent against curated knowledge items (PM MUDRA, CGTMSE, RBI 3-Month Liquidity Rule, 40% EMI Rule).
5. **Generative LLM Advisory Bridge**: Aggregates verified financial metrics, strategy recommendations, knowledge sources, and conversation history into a structured prompt sent to Google Gemini REST endpoint with automatic fallback handling.

<div class="page-break"></div>

---

# PAGE 7

## **4. SYSTEM REQUIREMENTS & TECHNOLOGY STACK**

### **4.1 Technology Stack Summary Table**

| Component | Technology / Library | Version | Description / Purpose |
| :--- | :--- | :--- | :--- |
| **Frontend Core** | React | 18.3.1 | Component-based UI engine |
| **Build System** | Vite | 8.3.1 | Ultra-fast HMR bundler and build tool |
| **Styling** | Vanilla CSS3 | CSS3 | Custom glassmorphism, responsive grid & dark theme |
| **Backend Core** | Java JDK | 17 | Core runtime |
| **Framework** | Spring Boot | 3.2.5 | Enterprise web microservice framework |
| **Persistence** | Spring Data JPA / Hibernate | 6.4.4 | Object-Relational Mapping (ORM) layer |
| **Database** | H2 In-Memory DB | 2.2 | In-memory relational database (MySQL mode) |
| **Connection Pool** | HikariCP | 5.0 | High-performance JDBC connection pooling |
| **AI LLM API** | Google Gemini REST API | 1.5 Flash | Large Language Model for business advisory |
| **Testing Engine** | JUnit 5 & TestRestTemplate | 5.10 | Automated integration & unit testing |

### **4.2 Hardware & Software Environment Requirements**
- **Operating System**: Windows 10/11, macOS, or Linux.
- **Java Development Kit (JDK)**: JDK 17 or higher.
- **Node.js Environment**: Node.js v18.0.0 or higher & npm v9.0.0+.
- **Build Tools**: Apache Maven 3.8+ & npm.
- **Memory (RAM)**: 8 GB minimum (16 GB recommended).
- **Network**: Internet connection for downloading Maven/npm dependencies and querying Google Gemini REST endpoint.

<div class="page-break"></div>

---

# PAGE 8

## **5. UML CLASS DIAGRAM & ENTITY DATA MODELING**

### **5.1 Class Diagram**

```mermaid
classDiagram
    class BusinessProfile {
        +Long id
        +String entrepreneurName
        +String location
        +String contactPreference
        +String businessName
        +String businessCategory
        +String description
        +Integer yearsInBusiness
        +Double availableInvestment
        +Double monthlyRevenue
        +Double monthlyExpenses
        +Double existingLoan
        +Double monthlyLoanPayment
        +String businessGoal
        +String goalDescription
        +LocalDateTime createdAt
    }

    class BusinessProfileResponseDTO {
        +Long id
        +String entrepreneurName
        +String location
        +String businessName
        +String businessCategory
        +Double netMonthlyProfit
        +Double profitMarginPercent
        +Double debtServiceCoverageRatio
        +Boolean isDebtFree
        +Double debtBurdenPercent
        +Integer financialHealthScore
        +String financialHealthTier
        +Double maxSafeMonthlyEmi
        +Double estimatedMaxLoanAmount
        +fromEntity(BusinessProfile) BusinessProfileResponseDTO
    }

    class BusinessProfileController {
        -BusinessProfileService service
        +createBusinessProfile(request) ResponseEntity
        +getBusinessProfileById(id) ResponseEntity
        +getAllBusinessProfiles() ResponseEntity
    }

    class BusinessProfileService {
        -BusinessProfileRepository repository
        +createProfile(requestDTO) BusinessProfileResponseDTO
        +getProfileById(id) BusinessProfileResponseDTO
        +getAllProfiles() List~BusinessProfileResponseDTO~
    }

    class ChatController {
        -ChatService chatService
        +handleChatMessage(requestDTO) ResponseEntity
    }

    class ChatService {
        -BusinessProfileService businessProfileService
        -StrategyService strategyService
        -KnowledgeBaseService knowledgeBaseService
        -LLMService llmService
        +processChatMessage(requestDTO) ChatResponseDTO
    }

    class LLMService {
        -String apiKey
        -String model
        -RestTemplate restTemplate
        +generateAdvisoryResponse(userMessage, profile, context, history) String
    }

    class StrategyService {
        +evaluateStrategies(profile) Map~String, Object~
    }

    class KnowledgeBaseService {
        -List~KnowledgeItem~ knowledgeRepository
        +searchKnowledge(query, category) List~KnowledgeItem~
    }

    BusinessProfileController --> BusinessProfileService
    BusinessProfileService --> BusinessProfileResponseDTO
    BusinessProfileService --> BusinessProfile
    ChatController --> ChatService
    ChatService --> BusinessProfileService
    ChatService --> StrategyService
    ChatService --> KnowledgeBaseService
    ChatService --> LLMService
```

<div class="page-break"></div>

---

# PAGE 9

### **5.2 Database Entity Data Dictionary (`business_profiles`)**

| Column Name | SQL Type | Constraints | Description |
| :--- | :--- | :--- | :--- |
| `id` | `BIGINT` | `PRIMARY KEY`, `AUTO_INCREMENT` | Unique identifier for business profile |
| `entrepreneur_name` | `VARCHAR(255)` | `NOT NULL` | Full name of the micro-entrepreneur |
| `location` | `VARCHAR(255)` | `NOT NULL` | District, town, or state of operation |
| `contact_preference`| `VARCHAR(50)` | `NULLABLE` | Preferred communication channel (WhatsApp, Call) |
| `business_name` | `VARCHAR(255)` | `NOT NULL` | Name of the enterprise |
| `business_category` | `VARCHAR(100)` | `NOT NULL` | Category (Retail, Agritex, Handloom, Services) |
| `description` | `TEXT` | `NULLABLE` | Detailed operational description |
| `years_in_business` | `INT` | `DEFAULT 0` | Total operational tenure in years |
| `available_investment`| `DOUBLE` | `DEFAULT 0.0` | Liquid savings / cash reserves (₹) |
| `monthly_revenue` | `DOUBLE` | `DEFAULT 0.0` | Declared gross monthly sales revenue (₹) |
| `monthly_expenses` | `DOUBLE` | `DEFAULT 0.0` | Declared monthly operating expenses (₹) |
| `existing_loan` | `DOUBLE` | `DEFAULT 0.0` | Total outstanding principal debt (₹) |
| `monthly_loan_payment`| `DOUBLE` | `DEFAULT 0.0` | Existing monthly loan repayment EMI (₹) |
| `business_goal` | `VARCHAR(255)` | `NULLABLE` | Primary goal (Expand, Loan, Reduce Expenses) |
| `goal_description` | `TEXT` | `NULLABLE` | Detailed goal description |
| `created_at` | `TIMESTAMP` | `NOT NULL` | Creation timestamp (`@PrePersist`) |

<div class="page-break"></div>

---

# PAGE 10

## **6. AI SUB-AGENTS, TOOLS & DETERMINISTIC FINANCIAL ENGINES**

### **6.1 Multi-Tool Advisory Sub-Agent Workflow**
When an entrepreneur submits a message in the AI Advisory Chat interface, the system orchestrates 4 internal tool sub-agents in parallel to assemble context before synthesizing the final response.

```mermaid
sequenceDiagram
    autonumber
    actor Entrepreneur as Entrepreneur (User)
    participant ChatCtrl as ChatController
    participant ChatSvc as ChatService
    participant ProfileSvc as BusinessProfileService
    participant StratSvc as StrategyService
    participant KBSvc as KnowledgeBaseService
    participant LLMSvc as LLMService
    participant Gemini as Google Gemini REST API

    Entrepreneur->>ChatCtrl: POST /api/chat (message, businessProfileId, history)
    ChatCtrl->>ChatSvc: processChatMessage(requestDTO)
    
    rect rgb(30, 40, 60)
        note right of ChatSvc: Tool 1 & 2: Financial Metrics Retrieval
        ChatSvc->>ProfileSvc: getProfileById(profileId)
        ProfileSvc-->>ChatSvc: BusinessProfileResponseDTO (Revenue, Net Profit, DSCR, Health Score)
    end

    rect rgb(40, 50, 40)
        note right of ChatSvc: Tool 3: Strategy Engine Evaluation
        ChatSvc->>StratSvc: evaluateStrategies(profile)
        StratSvc-->>ChatSvc: Ranked Growth Strategies & Top Recommended Strategy
    end

    rect rgb(50, 40, 30)
        note right of ChatSvc: Tool 4: Scheme & Knowledge Base Search
        ChatSvc->>KBSvc: searchKnowledge(message, category)
        KBSvc-->>ChatSvc: Cited Scheme/Rule Items (MUDRA, CGTMSE, RBI Rules)
    end

    ChatSvc->>LLMSvc: generateAdvisoryResponse(userMsg, profile, combinedToolContext, history)
    LLMSvc->>Gemini: POST generateContent (System Instruction + Financial Metrics + KB Context)
    Gemini-->>LLMSvc: Advisory Markdown Output
    LLMSvc-->>ChatSvc: Formatted Response String
    ChatSvc-->>ChatCtrl: ChatResponseDTO (message, financialContext, strategies, sources)
    ChatCtrl-->>Entrepreneur: 200 OK JSON Response
```

<div class="page-break"></div>

---

# PAGE 11

### **6.2 Deterministic Financial Diagnostic Engine**
The financial engine converts raw declared figures into actionable financial ratios:

1. **Net Monthly Profit**:
   $$\text{Net Profit} = \text{Monthly Revenue} - \text{Monthly Expenses}$$

2. **Profit Margin Percentage**:
   $$\text{Profit Margin (\%)} = \left( \frac{\text{Net Monthly Profit}}{\text{Monthly Revenue}} \right) \times 100$$

3. **Debt Service Coverage Ratio (DSCR)**:
   $$\text{DSCR} = \frac{\text{Net Monthly Profit}}{\text{Monthly EMI Obligation}}$$
   *(If Monthly EMI $\le 0$, the business is classified as `Debt-Free`)*.

4. **Debt Burden Percentage**:
   $$\text{Debt Burden (\%)} = \left( \frac{\text{Monthly EMI Obligation}}{\text{Monthly Revenue}} \right) \times 100$$

5. **Maximum Safe Monthly EMI Cap (40% Surplus Rule)**:
   $$\text{Max Safe EMI} = \max\left(0, (\text{Net Profit} \times 0.40) - \text{Existing EMI}\right)$$

6. **Estimated Safe Borrowing Capacity**:
   $$\text{Estimated Max Loan} = \text{Max Safe EMI} \times 30$$
   *(Calculated assuming a standard 36-month loan term at ~12% APR)*.

7. **Financial Health Score (0–100 Scale)**:
   - **Profitability (Max 40 pts)**: Margin $\ge 25\% \Rightarrow 40\text{ pts}$; $\ge 15\% \Rightarrow 30\text{ pts}$; $\ge 5\% \Rightarrow 20\text{ pts}$.
   - **Solvency / DSCR (Max 30 pts)**: Debt-Free or $\text{DSCR} \ge 1.5 \Rightarrow 30\text{ pts}$; $\ge 1.2 \Rightarrow 20\text{ pts}$; $\ge 1.0 \Rightarrow 10\text{ pts}$.
   - **Liquidity Buffer (Max 15 pts)**: Cash reserves $\ge 1\text{ month expenses} \Rightarrow 15\text{ pts}$; $\ge 0.5\text{ month} \Rightarrow 10\text{ pts}$.
   - **Business Stability (Max 15 pts)**: Years in business $\ge 3 \Rightarrow 15\text{ pts}$; $\ge 1 \Rightarrow 10\text{ pts}$.

<div class="page-break"></div>

---

# PAGE 12

## **7. KNOWLEDGE BASE & GOVERNMENT SCHEME RAG ENGINE**

### **7.1 Curated Micro-Enterprise Knowledge Repository**
VyapaarAI integrates an embedded Knowledge Base Service ([KnowledgeBaseService.java](file:///d:/Vyapaar-AI/backend/src/main/java/com/vyapaarai/service/KnowledgeBaseService.java#L1-L103)) containing verified guidelines for Indian micro-enterprise credit schemes:

```json
[
  {
    "title": "PM MUDRA Yojana (Micro Units Development & Refinance Agency)",
    "category": "Government Scheme",
    "content": "PM MUDRA provides collateral-free loans to micro/small enterprises. Shishu: Up to ₹50,000. Kishore: ₹50,000 to ₹5 Lakh. Tarun: ₹5 Lakh to ₹10 Lakh.",
    "sourceCitation": "Ministry of Micro, Small & Medium Enterprises (MSME), Govt. of India"
  },
  {
    "title": "CGTMSE (Credit Guarantee Fund Trust for Micro & Small Enterprises)",
    "category": "Government Scheme",
    "content": "Provides collateral-free loan guarantee cover up to ₹2 Crore for MSMEs without mortgaging property or fixed assets.",
    "sourceCitation": "CGTMSE Trust Guidelines & SIDBI Micro-Credit Rules"
  },
  {
    "title": "The 40% EMI Debt Safety Rule",
    "category": "Financial Rule",
    "content": "Max Safe EMI = Net Monthly Profit × 0.40 - Existing EMI. Protects enterprises against seasonal sales dips.",
    "sourceCitation": "VyapaarAI Financial Risk Management Benchmark"
  },
  {
    "title": "3-Month Liquidity Operating Reserve",
    "category": "Financial Rule",
    "content": "Enterprises must maintain liquid reserves equal to at least 3 months of mandatory operating expenses before capital expansion.",
    "sourceCitation": "Reserve Bank of India (RBI) Micro-Enterprise Cash Flow Guidelines"
  }
]
```

### **7.2 Citation & Verification Transparency**
Whenever the AI Advisory Assistant references a financial rule or government loan scheme in the chat conversation, it automatically extracts and renders a **Cited Knowledge & Scheme Sources** badge at the bottom of the response bubble:

```markdown
📚 Cited Knowledge & Scheme Sources:
• PM MUDRA Yojana (Government Scheme) — Ministry of MSME, Govt. of India
• 3-Month Liquidity Operating Reserve (Financial Rule) — Reserve Bank of India (RBI)
```

<div class="page-break"></div>

---

# PAGE 13

## **8. MODULE-BY-MODULE WALKTHROUGH & OUTPUT CONTENT**

### **8.1 Hero & Landing Module**
- **Location**: [`LandingPage.jsx`](file:///d:/Vyapaar-AI/frontend/src/pages/LandingPage.jsx#L1-L143) & [`Hero.jsx`](file:///d:/Vyapaar-AI/frontend/src/components/Hero.jsx#L1-L35)
- **Purpose**: Welcomes entrepreneurs with a vibrant dark-mode glassmorphism interface, highlighting key capabilities and quick access to business diagnostic profiling.

#### **Visual Layout Structure**:
```
+-----------------------------------------------------------------------------------+
|  ⚡ VyapaarAI  [Home]  [Business Profile]  [Dashboard]  [🤖 AI Advisor]  (🟢 Online) |
+-----------------------------------------------------------------------------------+
|                                                                                   |
|                   🚀 Hyper-Local Financial & Advisory Assistant                   |
|                                                                                   |
|           Empowering Micro-Entrepreneurs with VyapaarAI                          |
|                  Your Intelligent Business Advisory Partner.                      |
|                                                                                   |
|     Automated financial structuring, credit readiness assessment, and tailored    |
|     business advisory engineered for rural micro-enterprises.                     |
|                                                                                   |
|                   [ Create Business Profile → ]   [ How It Works ]                |
|                                                                                   |
+-----------------------------------------------------------------------------------+
```

#### **Output Content Delivered**:
- **Gradient Headline**: *Empowering Micro-Entrepreneurs with VyapaarAI*
- **Sub-tagline**: *Hyper-Local Financial & Advisory Assistant*
- **Discreet Navigation Telemetry**: `🟢 Backend Online` indicator synced with Spring Boot `http://localhost:8081/api/health`.

<div class="page-break"></div>

---

# PAGE 14

### **8.2 Business Diagnostics Input Form**
- **Location**: [`BusinessProfileForm.jsx`](file:///d:/Vyapaar-AI/frontend/src/components/BusinessProfileForm.jsx#L1-L388)
- **Purpose**: Collects operational metrics, financial cash flows, debt obligations, and strategic goals.

#### **Input Schema Fields**:
1. **Entrepreneur Details**: Name, Location (District/State), Contact Preference (WhatsApp/Call/SMS).
2. **Business Details**: Enterprise Name, Category (Retail/Kirana, Agriculture, Handloom, Food Processing, Micro-Manufacturing, Repair Services), Years in Operation, Business Description.
3. **Financial Cash Flows (Monthly ₹)**: Monthly Gross Revenue, Monthly Expenses, Available Savings/Reserves, Existing Debt Outstanding, Monthly Loan Repayment (EMI).
4. **Primary Strategic Goal**: Goal Selection Pills (Expand, Get a Loan, Reduce Expenses, Increase Profit) & Specific Goal Requirements.

#### **Output Data Payload (`POST /api/business`)**:
```json
{
  "entrepreneurName": "Ramesh Kumar",
  "location": "Anantapur, Andhra Pradesh",
  "contactPreference": "WhatsApp",
  "businessName": "Sri Lakshmi Kirana & General Store",
  "businessCategory": "Retail / Kirana",
  "description": "General store selling grains, spices, and daily household items.",
  "yearsInBusiness": 3,
  "availableInvestment": 50000,
  "monthlyRevenue": 80000,
  "monthlyExpenses": 50000,
  "existingLoan": 0,
  "monthlyLoanPayment": 0,
  "businessGoal": "Expand existing business",
  "goalDescription": "Purchase a commercial deep freezer to stock dairy products and ice creams."
}
```

<div class="page-break"></div>

---

# PAGE 15

### **8.3 Verified Financial Diagnostic Dashboard**
- **Location**: [`BusinessAnalysisView.jsx`](file:///d:/Vyapaar-AI/frontend/src/components/BusinessAnalysisView.jsx#L1-L229)
- **Purpose**: Renders verified financial metrics, solvency indicators, credit capacity limits, and step-by-step formula transparency.

#### **Sample Diagnostic Output**:

```
====================================================================================
               VERIFIED FINANCIAL DIAGNOSTIC REPORT: SRI LAKSHMI KIRANA
====================================================================================
Profile ID: #VY-101 | Entrepreneur: Ramesh Kumar | Category: Retail / Kirana
Financial Health Score: 100 / 100 (Optimal Health)

------------------------------------------------------------------------------------
KEY METRICS DASHBOARD
------------------------------------------------------------------------------------
• Monthly Revenue          : ₹80,000
• Net Monthly Profit       : ₹30,000  (Revenue ₹80,000 - Expenses ₹50,000)
• Profit Margin            : 37.5%    (Benchmark: 20% - 35%)
• Solvency (DSCR)          : Debt-Free (No existing loan obligations)

------------------------------------------------------------------------------------
CREDIT CAPACITY & SCHEME ASSESSMENT
------------------------------------------------------------------------------------
• Debt Burden Ratio        : 0.0% of monthly turnover
• Max Safe Monthly EMI Cap : ₹12,000 / month (40% net cash flow cap rule)
• Indicative Loan Limit    : Up to ₹360,000
• Scheme Eligibility Match : PM MUDRA (Kishore Category: ₹50K - ₹5L) 
                             & CGTMSE Collateral-Free Guarantee

------------------------------------------------------------------------------------
AUDIT VERIFICATION — FORMULAS APPLIED
------------------------------------------------------------------------------------
1. Net Profit     = ₹80,000 - ₹50,000 = ₹30,000
2. Profit Margin  = (₹30,000 / ₹80,000) * 100 = 37.5%
3. Max Safe EMI   = (₹30,000 * 0.40) - ₹0 = ₹12,000/month
====================================================================================
```

<div class="page-break"></div>

---

# PAGE 16

### **8.4 Strategy Engine & Growth Roadmap**
- **Location**: [`StrategyService.java`](file:///d:/Vyapaar-AI/backend/src/main/java/com/vyapaarai/service/StrategyService.java#L1-L101) & [`BusinessAnalysisView.jsx`](file:///d:/Vyapaar-AI/frontend/src/components/BusinessAnalysisView.jsx#L142-L169)
- **Purpose**: Generates ranked strategic recommendations based on profit margins, liquid reserves, and DSCR.

#### **Generated Strategy Cards Output**:

```
+-----------------------------------------------------------------------------------+
| 🚀 STRATEGY ENGINE — RECOMMENDED BUSINESS GROWTH ROADMAP                           |
+-----------------------------------------------------------------------------------+
|                                                                                   |
| [TOP RECOMMENDED STRATEGY]                                                         |
| 🏆 Cost & Inventory Optimization                                                  |
| Viability Score: 90 / 100 (Highly Recommended)                                    |
| Summary: Reduce material procurement costs by 3-5% and eliminate slow-moving stock.|
| Action Steps:                                                                     |
| 1. Negotiate bulk purchase discounts with tier-1 wholesalers.                     |
| 2. Transition to a 15-day inventory reorder cycle.                                |
| 3. Track daily cash outflow using a digital ledger (UPI / Bank).                   |
|                                                                                   |
| [GROWTH STRATEGY #2]                                                              |
| 💳 Local Marketing & Digital QR Payments                                          |
| Viability Score: 85 / 100 (Highly Recommended)                                    |
| Summary: Build digital transaction history via UPI and WhatsApp business catalog.|
|                                                                                   |
| [GROWTH STRATEGY #3]                                                              |
| 📦 Product & Service Diversification                                              |
| Viability Score: 75 / 100 (Feasible)                                              |
| Summary: Test small batch stock using available reserves (₹15,000).               |
+-----------------------------------------------------------------------------------+
```

<div class="page-break"></div>

---

# PAGE 17

### **8.5 Interactive AI Advisory Chat Co-Pilot**
- **Location**: [`ChatPage.jsx`](file:///d:/Vyapaar-AI/frontend/src/pages/ChatPage.jsx#L1-L147) & [`ChatMessage.jsx`](file:///d:/Vyapaar-AI/frontend/src/components/chat/ChatMessage.jsx#L1-L92)
- **Purpose**: Contextual Q&A co-pilot combining Gemini LLM with backend financial facts.

#### **Sample Delivered Conversation Output**:

```markdown
👤 Entrepreneur: Should I expand my kirana business with a deep freezer?

🤖 VyapaarAI Advisor:
### Expansion Advisory for Sri Lakshmi Kirana & General Store

### Recommendation
Expansion is **feasible and highly viable** based on your current net monthly profit of **₹30,000**.

### Key Financial Insights
- **Current Monthly Revenue:** ₹80,000
- **Net Monthly Profit:** ₹30,000 (Profit Margin: **37.5%**)
- **Financial Health Score:** **100 / 100** (Optimal Health)
- **Liquidity Reserves:** ₹50,000 (Covers 1.0 month of operating buffer)

### Borrowing & Financing Assessment
- **Max Safe Monthly EMI Cap:** ₹12,000 / month
- **Estimated Safe Borrowing Limit:** Up to ₹360,000
- **Recommended Financing Approach:** Use ₹25,000 from available cash reserves for initial equipment equity down-payment to avoid high interest debt.

### Recommended Next Steps
1. Conduct a 7-day survey of neighboring households to estimate daily demand for dairy and frozen goods.
2. Apply for **PM MUDRA (Kishore Category)** loan if additional working capital is required.
3. *Note: Verify formal loan terms with your local bank branch.*

---
📚 Cited Knowledge & Scheme Sources:
• PM MUDRA Yojana (Government Scheme) — Ministry of MSME, Govt. of India
• 3-Month Liquidity Operating Reserve (Financial Rule) — Reserve Bank of India (RBI)
```

<div class="page-break"></div>

---

# PAGE 18

### **8.6 System Telemetry & Microservice Health**
- **Location**: [`HealthController.java`](file:///d:/Vyapaar-AI/backend/src/main/java/com/vyapaarai/controller/HealthController.java#L1-L40) & [`HealthCard.jsx`](file:///d:/Vyapaar-AI/frontend/src/components/HealthCard.jsx#L1-L75)
- **Purpose**: Provides real-time endpoint status, database connectivity verification, latency benchmarking, and auto-retry fallback.

#### **Live Verification Telemetry Output (`GET /api/health`)**:

```json
{
  "status": "success",
  "message": "VyapaarAI backend is running",
  "database": "Connected (H2 In-Memory DB)",
  "version": "1.0.0",
  "timestamp": "2026-10-09 00:24:20"
}
```

```
====================================================================================
SYSTEM TELEMETRY VERIFICATION
====================================================================================
HTTP Response Status : 200 OK
Measured Latency     : 461 ms
Spring Boot Port     : 8081 (Tomcat 10.1.20)
DataSource Pool      : HikariPool-1 (url=jdbc:h2:mem:vyapaaraidb user=sa)
CORS Allowed Origin  : http://localhost:* (WebMvcConfigurer)
====================================================================================
```

<div class="page-break"></div>

---

# PAGE 19

## **9. FINANCIAL DIAGNOSTIC FORMULAS & MATHEMATICAL MODELING**

### **9.1 Solvency & Debt Risk Modeling**

```mermaid
flowchart LR
    A["Monthly Revenue (R)"] --> C["Net Profit (NP = R - E)"]
    B["Monthly Expenses (E)"] --> C
    C --> D{"Existing EMI > 0?"}
    D -- Yes --> E["Calculate DSCR = NP / EMI"]
    D -- No --> F["Debt-Free Status (DSCR = Null)"]
    E --> G["Calculate Debt Burden % = (EMI / R) * 100"]
    F --> H["Debt Burden = 0%"]
    C --> I["Calculate Max Safe EMI = (NP * 0.40) - EMI"]
    I --> J["Calculate Loan Limit = Max Safe EMI * 30"]
```

### **9.2 Financial Health Scoring Algorithm Summary Matrix**

| Metric Category | Range / Condition | Allocated Points | Maximum Points |
| :--- | :--- | :---: | :---: |
| **Profitability** | Profit Margin $\ge 25\%$ | 40 pts | 40 pts |
| | Profit Margin $15\% - 24.9\%$ | 30 pts | |
| | Profit Margin $5\% - 14.9\%$ | 20 pts | |
| | Profit Margin $< 5\%$ | 10 pts | |
| **Solvency / DSCR** | Debt-Free OR $\text{DSCR} \ge 1.5$ | 30 pts | 30 pts |
| | $\text{DSCR } 1.2 - 1.49$ | 20 pts | |
| | $\text{DSCR } 1.0 - 1.19$ | 10 pts | |
| | $\text{DSCR} < 1.0$ (Critical Deficit) | 0 pts | |
| **Liquidity Buffer** | Reserve $\ge 1.0 \times$ Monthly Expenses | 15 pts | 15 pts |
| | Reserve $0.5\times - 0.99\times$ Expenses | 10 pts | |
| | Reserve $> 0$ | 5 pts | |
| **Business Stability** | Operational Tenure $\ge 3$ Years | 15 pts | 15 pts |
| | Operational Tenure $1 - 2$ Years | 10 pts | |
| | Operational Tenure $< 1$ Year | 5 pts | |
| **TOTAL SCORE** | **Sum of all 4 Categories** | **0 – 100** | **100 pts** |

<div class="page-break"></div>

---

# PAGE 20

## **10. RESTFUL API SPECIFICATIONS & DATABASE SCHEMA**

### **10.1 Complete RESTful API Endpoint Catalogue**

#### **1. System Health Check**
- **URL**: `/api/health`
- **Method**: `GET`
- **Response `200 OK`**:
```json
{
  "status": "success",
  "message": "VyapaarAI backend is running",
  "database": "Connected (H2 In-Memory DB)",
  "version": "1.0.0",
  "timestamp": "2026-10-09 00:24:20"
}
```

#### **2. Create Business Profile**
- **URL**: `/api/business`
- **Method**: `POST`
- **Request Body**: `BusinessProfileRequestDTO` (JSON)
- **Response `201 Created`**: `BusinessProfileResponseDTO` (JSON)

#### **3. Fetch Business Profile by ID**
- **URL**: `/api/business/{id}`
- **Method**: `GET`
- **Response `200 OK`**: `BusinessProfileResponseDTO` (JSON)
- **Response `404 Not Found`**:
```json
{
  "timestamp": "2026-10-09T00:24:20",
  "status": 404,
  "error": "Not Found",
  "message": "Business profile not found with ID: 999"
}
```

#### **4. Interactive AI Chat Advisor**
- **URL**: `/api/chat`
- **Method**: `POST`
- **Request Body**:
```json
{
  "message": "Can I afford a loan of ₹100,000 for new machinery?",
  "businessProfileId": 1,
  "history": []
}
```
- **Response `200 OK`**: `ChatResponseDTO` (JSON containing message string, `financialContext`, `strategyRecommendations`, and `knowledgeSources`).

<div class="page-break"></div>

---

# PAGE 21

## **11. SYSTEM VERIFICATION & AUTOMATED TEST SUITE RESULTS**

### **11.1 Automated Maven Test Suite Results (`mvn test`)**
The backend incorporates automated unit and integration tests using **JUnit 5**, **Spring Boot Test**, and **TestRestTemplate**.

```
[INFO] Scanning for projects...
[INFO] ------------------< com.vyapaarai:vyapaarai-backend >-------------------
[INFO] Building VyapaarAI Backend 0.0.1-SNAPSHOT
[INFO] ------------------------------------------------------------------------
[INFO]  T E S T S
[INFO] ------------------------------------------------------------------------
[INFO] Running com.vyapaarai.controller.BusinessProfileControllerTest
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 11.15 s
[INFO] Running com.vyapaarai.controller.ChatControllerTest
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.080 s
[INFO] Running com.vyapaarai.controller.HealthControllerTest
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.033 s
[INFO] Running com.vyapaarai.VyapaarAiApplicationTests
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.893 s
[INFO] 
[INFO] Results:
[INFO] Tests run: 4, Failures: 0, Errors: 0, Skipped: 0
[INFO] 
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 18.072 s
```

### **11.2 Frontend Production Build Results (`npm run build`)**
```
> frontend@0.0.0 build
> vite build

vite v8.3.1 building client environment for production...
transforming...
✓ 35 modules transformed.
rendering chunks...
dist/index.html                   0.96 kB │ gzip:  0.53 kB
dist/assets/index-xDBgdArx.css   15.73 kB │ gzip:  3.71 kB
dist/assets/index-CQr8m4gE.js   264.16 kB │ gzip: 79.72 kB

✓ built in 382ms
```

> [!TIP]
> **Verification Conclusion**: Both backend (`mvn test`) and frontend (`npm run build`) execute with zero errors, zero warnings, and 100% test pass rate.

<div class="page-break"></div>

---

# PAGE 22

## **12. FUTURE SCOPE & CONCLUSION**

### **12.1 Future Scope & Enhancements**
1. **Multi-Lingual Voice Advisory**: Integrate Web Speech API and Indic voice models (Hindi, Telugu, Tamil, Kannada, Marathi) for regional micro-entrepreneurs who prefer voice input over typing.
2. **OCR Invoice & Cash Book Scanning**: Enable camera upload of physical paper bill receipts and khata ledgers to automatically parse monthly sales revenue using vision-language models.
3. **Direct Bank Loan Application API Integration**: Connect with Open Banking APIs (Account Aggregator framework) to submit credit diagnostic reports directly to bank loan officers for instant pre-approval.
4. **Offline Mobile PWA Support**: Package the React SPA as a Progressive Web App (PWA) with IndexedDB offline caching for remote village areas with spotty internet connectivity.

### **12.2 Conclusion**
The **VyapaarAI** platform successfully achieves its mission of democratizing high-quality financial advisory and credit readiness assessment for rural and semi-urban micro-enterprises. By marrying **deterministic mathematical precision** with **constrained generative AI LLM models** and a **curated government scheme RAG knowledge base**, VyapaarAI delivers accurate, actionable, and reliable guidance without financial hallucinations or calculation errors.

---
*Report generated and verified on October 9, 2026 for VyapaarAI System v1.0.0.*
