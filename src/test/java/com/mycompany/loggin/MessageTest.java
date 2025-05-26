/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.loggin;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.function.Executable;

/**
 *
 * @author lab_services_student
 */
public class MessageTest {
    
    @Test
    public void testValidMessageCreation() {
        Message msg = new Message("+27831234567", "Hello!");
        assertEquals("+27831234567", msg.getRecipient());
        assertEquals("Hello!", msg.getText());
    }

    @Test
    public void testInvalidRecipient_TooShort() {
        Exception exception = assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                new Message("+2783123", "Hi");
            }
        });
        assertEquals("Invalid recipient phone number.", exception.getMessage());
    }

    @Test
    public void testInvalidRecipient_WrongPrefix() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Message("0831234567", "Hi");
        });
        assertEquals("Invalid recipient phone number.", exception.getMessage());
    }

    @Test
    public void testInvalidRecipient_NonDigitCharacters() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Message("+27831abc567", "Hi");
        });
        assertEquals("Invalid recipient phone number.", exception.getMessage());
    }

    @Test
    public void testCheckRecipientCell_Valid() {
        assertTrue(Message.checkRecipientCell("+27831234567"));
    }

    @Test
    public void testCheckRecipientCell_Invalid_NoPlus() {
        assertFalse(Message.checkRecipientCell("27831234567"));
    }

    @Test
    public void testCheckRecipientCell_Invalid_TooShort() {
        assertFalse(Message.checkRecipientCell("+2783"));
    }

    @Test
    public void testDisplayMessageDetails() {
        Message msg = new Message("+27831234567", "Hello!");
        String expected = "To: +27831234567\nMessage: Hello!";
        assertEquals(expected, msg.displayMessageDetails());
    }
}