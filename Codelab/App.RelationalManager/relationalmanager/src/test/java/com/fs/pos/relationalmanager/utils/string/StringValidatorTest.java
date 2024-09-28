package com.fs.pos.relationalmanager.utils.string;

import org.junit.jupiter.api.Test;

import com.fs.pos.relationalmanager.utils.validate.StringValidator;

import static org.junit.jupiter.api.Assertions.*;

public class StringValidatorTest {

    @Test
    void testIsEmailInvalid_ValidEmail() {
        assertFalse(StringValidator.isEmailInvalid("test@example.com"));
    }

    @Test
    void testIsEmailInvalid_InvalidEmail_NoAtSymbol() {
        assertTrue(StringValidator.isEmailInvalid("testexample.com"));
    }

    @Test
    void testIsEmailInvalid_InvalidEmail_NoDomain() {
        assertTrue(StringValidator.isEmailInvalid("test@.com"));
    }

    @Test
    void testIsEmailInvalid_InvalidEmail_NoTLD() {
        assertTrue(StringValidator.isEmailInvalid("test@example"));
    }

    @Test
    void testIsEmailInvalid_InvalidEmail_SpecialCharacters() {
        assertTrue(StringValidator.isEmailInvalid("test@exa!mple.com"));
    }

    @Test
    void testIsEmailInvalid_InvalidEmail_EmptyString() {
        assertTrue(StringValidator.isEmailInvalid(""));
    }

    @Test
    void testIsEmailInvalid_InvalidEmail_Null() {
        assertTrue(StringValidator.isEmailInvalid(null));
    }
}