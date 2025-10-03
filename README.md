# 📚 Library Management System

This project is a **Library Management System** built using **Java (Maven)** with **MySQL** as the database.  
It was developed as part of the **Object-Oriented Programming (OOP)** course assignment.

The system provides two types of users with distinct roles:

- **Admin**
  - Manage user accounts (Admins, Staff, Members)
  - Manage library resources (Books, Categories)
  - View consolidated reports (`view_all_data`)

- **Staff**
  - Manage book loans and returns for Members
  - Track overdue books and calculate fines

## 🛠️ Tech Stack
- **Java 17+**
- **Maven**
- **MySQL**
- **JDBC**

## 🗄️ Database Schema (MySQL)

**Database Name:** `library_management`

### Tables
1. **`members`** – stores library members  
   - `member_id`, `name`, `username`, `phone`, `email`, `address`, `registration_date`

2. **`admins`** – stores admin and staff accounts  
   - `admin_id`, `name`, `username`, `password_hash`, `phone`, `email`, `role (enum: 'admin','staff')`, `created_at`

3. **`books`** – stores book information  
   - `book_id`, `title`, `author`, `publisher`, `year_published`, `isbn`, `total_quantity`, `available_quantity`, `category_id`

4. **`categories`** – book categories  
   - `category_id`, `category_name`

5. **`loans`** – track borrowing transactions  
   - `loan_id`, `member_id`, `admin_id`, `book_id`, `loan_date`, `due_date`, `return_date`, `fine_amount`, `status`

6. **`view_all_data`** – consolidated view combining Members, Books, Admin/Staff, and Loans  

## 📊 Example Workflow

1. **Admin** logs in → registers Members and Staff accounts  
2. **Staff** logs in → manages loan transactions for Members  
3. **Member** borrows/returns books via Staff  
4. System updates **loan status, availability, and fines** automatically  
5. **Admin** checks consolidated reports in `view_all_data`  

## ⚙️ Setup & Installation

### 1. Clone Repository
```bash
git clone https://github.com/username/library-management.git
cd library-management
````

### 2. Create MySQL Database

```sql
CREATE DATABASE library_management;
USE library_management;
```

### 3. Import Schema & Sample Data

```sql
-- Table: admins
CREATE TABLE admins (
  admin_id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  username VARCHAR(50) UNIQUE NOT NULL,
  password_hash VARCHAR(255) NOT NULL,
  phone VARCHAR(20),
  email VARCHAR(100),
  role ENUM('staff','admin') NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Table: members
CREATE TABLE members (
  member_id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  username VARCHAR(50) UNIQUE NOT NULL,
  phone VARCHAR(20),
  email VARCHAR(100),
  address TEXT,
  registration_date DATE
);

-- Table: categories
CREATE TABLE categories (
  category_id INT AUTO_INCREMENT PRIMARY KEY,
  category_name VARCHAR(100) NOT NULL
);

-- Table: books
CREATE TABLE books (
  book_id INT AUTO_INCREMENT PRIMARY KEY,
  title VARCHAR(255) NOT NULL,
  author VARCHAR(255),
  publisher VARCHAR(255),
  year_published YEAR,
  isbn VARCHAR(20),
  category_id INT,
  total_quantity INT NOT NULL,
  available_quantity INT NOT NULL,
  FOREIGN KEY (category_id) REFERENCES categories(category_id)
);

-- Table: loans
CREATE TABLE loans (
  loan_id INT AUTO_INCREMENT PRIMARY KEY,
  member_id INT,
  admin_id INT,
  book_id INT,
  loan_date DATE,
  due_date DATE,
  return_date DATE,
  fine_amount DECIMAL(10,2) DEFAULT 0.00,
  status ENUM('borrowed','returned','late'),
  FOREIGN KEY (member_id) REFERENCES members(member_id),
  FOREIGN KEY (admin_id) REFERENCES admins(admin_id),
  FOREIGN KEY (book_id) REFERENCES books(book_id)
);

-- View: view_all_data
CREATE VIEW view_all_data AS
SELECT l.loan_id, m.member_id, a.admin_id, b.book_id,
       m.name AS member_name, m.address, b.author, b.isbn, b.publisher, b.year_published,
       b.available_quantity, b.title AS book_title, c.category_id, c.category_name,
       a.name AS admin_name, a.username AS admin_username,
       l.loan_date, l.due_date, l.return_date, l.fine_amount, l.status
FROM loans l
JOIN members m ON l.member_id = m.member_id
JOIN admins a ON l.admin_id = a.admin_id
JOIN books b ON l.book_id = b.book_id
JOIN categories c ON b.category_id = c.category_id;

-- Sample Data
INSERT INTO admins (name, username, password_hash, role) VALUES
('Super Admin', 'admin1', 'hashed_password', 'admin'),
('Library Staff', 'staff1', 'hashed_password', 'staff');

INSERT INTO members (name, username, phone, email, address, registration_date) VALUES
('John Doe', 'jdoe', '08123456789', 'jdoe@mail.com', 'Jakarta', CURDATE()),
('Jane Smith', 'jsmith', '08987654321', 'jsmith@mail.com', 'Bandung', CURDATE());

INSERT INTO categories (category_name) VALUES ('Technology'), ('Fiction'), ('Science');

INSERT INTO books (title, author, publisher, year_published, isbn, category_id, total_quantity, available_quantity) VALUES
('Java Programming', 'James Gosling', 'Sun Press', 2010, '9781234567890', 1, 10, 10),
('The Great Gatsby', 'F. Scott Fitzgerald', 'Scribner', 1925, '9780743273565', 2, 5, 5);
```

### 4. Configure Database Connection
```
db.url=jdbc:mysql://localhost:3306/library_management
db.username=root
db.password=your_password
```

### 5. Build & Run
```
mvn clean install
mvn exec:java -Dexec.mainClass="com.library.management.Main"
```

## 👨‍💻 Author
Developed by: **I Putu Sutha Satyawan**

