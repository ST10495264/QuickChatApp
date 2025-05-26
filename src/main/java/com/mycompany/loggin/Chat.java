/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.loggin;

/**
 *
 * @author lab_services_student
 */
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONObject;
import org.json.*;
public class Chat {

    private static ArrayList<Message> sentMessages = new ArrayList<>();
    private static int totalMessagesSent = 0;

    public static void main(String[] args) {
        String firstName = JOptionPane.showInputDialog("Enter your first name:");
        String lastName = JOptionPane.showInputDialog("Enter your last name:");

        String username;
        while (true) {
            username = JOptionPane.showInputDialog("Enter your username (must contain an underscore and be no more than 5 characters):");
            if (Loggin.checkUsername(username)) {
                JOptionPane.showMessageDialog(null, "Username successfully captured.");
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Username not correctly formatted.");
            }
        }

        String password;
        while (true) {
            password = JOptionPane.showInputDialog("Enter your password (at least 8 characters, a capital letter, number, special char):");
            if (Loggin.checkPasswordComplexity(password)) {
                JOptionPane.showMessageDialog(null, "Password successfully captured.");
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Password not correctly formatted.");
            }
        }

        String phoneNumber;
        while (true) {
            phoneNumber = JOptionPane.showInputDialog("Enter your cell number in international code (+27...):");
            if (Loggin.checkCellNumber(phoneNumber)) {
                JOptionPane.showMessageDialog(null, "Cell phone number successfully added.");
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Cell phone number incorrectly formatted or missing +27.");
            }
        }

        // Registration confirmation
        JOptionPane.showMessageDialog(null, Loggin.registerUser(username, password));

        // Login Section
        JOptionPane.showMessageDialog(null, "---LOGIN SECTION---");

        while (true) {
            String enteredUsername = JOptionPane.showInputDialog("Enter your username:");
            String enteredPassword = JOptionPane.showInputDialog("Enter your password:");

            if (Loggin.loginUser(username, password, enteredUsername, enteredPassword)) {
                JOptionPane.showMessageDialog(null, Loggin.returnLoginStatus(username, password, enteredUsername, enteredPassword, firstName, lastName));
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Login failed: username or password incorrect. Try again.");
            }
        }

        // Chat Section
        JOptionPane.showMessageDialog(null, "Welcome to QuickChat.");

        int messageLimit = Integer.parseInt(JOptionPane.showInputDialog("How many messages would you like to send?"));

        while (true) {
            String menu = "Choose an option:\n1) Send Message\n2) Show sent messages\n3) Quit";
            int choice = Integer.parseInt(JOptionPane.showInputDialog(menu));

            switch (choice) {
                case 1 -> {
                    if (totalMessagesSent >= messageLimit) {
                        JOptionPane.showMessageDialog(null, "Message limit reached.");
                        break;
                    }

                    String recipient = JOptionPane.showInputDialog("Enter recipient's phone number (e.g. +27831234567):");
                    while (!Loggin.checkCellNumber(recipient)) {
                        recipient = JOptionPane.showInputDialog("Invalid recipient number. Must start with +27. Try again:");
                    }

                    String text = JOptionPane.showInputDialog("Enter your message text:");

                    String[] options = {"Send", "Store to JSON", "Disregard"};
                    int action = JOptionPane.showOptionDialog(null, "Choose what to do with the message:",
                            "Message Options", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                            null, options, options[0]);

                    if (action == 2 || action == JOptionPane.CLOSED_OPTION) {
                        JOptionPane.showMessageDialog(null, "Message disregarded.");
                        break;
                    }

                    try {
                        Message msg = new Message(recipient, text);
                        sentMessages.add(msg);
                        totalMessagesSent++;

                        if (action == 0) { // Send
                            JOptionPane.showMessageDialog(null, "Message sent:\n" + msg.displayMessageDetails());
                        } else if (action == 1) { // Store
                            try (FileWriter file = new FileWriter("stored_messages.json", true)) {
                                JSONObject json = new JSONObject();
                                json.put("Recipient", recipient);
                                json.put("Message", text);
                                file.write(json.toString(4) + ",\n");
                                JOptionPane.showMessageDialog(null, "Message stored to JSON:\n" + msg.displayMessageDetails());
                            } catch (IOException e) {
                                JOptionPane.showMessageDialog(null, "Failed to store message.");
                            }
                        }

                    } catch (IllegalArgumentException e) {
                        JOptionPane.showMessageDialog(null, "Failed to send message: " + e.getMessage());
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Unexpected error: " + e.getMessage());
                    }
                }

                case 2 -> {
                    if (sentMessages.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No messages sent yet.");
                    } else {
                        StringBuilder history = new StringBuilder();
                        for (Message msg : sentMessages) {
                            history.append(msg.displayMessageDetails()).append("\n\n");
                        }
                        JOptionPane.showMessageDialog(null, history.toString());
                    }
                }

                case 3 -> {
                    JOptionPane.showMessageDialog(null, "Total messages sent: " + totalMessagesSent + "\nExiting app.");
                    System.exit(0);
                }

                default -> JOptionPane.showMessageDialog(null, "Invalid option. Try again.");
            }
        }
    }
}