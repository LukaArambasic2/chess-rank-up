# Backend API Documentation

## AuthController Endpoints

### /auth/login

#### Description

This endpoint is used for logging in a member. It checks the provided credentials and returns a response indicating whether the login was successful or not.

#### Method

`POST`

#### Request Body

The request body should be a JSON object of type `MemberLoginDto` with the following fields:

-   `email`: The email of the member (String).
-   `password`: The password of the member (String).

Example:

```json
{
    "email": "jj56789@fer.hr",
    "password": "pass1"
}
```

#### Expected Validations

1. **Email and Password not null/empty**: Both fields must be provided and must not be empty.
2. **Password Format**: The password must be between 8 and 30 characters long and can contain only uppercase and lowercase letters, numbers, and the special characters '.', '?', '!', '\_', and '-'.

#### Response

-   **200 OK**: The login was successful.
-   **400 Bad Request**: The request body is invalid, the email/password is incorrect, or the member is not verified. Possible error messages:
    -   "Invalid email or password"

#### Example Responses

-   **Successful Login**

    ```json
    {
        "status": 200,
        "message": "Login successful"
    }
    ```

-   **Invalid Email/Password**
    ```json
    {
        "status": 400,
        "message": "Invalid email or password"
    }
    ```

### /auth/register

#### Description

This endpoint is used for registering a new member. It validates the provided data and creates a new member in the system if all validations pass.

#### Method

`POST`

#### Request Body

The request body should be a JSON object of type `MemberRegisterDto` with the following fields:

-   `firstName`: The first name of the member (String).
-   `lastName`: The last name of the member (String).
-   `jmbag`: The JMBAG of the member (String).
-   `email`: The email of the member (String).
-   `password`: The password of the member (String).
-   `repeatPassword`: The repeated password of the member (String).

Example:

```json
{
    "firstName": "Josko",
    "lastName": "Jovanovic",
    "jmbag": "0036540383",
    "email": "jj54038@fer.hr",
    "password": "pass1",
    "repeatPassword": "pass1"
}
```

#### Expected Validations

1. **First Name, Last Name, JMBAG, Email, Password, and Repeat Password not null/empty**: All fields must be provided and must not be empty.
2. **Passwords Match**: The `password` and `repeatPassword` must be the same.
3. **Password Format**: The password must be between 8 and 30 characters long and can contain only uppercase and lowercase letters, numbers, and the special characters '.', '?', '!', '\_', and '-'.
4. **Email Uniqueness**: The provided email must not already be in use.
5. **JMBAG Uniqueness**: The provided JMBAG must not already be in use.

#### Response

-   **200 OK**: The registration was successful.
-   **400 Bad Request**: The request body is invalid or some fields do not satisfy the validation criteria. Possible error messages:
    -   "Missing first name"
    -   "Missing last name"
    -   "Missing JMBAG"
    -   "Missing email"
    -   "Missing password"
    -   "Missing repeat password"
    -   "Passwords do not match"
    -   "Password can contain only: uppercase and lowercase letters, numbers and special characters '.', '?', '!', '\_' and '-'"
    -   "Invalid email or password"
-   **409 Conflict**: The email or JMBAG is already in use. Possible error messages:
    -   "Email already in use"
    -   "JMBAG already in use"

#### Example Responses

-   **Successful Registration**

    ```json
    {
        "status": 200,
        "message": "Registration successful"
    }
    ```

-   **Email Already in Use**

    ```json
    {
        "status": 409,
        "message": "Email already in use"
    }
    ```

-   **Passwords Do Not Match**

    ```json
    {
        "status": 400,
        "message": "Passwords do not match"
    }
    ```

-   **Invalid Password Format**
    ```json
    {
        "status": 400,
        "message": "Password can contain only: uppercase and lowercase letters, numbers and special characters '.', '?', '!', '_' and '-'"
    }
    ```
