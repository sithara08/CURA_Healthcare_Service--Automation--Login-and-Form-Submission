## Overview 

This project is a Selenium-based test automation framework designed to validate the **Login** and **Appointment Form Submission** functionalities of a sample web application. The framework uses **TestNG** for test execution and data-driven testing.

---

## Technologies Used  
- **Programming Language**: Java  
- **Test Framework**: TestNG  
- **Automation Tool**: Selenium WebDriver  
- **Reporting Tool**: ExtentReports  
- **Build Tool**: Maven  

---

## Test Cases Overview  
### 1. **Login Test**  
**Purpose**: Validate the login functionality with valid and invalid credentials.  
- **Scenarios**:  
  - Login with valid credentials (should redirect to the dashboard).  
  - Login with invalid credentials (should display an error message).  
- **Implementation**:  
  - Data-driven testing is used to provide multiple username-password combinations.  
  - Verifies redirection URL or error message as per the scenario.  

### 2. **Make Appointment Test**  
**Purpose**: Automate the process of submitting an appointment form.  
- **Scenarios**:  
  - Select a facility from the dropdown.  
  - Choose a health program via radio buttons.  
  - Provide a date and additional comments.  
  - Submit the form and validate redirection to the appointment summary page.  
- **Implementation**:  
  - Checks for valid dropdown and radio button selections.  
  - Verifies that the user is redirected to the summary page after submission.  


