# DEMO Secure Banking System 🏦

A robust Java-based banking system demonstrating enterprise-level design patterns and clean architecture principles. This application provides core banking functionalities including account management, secure authentication, and financial transactions, all backed by a MySQL database.

[![Java Version](https://img.shields.io/badge/Java-8%2B-orange)]()
[![Database](https://img.shields.io/badge/Database-MySQL-blue)]()
[![License](https://img.shields.io/badge/License-MIT-green)]()

## 📋 Table of Contents
- [Features](#features)
- [Architecture](#architecture)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Database Setup](#database-setup)
- [Configuration](#configuration)
- [Usage](#usage)
- [Project Structure](#project-structure)
- [Design Patterns](#design-patterns)
- [Contributing](#contributing)

## ✨ Features

- **User Management**
  - Create new user accounts with secure credential storage
  - Authenticate users with login validation
  - Builder pattern implementation for clean object construction

- **Account Operations**
  - View real-time account balance
  - Deposit funds with transaction logging
  - Withdraw money with insufficient funds validation
  - Transfer funds between accounts securely

- **Data Persistence**
  - All transactions stored in MySQL database
  - ACID-compliant operations
  - Data survives application restarts

- **Clean Architecture**
  - DAO (Data Access Object) layer for database operations
  - Service layer for business logic
  - Separation of concerns following SOLID principles

## 🏗️ Architecture

This project follows a layered architecture pattern:


 Presentation Layer      ← Console UI / Main Class

 Service Layer           ← Business Logic

 DAO Layer               ← Data Access Objects

 Database Layer (MySQL)  ← Persistence




### Layer Responsibilities

| Layer | Purpose | Example Classes |
|-------|---------|-----------------|
| **Presentation** | User interaction, input/output | `Main.java`, `BankingApp.java` |
| **Service** | Business rules, transaction logic | `TransactionService`, `AuthService` |
| **DAO** | Database queries, CRUD operations | `UserDAO`, `AccountDAO` |
| **Model** | Entity definitions | `User`, `Account`, `Transaction` |

## 📦 Prerequisites

Before running this application, ensure you have:

- **Java Development Kit (JDK)** 8 or higher

- **MySQL Server** 5.7 or higher

- **MySQL JDBC Driver** (`mysql-connector-j-9.3.0.jar` or compatible version)
- Download from [MySQL Official Site](https://dev.mysql.com/downloads/connector/j/)


## 🚀 Installation

### Step 1: Clone the Repository
git clone https://github.com/KavishkaVenuka/Java--BankApp.git
cd Java--BankApp



### Step 2: Add JDBC Driver
1. Download the MySQL Connector/J driver
2. Add the JAR file to your project's classpath:
   - **IDE Users**: Add to project libraries
   - **Command Line**: Use `-cp` flag when running

## 🗄️ Database Setup

### Step 1: Start MySQL Server
mysql -u root -p


### Step 2: Create Database
CREATE DATABASE bankingapp;
USE bankingapp;


### Step 3: Run SQL Scripts
Navigate to the `/sql` directory and execute the database schema:


### Step 3: Run SQL Scripts
Navigate to the `/sql` directory and execute the database schema:
mysql -u root -p abc_bank < sql/database.sql


**Expected Tables:**
- `users` - Stores user credentials and personal information
- `accounts` - Contains account details and balances
- `transactions` - Logs all financial operations

## ⚙️ Configuration

### Database Connection Setup

Locate the database configuration file (typically `DatabaseConnection.java` in `/src/util/`) and update with your credentials:

private static final String URL = "jdbc:mysql://localhost:3306/bankingapp";
private static final String USERNAME = "your_mysql_username";
private static final String PASSWORD = "your_mysql_password";


**Security Note:** For production use, consider using environment variables or external configuration files instead of hardcoding credentials.

## 💻 Usage

### Compile and Run

**Using Command Line:**
Compile
javac -cp .:mysql-connector-j-9.3.0.jar src/**/*.java -d bin

Run
java -cp bin:mysql-connector-j-9.3.0.jar Main


**Using IDE:**
1. Import the project
2. Add MySQL JDBC driver to build path
3. Run `Main.java`


### Example Workflow

1. **Create Account**
   - Enter personal details
   - System generates unique account number
   - Account stored in database

2. **Login**
   - Authenticate with credentials
   - Access account features

3. **Perform Transaction**
   - Select deposit/withdraw/transfer
   - System validates and executes
   - Transaction logged in database


## 🤝 Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📝 Future Enhancements

- [ ] Add interest calculation for savings accounts
- [ ] Implement transaction history view
- [ ] Add email notifications for transactions
- [ ] Create GUI using JavaFX or Swing
- [ ] Add multi-currency support
- [ ] Implement account statements (PDF generation)
- [ ] Add admin panel for account management

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👤 Author

**Kavishka Venuka**
- GitHub: [@KavishkaVenuka](https://github.com/KavishkaVenuka)

## 🙏 Acknowledgments

- Java JDBC documentation
- MySQL documentation
- Design patterns community resources

---

⭐ **Star this repository if you found it helpful!**

For questions or issues, please open an issue on GitHub.

