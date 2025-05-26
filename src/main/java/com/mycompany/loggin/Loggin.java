/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.loggin;

/**
 *
 * @author lab_services_student
 */
import java.util.Scanner;

public class Loggin {

    // Registers a user by validating their username and password
    public static String registerUser(String username, String password) {
        boolean isUsernameValid = checkUsername(username);
        boolean isPasswordValid = checkPasswordComplexity(password);

        if (isUsernameValid && isPasswordValid) {
            return "Registration successful!";
        } else if (!isUsernameValid && !isPasswordValid) {
            return "Both username and password are incorrectly formatted.";
        } else if (!isUsernameValid) {
            return "Username is not correctly formatted, please ensure that the username contains an underscore and is no longer than 5 characters.";
        } else {
            return "Password is not correctly formatted, please ensure it has at least 8 characters, a capital letter, a number, and a special character.";
        }
    }

    // Validates username format
    public static boolean checkUsername(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    // Validates password complexity
    public static boolean checkPasswordComplexity(String password) {
        boolean hasUppercase = password.matches(".*[A-Z].*");
        boolean hasDigit = password.matches(".*\\d.*");
        boolean hasSpecial = password.matches(".*[^a-zA-Z0-9].*");
        boolean longEnough = password.length() >= 8;

        return hasUppercase && hasDigit && hasSpecial && longEnough;
    }

    // Cell phone number check: +27 followed by 9-10 digits
    public static boolean checkCellNumber(String phoneNumber) {
        String regex = "^\\+27\\d{9,10}$";
        return phoneNumber.matches(regex);
    }

    // Login user by matching registered and entered credentials
    public static boolean loginUser(String registeredUsername, String registeredPassword, String enteredUsername, String enteredPassword) {
        return registeredUsername.equals(enteredUsername) && registeredPassword.equals(enteredPassword);
    }

    // Returns login status message
    public static String returnLoginStatus(String registeredUsername, String registeredPassword, String enteredUsername, String enteredPassword, String firstname, String lastname) {
        if (loginUser(registeredUsername, registeredPassword, enteredUsername, enteredPassword)) {
            return "Login successful!\nWelcome back, " + firstname + " " + lastname + "!";
        } else {
            return "Login failed: username or password incorrect.";
        }
    }

    // Main registration and login process
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter your First Name:");
        String firstname = input.nextLine();

        System.out.println("Enter your Last Name:");
        String lastname = input.nextLine();

        // Username input & validation
        String username;
        while (true) {
            System.out.println("Enter your username (must contain an underscore and be no more than 5 characters):");
            username = input.nextLine();
            if (checkUsername(username)) {
                System.out.println("Username successfully captured.");
                break;
            } else {
                System.out.println("Username is not correctly formatted.");
            }
        }

        // Password input & validation
        String password;
        while (true) {
            System.out.println("Enter your password (at least 8 characters, a capital letter, a number, and a special character):");
            password = input.nextLine();
            if (checkPasswordComplexity(password)) {
                System.out.println("Password successfully captured.");
                break;
            } else {
                System.out.println("Password is not correctly formatted.");
            }
        }

        // Cell phone input & validation
        String phoneNumber;
        while (true) {
            System.out.println("Enter your cell number in international format (+27...):");
            phoneNumber = input.nextLine();
            if (checkCellNumber(phoneNumber)) {
                System.out.println("Cell phone number successfully added.");
                break;
            } else {
                System.out.println("Cell phone number incorrectly formatted or missing +27.");
            }
        }

        // Registration confirmation
        System.out.println(registerUser(username, password));

        // Login Section
        System.out.println("\n--- LOGIN SECTION ---");

        while (true) {
            System.out.println("Enter your username:");
            String enteredUsername = input.nextLine();

            System.out.println("Enter your password:");
            String enteredPassword = input.nextLine();

            if (loginUser(username, password, enteredUsername, enteredPassword)) {
                System.out.println("---- LOGIN SUCCESSFUL ----");
                System.out.println("Welcome back, " + firstname + " " + lastname + "!");
                break;
            } else {
                System.out.println("Login failed: username or password incorrect.");
            }
        }
    }
}
