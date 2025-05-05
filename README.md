# 📦 Inventra - Inventory Management System

**Inventra** is an Inventory Management System developed using **Spring Boot**. It provides comprehensive APIs to manage warehouses, suppliers, products, transactions, inventory levels, orders, and order items — designed to support enterprises in managing their stock, suppliers, and sales operations efficiently.

---

## 🚀 Features

- ✅ Manage warehouses and locations  
- ✅ Supplier and product catalog management  
- ✅ Real-time inventory tracking  
- ✅ Order and transaction management  
- ✅ RESTful API with OpenAPI (Swagger UI)  
- ✅ Pagination, filtering, and search support  
- ✅ Modular DTO-based architecture  
- ✅ Fully documented endpoints via Swagger  

---

## 📚 API Documentation

Access the full Swagger UI at:  
**http://localhost:4001/swagger-ui/index.html**

![inventory-service](https://github.com/user-attachments/assets/4b0cc31e-724c-410a-bc33-ed9e5f3b1757)

You’ll find endpoints organized into the following groups:

### Warehouses
- `GET /api/v1/warehouses/{id}` - Get warehouse by ID  
- `PUT /api/v1/warehouses/{id}` - Update warehouse  
- `DELETE /api/v1/warehouses/{id}` - Delete warehouse  
- `GET /api/v1/warehouses` - List all warehouses  
- `POST /api/v1/warehouses` - Create new warehouse  

### Suppliers
- `GET /api/v1/suppliers/{id}` - Get supplier by ID  
- `PUT /api/v1/suppliers/{id}` - Update supplier  
- `DELETE /api/v1/suppliers/{id}` - Delete supplier  
- `GET /api/v1/suppliers` - List all suppliers  
- `POST /api/v1/suppliers` - Create new supplier  

### Products
- `GET /api/v1/products/{id}` - Get product by ID  
- `PUT /api/v1/products/{id}` - Update product  
- `DELETE /api/v1/products/{id}` - Delete product  
- `GET /api/v1/products` - List all products  
- `POST /api/v1/products` - Create new product  
- `GET /api/v1/products/search` - Search products by name  

### Transactions
- `GET /api/v1/transactions` - List all transactions  
- `POST /api/v1/transactions` - Create new transaction  
- `GET /api/v1/transactions/{id}` - Get transaction by ID  
- `DELETE /api/v1/transactions/{id}` - Delete transaction  

### Inventory
- `GET /api/v1/inventories/{id}` - Get inventory by ID  
- `PUT /api/v1/inventories/{id}` - Update inventory  
- `DELETE /api/v1/inventories/{id}` - Delete inventory  
- `GET /api/v1/inventories` - List all inventory items  
- `POST /api/v1/inventories` - Create new inventory entry  

### Orders
- `GET /api/v1/orders/{id}` - Get order by ID  
- `PUT /api/v1/orders/{id}` - Update order  
- `DELETE /api/v1/orders/{id}` - Delete order  
- `GET /api/v1/orders` - List all orders  
- `POST /api/v1/orders` - Create new order  

### Order Items
- `GET /api/v1/order-items/{id}` - Get order item by ID  
- `PUT /api/v1/order-items/{id}` - Update order item  
- `DELETE /api/v1/order-items/{id}` - Delete order item  
- `GET /api/v1/order-items` - List all order items  
- `POST /api/v1/order-items` - Create new order item  

---

## 🛠️ Tech Stack

- **Java 21**  
- **Spring Boot**  
- **Spring Web**  
- **Spring Data JPA**  
- **Swagger / OpenAPI**  
- **PostgreSQL / H2** (configurable)  
- **Maven**  

---

## 📦 Getting Started

### Prerequisites

- Java 21  
- Maven  
- PostgreSQL or H2 (for local testing)  

### Run the Project

```bash
# Clone the repository
git clone https://github.com/essam-tobgi-dev/inventra.git
cd inventra

# Build the application
mvn clean install

# Run the application
mvn spring-boot:run
````

Once running, access the Swagger docs at:
**[http://localhost:4001/swagger-ui/index.html](http://localhost:4001/swagger-ui/index.html)**

---

## 📂 Project Structure

```
inventra/
├── resources/
├── dtos/
├── models/
├── repositories/
├── services/
├── exceptions/
├── enums/
├── validations/
└── mappers/
```

---

## 📘 Contributing

* Fork the repository
* Create your feature branch: `git checkout -b feature/new-feature`
* Commit your changes: `git commit -am 'Add new feature'`
* Push to the branch: `git push origin feature/new-feature`
* Create a new Pull Request

---

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

---

## 🔗 Links

* 🗂️ GitHub Repo: `essam-tobgi-dev/inventra`
* 🧪 Swagger API Docs: `http://localhost:4001/swagger-ui/index.html`
* Built with 💡 by **Essam El-Tobgi**

---
