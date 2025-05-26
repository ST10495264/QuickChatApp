/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.loggin;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author lab_services_student
 */
public class LogginTest {
    
    public LogginTest() {
    }

    /**
     * Test of registerUser method, of class Loggin.
     */
   @Test
    public void testRegisterUserMessage() {
        String expected = "User successfully registered with username: test_";
        String actual = Loggin.registerUser("test_", "Password1!");
        assertEquals(expected, actual);
    }

    /**
     * Test of checkUsername method, of class Loggin.
     */
    @org.junit.jupiter.api.Test
    public void testCheckUsername() {
    
        assertTrue(Loggin.checkUsername("abc_"));
    }

    @Test
    public void testInvalidUsername_NoUnderscore() {
        assertFalse(Loggin.checkUsername("abcde"));
    }
    
    /**
     * Test of checkPasswordComplexity method, of class Loggin.
     */
    @org.junit.jupiter.api.Test
    public void testCheckPasswordComplexity() {
     assertTrue(Loggin.checkPasswordComplexity("Password1!"));
    }

    /**
     * Test of checkCellNumber method, of class Loggin.
     */
    @org.junit.jupiter.api.Test
    public void testCheckCellNumber() {
    assertTrue(Loggin.checkCellNumber("+27831234567"));
    }

    /**
     * Test of loginUser method, of class Loggin.
     */
    @org.junit.jupiter.api.Test
    public void testLoginUser() {
    assertTrue(Loggin.loginUser("user_", "Password1!", "user_", "Password1!"));
    }

    /**
     * Test of returnLoginStatus method, of class Loggin.
     */
    @org.junit.jupiter.api.Test
    public void testReturnLoginStatus() {
     assertFalse(Loggin.loginUser("user_", "Password1!", "user_", "WrongPass"));
    }

    /**
     * Test of main method, of class Loggin.
     */
   
    
}
