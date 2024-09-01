package iti.jets.dao;

import iti.jets.model.Customer;
import iti.jets.model.User;
import jakarta.persistence.EntityManager;
import org.junit.Test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Collections;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserDaoTest {
//    @Test
//        public void testValidateCredentials() {
//            // Create a mock user and hash the password
//            String username = "testUser";
//            String password = "testPassword";
//            String hashedPassword = hashPassword(password);
//
//            // Mock the EntityManager behavior
//            EntityManager entityManager = mock(EntityManager.class);
//            when(entityManager.createQuery(anyString())).thenReturn(mockQuery);
//            when(mockQuery.setParameter(anyString(), any())).thenReturn(mockQuery);
//            when(mockQuery.getResultList()).thenReturn(Collections.singletonList(new User(username, hashedPassword)));
//
//            // Initialize your class with the EntityManager mock
//            Customer customer = new Customer(entityManager);
//
//            assertTrue(customer.validateCredentials(username, password));
//        }
//
//        @Test
//        public void testRegister() {
//            // Create test data
//            String username = "testUser";
//            String password = "testPassword";
//            String name = "Test Name";
//            // Add other necessary data for registration
//
//            // Mock the EntityManager behavior
//            EntityManager entityManager = mock(EntityManager.class);
//            when(entityManager.createQuery(anyString())).thenReturn(mockQuery);
//            when(mockQuery.setParameter(anyString(), any())).thenReturn(mockQuery);
//            when(mockQuery.getResultList()).thenReturn(Collections.emptyList()); // Simulate that the username is not taken
//
//            // Initialize your class with the EntityManager mock
//            YourClass yourClass = new YourClass(entityManager);
//
//            Customer registeredCustomer = yourClass.Register(username, password, name, ...); // Call your registration method
//
//            assertNotNull(registeredCustomer); // Ensure that a customer is registered
//        }
//
//
//        @Test
//        public void testHashPassword() {
//
//            String password = "testPassword";
//            String hashedPassword = hashPassword(password);
//
//            assertNotNull(hashedPassword); // Ensure that the hashed password is not null
//            assertNotEquals(password, hashedPassword); // Ensure that the hashed password is different from the original password
//        }
//    }

}
