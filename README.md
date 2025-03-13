**Secure Password Manager - Project Report**

## **1. Introduction**
Secure Password Manager is a command-line interface (CLI) tool designed to securely store and manage user credentials. The application ensures password confidentiality through encryption and provides a simple yet effective way for users to retrieve and manage stored credentials.

## **2. Objectives**
- To develop a secure, easy-to-use CLI-based password manager.
- To implement encryption techniques for securely storing passwords.
- To provide CRUD (Create, Read, Update, Delete) operations on stored credentials.
- To ensure modularity and maintainability in the codebase.

## **3. Technology Stack**
- **Programming Language:** Java
- **Database:** SQLite
- **Encryption Library:** AES-based encryption (custom implementation)
- **Development Tools:** VSCodium, IntelliJ IDEA, Maven

## **4. Features**
- **Secure Storage:** Encrypts passwords before storing them in the database.
- **User Authentication:** Ensures only authorized access to the stored credentials.
- **Password Retrieval:** Retrieves and decrypts stored passwords when requested.
- **Cross-Platform Support:** Works on Windows and Linux environments.
- **CLI-Based Interaction:** No GUI, designed for terminal-based usage.

## **5. System Design**
### **5.1 Architecture**
The project follows a modular approach:
- **DatabaseHelper.java**: Handles database connections and operations.
- **EncryptionUtil.java**: Encrypts and decrypts passwords using AES.
- **Main.java**: CLI interface for user interaction.

### **5.2 Database Schema**
The SQLite database (`passwords.db`) consists of a single table:
```sql
CREATE TABLE passwords (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    site TEXT NOT NULL,
    username TEXT NOT NULL,
    password TEXT NOT NULL
);
```

## **6. Implementation Details**
### **6.1 Database Operations**
- **Connect to SQLite**: Establishes a connection with the database.
- **Initialize Database**: Creates the passwords table if it does not exist.
- **Insert Password**: Encrypts and stores credentials.
- **Retrieve Password**: Fetches and decrypts credentials when requested.

### **6.2 Encryption Mechanism**
- Uses AES (Advanced Encryption Standard) for password encryption.
- AES keys are securely managed to prevent unauthorized decryption.

## **7. Usage Instructions**
1. **Run the application**:
   ```sh
   java -jar \SPM-1.0-SNAPSHOT.jar
   ```
2. **Available Commands**:
   - `add <site> <username> <password>` - Stores a new credential.
   - `get <site>` - Retrieves stored credentials for a website.
   - `delete <site>` - Deletes stored credentials.

## **8. Challenges & Solutions**
- **Challenge:** Secure storage of passwords.
  - **Solution:** Implemented AES encryption to store encrypted passwords.
- **Challenge:** Ensuring database integrity.
  - **Solution:** Used parameterized queries to prevent SQL injection.

## **9. Future Enhancements**
- Implement a GUI-based interface for ease of use.
- Introduce multi-user authentication.
- Implement cloud-based secure storage.

## **10. Conclusion**
The Secure Password Manager (CLI version) successfully provides a secure and efficient way to store and manage passwords. With encryption in place, it ensures data confidentiality and integrity. Future iterations aim to enhance usability through a graphical interface and cloud storage capabilities.

