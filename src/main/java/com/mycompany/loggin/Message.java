/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.loggin;

/**
 *
 * @author lab_services_student
 */
public class Message {
    private String recipient;
    private String text;

    // Constructor — validates and sets recipient and message text
    public Message(String recipient, String text) {
        if (!checkRecipientCell(recipient)) {
            throw new IllegalArgumentException("Invalid recipient phone number.");
        }
        this.recipient = recipient;
        this.text = text;
    }

    // Method to validate the recipient number format
    public static boolean checkRecipientCell(String cellNumber) {
        if (cellNumber == null || cellNumber.length() < 12 || cellNumber.length() > 13) {
            return false;
        }
        if (!cellNumber.startsWith("+27")) {
            return false;
        }

        // Check if the rest are digits
        for (int i = 3; i < cellNumber.length(); i++) {
            if (!Character.isDigit(cellNumber.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    // Method to display the message details
    public String displayMessageDetails() {
        return "To: " + recipient + "\nMessage: " + text;
    }

    // Getters
    public String getRecipient() {
        return recipient;
    }

    public String getText() {
        return text;
    }
}