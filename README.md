# 🔐 Password Generator (Java Console App)

A beginner-friendly **Java console-based Password Generator application** that allows users to generate **strong and secure passwords** using a simple Command Line Interface (CLI).  
The project also includes a **Password Strength Checker**, and an optional feature to **securely save passwords using AES encryption** (Java Cryptography Architecture - JCA/JCE).

This project is ideal for students learning **Java basics, randomization, input handling, file I/O, and basic cryptography**.

---

## 🚀 Features

### 🔑 Password Generator
* ✅ Generate strong random passwords
* 🔢 User selects password length
* 🔠 User chooses character types:
  * Uppercase letters (A-Z)
  * Lowercase letters (a-z)
  * Numbers (0-9)
  * Symbols (!@#$%^&*)

### 📊 Strength Checker
* ✅ Check password strength
* Strength levels:
  * WEAK
  * MEDIUM
  * GOOD
  * GREAT

### 🔒 Secure Password Vault (Optional - Advanced)
* 💾 Save passwords securely in an encrypted file (`vault.enc`)
* 🔐 Uses **AES encryption** with master key
* 🔓 View/decrypt saved passwords using master key

---

## 🛠️ Tech Stack

* **Language:** Java  
* **Concepts Used:**
  * Randomization using `SecureRandom`
  * String building and character pools
  * Input handling using `Scanner`
  * File handling (`FileWriter`, `BufferedReader`)
  * Object-Oriented Programming (OOP)

* **Optional (Advanced):**
  * Java Cryptography Architecture (JCA/JCE)
  * AES Encryption (`AES/CBC/PKCS5Padding`)
  * PBKDF2 Key Derivation (`PBKDF2WithHmacSHA256`)

**No external libraries used** ✅

---

## 📂 Project Structure

```
Password-Generator/
│── README.md
│── vault.enc              # Auto-created file to store encrypted passwords
└── src/
    └── com/passwordgen/
        ├── Main.java
        ├── PasswordGenerator.java
        ├── PasswordStrengthChecker.java
        ├── CryptoManager.java
        └── VaultManager.java
```

---

## ⚙️ How to Run the Application

### 🔹 Prerequisites
* Java JDK 8 or above
* VS Code / Command Prompt / Terminal

---

### ▶️ Steps (Windows / macOS / Linux)

1. **Clone the repository**
```bash
git clone <your-repo-url>
cd <repo-folder>
```

2. **Compile**
```bash
javac -d out src/com/passwordgen/*.java
```

3. **Run**
```bash
java -cp out com.passwordgen.Main
```

---

## 🧾 Sample Menu Output

```txt
==============================
     JAVA PASSWORD TOOL
==============================
1. Generate Password
2. Check Password Strength
3. Save Password Securely (AES Vault)
4. View Saved Vault (Decrypt)
5. Exit
Choose option:
```

---

## 🧪 Sample Inputs & Outputs

### ✅ 1) Generate Password
**Input**
```txt
1
Y
Y
Y
Y
14
```

**Output**
```txt
✅ Generated Password: M7@qL1z!kQ#8pR
🔍 Strength: GREAT
```

---

### ✅ 2) Strength Check
**Input**
```txt
2
hello123
```

**Output**
```txt
🔍 Strength: MEDIUM
```

---

### ✅ 3) Save Password Securely (AES Vault)
**Input**
```txt
3
Gmail
example@gmail.com
Sravan@2026
MySecretKey
```

**Output**
```txt
✅ Saved securely to vault.enc
```

---

### ✅ 4) View Saved Vault (Decrypt)
**Input**
```txt
4
MySecretKey
```

**Output**
```txt
--- SAVED VAULT CONTENT ---
SITE: Gmail
USER: example@gmail.com
PASS: Sravan@2026
---
```

---

## 💾 Data Persistence (Vault)

* Encrypted passwords are stored in a file called: `vault.enc`
* File is auto-created when saving credentials
* All saved entries are encrypted and cannot be read without master key

---

## 🎯 Learning Outcomes

* Building CLI applications in Java
* Using `SecureRandom` for secure password creation
* Handling user input and validation
* Password rule design and randomness
* Checking password strength
* (Optional) Learning encryption basics with AES and PBKDF2

---

## 📌 Future Enhancements (Optional)

* Copy password to clipboard
* Generate multiple passwords at once
* Mask password input while typing
* Add password expiry / reminders
* GUI version using JavaFX/Swing

---

## 👨‍🎓 Author

**Sravan**  
B.Tech Student  
This project is part of learning assignment / mini project.

---

⭐ If you find this project useful, feel free to star the repository!
