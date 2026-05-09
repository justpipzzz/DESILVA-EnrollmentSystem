# OOP ENROLLMENT SYSTEM

---
Author: Allestair Philip V. De Silva
## **How to Run the Code**
To test the system effectively and avoid missing dependencies (e.g., trying to enroll a student in a section that doesn't exist), please follow this specific flow:

1. Access the project file in your `[directory]` and open `src/main/java/org/example/Main.java`.
2. **Run** the program to launch the interactive CLI menu.
3. First, create a **Course** and then register an **Instructor**.
4. After that, create a **Department** (complete everything inside the "Manage Department" menu, including setting up Sections).
5. **Enroll a Student** into the newly created system.
6. Use **Manage Student** to view, update, or remove the student's records.
7. **Check Tuition** to assess the student's fees based on their units and process payments.
8. **Exit/Close the program** when you are done testing.

---
## **Encapsulation**

![Screenshot 2026-02-07 153838.png](../../../resources/images/Screenshot%202026-02-07%20153838.png)
---
## **Inheritance**
![Screenshot 2026-03-07 164440.png](../../../resources/images/Screenshot%202026-03-07%20164440.png)
![Screenshot 2026-03-07 164747.png](../../../resources/images/Screenshot%202026-03-07%20164747.png)
![Screenshot 2026-03-07 164831.png](../../../resources/images/Screenshot%202026-03-07%20164831.png)

---

## **Abstraction**
### **Person Class**
![img.png](../../../resources/images/img.png)

### **Student Class**
![img_1.png](../../../resources/images/img_1.png)

### **Instructor Class**
![img_2.png](../../../resources/images/img_2.png)

---

## **System Architecture (Data Hierarchy)**

* **Department**: Represents a college/department which contains multiple Sections.
* **Section**: Represents a specific class block. It enforces a `maxCapacity` and links to an assigned `Instructor`, a
  specific `Course`, and a list of enrolled `Student`s.
* **TuitionFeePayment**: A pure data entity that tracks the financial status, total fees, and amount paid for a specific
  student.

---

## **Service Layer (Interfaces & Exceptions)**
The system strictly implements an Interface-Driven Architecture. All business logic is decoupled from data models using
interface contracts (e.g., `IStudentService`, `IEnrollmentService`).
* **Facade Pattern:** Uses `CampusRegistrar` to act as a centralized bridge, cleanly delegating operations to the respective services without cluttering the main program.

**Custom Exceptions:**
To enforce real-world business validations, the system utilizes custom exceptions instead of console printing within the
service layer:
* `SectionFullException`: Thrown when an enrollment attempt exceeds a Section's `maxCapacity`.
* `DuplicateIDException`: Thrown when attempting to register an entity with an existing ID.

---

## **User Interface (CLI) & Exception Handling**
The application now runs on a fully Interface-Driven Console Menu.
* **Separation of Concerns:** The CLI handles all formatting and data presentation directly, fetching pure data from the
  secure Entities via the `CampusRegistrar` bridge.
* **Robust Error Handling:** The CLI actively catches custom exceptions (like `DuplicateIDException` and
  `SectionFullException`) using `try-catch` blocks. It also actively verifies entity existence before updates/deletions to prevent silent failures.

---

## **Course Management**
The system fully supports Course operations via the `ICourseService` contract.
* **Full CRUD Operations:** Users can add, view, update, and remove courses seamlessly.
* **Validation:** Actively prevents duplicate Course IDs from being registered.

---

## **Instructor Management**
The system fully supports Instructor operations via the `IInstructorService` contract.
* **Full CRUD Operations:** Users can register new instructors, update their details, delete records, and view the roster directly from the interactive CLI.
* **Validation:** Instructor registration utilizes the `DuplicateIDException` to ensure no two instructors share the
  same ID.

---

## **Enrollment & Department Hierarchy**
* **Capacity Validation:** The system dynamically validates section capacity during enrollment, actively throwing a `SectionFullException` when a section's maximum limit is reached.
* **Dynamic Seat Allocation:** When a student is removed from the system, the system automatically frees up their seat in their assigned Section.
* **Hierarchy Mapping:** Capable of displaying complex data relationships linking Departments, Courses, Sections, Instructors, and Enrolled Students.

---

## **Tuition Fee Management**
* **Dynamic Computation:** Dynamically calculates a student's total tuition based directly on the specific number of courses/units they are enrolled in, rather than a static fee.
* **Financial Processing:** Handles fee assessments and payment tracking independently via the `ITuitionService` contract.
* **Balance Tracking:** Accurately calculates total assessed fees, payments applied, and the remaining student balance.

---

## **Unit Testing (JUnit 5)**
The core business logic is fortified with automated Unit Tests using JUnit 5.
* **Service Coverage:** Covers Student, Course, Instructor, Department, and Section Registration services.
* **Logic Validation:** Validates capacity limit boundaries (`SectionFullException`), duplicate ID blocking (`DuplicateIDException`), and ensures the accuracy of dynamic tuition math.