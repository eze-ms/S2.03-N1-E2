# Optical Management System

## 📄 Description
This project manages the process of optical product sales using a MongoDB database. It performs CRUD (Create, Read, Update, Delete) operations for clients, glasses, sales, employees, and suppliers, facilitating the management and traceability of optical sales and customer relationships.

### Features
1. **Database Connection:**
    - MongoDB is used to manage collections related to glasses, clients, suppliers, employees, and sales.
2. **Data Model:**
    - **Client:** Contains basic client information (name, address, email, phone, etc.).
    - **Glasses:** Represents optical products with details such as brand, lens power, frame type, frame color, price, and supplier reference.
    - **Supplier:** Contains details about the suppliers providing the glasses.
    - **Sales:** Captures the details of sales transactions involving clients, glasses, and employees.
    - **Employee:** Information about the employees handling sales operations.
3. **Queries:**
    - Queries to retrieve clients, glasses, sales, suppliers, and employees.
    - Complex queries such as listing all clients associated with specific glasses or showing a supplier for a specific product.

4. **Relationships:**
    - **Client and Glasses:** Multiple clients can be associated with the same glasses.
    - **Glasses and Supplier:** Each glasses entry references a supplier.
    - **Sales:** A sale involves a client, glasses, and an employee.

---

## 🔧 Running the Project
1. Ensure the MongoDB server is running and accessible.
2. Run the `Main.java` file to insert initial data and test CRUD operations for clients, glasses, suppliers, employees, and sales.
3. Verify the collections and documents are created correctly and that queries return the expected results.

---

## 📈 Architecture
### Repository
- The repository handles direct CRUD operations on the MongoDB database.
- Methods such as `insertClient`, `insertGlasses`, `insertSupplier`, and `insertSales` handle insertions, updates, and queries.

### Services
- **Each service handles a specific domain:**
    - `GlassesService`: Manages glasses-related operations, including supplier associations and client interactions.
    - `ClientService`: Handles client registration and data updates.
    - `SalesService`: Manages sales records and listing operations.
    - `SupplierService`: Manages supplier-related operations and data consistency.
    - `EmployeeService`: Manages employee data and relationships with sales.

**The service layer delegates database operations to the repository for better separation of concerns.**

---

## ✨ Additional Features
The system is designed to be scalable, supporting:
- Expansion to include more product types.
- Advanced reporting for sales and inventory management.

---

## 📊 Project Structure
### Models
1. **Client:** Represents a client with fields like `name`, `address`, `email`, `phone`, and `registerDate`.
2. **Glasses:** Represents glasses with fields like `brand`, `frameType`, `price`, and `supplierId`.
3. **Supplier:** Represents a supplier with `name`, `address`, `phone`, `fax`, and `NIF`.
4. **Sales:** Represents a sales record, associating `clientId`, `glassesId`, `employeeId`, and `saleDate`.
5. **Employee:** Represents an employee with fields like `name`, `lastName`, and `phone`.

### Repositories
- **DatabaseRepository:**
    - Manages CRUD operations for all entities (`client`, `glasses`, `supplier`, `sales`, and `employee`).

### Services
- **GlassesService:** Manages operations related to glasses, including supplier and client interactions.
- **ClientService:** Manages client-related operations.
- **SalesService:** Manages sales records and data validation.
- **SupplierService:** Manages supplier details and validation.
- **EmployeeService:** Manages employee data.

---

## 📌 Conclusion
This project offers a well-structured and efficient system for managing optical sales using MongoDB. It ensures data integrity, proper relationship management, and scalability for future enhancements.

---

## 💻 Technologies Used
- **Java**
- **MongoDB**
- **MongoDB Java Driver**

---

## 📊 Requirements
- **MongoDB Server:** Ensure MongoDB is installed and running.
- **Java:** Java Development Kit (JDK) 17+.

---

## 🛠️ Installation
1. Clone this repository:
   ```bash
   git clone https://github.com/eze-ms/S2.03-N1-E2
   ```
2. Ensure MongoDB is running locally.
3. Run the `Main.java` file to test the basic operations.

---

© 2025. Developed by Ezequiel Macchi Seoane. 

