User Authentication:

Users should be able to log in or register for an account.

classes → User , Admin

User → User’s information

Admin → RegisterUser(), saveUserCredentials(), loginUser(),validateCredentials()

 User credentials (username and password) should be securely stored and validated.

 1. Create a User Class:
• Define a User class to store user information (username and password).
• You can use a vector or an array to store multiple user objects.

2. User Registration:
• Prompt the user to enter a username and password during registration.
• Validate the input (e.g., check if the username is unique).
• Add the new user to your data structure (vector or array).

3. User Login:
• Ask the user to enter their username and password.
• Validate the credentials against the stored user data.
• If valid, allow access; otherwise, display an error message

