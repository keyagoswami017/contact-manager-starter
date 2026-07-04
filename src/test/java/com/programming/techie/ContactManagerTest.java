package com.programming.techie;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

//@TestInstance(TestInstance.Lifecycle.PER_CLASS) : we don't need to write static if this is being enabled
//@Disabled : used to disable any test
class ContactManagerTest {

    private ContactManager contactManager;
    @BeforeAll
    public static void setupAll(){
        System.out.println("Should print before all Tests");
    }

    @BeforeEach
    public void setup(){
        contactManager = new ContactManager();
    }

    @Test
    public void shouldCreateContact(){
        contactManager.addContact("Jane", "Doe", "0123456789");
        Assertions.assertFalse(contactManager.getAllContacts().isEmpty(),"Contact is empty");
        Assertions.assertEquals(1, contactManager.getAllContacts().size());
        Assertions.assertTrue(contactManager.getAllContacts().stream()
                .anyMatch(contact -> contact.getFirstName().equals("Jane") &&
                        contact.getLastName().equals("Doe") &&
                        contact.getPhoneNumber().equals("0123456789")));
    }

    @Test
    @DisplayName("Should Not Create Contact When First Name is Null")
    public void shouldThrowRunTimeExceptionWhenFirstNameIsNull(){
        Assertions.assertThrows(RuntimeException.class, () -> {contactManager.addContact(null,"Lname","0123456789");});
    }

    @DisplayName("Repeat contact creation 2 times")
    @ParameterizedTest
    @ValueSource(strings = {"0246897531","0246897537"})
    public void shouldTestContact(String contact){
        contactManager.addContact("John","Doe",contact);
        assertFalse(contactManager.getAllContacts().isEmpty());
        assertEquals(1,contactManager.getAllContacts().size());
    }


    @AfterAll
    public static void tearDownAll(){
        System.out.println("Should be executed at the end of tests");
    }

    @AfterEach
    public void tearDown(){
        System.out.println("Should be executed at the end of each test");
    }

    @Nested
    class RepeatedTestClass{
        @DisplayName("Should Create Contact")
        @RepeatedTest(value = 2)
        public void shouldTestLastName(){
            contactManager.addContact("John","Doe","0246897531");
            assertFalse(contactManager.getAllContacts().isEmpty());
            assertEquals(1,contactManager.getAllContacts().size());
        }

    }

}