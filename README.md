# 🌟 Project: Shadow Be Fearless - Security & Geo-Spatial Threat Monitoring Platform

---

## 💡 Overview & Problem Solved

**Shadow Be Fearless** is a security application designed to enhance women's safety during travel by providing features for real-time monitoring, reporting, and emergency response across both mobile and web platforms.

The core technical challenge was developing a **robust, multi-user Flask-based RESTful API** capable of handling simultaneous, real-time requests from a mobile user base (emergency shake service, location viewing) and disparate web clients (Admin, Police, Subadmin).

---

## 🛠️ Technical Stack & Architecture

| Component | Technology | Role in the Project |
| :--- | :--- | :--- |
| **Back-End** | **Flask (Python)** | Core logic, routing, and processing of all web and mobile requests. |
| **Database** | **SQLite** | Data persistence managed via direct Python sqlite3 module calls and custom SQL queries. |
| **API** | **RESTful Services** | Built endpoints for data exchange between the Flask server, the Java mobile application, and web clients. |
| **Front-End** | HTML, CSS, Bootstrap, JavaScript | Presentation layer for Admin, Subadmin, and Police web portals. |
| **Mobile** | Java (Android) & Volley | Client-side application for real-time user interaction and emergency services. |
| **Development** | PyCharm, WAMP (Windows, Apache, MySQL, PHP) | Development environment and local server setup. |

---

## 🧠 Back-End Focus & Contributions

This project demonstrates my ability to design, implement, and secure a multi-client Python Back-End system.

* **API Design & Implementation:** Developed and documented **[X]** RESTful endpoints using Flask, covering key functionalities like user authentication, complaint submission, and real-time location updates.
* **Data Modeling & ORM:** Designed a complex relational data schema (including models for Users, Complaints, Locations, and specific administrative roles) and ensured data integrity using custom, optimized SQL queries for native SQLite integration.
* **Geo-Spatial Logic:** Implemented back-end logic for processing **"Managing Safe and Dangerous Locations"** data, including photo analysis and verification workflows used by the Subadmin role.
* **Asynchronous Tasks (Implied):** Handled the immediate, high-priority nature of the **"Shake Service"** emergency feature, ensuring the server could rapidly receive and notify police/admin users.
* **Multi-Role Authorization:** Implemented robust **role-based access control (RBAC)** to restrict API access and features between Admin, Subadmin, and Police users based on the logged-in user's credentials.

---

## ✨ Key Features Implemented

* **Admin Platform 🛠️:** Vehicle/Notification/Role Management, Feedback Review.
* **Subadmin Platform 🏢:** **Safe/Dangerous Location Management & Verification Workflow.**
* **Police Platform 👮‍♂️:** **Real-Time Emergency Request Viewing** and Dangerous Spot Vetting.
* **Mobile User Features 📱:** Add Complaint, View Locations, **Shake Service for Immediate Emergency Response**, User Chat, Manage Emergency Numbers.

---

## 🏃 How to Run the Project Locally

1.  **Clone the Repository:**
    ```bash
    git clone [Your GitHub URL Here]
    cd shadow-be-fearless
    ```

2.  **Set Up the Python Environment:**
    ```bash
    # Assuming you use a virtual environment
    python -m venv venv
    source venv/bin/activate  # On Windows use `venv\Scripts\activate`
    pip install -r requirements.txt
    ```

3.  **Database Setup:**
    * Initialize the SQLite database (Specify your command here, e.g., `python manage.py init_db`).

4.  **Run the Flask Server:**
    ```bash
    python app.py  # Or whatever your main server file is
    ```
5.  **Access:** The web application will be available at `http://127.0.0.1:5000/`. 
