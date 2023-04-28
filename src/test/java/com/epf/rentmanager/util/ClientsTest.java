package com.epf.rentmanager.util;

import com.epf.rentmanager.model.Client;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class ClientsTest {

    @Test
    void isLegal_should_return_true_when_age_is_over_18() {

        // Given
        Client legalUser = new Client("John", "Doe", "john.doe@ensta.fr", LocalDate.parse("2000-11-12"));
        // Then

        assertTrue(Clients.isLegal(legalUser));
    }

    @Test
    void isLegal_should_return_false_when_age_is_under_18() {

        // Given

        Client illegaUser = new Client("John", "Doe", "john.doe@ensta.fr", LocalDate.parse("2020-11-12"));


        // Then

        assertFalse(Clients.isLegal(illegaUser));
    }
}