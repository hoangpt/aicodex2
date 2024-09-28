# Test Cases for CRUD Users

## Test Case 1: Create User
**Description:** Verify that a new user can be created successfully.

**Steps:**
1. Send a POST request to `/users` endpoint with user data.
2. Verify the response status code is 201 (Created).
3. Verify the response body contains the created user's details.

**Expected Result:** The user is created and the response contains the user's details.

## Test Case 2: Read User
**Description:** Verify that an existing user can be retrieved successfully.

**Steps:**
1. Send a GET request to `/users/{userId}` endpoint.
2. Verify the response status code is 200 (OK).
3. Verify the response body contains the user's details.

**Expected Result:** The user details are retrieved successfully.

## Test Case 3: Update User
**Description:** Verify that an existing user's details can be updated successfully.

**Steps:**
1. Send a PUT request to `/users/{userId}` endpoint with updated user data.
2. Verify the response status code is 200 (OK).
3. Verify the response body contains the updated user's details.

**Expected Result:** The user's details are updated successfully.

## Test Case 4: Delete User
**Description:** Verify that an existing user can be deleted successfully.

**Steps:**
1. Send a DELETE request to `/users/{userId}` endpoint.
2. Verify the response status code is 204 (No Content).

**Expected Result:** The user is deleted successfully.