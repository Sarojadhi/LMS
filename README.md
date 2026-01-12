# 📚 Student Record Management System

A fully-featured Java console application for managing student records with persistent file-based storage. This system demonstrates core Java file handling concepts while providing practical CRUD (Create, Read, Update, Delete) operations through an intuitive command-line interface.

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![File I/O](https://img.shields.io/badge/File%20I%2FO-Data%20Persistence-blue)
![Console App](https://img.shields.io/badge/Console-Application-green)

## 📋 Table of Contents
- [Overview](#overview)
- [Features](#-features)
- [Technology Stack](#-technology-stack)
- [Installation](#-installation)
- [Usage Guide](#-usage-guide)
- [Data Storage](#-data-storage)
- [Code Structure](#-code-structure)
- [Implementation Details](#-implementation-details)
- [Error Handling](#-error-handling)
- [Limitations](#-limitations)
- [Future Enhancements](#-future-enhancements)
- [Contributing](#-contributing)
- [License](#-license)

## 📖 Overview

The **Student Record Management System** is a console-based Java application designed to manage student information efficiently. It provides a simple yet powerful interface for performing basic database-like operations without requiring complex database setup. All data is stored persistently in a plain text file, making it portable and easy to understand.

This project serves as an excellent learning resource for understanding:
- File handling operations in Java
- Data persistence techniques
- CRUD operations implementation
- Console-based user interface design
- Error handling and input validation

## ✨ Features

### Core Functionalities
- **📝 Add Student Records**: Create new student entries with unique ID, name, and age
- **👁️ View All Students**: Display complete list of registered students in formatted output
- **🔍 Search Student**: Quickly find specific students using their unique ID
- **🗑️ Delete Student**: Remove student records from the system permanently
- **💾 Data Persistence**: Automatic saving of all records to `student.txt` file

### User Experience
- **🔄 Interactive Menu System**: Intuitive numbered menu for easy navigation
- **✅ Immediate Feedback**: Success and error messages for all operations
- **🔧 Automatic File Management**: Creates data file on first run if it doesn't exist
- **🛡️ Safe Operations**: File operations include proper error handling and cleanup

## 🔧 Technology Stack

### Programming Language
- **Java SE 8+**: Core application logic and console interface

### File Handling Components
- **`java.io.File`**: File creation and existence checking
- **`BufferedReader`/`BufferedWriter`**: Efficient text file reading and writing
- **`FileReader`/`FileWriter`**: Character stream file operations
- **Append Mode**: Preserves existing data when adding new records

### User Input Handling
- **`java.util.Scanner`**: Console input parsing with buffer management
- **Input Validation**: Basic type checking for age field

### Design Patterns
- **Modular Programming**: Separate methods for each operation
- **Resource Management**: Try-with-resources for automatic cleanup
- **Temporary File Pattern**: Safe deletion through file recreation

## 🚀 Installation

### Prerequisites
1. **Java Development Kit (JDK) 8 or higher**
   - Download from [Oracle JDK](https://www.oracle.com/java/technologies/javase-downloads.html) or [OpenJDK](https://openjdk.org/)
   - Verify installation: `java -version` and `javac -version`

2. **Command Line Interface** (Terminal/CMD/PowerShell)

### Setup Instructions

#### Option 1: Using Git Clone
```bash
# Clone the repository to your local machine
git clone https://github.com/yourusername/student-record-management-system.git

# Navigate to the project directory
cd student-record-management-system

# Compile the Java source file
javac LibraryManagementSystem.java

# Run the application
java LibraryManagementSystem