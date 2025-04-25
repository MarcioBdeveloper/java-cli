# Capital Gain CLI

## 📌 Project Overview
This project is a **Command-Line Interface (CLI)** written in **Java 17** to process capital gain operations.

## 🛠 Solution
The application reads financial transactions from the command line, processes them, and returns the results.

## 📌 Technologies Used
- **Java Version:** 17
- **Libraries:**
    - `jackson-databind`
    - `junit-jupiter-api`
    - `junit-jupiter-engine`
- **Build Tool:** Maven

---

## 🚀 Build and Run the Project

### 🔧 Running with Java (Locally)
1. Open your **command-line terminal**.
2. Navigate to the project directory: Example - cd /Enviroment/workspacejava/JavaCli
3. Execute the command: **mvn clean package**
4. Execute the command: **java -jar target/app-cli-1.jar**
5. Submit cases in prompt: Example  - [{"operation":"buy", "unit-cost":10.00, "quantity": 100}, {"operation":"sell", "unit-cost":15.00, "quantity": 50}, {"operation":"sell", "unit-cost":15.00, "quantity": 50}]
6. Send line empty for stop

### 🔧 Running with Dokcer (Locally)
1. Open your command-line terminal.
2. Navigate to the directory containing Java class files. Example: cd /Enviroment/workspacejava/JavaCli
3. Execute the command: **mvn clean package**
4. Execute the command: **docker buildx build -t app-cli:latest .**
5. Execute the command: **docker run -i app-cli:latest**
6. Submit cases in prompt: Example  - [{"operation":"buy", "unit-cost":10.00, "quantity": 100}, {"operation":"sell", "unit-cost":15.00, "quantity": 50}, {"operation":"sell", "unit-cost":15.00, "quantity": 50}]
7. Submit line empty for stop

### 🔧 Running unit test (Locally)
1. Execute the command: **mvn test**

### 🔧 Test Cases
- **Case 1**
    - `[{"operation":"buy", "unit-cost":10.00, "quantity": 100}, {"operation":"sell", "unit-cost":15.00, "quantity": 50}, {"operation":"sell", "unit-cost":15.00, "quantity": 50}]`
    - `result= [{"tax":0.0},{"tax":0.0},{"tax":0.0}]`

- **Case 2**
    - `[{"operation":"buy", "unit-cost":10.00, "quantity": 10000}, {"operation":"sell", "unit-cost":20.00, "quantity": 5000}, {"operation":"sell", "unit-cost":5.00, "quantity": 5000}]`
    - `result= [{"tax":0.0},{"tax":10000.0},{"tax":0.0}]`

- **Case 3**
    - `[{"operation":"buy", "unit-cost":10.00, "quantity": 10000}, {"operation":"sell", "unit-cost":5.00, "quantity": 5000}, {"operation":"sell", "unit-cost":20.00, "quantity": 3000}]`
    - `result= [{"tax":0.0},{"tax":0.0},{"tax":1000.0}]`

- **Case 4**
    - `[{"operation":"buy", "unit-cost":10.00, "quantity": 10000}, {"operation":"buy", "unit-cost":25.00, "quantity": 5000}, {"operation":"sell", "unit-cost":15.00, "quantity": 10000}]`
    - `result= [{"tax":0.0},{"tax":0.0},{"tax":0.0}]`

- **Case 5**
    - `[{"operation":"buy", "unit-cost":10.00, "quantity": 10000}, {"operation":"buy", "unit-cost":25.00, "quantity": 5000}, {"operation":"sell", "unit-cost":15.00, "quantity": 10000}, {"operation":"sell", "unit-cost":25.00, "quantity": 5000}]`
    - `result= [{"tax":0.0},{"tax":0.0},{"tax":0.0},{"tax":10000.0}]`

- **Case 6**
    - `[{"operation":"buy", "unit-cost":10.00, "quantity": 10000}, {"operation":"sell", "unit-cost":2.00, "quantity": 5000}, {"operation":"sell", "unit-cost":20.00, "quantity": 2000}, {"operation":"sell", "unit-cost":20.00, "quantity": 2000}, {"operation":"sell", "unit-cost":25.00, "quantity": 1000}]`
    - `result= [{"tax":0.0},{"tax":0.0},{"tax":0.0},{"tax":0.0},{"tax":3000.0}]`

- **Case 7**
    - `[{"operation":"buy", "unit-cost":10.00, "quantity": 10000}, {"operation":"sell", "unit-cost":2.00, "quantity": 5000}, {"operation":"sell", "unit-cost":20.00, "quantity": 2000}, {"operation":"sell", "unit-cost":20.00, "quantity": 2000}, {"operation":"sell", "unit-cost":25.00, "quantity": 1000}, {"operation":"buy", "unit-cost":20.00, "quantity": 10000}, {"operation":"sell", "unit-cost":15.00, "quantity": 5000}, {"operation":"sell", "unit-cost":30.00, "quantity": 4350}, {"operation":"sell", "unit-cost":30.00, "quantity": 650}]`
    - `result= [{"tax":0.0},{"tax":0.0},{"tax":0.0},{"tax":0.0},{"tax":3000.0},{"tax":0.0},{"tax":0.0},{"tax":3700.0},{"tax":0.0}]`

- **Case 8**
    - `[{"operation":"buy", "unit-cost":10.00, "quantity": 10000}, {"operation":"sell", "unit-cost":50.00, "quantity": 10000}, {"operation":"buy", "unit-cost":20.00, "quantity": 10000}, {"operation":"sell", "unit-cost":50.00, "quantity": 10000}]`
    - `result= [{"tax":0.0},{"tax":80000.0},{"tax":0.0},{"tax":60000.0}]`

- **Case 9**
    - `[{"operation": "buy", "unit-cost": 5000.00, "quantity": 10}, {"operation": "sell", "unit-cost": 4000.00, "quantity": 5}, {"operation": "buy", "unit-cost": 15000.00, "quantity": 5}, {"operation": "buy", "unit-cost": 4000.00, "quantity": 2}, {"operation": "buy", "unit-cost": 23000.00, "quantity": 2}, {"operation": "sell", "unit-cost": 20000.00, "quantity": 1}, {"operation": "sell", "unit-cost": 12000.00, "quantity": 10}, {"operation": "sell", "unit-cost": 15000.00, "quantity": 3}]`
    - `result= [{"tax":0.0},{"tax":0.0},{"tax":0.0},{"tax":0.0},{"tax":0.0},{"tax":0.0},{"tax":1000.0},{"tax":2400.0}]`

### 🔧 Test Cases in file casefile.txt
    - `Execute the command: docker run -i app-cli:latest < casefile.txt`