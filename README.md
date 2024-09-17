# E-Commerce Website for Cosmetics Products (SHOMYA)

This e-commerce website for cosmetics is built using Java Servlets and JSP, following the MVC pattern for dynamic content rendering. We use JPA with Hibernate for ORM to interact with a MySQL database. It's deployed on Apache Tomcat, The platform offers features like product browsing, searching, filtration, categorizing products also shopping cart management, and virtual checkout, ensuring an efficient and scalable shopping experience.



## Table of Contents
- [Project Overview](#project-overview)
- [Technologies Used](#technologies-used)
- [Team Members](#team-members)
- [TeamBacklog](#teambacklog)
- [Getting Started](#getting-started)



## Project Overview
This e-commerce website allows users to browse, search, and virtually purchase cosmetic products. It includes features such as product listings, shopping cart management, order processing, and user authentication.

## Technologies Used
- **Java Servlets & JSP**: For the server-side logic and dynamic web content.
- **Apache Tomcat**: The web server used for deployment.
- **JPA (Java Persistence API) with Hibernate**: ORM framework for managing database operations.
- **MySQL**: Relational database for storing application data.
- **TeamBacklog**: For project management and tracking.

## Team Members
- **[Sharpel Malak]**
  - Email: [sharbelmalak@gmail.com]
  - GitHub: [https://github.com/sharpelmalak](https://github.com/sharpelmalak)
- **[Mohamed Yasser]**
  - Email: [mohamedd.yasser15@gmail.com]
  - GitHub: [https://github.com/MoYaserr](https://github.com/MoYaserr)
- **[Yasmeen Saad]**
  - Email: [Yasmeenesaad@gmail.com]
  - GitHub: [https://github.com/yasmeenesaad](https://github.com/yasmeenesaad))


## TeamBacklog
For project management and tracking, please refer to our TeamBacklog sheet: [TeamBacklog Link]([https://example.com/your-teambacklog-link](https://docs.google.com/spreadsheets/d/19wdoVy30Yd0z1drf2HZa1oTiyqRDgZ4PleSpzl5oty4/edit?usp=sharing))

## Getting Started
To get a copy of this project up and running on your local machine, follow these steps.

### Prerequisites
- Java Development Kit (JDK) 8 or later
- Apache Tomcat 7 
- MySQL Server
- Git
- IDE (e.g., Eclipse, IntelliJ IDEA)

### Steps to Clone and Run the Project
1. **Clone the repository**:
   ```bash
   git clone https://github.com/yourusername/your-repository.git

2. Open your IDE and select **Import Project**.
3. Choose the option to import an existing **Maven** project.

### Set Up the Database and Connection with the project
1-Run the project's SQL script located in the `Design/sql_shomyav5.sql` file to create schema and the necessary tables.

2-Configure persistence.xml:
Open src/main/resources/META-INF/persistence.xml.
Update the database URL, username, and password to match your MySQL configuration.


### Deploy to Tomcat


Right-click on the project in your IDE and select Run on Server.
Choose Apache Tomcat as the server and deploy the application.
Access the Application:

Open a web browser and go to http://localhost:8080/shomya

Thank you for exploring our e-commerce website for cosmetics! We hope you find it as exciting and functional as we do. Happy shopping! 😊

